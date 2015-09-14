package com.hand.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hand.dao.CustomerDaoI;
import com.hand.entity.Customer;
import com.hand.entity.Payment;
import com.hand.entity.Rental;
import com.hand.entity.Store;

@Repository("CusDao")
public class CustomerDaoImpl implements CustomerDaoI{

	/**
	 * 使用@Autowired注解将sessionFactory注入到UserDaoImpl中，省略了get/set方法
	 */
	@Autowired
	private SessionFactory sessionFactory;

	public List getCusList(int pagestart) {
		//分页  从pahestart开始  每次取20行
		List cuslist = null;
		try{
		String hql = "FROM Customer";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(pagestart);
		query.setMaxResults(20);
		cuslist = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cuslist;
		//return sessionFactory.openSession().createCriteria(Customer.class).list();

	}



	public List getCus(Customer cus) {
		List cuslist = null;
		try{
		String sql = "select * from customer where first_name = ? and last_name = ?";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, cus.getFirstName());
		query.setParameter(1, cus.getLastName());
		cuslist = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cuslist;
	}

	public Integer getCustomerCount() {
		Integer result = 0;
		try{
		String sql = "select count(customer_id) from customer";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		result = Integer.valueOf(query.list().get(0).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

//	public Integer deleteCusinPayment(Customer cus) {
//		Session session = sessionFactory.getCurrentSession();
//		Payment loadpayment = (Payment) session.get(Payment.class, cus.getCustomerId());
//		session.delete(loadpayment);
//		return 1;
//	}
//	public Integer deleteCusinRental(Customer cus){
//		Session session = sessionFactory.getCurrentSession();
//		Rental loadrental = (Rental) session.get(Rental.class, cus.getCustomerId());
//		session.delete(loadrental);
//		return 1;
//
//	}
	public Integer deleteCus(Customer cus) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null; 
		try{
			tx = session.beginTransaction();
			session.delete(cus);
			tx.commit();
			session.flush();
		}
		catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
			return 0;
			}
		return 1;
	}
	//添加用户信息
	public Short addCus(Customer cus) {
		Short result = 0;
		try{
		result = (Short) sessionFactory.getCurrentSession().save(cus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//修改用户信息
	public void updateCus(Customer cus) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null; 
		try{
			tx = session.beginTransaction();
			session.update(cus);
			tx.commit();
			session.flush();
		}
		catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}



	public Customer getCuswithId(Short cusid) {
		Customer cus = null;
		try{
		cus = (Customer)sessionFactory.getCurrentSession().get(Customer.class, cusid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cus;
	}



	public Store getStore(Byte storeId) {
		Store st = null;
		try{
		st = (Store) sessionFactory.getCurrentSession().get(Store.class, storeId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return st;
	}

}
