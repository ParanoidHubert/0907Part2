package com.hand.dao;

import java.util.List;

import com.hand.entity.Address;

public interface AddressDaoI {
	//获取地址列表
	List getAddressList();
	
	//根据传入的addressId获取特定地址对象
	Address getAddress(Short addressId);
}
