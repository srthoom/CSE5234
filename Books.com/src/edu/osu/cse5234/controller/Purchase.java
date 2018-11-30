package edu.osu.cse5234.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.osu.cse5234.business.OrderProcessingServiceBean;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.Item;
import edu.osu.cse5234.business.view.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;
import edu.osu.cse5234.util.ServiceLocator;

//singleton
//stateless
//spring creates
//all data on stack not heap
//heap is persistence data
//stack if method and its data and it is wiped out after that

@Controller
@SessionAttributes("order")
@RequestMapping("/")
public class Purchase {
	
	ServiceLocator servicelocator;
	OrderProcessingServiceBean opsb;
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(){
		return "home";
	}

	@RequestMapping(path = "/aboutus", method = RequestMethod.GET)
	public String aboutUs() {
		return "AboutUs";
	}
	
	@RequestMapping(path = "/contactus", method = RequestMethod.GET)
	public String contactUs() {
		return "ContactUs";
	}
	
	@RequestMapping(path = "/purchase", method = RequestMethod.GET)
	public String viewOrderEntryPage(Model model) throws Exception{
		Order order = new Order();	
		Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
		ArrayList<Item> itemsInInventory = new ArrayList<>();
		ArrayList<LineItem> itemsInOrder = new ArrayList<>();
		itemsInInventory = inventory.getItemList();
		for(Item i: itemsInInventory) {
			LineItem lineitem = new LineItem();
			lineitem.setItem_name(i.getItemName());			
			lineitem.setItem_id(Integer.toString(i.getItemID()));
			lineitem.setQuantity("");
			itemsInOrder.add(lineitem);
		}
		
		order.setItems(itemsInOrder);
		model.addAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path="/purchase/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order,BindingResult result,Model model,  HttpServletRequest request) {
	
		opsb = ServiceLocator.getOrderProcessingService();
		boolean orderValid = opsb.validateItemAvailability(order);
		if(!orderValid) {
			
			request.setAttribute("errorMsg", "invalid quantity/item unavailable");
			return "OrderEntryForm";
		}else {
			
			Order ordered = opsb.removeUnorderedItems(order);			
			request.getSession().setAttribute("order", ordered);		
			return "redirect:/purchase/paymentEntry";	
		}
		
	}
	
	@RequestMapping(path="/purchase/paymentEntry", method=RequestMethod.GET)
	public String viewPaymentEntryPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		PaymentInfo payInfo = new PaymentInfo();		
		model.addAttribute("payInfo", payInfo);		
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path="/purchase/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@Valid @ModelAttribute("payInfo") PaymentInfo payInfo, BindingResult result,Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			return "PaymentEntryForm";
		}else {
			Order order = (Order)request.getSession().getAttribute("order");
			order.setPayment(payInfo);
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("payInfo", payInfo);
			return "redirect:/purchase/shippingEntry";
		}
		
	}
	
	@RequestMapping(path="/purchase/shippingEntry", method = RequestMethod.GET)
	public String viewShippingInfoPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		ShippingInfo shipInfo = new ShippingInfo();
		model.addAttribute("shipInfo", shipInfo);
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path="/purchase/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@Valid @ModelAttribute("shipInfo") ShippingInfo shipInfo,BindingResult result, Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			return "ShippingEntryForm";
		}else {
			Order order = (Order)request.getSession().getAttribute("order");
			order.setShipping(shipInfo);
			order.setCustomerName(shipInfo.getName());
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("shipInfo", shipInfo);
			return "viewOrder";
		}		
	}
	
	@RequestMapping(path="/purchase/confirmOrder", method=RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request) {
		
		opsb = ServiceLocator.getOrderProcessingService();
		Order order = (Order)request.getSession().getAttribute("order");		
		String confirmationCode = opsb.processOrder(order);
		request.getSession().setAttribute("confirmationCode", confirmationCode);
		return "Confirmation";
	}
}
