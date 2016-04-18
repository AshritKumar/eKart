package servletsEx.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	    resp.setHeader("Pragma", "no-cache"); //HTTP 1.0
	    resp.setDateHeader("Expires", 0); //prevents caching at the proxy server
	    resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");// Set standard HTTP/1.1 no-cache headers.
	    resp.addHeader("Cache-Control", "post-check=0, pre-check=0");// Set IE extended HTTP/1.1 no-cache headers (use addHeader)
		HttpSession session = req.getSession(false);
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		if(session!=null){
			System.out.println("Invalidating session "+session.getId()+" "+session.getAttribute("userName"));
			session.removeAttribute("userName");
			session.invalidate();
			Cookie[] cookies = req.getCookies();
			for(Cookie ck : cookies){
				if(ck.getName().equalsIgnoreCase("userName")){
					System.out.println("Removing user name cookie");
					ck.setMaxAge(0);
					resp.addCookie(ck);
				}
				
				if(ck.getName().equalsIgnoreCase("password")){
					System.out.println("Removing password cookie");
					ck.setMaxAge(0);
					resp.addCookie(ck);
				}
			}
			out.println("Successfully logged out<br>");
			out.println("<a href=\"login.jsp\">click to login</a>");
		}
		else{
			System.out.println("not logged in!!!");
			out.println("Please login first");
			out.println("<a href=\"login.jsp\">click to login</a>");
		}
		
	}

}
