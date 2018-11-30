package edu.osu.cse5234.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import com.chase.payment.CreditCardPayment;
import com.chase.payment.PaymentProcessorService;

import edu.osu.cse5234.business.view.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.util.ServiceLocator;

@Resource(name="jms/emailQCF", lookup="jms/emailQCF", type=ConnectionFactory.class) 
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

	ServiceLocator servicelocator;
	
	//not stateless
	//single instance of ejb is not sufficient
	//ejb - instance pooling. during runtime you can specify the number of ekb instances (usually 25)
	@PersistenceContext
	EntityManager entitymanager;
	
	@WebServiceRef(wsdlLocation="http://localhost:9080/ChaseBankApplication/PaymentProcessorService?wsdl")
	private PaymentProcessorService service;
	
	@Inject
	@JMSConnectionFactory("java:comp/env/jms/emailQCF")
	private JMSContext jmsContext;

	@Resource(lookup="jms/emailQ")
	private Queue queue;
	
	public OrderProcessingServiceBean() {
    }
	
	public boolean validateItemAvailability(Order order) {
		return ServiceLocator.getInventoryService().validateQuantity(order.getItems());		
	} 
	
	public Order removeUnorderedItems(Order order) {
		Order ordered = order;
		List<LineItem> items = order.getItems();
		ArrayList<LineItem> orderedItems = new ArrayList<>();
		for(LineItem i:items) {
			if(!i.getQuantity().equals("")) {
				orderedItems.add(i);
			}				
		}
		ordered.setItems(orderedItems);
		return ordered;
	}
    
    public String processOrder(Order order) {
    	boolean orderValid = ServiceLocator.getInventoryService().validateQuantity(order.getItems());
    	CreditCardPayment ccp = new CreditCardPayment();
    	PaymentInfo pi = order.getPayment();
    	
    	ccp.setCardHolderName(pi.getCardHolderName());
    	ccp.setCardNumber(pi.getCreditCardNum());
    	ccp.setCvvCode(pi.getCvvCode());
    	ccp.setExpiryDate(pi.getExpDate());
    	
    	String retValue = service.getPaymentProcessorPort().processPayment(ccp); 
    	
    	if(orderValid && retValue.equals("1")) {  
    		order.getPayment().setConfirm_code("CONFIRMED");
    		entitymanager.persist(order);
    		entitymanager.flush();
    		//ServiceLocator.getInventoryService().updateInventory(order.getItems());
    		
    		notifyUser();
    		
    		return "CONFIRMED";
    	}else {
    		return "invalid";
    	}
    	
    }
    
    
    private void notifyUser() {

    	String message =  "Your order was successfully submitted. " + 
    	     	"You will hear from us when items are shipped. " + 
    	      	new Date();

    	System.out.println("Sending message: " + message);
    	jmsContext.createProducer().send(queue, message);
    	System.out.println("Message Sent!");
    	}


}
