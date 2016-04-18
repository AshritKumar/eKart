package servletsEx.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ValidateUser")
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		System.out.println(remember);
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		
		if(userName!=null && password!=null){
			if(userName.equals("ashrit") && password.equals("ashrit")){
				System.out.println("Hey Hey sucess !!!");
				System.out.println("Creating a session for user "+userName);
				HttpSession session = request.getSession();
				session.setAttribute("userName", userName);
				System.out.println(session.getId());
				System.out.println("is new ? "+session.isNew());
				session.setMaxInactiveInterval(600);
				if(remember!=null &&remember.equalsIgnoreCase("on")){
					System.out.println("Setting cookies !!");
					Cookie ckUserName = new Cookie("userName", userName);
					Cookie ckPassword = new Cookie("password", password);
					ckUserName.setMaxAge(60*60*12);
					ckPassword.setMaxAge(60*60*12);
					response.addCookie(ckUserName);
					response.addCookie(ckPassword);
				}
				
				String url = response.encodeRedirectURL("getitems.jsp");
				System.out.println(url);
				response.sendRedirect(url);
			}
			
			else{
				System.out.println("Login unsuccesful....");
				out.println("<center>Invalid user name or password</center>");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
				
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
			resp.sendRedirect("login.jsp");
		
		
	}

}
