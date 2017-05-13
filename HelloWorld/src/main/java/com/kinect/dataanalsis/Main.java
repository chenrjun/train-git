package com.kinect.dataanalsis;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.kinect.hibernate.BodyInfoTest;
import com.kinect.hibernate.SessionManager;

public class Main {
	
	public static void main(String[] args)
	{
		String sport = "pendulum";
		
		SessionManager sessionManager = new SessionManager();
		EuclideanDistance distance = new EuclideanDistance(sessionManager.getBodyInfo(null), new PendulumDistance());
		List<BodyInfoTest> bodyInfoTests = sessionManager.getBodyInfoTest(null);
		long stime = System.currentTimeMillis();
		
		for(int i=0;i<bodyInfoTests.size();i++)
		{
			System.out.println("-----------------第"+i+"次----------------------");
			BodyInfoTest bodyInfoTest = bodyInfoTests.get(i);
			double dis = distance.getMinDistance(bodyInfoTest);
			double finalerror = distance.getFinalError();
			
			DecimalFormat decimalFormat = new DecimalFormat("#.#######");
			String resultss = decimalFormat.format(finalerror);
			
			System.out.println("距離:" + dis + "   誤差" + resultss);
		}			
		
		long etime = System.currentTimeMillis();
		 
		System.out.println("秒數:"+(etime-stime));
		
		
	}
	
	
}
