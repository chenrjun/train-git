package com.kinect.postureDetect;
import com.kinect.hibernate.BodyNode;

/**
 * 鐘擺運動計算方式
 * @author JH
 *
 */
public class PendulumErrorCaculate implements IErrorCaculate 
{
	
	private Integer startWristLeftX=0;
	
	private Integer endWristLeftX=0;
	
	
	
	public PendulumErrorCaculate()
	{
		
	}
	
	public void setStartWristLeftX(BodyNode bodyNode)
	{
		this.startWristLeftX = bodyNode.getWrist_left()[0];
	}
	
	public void setEndWristLeftX(BodyNode bodyNode)
	{
		int endX = bodyNode.getWrist_left()[0];
		
		if(endX > this.endWristLeftX)
			this.endWristLeftX = bodyNode.getWrist_left()[0];
	}
	
	public Integer getStartWristLeftX() {
		return startWristLeftX;
	}

	public Integer getEndWristLeftX() {
		return endWristLeftX;
	}
	
	public boolean bothSeted(){
		if(startWristLeftX!=0 && endWristLeftX!=0)
			return true;
		
		return false;
	}

	@Override
	public int errorCaculate()
	{
		//手肘向後擺動 回傳錯誤
//		if(startWristLeftX<endWristLeftX)
//			return 0;
		
		
		int errorResult = endWristLeftX - startWristLeftX;
		
		return errorResult;
	}
	
	private void NodeInitial(){
		this.startWristLeftX=0;
		this.endWristLeftX=0;
	}

	@Override
	public Integer errNumber() {
		
		Integer error = (int) errorCaculate();
		
		NodeInitial();
		
		if(error>150) return 3;
		else if(error>80 && error<=150) return 0;
		else if(error>0 && error<=80) return 1;
		else if(error<=0) return 2;
		
		
		return null;
	}
	
	
	
	
}
