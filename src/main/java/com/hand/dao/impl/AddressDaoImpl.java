package com.hand.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hand.dao.AddressDaoI;
import com.hand.entity.Address;
@Repository("addressDao")
public class AddressDaoImpl implements AddressDaoI{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List getAddressList() {
		Session session = sessionFactory.openSession();
		List addresslist = null;
		try{
			String hql = "SELECT A.address,A.addressId FROM Address A ";
			addresslist = session.createQuery(hql).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return addresslist;
	}

	public Address getAddress(Short addressId) {
		
		Session session = sessionFactory.openSession();
		Address address = null;
			try{
			address = (Address) session.get(Address.class, addressId); ;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return address;
	}

}
