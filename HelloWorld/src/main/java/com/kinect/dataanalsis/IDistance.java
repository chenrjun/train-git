package com.kinect.dataanalsis;

import com.kinect.hibernate.BodyInfo;
import com.kinect.hibernate.BodyInfoTest;
import com.kinect.hibernate.BodyNode;

public interface IDistance {
	
	public double distanceCompute(BodyInfo pastData,BodyNode currentData);
	
	public double distanceCompute(BodyInfo pastData,BodyInfoTest currentData);
	
	public double errorCompute(BodyInfo pastData,BodyNode currentData);
	
	public double errorCompute(BodyInfo pastData,BodyInfoTest currentData);
	
}
