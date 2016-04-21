package servletsEx.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="authFilter",urlPatterns={"/login.jsp"},dispatcherTypes={DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE})
public class AuthenticationFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//In the init method of the Filter
		System.out.println("In init of filter");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		System.out.println("Filtering the login request.....");
		
		HttpSession session = req.getSession(false);
		if(session!=null){
			System.out.println(session.getId());
			String userName = (String)session.getAttribute("userName");
			System.out.println("Authentication Filter : Already logged in as "+userName+" forwarding to get items page . . ");
			RequestDispatcher rd = request.getRequestDispatcher("getitems.jsp");
			rd.forward(request, response);
		}
		else{
			Cookie cookies[] = req.getCookies();
			String userName ="", password="";
			if(cookies!=null){
				System.out.println("getting Cookies");
				for(Cookie ck : cookies){
					if(ck.getName().equalsIgnoreCase("userName")){
						userName=ck.getValue();
						System.out.println("User Name - "+userName);
					}
					
					if(ck.getName().equalsIgnoreCase("password")){
						password=ck.getValue();
						System.out.println("password - "+password);
					}
				}
			}

			if(!userName.isEmpty() && !password.isEmpty()){
				if(userName.equals("ashrit") && password.equals("ashrit")){
					System.out.println("Cookies set forwarding request to get items page !!"+" user "+userName);
					session = req.getSession();
					session.setAttribute("userName", userName);
					RequestDispatcher rd = request.getRequestDispatcher("getitems.jsp");
					rd.forward(request, response);
					//response.sendRedirect("getitems.jsp");
				}
			}
		}
		
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
		System.out.println("In destroy method");
	}

}
