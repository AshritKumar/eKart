package servletsEx.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servletsEx.beans.ItemBean;
import servletsEx.dao.DBSelects;

import com.google.gson.Gson;
@WebServlet(name="displayItems",urlPatterns= {"/displayItem"})
public class DisplayItemServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		 Gson g = new Gson();
		HttpSession session  = req.getSession(false);
		if(session!=null){
			Enumeration<String> en = req.getParameterNames();
			String k="";
			  while(en.hasMoreElements()){
				   k = en.nextElement();
			  System.out.println(k+" "+req.getParameter(k)); 
			  }
			 
			String param = k;
			DBSelects ds = new DBSelects();
			//System.out.println(param);
			if (param.equalsIgnoreCase("catID")) {
				Integer catID = Integer.parseInt(req.getParameter(param));
				Map<Object, String> subList = ds.getSubCatagoryList(catID);
				String subMap = g.toJson(subList);
				System.out.println(subMap);
				out.print(subMap);
			}
			
			//Items//
			
			if (param.equalsIgnoreCase("subCatID")) {
			
				Integer subCatID = Integer.parseInt(req.getParameter(param));
				List<ItemBean> itemList = ds.getItems(subCatID);
				String subMap = g.toJson(itemList);//.replace('[', '\0').replace(']', '\0');
				
				System.out.println(subMap);
				
				out.print(subMap);
				System.out.println("*********************************");
			}
		}
		else{
			System.out.println("Session Expired - Display items page!!");
		}
		
		
		
	}
	
}


