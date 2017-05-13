package com.kinect.dataanalsis;

import com.kinect.hibernate.BodyInfo;
import com.kinect.hibernate.BodyInfoTest;
import com.kinect.hibernate.BodyNode;


/*
 * 牽張運動距離計算
 */
public class StretchDistance implements IDistance 
{

	@Override
	public double distanceCompute(BodyInfo pastData, BodyNode currentData) {
		
		int pastWristRightY = pastData.getWrist_rightY();
		int pastWristLeftY = pastData.getWrist_leftY();
		int pastSymptom = pastData.getSymptoms();
		int pastRegion = pastData.getRehabilitationregion();
		int pastErrorPosture = pastData.getErrorposture();
		
		
		int presentWristRightY = currentData.getWrist_right()[1];
		int presentWristLeftY = currentData.getWrist_left()[1];
		int presentSymptom = currentData.getSmptoms();
		int presentRegion = currentData.getRehabilitationregion();
		int presentErrorPosture = currentData.getErrorposture();
		
		
		double WristRightYSquare = Math.pow(pastWristRightY - presentWristRightY, 2);
		double WristLeftYSquare = Math.pow(pastWristLeftY-presentWristLeftY, 2);
		double SymptomSquare = Math.pow(pastSymptom-presentSymptom, 2);
		double RegionSquare = Math.pow(pastRegion - presentRegion,2);
		double ErrorPostureSquare = Math.pow(pastErrorPosture - presentErrorPosture, 2);
		
		double result = Math.sqrt(WristRightYSquare + WristLeftYSquare + SymptomSquare + RegionSquare + ErrorPostureSquare);
		
		return result;
	}

	@Override
	public double distanceCompute(BodyInfo pastData, BodyInfoTest currentData) {

		int pastWristRightY = pastData.getWrist_rightY();
		int pastWristLeftY = pastData.getWrist_leftY();
		int pastSymptom = pastData.getSymptoms();
		int pastRegion = pastData.getRehabilitationregion();
		int pastErrorPosture = pastData.getErrorposture();
		
		int presentWristRightY = currentData.getWrist_rightY();
		int presentWristLeftY = currentData.getWrist_leftY();
		int presentSymptom = currentData.getSymptoms();
		int presentRegion = currentData.getRehabilitationregion();
		int presentErrorPosture = currentData.getErrorposture();
		
		
		double WristRightYSquare = Math.pow(pastWristRightY - presentWristRightY, 2);
		double WristLeftYSquare = Math.pow(pastWristLeftY-presentWristLeftY, 2);
		double SymptomSquare = Math.pow(pastSymptom-presentSymptom, 2);
		double RegionSquare = Math.pow(pastRegion - presentRegion,2);
		double ErrorPostureSquare = Math.pow(pastErrorPosture - presentErrorPosture, 2);
		
		
		double result = Math.sqrt(WristRightYSquare + WristLeftYSquare + SymptomSquare + RegionSquare + ErrorPostureSquare);
		
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
