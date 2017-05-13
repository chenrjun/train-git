package com.kinect.dataanalsis;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Id;

import com.kinect.hibernate.BodyInfo;
import com.kinect.hibernate.BodyInfoTest;
import com.kinect.hibernate.BodyNode;

public class EuclideanDistance {

	private List<BodyInfo> pastDatas;

	private IDistance iDistance;

	private Double finalerror=null;
	
	public EuclideanDistance(List<BodyInfo> pastData, IDistance iDistance) {
		this.pastDatas = pastData;
		this.iDistance = iDistance;
	}

	// 回傳最小距離
	public double getMinDistance(BodyNode currentData) 
	{
		Double result = null;
		Double err = null;
		
		// 過往資料總數
		int pastDataCount = pastDatas.size();

		// 開始比對距離
		for (int i = 0; i < pastDataCount; i++) {
			BodyInfo pastData = pastDatas.get(i);
			double resultTmp = 0;

			if (result == null) {
				resultTmp = iDistance.distanceCompute(pastData, currentData);
				continue;
			}

			// 當前資料與 過往資料距離計算
			resultTmp = iDistance.distanceCompute(pastData, currentData);

			if (resultTmp < result)
				result = resultTmp;
		}
		return result;
	}

	public double getMinDistance(BodyInfoTest currentData) 
	{
		Double result = null;
		
		Double err = null;
		// 過往資料總數
		int pastDataCount = pastDatas.size();

		// 開始比對距離
		for (int i = 0; i < pastDataCount; i++) 
		{
			BodyInfo pastData = pastDatas.get(i);
			double resultTmp = 0;
			double errtmp = 0;
//			if(pastData.getErrorposture() != currentData.getErrorposture())
//				continue;

			if (result==null || err==null) {
				result = iDistance.distanceCompute(pastData, currentData);
				err = iDistance.errorCompute(pastData, currentData);
				continue;
			}
			
			// 當前資料與 過往資料距離計算
			resultTmp = iDistance.distanceCompute(pastData, currentData);
			errtmp=iDistance.errorCompute(pastData, currentData);
			
			if (result > resultTmp || finalerror > errtmp)
			{
//				System.out.print(pastData.getId());System.out.print(" : ");
//				System.out.print(pastData.getWrist_leftX());System.out.print(" : ");
//				System.out.print(pastData.getErrorvalue());System.out.print(" : ");
//				System.out.print(pastData.getRehabilitationregion());System.out.print(" : ");
//				System.out.print(pastData.getSymptoms());System.out.print(" : ");
//				System.out.print(pastData.getErrorposture());
//				
//				System.out.println("\n\n");
//				System.out.print(currentData.getId());System.out.print(" : ");
//				System.out.print(currentData.getWrist_leftX());System.out.print(" : ");
//				System.out.print(currentData.getErrorvalue());System.out.print(" : ");
//				System.out.print(currentData.getRehabilitationregion());System.out.print(" : ");
//				System.out.print(currentData.getSymptoms());System.out.print(" : ");
//				System.out.println(currentData.getErrorposture());
				
//				System.out.println("----------------------------------");
				finalerror = errtmp;
				result = resultTmp;
//				DecimalFormat decimalFormat = new DecimalFormat("#.#######");
//				String resultss = decimalFormat.format(err);
//				System.out.println("距離"+result + "   誤差:"+resultss+"\n");
			}
			
			
		}
		
		return result;
	}
	
	public double getFinalError(){
		return finalerror;
	}

}
