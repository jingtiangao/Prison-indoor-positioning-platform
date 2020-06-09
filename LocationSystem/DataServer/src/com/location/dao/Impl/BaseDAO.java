package com.location.dao.Impl;

import java.io.Serializable;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.location.dao.hibernate.HibernateSessionFactory;

public class BaseDAO<T> {

	/**
	 * 鎻掑叆鏁版嵁
	 * 
	 * @param object
	 */
	public void create(T object) {

		Session session = HibernateSessionFactory.getSessionFactory().openSession();

		try {
			session.beginTransaction();

			session.save(object);

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * 鏇存柊鏁版嵁搴�
	 * 
	 * @param object
	 */
	public void update(T object) {

		Session session = HibernateSessionFactory.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * 浠庢暟鎹簱涓垹闄�
	 * 
	 * @param object
	 */
	public void delete(T object) {

		Session session = HibernateSessionFactory.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * 鏌ユ壘鍗曚釜Entity Bean
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T find(Class<? extends T> clazz, Serializable id) {

		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			return (T) session.get(clazz, id);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	/**
	 * 鏌ユ壘澶氫釜Entity Bean
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String hql) {

		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			return session.createQuery(hql).list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	public void dropAll(){
		Session session=HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		try {
			Query query=session.createQuery("delete from Device_info");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}finally {
			session.close();
		}
	}
	
	
	
}
