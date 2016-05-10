package test;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import servletsEx.beans.CatagoryBean;
import servletsEx.beans.ItemBean;

public class AddItems {
	
	public static void main(String[] args) {
		ItemBean ib = new ItemBean();
		CatagoryBean catagory = null;
		
		ib.setItemName("Lenovo K4 Note Vibe");
		ib.setItemDesc("Lenove K4 note comes with new finger print scanning technology, amazing 13 MP rear cam and 5 MP front cam");
		
		ib.setSubCatID(1004);
		ib.setStock(1022);
		ib.setPrice(11999.0);
		ib.setSupID(6003);
		
		SessionFactory sf = GetSessionFactory.getFactory();
		Transaction txn=null;
		Session hs = null;
		try{
			hs = sf.openSession();
			hs.beginTransaction();
			
			//get a catagory and set it to the item bean
			catagory = (CatagoryBean)hs.get(CatagoryBean.class, 10007);
			ib.setCatagory(catagory);
			
			hs.save(ib);
			txn = hs.getTransaction();
			txn.commit();
			
			
		}
		
		catch(HibernateException he){
			if(txn!=null){
				System.out.println("rolling back the transaction");
				txn.rollback();
			}
			
		}
		
		finally{
			System.out.println("Finally");
			if(hs!=null){
				hs.close();
				sf.close();
			}
		}
		
		
	}

}
