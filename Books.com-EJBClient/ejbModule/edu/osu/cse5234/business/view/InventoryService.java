package edu.osu.cse5234.business.view;

import java.util.Collection;

public interface InventoryService {
	public Inventory getAvailableInventory();
	public boolean validateQuantity(Collection<LineItem> items);
	public boolean updateInventory(Collection<LineItem> items);

}
