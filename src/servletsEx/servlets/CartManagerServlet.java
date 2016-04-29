package servletsEx.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import servletsEx.beans.ItemBean;
import servletsEx.dao.DBSelects;
import servletsEx.utils.Cart;

@WebServlet(description = "Manages to cart actions.", urlPatterns = { "/cartManager" })
public class CartManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Cart Manager ......");
		PrintWriter out = response.getWriter();
		
		Enumeration<String> en = request.getParameterNames();
		String k="";
		  while(en.hasMoreElements()){
			   k = en.nextElement();
		  System.out.println(k+" "+request.getParameter(k)); 
		  }
		 
		String param = k;
		
		
		HttpSession session = request.getSession(false);
		DBSelects dbs = new DBSelects();
		if(param.equalsIgnoreCase("addItemID")){
			Integer itemID = Integer.parseInt(request.getParameter(param));
			ItemBean item=  dbs.getItem(itemID);
			Cart cart = (Cart)session.getAttribute("cart");
			if(cart!=null){
				System.out.println("\n\n cart already exists . . adding the item");
				boolean status = cart.addItemTOCart(item);
				JsonObject json = new JsonObject();
				json.addProperty("size", cart.getCartSize());
				json.addProperty("status", status);
				out.println(json.toString());
			}
			else{
				System.out.println("Cart empty creating a new cart . . . !!!!");
				Cart c = new Cart();
				boolean status = c.addItemTOCart(item);
				JsonObject json = new JsonObject();
				json.addProperty("size", c.getCartSize());
				json.addProperty("status", status);
				session.setAttribute("cart", c);
				System.out.println(json.toString());
				out.println(json.toString());
			}
		}
		
	}

}
