package com.pp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter{  
	  
public void init(FilterConfig arg0) throws ServletException {}  
      
public void doFilter(ServletRequest req, ServletResponse resp,  
        FilterChain chain) throws IOException, ServletException {  
          
    PrintWriter out=resp.getWriter();  
          
    String password=req.getParameter("password");  
    if(password.equals("admin")){  
    chain.doFilter(req, resp);//sends request to next resource  
    }  
    else{  
    	
    	out.println("<html>");
		out.println("<body>");
		out.println("<h1>  username or password error! </h1>");
		out.println("</body>");
		out.println("</html>");
		RequestDispatcher rd=req.getRequestDispatcher("index.html");  
		rd.include(req, resp);  
    }  
          
}  
    public void destroy() {}  
  
}  
