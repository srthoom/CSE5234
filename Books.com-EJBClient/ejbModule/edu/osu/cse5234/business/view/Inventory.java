package edu.osu.cse5234.business.view;

import java.io.Serializable;
import java.util.ArrayList;


public class Inventory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Item> itemList;
	
	public Inventory() {
		itemList = new ArrayList<>();
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	

}
