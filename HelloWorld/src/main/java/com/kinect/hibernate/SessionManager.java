package com.kinect.hibernate;


import java.io.File;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionManager {
	
	
	private SessionFactory factory;
	
	public SessionManager(){
		factory = new Configuration().configure(new File("src/main/java/com/kinect/hibernate/hibernate.cfg.xml")).
				addAnnotatedClass(BodyInfo.class).
				addAnnotatedClass(BodyInfoTest.class).
				buildSessionFactory();
	}
	
	
	
	
	
	public Integer inserBodyInfo(BodyInfo bodyInfo)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer id = null;
		tx = session.beginTransaction();
		id=(Integer) session.save(bodyInfo);
		tx.commit();
		session.close();
		factory.close();
		return id;
	}
	
	
	//取得訓練
	public List<BodyInfo> getBodyInfo(String whitchSport)
	{
		Session session = factory.openSession();
		Query query=null;
		if(whitchSport!=null)
		{	query= session.createQuery("select b from BodyInfo b where b.tag=:something");
			query.setParameter("something", whitchSport);
		}
		else
			query= session.createQuery("select b from BodyInfo b ");
		
		List<BodyInfo> bodyinfos = query.getResultList();
		session.close();
		return bodyinfos;
	}
	
	//取得測試資料
	public List<BodyInfoTest> getBodyInfoTest(String sport)
	{
		Session session = factory.openSession();
		Query query=null;
		if(sport!=null)
		{	query= session.createQuery("select b from BodyInfoTest b where b.tag=:something");
			query.setParameter("something", sport);
		}
		else
			query= session.createQuery("select b from BodyInfoTest b ");
		
		List<BodyInfoTest> bodyInfoTests = query.getResultList();
		session.close();
		return bodyInfoTests;
		
	}
	
	
	
	
	
	
	
	
	
	
}
