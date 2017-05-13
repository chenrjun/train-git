package com.kinect.main;


import com.kinect.hibernate.BodyInfo;
import com.kinect.hibernate.BodyNode;
import com.kinect.hibernate.SessionManager;
import com.kinect.postureDetect.IErrorCaculate;
import com.kinect.postureDetect.PendulumErrorCaculate;
import com.kinect.postureDetect.PendulumFlow;
import com.kinect.postureDetect.PostureFlow;
import com.kinect.postureDetect.StretchErrorCaculate;
import com.kinect.postureDetect.StretchFlow;
import com.kinect.postureDetect.WallErrorCaculate;
import com.kinect.postureDetect.WallFlow;
public class Main {

	public static void main(String[] args) throws InterruptedException 
	{
		int width = 800;
		int height = 550;
		Kinect kinect = new Kinect(width,height);
		
		kinect.startKinectSensor();
		PostureFlow flow = new WallFlow(new WallErrorCaculate());
		Thread.sleep(5000);
		
		int i=1;
		while (true) 
		{
			BodyNode bodyNode = kinect.getBodyNode();
			if(bodyNode!=null)
			{
				IErrorCaculate err = flow.startFlow(bodyNode);
				if(err!=null)
				{
					int errca = err.errorCaculate();
					int errlevel = err.errNumber();
					
//					String errvalStr= String.valueOf(errca);
//					if(errca>0 && errca <100)
//						errvalStr = "0" + errvalStr;
//					System.out.println("錯誤值:" + errvalStr + "    錯誤等級:" + errlevel + "  次數:" + i);
					
					Integer resultnum = bodyinfopackage(bodyNode,"wall",1,3,errlevel,errca);
					if(resultnum!=null)
					{
						String errvalStr= String.valueOf(errca);
						if(errca>0 && errca <100)
							errvalStr = "0" + errvalStr;
						System.out.println("錯誤值:" + errvalStr + "    錯誤等級:" + errlevel + "  次數:" + i);
						
						if(i>=500)
							System.exit(0);
						
						i++;
					}
					
				}
			}
			
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static Integer bodyinfopackage(BodyNode bodyNode,String tag,int symptoms,int region,int errposture,int errorvalue)
	{
		SessionManager sessionManager = new SessionManager();
		BodyInfo bodyInfo = new BodyInfo();
		bodyInfo.setSpinebaseX(bodyNode.getSpinebase()[0]);
		bodyInfo.setSpinebaseY(bodyNode.getSpinebase()[1]);
		bodyInfo.setSpine_midX(bodyNode.getSpine_mid()[0]);
		bodyInfo.setSpine_midY(bodyNode.getSpine_mid()[1]);
		bodyInfo.setNeckX(bodyNode.getNeck()[0]);
		bodyInfo.setNeckY(bodyNode.getNeck()[1]);
		bodyInfo.setHeadX(bodyNode.getHead()[0]);
		bodyInfo.setHeadY(bodyNode.getHead()[1]);
		bodyInfo.setShoulder_leftX(bodyNode.getShoulder_left()[0]);
		bodyInfo.setShoulder_leftY(bodyNode.getShoulder_left()[1]);
		bodyInfo.setElbow_leftX(bodyNode.getElbow_left()[0]);
		bodyInfo.setElbow_leftY(bodyNode.getElbow_left()[1]);
		bodyInfo.setWrist_leftX(bodyNode.getWrist_left()[0]);
		bodyInfo.setWrist_leftY(bodyNode.getWrist_left()[1]);
		bodyInfo.setHand_leftX(bodyNode.getHand_left()[0]);
		bodyInfo.setHand_leftY(bodyNode.getHand_left()[1]);
		bodyInfo.setShoulder_rightX(bodyNode.getShoulder_right()[0]);
		bodyInfo.setShoulder_rightY(bodyNode.getShoulder_right()[1]);
		bodyInfo.setElbow_rightX(bodyNode.getElbow_right()[0]);
		bodyInfo.setElbow_rightY(bodyNode.getElbow_right()[1]);
		bodyInfo.setWrist_rightX(bodyNode.getWrist_right()[0]);
		bodyInfo.setWrist_rightY(bodyNode.getWrist_right()[1]);
		bodyInfo.setHand_rightX(bodyNode.getHand_right()[0]);
		bodyInfo.setHand_rightY(bodyNode.getHand_right()[1]);
		bodyInfo.setHip_leftX(bodyNode.getHip_left()[0]);
		bodyInfo.setHip_leftY(bodyNode.getHip_left()[1]);
		bodyInfo.setKnee_leftX(bodyNode.getKnee_left()[0]);
		bodyInfo.setKnee_leftY(bodyNode.getKnee_left()[1]);
		bodyInfo.setAnkle_leftX(bodyNode.getAnkle_left()[0]);
		bodyInfo.setAnkle_leftY(bodyNode.getAnkle_left()[1]);
		bodyInfo.setFoot_leftX(bodyNode.getFoot_left()[0]);
		bodyInfo.setFoot_leftY(bodyNode.getFoot_left()[1]);
		bodyInfo.setHip_rightX(bodyNode.getHip_right()[0]);
		bodyInfo.setHip_rightY(bodyNode.getHip_right()[1]);
		bodyInfo.setKnee_rightX(bodyNode.getKnee_right()[0]);
		bodyInfo.setKnee_rightY(bodyNode.getKnee_right()[1]);
		bodyInfo.setAnkle_rightX(bodyNode.getAnkle_right()[0]);
		bodyInfo.setAnkle_rightY(bodyNode.getAnkle_right()[1]);
		bodyInfo.setFoot_rightX(bodyNode.getFoot_right()[0]);
		bodyInfo.setFoot_rightY(bodyNode.getFoot_right()[1]);
		bodyInfo.setSpine_shouldedX(bodyNode.getSpine_shoulded()[0]);
		bodyInfo.setSpine_shouldedY(bodyNode.getSpine_shoulded()[1]);
		bodyInfo.setHand_tip_leftX(bodyNode.getHand_tip_left()[0]);
		bodyInfo.setHand_tip_leftY(bodyNode.getHand_tip_left()[1]);
		bodyInfo.setThumb_leftX(bodyNode.getThumb_left()[0]);
		bodyInfo.setThumb_leftY(bodyNode.getThumb_left()[1]);
		bodyInfo.setHand_tip_rightX(bodyNode.getHand_tip_right()[0]);
		bodyInfo.setHand_tip_rightY(bodyNode.getHand_tip_right()[1]);
		bodyInfo.setThumb_rightX(bodyNode.getThumb_right()[0]);
		bodyInfo.setThumb_rightY(bodyNode.getThumb_right()[1]);
		bodyInfo.setErrorposture(errposture);
		
		bodyInfo.setRehabilitationregion(region);
		
		bodyInfo.setTag(tag);
		
		bodyInfo.setErrorvalue(errorvalue);
		
		bodyInfo.setSymptoms(symptoms);
		return sessionManager.inserBodyInfo(bodyInfo);
	}
}

	
