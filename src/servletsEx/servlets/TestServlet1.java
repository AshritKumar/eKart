package servletsEx.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import servletsEx.beans.ItemBean;


public class TestServlet1 extends HttpServlet{
	String x = null;
	public TestServlet1() {
		System.out.println("Constructor called.............");
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("HELLOWORLD");
		System.out.println("In do get . . . . . .");
		HttpSession ses = req.getSession();
		if(ses!=null)
		ses.setAttribute("name", "Ashrit");
		System.out.println(ses);
		System.out.println(resp.encodeURL("getitems.jsp"));
		out.println(ses.getId());
		out.println(resp.encodeURL(req.getContextPath()+"/getitems.jsp"));
		out.println("<a href="+resp.encodeURL(req.getContextPath()+"/getitems.jsp")+"> click</a>");
		
	}
	
	public static void main(String[] args) {
	/*	HashMap<Integer,String> cart = new HashMap<>();
		String name = cart.put(1, "ashrit");
		System.out.println(name);
		System.out.println(cart.put(1, "kumar"));
		System.out.println(cart);*/
		
		JsonObject json = new JsonObject();
		json.addProperty("size", 10);
		json.addProperty("status", true);
		System.out.println(json.toString());
		
	}

}
