package edu.osu.cse5234.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;
import edu.osu.cse5234.business.view.LineItem;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

	String MY_QUERY = "Select i from Item i";

	@PersistenceContext
	EntityManager entityManager;
	
	
    public InventoryServiceBean() {
    }

	@Override
	public Inventory getAvailableInventory() {
		
		Inventory availableBooks = new Inventory();
		List<Item> itemList = entityManager.createQuery(MY_QUERY, Item.class).getResultList();		
		availableBooks.setItemList(new ArrayList<Item>(itemList));
		return availableBooks;
	}

	@Override
	public boolean validateQuantity(Collection<LineItem> itemCollection) {				
		int emptyCount=0;
		ArrayList<LineItem> items = (ArrayList<LineItem>) itemCollection;
		HashMap<String,String> itemQtyMap = new HashMap<>();
		ArrayList<Item> itemsInInventory = getAvailableInventory().getItemList();
		for(Item i : itemsInInventory) {
			itemQtyMap.put(i.getItemName(),i.getItemQty());
		}			
		for (int i = 0; i < items.size(); i++) {
			LineItem item = items.get(i);
			if(!item.getQuantity().isEmpty()) {
				try {
					int qty=Integer.parseInt(item.getQuantity());
					if(qty<0)
						return false;
					else if(qty==0)
						emptyCount++;
					else {
						int qtyInStock = Integer.parseInt(itemQtyMap.get(item.getItem_name()));
						if(qty>qtyInStock)
							return false;
					}
				}catch(Exception e) {
					return false;
				}
			}else {
				emptyCount++;
			}						
		}
		if(emptyCount<items.size()) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public boolean updateInventory(Collection<LineItem> items) {
		for(LineItem i:items) {	
			String findByNameQry = "SELECT i FROM Item i where i.itemName='" + i.getItem_name() + "'";
			Item itemInInventory = (Item)entityManager.createQuery(findByNameQry).getSingleResult();
			int qtyOrdered = Integer.parseInt(i.getQuantity());
			int qtyInInventory = Integer.parseInt(itemInInventory.getItemQty());
			String remainingQty = Integer.toString(qtyInInventory-qtyOrdered);
			String updateQuantityQry = "update Item set itemQty = '"+remainingQty+"' where itemName='" + i.getItem_name()+"'" ;
			entityManager.createQuery(updateQuantityQry).executeUpdate();
			
		}	
		
		return true;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
