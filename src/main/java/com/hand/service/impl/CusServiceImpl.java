package com.hand.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hand.dao.impl.CustomerDaoImpl;
import com.hand.entity.Customer;
import com.hand.entity.Store;
import com.hand.service.CusServiceI;

//使用Spring提供的@Service注解将CusServiceImpl标注为一个Service
@Service("CusService")
public class CusServiceImpl implements CusServiceI{

	/**
	 * 注入Cusdao
	 */
	@Autowired
	private CustomerDaoImpl CusDao;
	
	public List getCusList(Integer pagestart) {
//		List<Customer> cuslist = new ArrayList<Customer>();
//		List list = CusDao.getCusList();
//		Customer cus = null;
//		for(int i=0;i<list.size();i++){
//			
//		}
//		List<Customer> cuslist = new ArrayList<Customer>();
//		List list = CusDao.getCusList();
//		for(int i = 0;i<list.size();i++){
//			Customer cus = (Customer) list.get(i);
//			Address addrset = cus.getAddress();
//		}
		
		//System.out.println(list);
		//System.out.println(CusDao.getCusList().size());
//		for(int i =0;i<list.size();i++){
//			Customer cus = (Customer)list.get(i).;
//			System.out.println(cus.getFirstName());
//			cuslist.add(cus);
//		}
		
//		for(int i =0;i<cuslist.size();i++){
//			System.out.println("in  cusService:"+cuslist.get(i).getFirstName());
//		}
		return CusDao.getCusList(pagestart);
		
		
		
		//return CusDao.getCusList();
	}

	public boolean checkLogin(Customer cus) {
		boolean bool = false;
		System.out.println(CusDao.getCus(cus));
		if(!CusDao.getCus(cus).isEmpty()){bool=true;};
		
		return bool;
	}

	public Integer getCusCount() {
		Integer Count = CusDao.getCustomerCount();
		return Count;
	}

	public boolean delCus(Short cusid) {
		//获得持久化对象
		Customer cus = CusDao.getCuswithId(cusid);
//		Integer result = CusDao.deleteCusinPayment(cus);
//		System.out.println(result);
//		result = CusDao.deleteCusinRental(cus);
//		System.out.println(result);
//		Integer result = 0;
//		if(cus!=null){
		Integer result = CusDao.deleteCus(cus);
//		}
//		System.out.println(result);
		if(result!=0)
		return true;
		else return false;
	}
	/*
	 * 以CusId获取指定Id的持久化对象
	 */
	public Customer getCus(Short cusid){
		return CusDao.getCuswithId(cusid);
	}

	public boolean addCus(Customer cus) {
		Short result = CusDao.addCus(cus);
		System.out.println("in CusService add"+result);
		if(result!=0)
		return true;
		else return false;
	}

	public boolean editCus(Customer cus) {
//		Customer editCus = CusDao.getCuswithId(cus.getCustomerId());
//		System.out.println("before service update");
//		editCus.setAddress(cus.getAddress());
//		editCus.setFirstName(cus.getFirstName());
//		editCus.setLastName(cus.getLastName());
//		editCus.setEmail(cus.getEmail());
//		editCus.setLastUpdate(new Date());
		try{
		CusDao.updateCus(cus);
		System.out.println("after service update");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Store getstoreforadd(Byte storeId){
		return CusDao.getStore(storeId);
	}

}
