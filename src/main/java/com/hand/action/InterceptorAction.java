package com.hand.action;

import com.opensymphony.xwork2.ActionSupport;

public class InterceptorAction extends ActionSupport{
	 private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String execute() throws Exception {
	      System.out.println("Inside action....");
	      return "success";
	   }  
}
