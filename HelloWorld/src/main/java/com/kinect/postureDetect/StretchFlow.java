package com.kinect.postureDetect;

import com.kinect.hibernate.BodyNode;

public class StretchFlow extends PostureFlow
{
	
	private StretchErrorCaculate caculate;
	
	public StretchFlow(StretchErrorCaculate caculate) 
	{
		this.caculate = caculate;
	}
	
	@Override
	public IErrorCaculate startFlow(BodyNode bodyNode)
	{
		caculate.setWristLeftY(bodyNode.getWrist_left()[1]);
		caculate.setWristRightY(bodyNode.getWrist_right()[1]);
//		System.out.print("左手:" + bodyNode.getWrist_left()[1] + ":" + "右手:"+bodyNode.getWrist_right()[1]);
		
		return caculate;
	}
	
}
