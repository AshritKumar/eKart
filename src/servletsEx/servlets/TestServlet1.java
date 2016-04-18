package servletsEx.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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

}
