package com.hand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hand.dao.AddressDaoI;
import com.hand.dao.impl.AddressDaoImpl;
import com.hand.entity.Address;
import com.hand.service.AddressServiceI;
@Service("addressService")
public class AddressServiceImpl implements AddressServiceI{
	
	@Autowired
	private AddressDaoImpl addressDao;
	
	public List getAddressList() {
		System.out.println("in addressService getAddressList");
		return addressDao.getAddressList();
	}

	public Address getAddress(Short addressId) {
		System.out.println("in addressService getAddress");
		return addressDao.getAddress(addressId);
	}
	

	
}
