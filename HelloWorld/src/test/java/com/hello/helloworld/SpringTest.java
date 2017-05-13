package com.hello.helloworld;

import java.net.URL; 
import java.net.URLClassLoader;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.kinect.main.Simple;

import edu.ufl.digitalworlds.j4k.J4KSDK;

//@ContextConfiguration(value={})
public class SpringTest {
	
//	@Autowired
//	private SessionFactory ds;
	
	
	@Test
	public void test(){
		
//		KinectMachineFactory kinectfactory = new KinectMachineFactory();
//		kinectfactory.start();
//		try {Thread.sleep(5000);} catch (InterruptedException e) {}
//		kinectfactory.stop();
//		
//		System.out.println("stoped");
//		System.out.println("waiting for 2 second");
//		try {Thread.sleep(2000);} catch (InterruptedException e) {}
//		System.out.println("start again");
//		
//		System.gc();
//		
//		KinectMachineFactory kinectfactory2 = new KinectMachineFactory();
//		kinectfactory2.start();	
//		try {Thread.sleep(5000);} catch (InterruptedException e) {}
//		kinectfactory2.stop();
		int a=103;
		int b=146;
		
		float da = (float)a;
		float db = (float)b;
		
		float cc = (float)1/500;
		
		System.out.println(((db-da)/db)*0.002);
		System.out.println(((db-da)/db)*0.003);
		
		if(((db-da)/db)*0.002 < ((db-da)/db)*0.003)
		{
			System.out.println("tt");
		}
		
	}

}
