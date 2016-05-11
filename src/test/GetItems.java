package test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import servletsEx.beans.ItemBean;

public class GetItems {
	
	public static void main(String[] args) {
		
		SessionFactory sf = GetSessionFactory.getFactory();
		Session hs = sf.openSession();
		
		ItemBean ib = (ItemBean)hs.get(ItemBean.class, 192);
		
		//System.out.println(ib.getItemName());
		//System.out.println(ib.getItemDesc());
		try{
			Criteria crit = hs.createCriteria(ItemBean.class);
			crit.add(Restrictions.like("itemName", "%lenovo%"));
			List<ItemBean>items = crit.list();
			//hs.close();
			for(ItemBean item: items){
				System.out.print(item.getItemName()+" - "+item.getPrice()+" - "+item.getCatagory().getCatagoryName()+" - "+item.getSubCatagory().getSubCatName());
				System.out.println();
			}
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		
		finally{
			hs.close();
			sf.close();
		}
	
		
	}

}
