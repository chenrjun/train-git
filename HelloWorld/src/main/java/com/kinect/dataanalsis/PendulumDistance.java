package com.kinect.dataanalsis;

import com.kinect.hibernate.BodyInfo;
import com.kinect.hibernate.BodyInfoTest;
import com.kinect.hibernate.BodyNode;

/*
 * 鐘擺運動距離計算
 */
public class PendulumDistance implements IDistance {

	@Override
	public double distanceCompute(BodyInfo pastData, BodyNode currentData) {

		int pastWristLeftX = pastData.getWrist_leftX();
		int pastSymptom = pastData.getSymptoms();
		int pastRegion = pastData.getRehabilitationregion();
		int pastErrorPosture = pastData.getErrorposture();

		int presentWristLeftX = currentData.getWrist_left()[0];
		int presentSymptom = currentData.getSmptoms();
		int presentRegion = currentData.getRehabilitationregion();
		int presentErrorPosture = currentData.getErrorposture();

		double WristLeftXSquare = Math.pow(pastWristLeftX - presentWristLeftX, 2);
		double SymptomSquare = Math.pow(pastSymptom - presentSymptom, 2);
		double RegionSquare = Math.pow(pastRegion - presentRegion, 2);
		double ErrorPostureSquare = Math.pow(pastErrorPosture - presentErrorPosture, 2);

		double result = Math.sqrt(WristLeftXSquare + SymptomSquare + RegionSquare + ErrorPostureSquare);

		return result;
	}

	@Override
	public double distanceCompute(BodyInfo pastData, BodyInfoTest currentData) {
		int pastWristLeftX = pastData.getWrist_leftX();
		int pasterrorvalue = pastData.getErrorvalue();
		int pastSymptom = pastData.getSymptoms();
		int pastRegion = pastData.getRehabilitationregion();
		int pastErrorPosture = pastData.getErrorposture();

		int presentWristLeftX = currentData.getWrist_leftX();
		int presenterrorvalue = currentData.getErrorvalue();
		int presentSymptom = currentData.getSymptoms();
		int presentRegion = currentData.getRehabilitationregion();
		int presentErrorPosture = currentData.getErrorposture();

		double WristLeftXSquare = Math.pow(pastWristLeftX - presentWristLeftX, 2);
		double errorvalue = Math.pow(pasterrorvalue - presenterrorvalue, 2);
		double SymptomSquare = Math.pow(pastSymptom - presentSymptom, 2);
		double RegionSquare = Math.pow(pastRegion - presentRegion, 2);
		double ErrorPostureSquare = Math.pow(pastErrorPosture - presentErrorPosture, 2);

		double result = Math.sqrt(WristLeftXSquare + SymptomSquare + RegionSquare + ErrorPostureSquare+ errorvalue);

		return result;
	}

	@Override
	public double errorCompute(BodyInfo pastData, BodyNode currentData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double errorCompute(BodyInfo pastData, BodyInfoTest currentData) 
	{
		double pastWristLeftX = (double)pastData.getWrist_leftX();
		double pasterrorvalue = (double)pastData.getErrorvalue();
		double pastSymptom = (double)pastData.getSymptoms();
		double pastRegion = (double)pastData.getRehabilitationregion();
		double pastErrorPosture = (double)pastData.getErrorposture();

		double presentWristLeftX = (double)currentData.getWrist_leftX();
		double presenterrorvalue = (double)currentData.getErrorvalue();
		double presentSymptom = (double)currentData.getSymptoms();
		double presentRegion = (double)currentData.getRehabilitationregion();
		double presentErrorPosture = (double)currentData.getErrorposture();

		double wrist = (Math.abs((presentWristLeftX - pastWristLeftX)))/pastWristLeftX;
		
		double errorvalue = (Math.abs((presenterrorvalue - pasterrorvalue)))/pasterrorvalue;
		
		double symptom = (Math.abs((presentSymptom - pastSymptom)))/pastSymptom;
		
		double region = (Math.abs((presentRegion - pastRegion)))/pastSymptom;
		
		double error = (Math.abs((presentErrorPosture-pastErrorPosture)))/pastErrorPosture;
		
		double err=(wrist + symptom + region + errorvalue);
		double tc=1.0/500.0;
		
//		System.out.println(err + "@@@@@@@@@@@@@@@@@@@@@@@@@");
		if(!(Double.isNaN(error)))
			err+=error;
		
		err= err*tc;
//		System.out.println(err);
		return Math.abs(err);
	}

}
