package com.kinect.hibernate;


import edu.ufl.digitalworlds.j4k.Skeleton;
/*
 * for kinect to class
 */
public class BodyNode 
{
	
	//canvas寬度
	private int width;
	
	//canvas高度
	private int height;
	
	
	public BodyNode(int width,int height)
	{
		this.width = width;
		
		this.height =height;
	}
	
	private int[] spinebase;
	private int[] spine_mid;
	private int[] neck;
	private int[] head;
	private int[] shoulder_left;
	private int[] elbow_left;
	private int[] wrist_left;
	private int[] hand_left;
	private int[] shoulder_right;
	private int[] elbow_right;
	private int[] wrist_right;
	private int[] hand_right;
	private int[] hip_left;
	private int[] knee_left;
	private int[] ankle_left;
	private int[] foot_left;
	private int[] hip_right;
	private int[] knee_right;
	private int[] ankle_right;
	private int[] foot_right;
	private int[] spine_shoulded;
	private int[] hand_tip_left;
	private int[] thumb_left;
	private int[] hand_tip_right;
	private int[] thumb_right;
	private int errorvalue;
	private int rehabilitationregion;
	private int smptoms;
	private int errorposture;
	
	public void setEachNode(Skeleton skeleton)
	{
		
		this.spinebase 	   = skeleton.get2DJoint(0, width,height);
		this.spine_mid 	   = skeleton.get2DJoint(1, width,height);
		this.neck	   	   = skeleton.get2DJoint(2, width,height);
		this.head	   	   = skeleton.get2DJoint(3, width,height);
		this.shoulder_left = skeleton.get2DJoint(4, width,height);
		this.elbow_left	   = skeleton.get2DJoint(5, width,height);
		this.wrist_left	   = skeleton.get2DJoint(6, width,height);
		this.hand_left	   = skeleton.get2DJoint(7, width,height);
		this.shoulder_right= skeleton.get2DJoint(8, width,height);
		this.elbow_right   = skeleton.get2DJoint(9, width,height);
		this.wrist_right   = skeleton.get2DJoint(10, width,height);
		this.hand_right	   = skeleton.get2DJoint(11, width,height);
		this.hip_left	   = skeleton.get2DJoint(12, width,height);
		this.knee_left	   = skeleton.get2DJoint(13, width,height);
		this.ankle_left	   = skeleton.get2DJoint(14, width,height);
		this.foot_left	   = skeleton.get2DJoint(15, width,height);
		this.hip_right	   = skeleton.get2DJoint(16, width,height);
		this.knee_right	   = skeleton.get2DJoint(17, width,height);
		this.ankle_right   = skeleton.get2DJoint(18, width,height);
		this.foot_right	   = skeleton.get2DJoint(19, width,height);
		this.spine_shoulded= skeleton.get2DJoint(20, width,height);
		this.hand_tip_left = skeleton.get2DJoint(21, width,height);
		this.thumb_left	   = skeleton.get2DJoint(22, width,height);
		this.hand_tip_right= skeleton.get2DJoint(23, width,height);
		this.thumb_right   = skeleton.get2DJoint(24, width,height);
	}

	public int[] getSpinebase() {
		return spinebase;
	}



	public int[] getSpine_mid() {
		return spine_mid;
	}



	public int[] getNeck() {
		return neck;
	}



	public int[] getHead() {
		return head;
	}



	public int[] getShoulder_left() {
		return shoulder_left;
	}



	public int[] getElbow_left() {
		return elbow_left;
	}



	public int[] getWrist_left() {
		return wrist_left;
	}



	public int[] getHand_left() {
		return hand_left;
	}



	public int[] getShoulder_right() {
		return shoulder_right;
	}



	public int[] getElbow_right() {
		return elbow_right;
	}



	public int[] getWrist_right() {
		return wrist_right;
	}



	public int[] getHand_right() {
		return hand_right;
	}



	public int[] getHip_left() {
		return hip_left;
	}



	public int[] getKnee_left() {
		return knee_left;
	}



	public int[] getAnkle_left() {
		return ankle_left;
	}



	public int[] getFoot_left() {
		return foot_left;
	}



	public int[] getHip_right() {
		return hip_right;
	}



	public int[] getKnee_right() {
		return knee_right;
	}



	public int[] getAnkle_right() {
		return ankle_right;
	}



	public int[] getFoot_right() {
		return foot_right;
	}



	public int[] getSpine_shoulded() {
		return spine_shoulded;
	}



	public int[] getHand_tip_left() {
		return hand_tip_left;
	}



	public int[] getThumb_left() {
		return thumb_left;
	}



	public int[] getHand_tip_right() {
		return hand_tip_right;
	}



	public int[] getThumb_right() {
		return thumb_right;
	}

	

	public int getErrorvalue() {
		return errorvalue;
	}

	public int getRehabilitationregion() {
		return rehabilitationregion;
	}



	public int getSmptoms() {
		return smptoms;
	}



	public int getErrorposture() {
		return errorposture;
	}
	


	
	public void bodyNodeInitialize(){
		this.spinebase 	   = null;
		this.spine_mid 	   = null;
		this.neck	   	   = null;
		this.head	   	   = null;
		this.shoulder_left = null;
		this.elbow_left	   = null;
		this.wrist_left	   = null;
		this.hand_left	   = null;
		this.shoulder_right= null;
		this.elbow_right   = null;
		this.wrist_right   = null;
		this.hand_right	   = null;
		this.hip_left	   = null;
		this.knee_left	   = null;
		this.ankle_left	   = null;
		this.foot_left	   = null;
		this.hip_right	   = null;
		this.knee_right	   = null;
		this.ankle_right   = null;
		this.foot_right	   = null;
		this.spine_shoulded= null;
		this.hand_tip_left = null;
		this.thumb_left	   = null;
		this.hand_tip_right= null;
		this.thumb_right   = null;
	}


	
}
