package test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import servletsEx.beans.AddressBean;
import servletsEx.beans.UserBean;

public class AddUser {
	
	public static void main(String[] args) {
		SessionFactory sf = GetSessionFactory.getFactory();
		
		UserBean ub = new UserBean();
		
		ub.setFirstName("ashrit");
		ub.setLastName("kumar");
		ub.setEmail("sam@gmail.com");
		ub.setUserName("samudralaa");
		
		AddressBean add1 = new AddressBean();
		add1.setStreet("Borabanda");
		add1.setCity("Hyd");
		add1.setPin(500018);
		add1.setState("Telangana");
		add1.setCountry("Indaia");
		
		AddressBean add2 = new AddressBean();
		add2.setStreet("Annaram");
		add2.setCity("Manakondur");
		add2.setPin(505469);
		add2.setState("Telangana");
		add2.setCountry("Indaia");
		
		Set<AddressBean> addrList = new HashSet<>();
		addrList.add(add1);
		addrList.add(add2);
		
		ub.setAddress(addrList);
		
		Transaction txn=null;
		Session hs = null;
		try{
			hs = sf.openSession();
			hs.beginTransaction();
			hs.save(ub);
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
			if(hs!=null){
				hs.close();
				sf.close();
			}
		}
	}

}
