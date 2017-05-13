package com.kinect.postureDetect;

import com.kinect.hibernate.BodyNode;

public class WallFlow extends PostureFlow 
{

	private WallErrorCaculate caculate;
	
	public WallFlow(WallErrorCaculate caculate){
		this.caculate = caculate;
	}
	
	
	
	
	
	@Override
	public IErrorCaculate startFlow(BodyNode bodyNode) {
		caculate.setElbow_left(bodyNode.getElbow_left());
		caculate.setElbow_right(bodyNode.getElbow_right());
		caculate.setShoulder_left(bodyNode.getShoulder_left());
		caculate.setShoulder_right(bodyNode.getShoulder_right());
		caculate.setWrist_left(bodyNode.getWrist_left());
		caculate.setWrist_right(bodyNode.getWrist_right());
		
//		caculate.errorCaculate()
		
		return caculate;
	}
	
}
