package com.kinect.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

public class Test {

	public static void main(String[] args) 
	{
		SessionManager sessionManager = new SessionManager();
		
		List<BodyInfo> bodyInfos = sessionManager.getBodyInfo("wall");
		
		System.out.println(bodyInfos.size());
		
//		for(BodyInfo bodyInfo : bodyInfos){
//			System.out.println(bodyInfo.getTag());
//		}
		
	}

}
