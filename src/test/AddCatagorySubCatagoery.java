package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import servletsEx.beans.CatagoryBean;
import servletsEx.beans.SubCatagoryBean;

public class AddCatagorySubCatagoery {
	
	public void addSubCatagory(){
		SessionFactory sf=null;
		Session hs=null;
		Transaction txn = null;
		
		CatagoryBean cb;
		
		SubCatagoryBean sb = new SubCatagoryBean();
		sb.setSubCatName("clothing");
		
		
		try{
			sf = GetSessionFactory.getFactory();
			hs = sf.openSession();
			cb = (CatagoryBean)hs.get(CatagoryBean.class, 10009);
			sb.setCatagory(cb);
			hs.beginTransaction();
			hs.save(sb);
			txn = hs.getTransaction();
			txn.commit();
			
		}
		
		catch(HibernateException he){
			if(txn!=null)
			txn.rollback();
			he.printStackTrace();
		}
		
		finally{
			if(hs!=null)
			hs.close();
			if(sf!=null)
			sf.close();
		}
	}
	
	public static void main(String[] args) {
		
		AddCatagorySubCatagoery acs = new AddCatagorySubCatagoery();
		
		acs.addSubCatagory();
	}

}
