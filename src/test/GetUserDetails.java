package test;

import java.util.Collection;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import servletsEx.beans.AddressBean;
import servletsEx.beans.UserBean;

public class GetUserDetails {
	
	public static void main(String[] args) {
		SessionFactory sf = GetSessionFactory.getFactory();
		sf.openSession();
		Transaction txn = null;
		Session hs = null;
		UserBean ub = null;
		try{
			hs = sf.openSession();
			
			//We will not get the orginal obj here we will get proxy obj even though fetch type is lazy or eager
			ub = (UserBean)hs.get(UserBean.class, 7);
			
			System.out.println(ub.getFirstName());
			System.out.println(ub.getLastName());
			System.out.println(ub.getEmail());
			System.out.println(ub.getUserName());
			
			//hs.close();
			//if we retrive after session is closeed in lazy type we will get exception.
			Collection<AddressBean> addList = ub.getAddress();
			
			for(AddressBean ab : addList){
				System.out.println(ab.getStreet());
			}
		}
		catch(HibernateException he){
			System.out.println("In exception");
			he.printStackTrace();
		}
		finally{
			hs.close();
			sf.close();
		}
	}

}
