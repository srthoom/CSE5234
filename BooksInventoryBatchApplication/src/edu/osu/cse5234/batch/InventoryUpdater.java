package edu.osu.cse5234.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InventoryUpdater {

	public static void main(String[] args) {
		System.out.println("Starting Inventory Update ...");
		try {
			Connection conn = createConnection();
			Collection<Integer> newOrderIds = getNewOrders(conn);
			//System.out.println(newOrderIds);
			Map<Integer, Integer> orderedItems = getOrderedLineItems(newOrderIds, conn);
			//System.out.println(Arrays.toString(orderedItems.entrySet().toArray()));
			updateInventory(orderedItems, conn);
			updateOrderStatus(newOrderIds, conn);
			conn.close();
			System.out.println("Connection closed...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:C:\\workspace\\cse5234\\h2db\\BooksDB", "sa", "");
		return conn;
	}
	
	private static Collection<Integer> getNewOrders(Connection conn) throws SQLException {
		Collection<Integer> orderIds = new ArrayList<Integer>();
		ResultSet rset = conn.createStatement().executeQuery("select ID from CUSTOMER_ORDER where STATUS =  'New'");
		while (rset.next()) {
			orderIds.add(new Integer(rset.getInt("ID")));
		}
		return orderIds;
	}
	
	private static Map<Integer, Integer> getOrderedLineItems(Collection<Integer> newOrderIds,Connection conn)  throws SQLException {
		
		HashMap<Integer, Integer> idQtyMap = new HashMap<>();
		Iterator<Integer> iter = newOrderIds.iterator();
		while(iter.hasNext()) {
			int id = iter.next();
			String queryString = "select ITEM_ID, QUANTITY from CUSTOMER_ORDER_LINE_ITEM where ID ="+ id ;
			//System.out.println("CUSTOMER ID----->"+id);
			ResultSet rset = conn.createStatement().executeQuery(queryString);
			while (rset.next()) {
				int itemid = new Integer(rset.getInt("ITEM_ID"));
				int itemQty = Integer.parseInt(rset.getString("QUANTITY"));
				//System.out.println(itemid + " : " + itemQty);
				if(idQtyMap.containsKey(itemid)) {
					int qty = idQtyMap.get(itemid);
					qty = qty + itemQty;
					idQtyMap.put(itemid, qty);
				}else {
					idQtyMap.put(itemid, itemQty);
				}
			}			
		}		
		return idQtyMap;
	}
	
	private static void updateInventory(Map<Integer, Integer> orderedItems, Connection conn) throws SQLException {
		for(Integer id:orderedItems.keySet()) {
			int orderedQty = orderedItems.get(id);
			int availableQty=0;
			ResultSet rset = conn.createStatement().executeQuery("select AVAILABLE_QUANTITY from ITEM where ID =" + id);
			rset.next(); 
			availableQty = new Integer(rset.getInt("AVAILABLE_QUANTITY"));				
			availableQty = availableQty-orderedQty;
			String updateString = "UPDATE ITEM " + "SET AVAILABLE_QUANTITY = "+availableQty;
			conn.createStatement().executeUpdate(updateString);
		}
		
	}
	
	private static void updateOrderStatus(Collection<Integer> newOrderIds,Connection conn) throws SQLException {
		Iterator<Integer> iter = newOrderIds.iterator();
		while(iter.hasNext()) {
			int id = iter.next();
			String updateString = "UPDATE CUSTOMER_ORDER " + "SET STATUS = 'Processed' WHERE id="+id; 
	        conn.createStatement().executeUpdate(updateString); 
		}
		
	}

}
