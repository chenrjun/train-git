package com.kinect.postureDetect;

public class WallErrorCaculate implements IErrorCaculate {

	private int[] wrist_left;

	private int[] elbow_left;

	private int[] shoulder_left;

	private int[] wrist_right;

	private int[] elbow_right;

	private int[] shoulder_right;

	public int[] getWrist_left() {
		return wrist_left;
	}

	public void setWrist_left(int[] wrist_left) {
		this.wrist_left = wrist_left;
	}

	public int[] getElbow_left() {
		return elbow_left;
	}

	public void setElbow_left(int[] elbow_left) {
		this.elbow_left = elbow_left;
	}

	public int[] getShoulder_left() {
		return shoulder_left;
	}

	public void setShoulder_left(int[] shoulder_left) {
		this.shoulder_left = shoulder_left;
	}

	public int[] getWrist_right() {
		return wrist_right;
	}

	public void setWrist_right(int[] wrist_right) {
		this.wrist_right = wrist_right;
	}

	public int[] getElbow_right() {
		return elbow_right;
	}

	public void setElbow_right(int[] elbow_right) {
		this.elbow_right = elbow_right;
	}

	public int[] getShoulder_right() {
		return shoulder_right;
	}

	public void setShoulder_right(int[] shoulder_right) {
		this.shoulder_right = shoulder_right;
	}

	// 手到肩
	private double lineWEL() {
		double result = Math.sqrt(((wrist_left[0] - elbow_left[0]) * (wrist_left[0] - elbow_left[0]))
				+ ((wrist_left[1] - elbow_left[1]) * (wrist_left[1] - elbow_left[1])));
		return result;
	}

	// 手肘到肩膀線
	private double lineWES() {
		double result = Math.sqrt(((elbow_left[0] - shoulder_left[0]) * ((elbow_left[0] - shoulder_left[0])))
				+ ((elbow_left[1] - shoulder_left[1]) * (elbow_left[1] - shoulder_left[1])));

		return result;
	}

	@Override
	public int errorCaculate() {
		
		
		int a=distance(shoulder_left, wrist_left);
		int b=distance(elbow_left, shoulder_left);
		int c=distance(elbow_left, wrist_left);
		//餘弦定理 求角度, 首要條件,需要知道三邊長
		return (int)Math.toDegrees(Math.acos((double)((b*b) + (c*c)-(a*a))/(2*b*c)));
	}

	@Override
	public Integer errNumber() {
		int errval = errorCaculate();
		
		if(errval<=70)
			return 6;
		
		return 0;
	}
	
	protected int distance(int[] point1,int[] point2)
	{
		return (int) Math.sqrt(((point1[0]-point2[0])*(point1[0]-point2[0])) + (((point1[1]-point2[1])*(point1[1]-point2[1]))));
	}

}
