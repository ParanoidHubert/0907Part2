package com.hand.service;

import java.util.List;

import com.hand.entity.Address;

public interface AddressServiceI {
	//获取address列表
	List getAddressList();
	
	//根据传入的addressId获取特定address对象
	Address getAddress(Short addressId);
}
