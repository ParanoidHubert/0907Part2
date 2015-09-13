package com.hand.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hand.entity.Address;
import com.hand.service.impl.AddressServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

//@Action("Address")
public class AddressAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6973243164233210220L;

	@Autowired
	private AddressServiceImpl addressService;
	
	public void getaddress(){
		//System.out.println("in addressAction getaddress");
		List<Address> addresslist = addressService.getAddressList();
		Gson gson = new Gson();
		String json = gson.toJson(addresslist);
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(json);
			out.flush();
			out.close();
			//System.out.println("json输出完毕！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
