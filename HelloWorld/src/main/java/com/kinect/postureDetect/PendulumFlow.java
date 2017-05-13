package com.kinect.postureDetect;


import com.kinect.hibernate.BodyNode;

public class PendulumFlow extends PostureFlow {
	
	
	private PendulumErrorCaculate caculate;
	
	public PendulumFlow(PendulumErrorCaculate caculate)
	{
		this.caculate = caculate;
	}
	
	
	public IErrorCaculate startFlow(BodyNode bodyNode)
	{
		
		if(bodyNode==null)
			return null;
		
		
		
		//垂直
		int elbowLeftX = bodyNode.getElbow_left()[0];
		int spineMidX   = bodyNode.getSpine_mid()[0];
		//水平
		int elbowLeftY = bodyNode.getElbow_left()[1];
		int spineMidY = bodyNode.getSpine_mid()[1];

		for(int i=-8;i<=8;i++)
		{
			if(elbowLeftX==spineMidX+i)
			{
				spineMidX = spineMidX+i;
				break;
			}
		}
		
		int distance=distance(bodyNode.getElbow_left(), bodyNode.getSpine_mid());
//		System.out.print("距離:" + distance(bodyNode.getElbow_left(), bodyNode.getSpine_mid()) + "  ");
//		System.out.println(Arrays.toString(bodyNode.getElbow_left()) + "--->" + Arrays.toString(bodyNode.getSpine_mid())); 
		
		//左手肘與脊椎中間Y相同
		if(distance<25 && elbowLeftY > spineMidY && elbowLeftX == spineMidX)
		{
			//如果 起始位置 與 終止位置 都沒有被設定 則設定起始位置後,結束流程
			if(!(caculate.bothSeted()))
			{
				caculate.setStartWristLeftX(bodyNode);
				return null;
			}
			
			System.out.print("與胸口距離:" + distance + "--->");
			return caculate;
			
		}else
		{
			int start = caculate.getStartWristLeftX();
			if((start+10)>bodyNode.getWrist_left()[0] && (start-10) < bodyNode.getWrist_left()[0])
				return null;
			
			caculate.setEndWristLeftX(bodyNode);
		}
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
}
