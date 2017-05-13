package com.kinect.dataanalsis;

import com.kinect.hibernate.BodyInfo;
import com.kinect.hibernate.BodyInfoTest;
import com.kinect.hibernate.BodyNode;

public class WallDistance implements IDistance
{

	
	
	@Override
	public double distanceCompute(BodyInfo pastData, BodyNode currentData) 
	{
		
		//------------------------------------------過往資料
		int pastWristLeftX = pastData.getWrist_leftX();
		int pastWristLeftY = pastData.getWrist_leftY();
		
		int pastElbowLeftX = pastData.getElbow_leftX();
		int pastElbowLeftY = pastData.getElbow_leftY();
		
		int pastShouldLeftX = pastData.getShoulder_leftX();
		int pastShouldLeftY = pastData.getShoulder_leftY();
		
		int pastSymptom = pastData.getSymptoms();
		int pastRegion = pastData.getRehabilitationregion();
		int pastErrorPosture = pastData.getErrorposture();
		
		//------------------------------------------當前資料
		int presentWristLeftX = currentData.getWrist_left()[0];
		int presentWristLeftY = currentData.getWrist_left()[1];
		
		int presentElbowLeftX = currentData.getElbow_left()[0];
		int presentElbowLeftY = currentData.getElbow_left()[1];
		
		int presentShouldLeftX = currentData.getShoulder_left()[0];
		int presentShouldLeftY = currentData.getShoulder_left()[1];
		
		int presentSymptom = currentData.getSmptoms();
		int presentRegion = currentData.getRehabilitationregion();
		int presentErrorPosture = currentData.getErrorposture();
		
		
		double WristLeftXSquare = Math.pow(pastWristLeftX-presentWristLeftX, 2);
		double WristLeftYSquare = Math.pow(pastWristLeftY - presentWristLeftY, 2);
		double ElbowLeftXSquare = Math.pow(pastElbowLeftX-presentElbowLeftX, 2);
		double ElbowLeftYSquare = Math.pow(pastElbowLeftY - presentElbowLeftY, 2);
		double ShouldLeftXSquare = Math.pow(pastShouldLeftX-presentShouldLeftX, 2);
		double ShouldLeftYSquare = Math.pow(pastShouldLeftY - presentShouldLeftY, 2);
		double SymptomSquare = Math.pow(pastSymptom-presentSymptom, 2);
		double RegionSquare = Math.pow(pastRegion - presentRegion,2);
		double ErrorPostureSquare = Math.pow(pastErrorPosture - presentErrorPosture, 2);
		
		
		double result = Math.sqrt(WristLeftXSquare + WristLeftYSquare + ElbowLeftXSquare + ElbowLeftYSquare+
				ShouldLeftXSquare + ShouldLeftYSquare + SymptomSquare + RegionSquare + ErrorPostureSquare);
		
		return result;
	}

	@Override
	public double distanceCompute(BodyInfo pastData, BodyInfoTest currentData) {
		//------------------------------------------過往資料
				int pastWristLeftX = pastData.getWrist_leftX();
				int pastWristLeftY = pastData.getWrist_leftY();
				
				int pastElbowLeftX = pastData.getElbow_leftX();
				int pastElbowLeftY = pastData.getElbow_leftY();
				
				int pastShouldLeftX = pastData.getShoulder_leftX();
				int pastShouldLeftY = pastData.getShoulder_leftY();
				
				int pastSymptom = pastData.getSymptoms();
				int pastRegion = pastData.getRehabilitationregion();
				int pastErrorPosture = pastData.getErrorposture();
				
				//------------------------------------------當前資料
				int presentWristLeftX = currentData.getWrist_leftX();
				int presentWristLeftY = currentData.getWrist_leftY();
				
				int presentElbowLeftX = currentData.getElbow_leftX();
				int presentElbowLeftY = currentData.getElbow_leftY();
				
				int presentShouldLeftX = currentData.getShoulder_leftX();
				int presentShouldLeftY = currentData.getShoulder_leftY();
				
				int presentSymptom = currentData.getSymptoms();
				int presentRegion = currentData.getRehabilitationregion();
				int presentErrorPosture = currentData.getErrorposture();
				
				
				double WristLeftXSquare = Math.pow(pastWristLeftX-presentWristLeftX, 2);
				double WristLeftYSquare = Math.pow(pastWristLeftY - presentWristLeftY, 2);
				double ElbowLeftXSquare = Math.pow(pastElbowLeftX-presentElbowLeftX, 2);
				double ElbowLeftYSquare = Math.pow(pastElbowLeftY - presentElbowLeftY, 2);
				double ShouldLeftXSquare = Math.pow(pastShouldLeftX-presentShouldLeftX, 2);
				double ShouldLeftYSquare = Math.pow(pastShouldLeftY - presentShouldLeftY, 2);
				double SymptomSquare = Math.pow(pastSymptom-presentSymptom, 2);
				double RegionSquare = Math.pow(pastRegion - presentRegion,2);
				double ErrorPostureSquare = Math.pow(pastErrorPosture - presentErrorPosture, 2);
				
				
				double result = Math.sqrt(WristLeftXSquare + WristLeftYSquare + ElbowLeftXSquare + ElbowLeftYSquare+
						ShouldLeftXSquare + ShouldLeftYSquare + SymptomSquare + RegionSquare + ErrorPostureSquare);
				
				return result;
	}

	@Override
	public double errorCompute(BodyInfo pastData, BodyNode currentData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double errorCompute(BodyInfo pastData, BodyInfoTest currentData) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
