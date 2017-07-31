package com.bonus.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils; 
import com.bonus.bean.User;

public class UserFilter  implements Filter{
	private static String  noUrl;
	private String excludedPages;
	private String[] excludedPageArray;
	    
		
		public void destroy() {
	       
		}

		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String path = req.getServletPath();
			HttpSession session = req.getSession();
			String register = "reg";
		//       String requestStr =   req.getRequestURI();
		     
		    int number =  path.indexOf('.')==-1?0:path.indexOf('.');
			int end = path.lastIndexOf("/")==-1?0:path.lastIndexOf('/');     
		      
			String password = null;
			String userName = null;
			String suffix = path.substring(number);
			   
			if(number!=0&&end!=0){
				register = path.substring(end+1,number);
			}
			boolean isExcludedPage = false;     
			for (String page : excludedPageArray) {
				if(path.contains(page)){     
					isExcludedPage = true;     
					break;     
				}     
			} 
			
			if(isExcludedPage){
				chain.doFilter(req, res);
			}
			else{
		       if(".html".equals(suffix)){   
		    	   User us = (User)session.getAttribute("user");
			
		    	   if(us!=null){
						chain.doFilter(req, res);
					
		    	   }else{
		 
		    	      Cookie[] cks =req.getCookies();
	 				 if(cks!=null&&cks.length>0){ 
		    		     for(int i= 0;cks[i]!=null&&i<cks.length-1;i++){
	 				         if(!"JSESSIONID".equals(cks[i].getName())){
			    		    	 password = cks[i].getValue();
		 				         userName = cks[i].getName();
	 				         }
	 				     }
		    		     
	 				 }  
		    		   
		    		   if("register".equals(register)||(password!=null&&userName!=null)){
		    				chain.doFilter(req, res);
		    		   }else{
						  req.getRequestDispatcher("login.html").forward(req, res);
		    		   }
					}   
		    	   
		       }else{
		    	   chain.doFilter(req, res);
		       }
			}
		}
		       
		       

		public void init(FilterConfig config) throws ServletException {
			excludedPages = config.getInitParameter("noFilterUrl");     
			if (StringUtils.isNotEmpty(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
			}     
			return;  
		}
}
