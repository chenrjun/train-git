package com.kinect.postureDetect;
import com.kinect.hibernate.BodyNode;

public abstract class PostureFlow {
	
	
	
	public abstract IErrorCaculate startFlow(BodyNode bodyNode);
	
	
	protected int distance(int[] point1,int[] point2)
	{
		return (int) Math.sqrt(((point1[0]-point2[0])*(point1[0]-point2[0])) + (((point1[1]-point2[1])*(point1[1]-point2[1]))));
	}
}
