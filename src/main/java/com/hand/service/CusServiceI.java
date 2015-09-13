package com.hand.service;

import java.util.List;

import com.hand.entity.Customer;
import com.hand.entity.Store;

public interface CusServiceI {
	/**
	 * 获取客户信息
	 * @param pagestart--从第pagestart开始，取20个
	 * @return
	 */
	List<Customer> getCusList(Integer pagestart);
	
	/**
	 * 登录验证
	 */
	boolean checkLogin(Customer cus);
	
	Integer getCusCount();
	
	boolean delCus(Short cusid);
	
	boolean addCus(Customer cus);
	
	boolean editCus(Customer cus);
	
	Store getstoreforadd(Byte storeId);
}
