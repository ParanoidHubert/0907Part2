package com.hand.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/* let us do some pre-processing */
		//	      String output = "Pre-Processing"; 
		//	      System.out.println(output);
		
		/* let us call action or next interceptor */
		invocation.invoke();
		ActionContext actionContext = invocation.getInvocationContext();  
		Map<String, Object> session = actionContext.getSession();
		String islogin = (String) session.get("flag");
		if(islogin != null && "login".equals(islogin)){
			System.out.println("login success");
			return invocation.invoke();  
		}
		
		/* let us do some post-processing */
		//	      output = "Post-Processing"; 
		//	      System.out.println(output);
		System.out.println("no login");
		//actionContext.put("tip", "你还没有登录");  
		return "notlogin";
	}

}
