package com.kinect.postureDetect;

public class StretchErrorCaculate implements IErrorCaculate
{
	
	private int wristLeftY;
	
	private int wristRightY;
	
	
	public int getWristLeftY() {
		return wristLeftY;
	}

	public void setWristLeftY(int wristLeftY) {
		this.wristLeftY = wristLeftY;
	}

	public int getWristRightY() {
		return wristRightY;
	}
	
	public void setWristRightY(int wristRightY) {
		this.wristRightY = wristRightY;
	}

	@Override
	public int errorCaculate() 
	{
		int result = wristLeftY - wristRightY;
		return result;
	}

	@Override
	public Integer errNumber() {
		
		// TODO Auto-generated method stub
		int errreuslt = errorCaculate();
		if(errreuslt>180)
			return 4;
		
		if(errreuslt>100 && errreuslt<=180)
			return 0;
		
		if(errreuslt<=100)
			return 5;
		
		return null;
	}
	
}
