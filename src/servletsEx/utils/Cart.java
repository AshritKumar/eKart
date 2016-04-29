package servletsEx.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import servletsEx.beans.ItemBean;

public class Cart {
	
	private HashSet<ItemBean> cart = new HashSet<>();
	private ItemBean item;
	
	public Boolean addItemTOCart(ItemBean item){
		System.out.println("adding "+item.getItemName()+" - "+item.getItemID()+" to cart");
		boolean stauts = cart.add(item);
		return stauts;
	}
	
	public Boolean removeItemFromCart(ItemBean item){
		System.out.println("removing "+item.getItemName()+" - "+item.getItemID()+" to cart");
		boolean status = cart.remove(item);
		
		return status;
	}
	
	public int getCartSize(){
		return cart.size();
	}
	
	public boolean isEmpty(){
		return cart.isEmpty();
	}
	
	public Set<ItemBean> getCartItems(){
		return cart;
	}

}
