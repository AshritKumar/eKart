package servletsEx.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import servletsEx.beans.ItemBean;
import servletsEx.utils.DBUtils;

public class DBSelects {

	public Map<Integer, String> getCatagoryList(Integer catID) {
		Connection con = DBUtils.getDBConnection("sqlserver");
		////Debugging Connetions///////////
		
		/*System.out.println("In DB selects get cat list . . . .");
		System.out.println(con.hashCode());
		System.out.println(con);*/
		
		ResultSet rs = null;
		Statement stmt = null;
		Map<Integer, String> catList = new HashMap<>();
		String query = "";
		if (catID == null)
			query = "select * from catagories";
		else
			query = "select * from catagories where catagoryID = " + catID;
		System.out.println(query);
		if (con != null) {
			try {
				stmt = con.createStatement();

				rs = stmt.executeQuery("select * from catagories");
				while (rs.next()) {
					catList.put(rs.getInt(1), rs.getString(2));
				}
				/*Gson g = new Gson();
				String catMap = g.toJson(catList);
				// System.out.println(catMap);
*/
				rs.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					/*System.out.println("Closing connection in cat list");
					System.out.println(con.hashCode());
					System.out.println(con);*/
					stmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		return catList;
	}
	
	public Map getSubCatagoryList(Integer catID) {
		Connection con = DBUtils.getDBConnection("sqlserver");
		/*System.out.println("In DB selects get sub CatList list . . . .");
		System.out.println(con.hashCode());
		System.out.println(con);*/
		ResultSet rs = null;
		Statement stmt = null;
		Map<Integer, String> subCatList = new HashMap<>();
		String query = "";
		if (catID == null)
			query = "select * from subCatagories";
		else
			query = "select * from subCatagories where catagoryID = " + catID;
		System.out.println(query);
		if (con != null) {
			try {
				stmt = con.createStatement();

				rs = stmt.executeQuery(query);
				while (rs.next()) {
					subCatList.put(rs.getInt(1), rs.getString(3));
				}
				/*Gson g = new Gson();
				String catMap = g.toJson(catList);
				// System.out.println(catMap);
*/
				rs.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					System.out.println("Closing connection");
					stmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return subCatList;
	}
	
	public List<ItemBean> getItems(Integer subCatID) {
		Connection con = DBUtils.getDBConnection("sqlserver");
		/*System.out.println("In DB selects get sub CatList list . . . .");
		System.out.println(con.hashCode());
		System.out.println(con);*/
		ResultSet rs = null;
		Statement stmt = null;
		List<ItemBean> itemList = new ArrayList<>();
		String query = "";
		if (subCatID == null)
			query = "select * from items";
		else
			query = "select * from items where subCatID = " + subCatID;
		System.out.println(query);
		if (con != null) {
			try {
				stmt = con.createStatement();

				rs = stmt.executeQuery(query);
				while (rs.next()) {
					ItemBean ib = new ItemBean();
					ib.setItemID(rs.getInt(1));
					ib.setItemName(rs.getString(2));
					ib.setItemDesc(rs.getString(3));
					ib.setCatID(rs.getInt(4));
					ib.setSubCatID(rs.getInt(5));
					ib.setStock(rs.getInt(6));
					ib.setSupID(rs.getInt(7));
					ib.setPrice(rs.getDouble(8));
					
					itemList.add(ib);
					
				}
				/*Gson g = new Gson();
				String catMap = g.toJson(catList);
				// System.out.println(catMap);
*/
				rs.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					System.out.println("Closing connection");
					stmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return itemList;
	}
	
	
	public ItemBean getItem(Integer itemID) {
		Connection con = DBUtils.getDBConnection("sqlserver");
		/*System.out.println("In DB selects get sub CatList list . . . .");
		System.out.println(con.hashCode());
		System.out.println(con);*/
		ResultSet rs = null;
		Statement stmt = null;
		List<ItemBean> itemList = new ArrayList<>();
		String query = "select * from items where itemID = " + itemID;
		
		System.out.println(query);
		ItemBean ib = new ItemBean();
		if (con != null) {
			try {
				stmt = con.createStatement();

				rs = stmt.executeQuery(query);
				while (rs.next()) {
					
					ib.setItemID(rs.getInt(1));
					ib.setItemName(rs.getString(2));
					ib.setItemDesc(rs.getString(3));
					ib.setCatID(rs.getInt(4));
					ib.setSubCatID(rs.getInt(5));
					ib.setStock(rs.getInt(6));
					ib.setSupID(rs.getInt(7));
					ib.setPrice(rs.getDouble(8));	
				}
				/*Gson g = new Gson();
				String catMap = g.toJson(catList);
				// System.out.println(catMap);
*/
				rs.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					System.out.println("Closing connection");
					stmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ib;
	}

}
