package com.kinect.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * for hibernate to class
 */


@Entity
@Table(name="inputlayer")
public class BodyInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private int spinebaseX;
	private int spinebaseY;

	private int spine_midX;	
	private int spine_midY;

	private int neckX;
	private int neckY;

	private int headX;	
	private int headY;

	private int shoulder_leftX;	
	private int shoulder_leftY;

	private int elbow_leftX;
	private int elbow_leftY;

	private int wrist_leftX;	
	private int wrist_leftY;

	private int hand_leftX;	
	private int hand_leftY;

	private int shoulder_rightX;	
	private int shoulder_rightY;

	private int elbow_rightX;
	private int elbow_rightY;

	private int wrist_rightX;	
	private int wrist_rightY;

	private int hand_rightX;	
	private int hand_rightY;

	private int hip_leftX;
	private int hip_leftY;

	private int knee_leftX;	
	private int knee_leftY;

	private int ankle_leftX;
	private int ankle_leftY;

	private int foot_leftX;
	private int foot_leftY;

	private int hip_rightX;	
	private int hip_rightY;

	private int knee_rightX;	
	private int knee_rightY;

	private int ankle_rightX;	
	private int ankle_rightY;

	private int foot_rightX;	
	private int foot_rightY;

	private int spine_shouldedX;	
	private int spine_shouldedY;

	private int hand_tip_leftX;
	private int hand_tip_leftY;

	private int thumb_leftX;	
	private int thumb_leftY;

	private int hand_tip_rightX;	
	private int hand_tip_rightY;

	private int thumb_rightX;	
	private int thumb_rightY;
	
	private int errorvalue;

	private int rehabilitationregion;	

	private int symptoms;
	
	private int errorposture;	
	
	private String tag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSpinebaseX() {
		return spinebaseX;
	}
	
	public void setSpinebaseX(int spinebaseX) {
		this.spinebaseX = spinebaseX;
	}
	
	public int getSpinebaseY() {
		return spinebaseY;
	}
	
	public void setSpinebaseY(int spinebaseY) {
		this.spinebaseY = spinebaseY;
	}
	public int getSpine_midX() {
		return spine_midX;
	}
	public void setSpine_midX(int spine_midX) {
		this.spine_midX = spine_midX;
	}
	public int getSpine_midY() {
		return spine_midY;
	}
	public void setSpine_midY(int spine_midY) {
		this.spine_midY = spine_midY;
	}
	public int getNeckX() {
		return neckX;
	}
	public void setNeckX(int neckX) {
		this.neckX = neckX;
	}
	public int getNeckY() {
		return neckY;
	}
	public void setNeckY(int neckY) {
		this.neckY = neckY;
	}
	public int getHeadX() {
		return headX;
	}
	public void setHeadX(int headX) {
		this.headX = headX;
	}
	public int getHeadY() {
		return headY;
	}
	public void setHeadY(int headY) {
		this.headY = headY;
	}
	public int getShoulder_leftX() {
		return shoulder_leftX;
	}
	public void setShoulder_leftX(int shoulder_leftX) {
		this.shoulder_leftX = shoulder_leftX;
	}
	public int getShoulder_leftY() {
		return shoulder_leftY;
	}
	public void setShoulder_leftY(int shoulder_leftY) {
		this.shoulder_leftY = shoulder_leftY;
	}
	public int getElbow_leftX() {
		return elbow_leftX;
	}
	public void setElbow_leftX(int elbow_leftX) {
		this.elbow_leftX = elbow_leftX;
	}
	public int getElbow_leftY() {
		return elbow_leftY;
	}
	public void setElbow_leftY(int elbow_leftY) {
		this.elbow_leftY = elbow_leftY;
	}
	public int getWrist_leftX() {
		return wrist_leftX;
	}
	public void setWrist_leftX(int wrist_leftX) {
		this.wrist_leftX = wrist_leftX;
	}
	public int getWrist_leftY() {
		return wrist_leftY;
	}
	public void setWrist_leftY(int wrist_leftY) {
		this.wrist_leftY = wrist_leftY;
	}
	public int getHand_leftX() {
		return hand_leftX;
	}
	public void setHand_leftX(int hand_leftX) {
		this.hand_leftX = hand_leftX;
	}
	public int getHand_leftY() {
		return hand_leftY;
	}
	public void setHand_leftY(int hand_leftY) {
		this.hand_leftY = hand_leftY;
	}
	public int getShoulder_rightX() {
		return shoulder_rightX;
	}
	public void setShoulder_rightX(int shoulder_rightX) {
		this.shoulder_rightX = shoulder_rightX;
	}
	public int getShoulder_rightY() {
		return shoulder_rightY;
	}
	public void setShoulder_rightY(int shoulder_rightY) {
		this.shoulder_rightY = shoulder_rightY;
	}
	public int getElbow_rightX() {
		return elbow_rightX;
	}
	public void setElbow_rightX(int elbow_rightX) {
		this.elbow_rightX = elbow_rightX;
	}
	public int getElbow_rightY() {
		return elbow_rightY;
	}
	public void setElbow_rightY(int elbow_rightY) {
		this.elbow_rightY = elbow_rightY;
	}
	public int getWrist_rightX() {
		return wrist_rightX;
	}
	public void setWrist_rightX(int wrist_rightX) {
		this.wrist_rightX = wrist_rightX;
	}
	public int getWrist_rightY() {
		return wrist_rightY;
	}
	public void setWrist_rightY(int wrist_rightY) {
		this.wrist_rightY = wrist_rightY;
	}
	public int getHand_rightX() {
		return hand_rightX;
	}
	public void setHand_rightX(int hand_rightX) {
		this.hand_rightX = hand_rightX;
	}
	public int getHand_rightY() {
		return hand_rightY;
	}
	public void setHand_rightY(int hand_rightY) {
		this.hand_rightY = hand_rightY;
	}
	public int getHip_leftX() {
		return hip_leftX;
	}
	public void setHip_leftX(int hip_leftX) {
		this.hip_leftX = hip_leftX;
	}
	public int getHip_leftY() {
		return hip_leftY;
	}
	public void setHip_leftY(int hip_leftY) {
		this.hip_leftY = hip_leftY;
	}
	public int getKnee_leftX() {
		return knee_leftX;
	}
	public void setKnee_leftX(int knee_leftX) {
		this.knee_leftX = knee_leftX;
	}
	public int getKnee_leftY() {
		return knee_leftY;
	}
	public void setKnee_leftY(int knee_leftY) {
		this.knee_leftY = knee_leftY;
	}
	public int getAnkle_leftX() {
		return ankle_leftX;
	}
	public void setAnkle_leftX(int ankle_leftX) {
		this.ankle_leftX = ankle_leftX;
	}
	public int getAnkle_leftY() {
		return ankle_leftY;
	}
	public void setAnkle_leftY(int ankle_leftY) {
		this.ankle_leftY = ankle_leftY;
	}
	public int getFoot_leftX() {
		return foot_leftX;
	}
	public void setFoot_leftX(int foot_leftX) {
		this.foot_leftX = foot_leftX;
	}
	public int getFoot_leftY() {
		return foot_leftY;
	}
	public void setFoot_leftY(int foot_leftY) {
		this.foot_leftY = foot_leftY;
	}
	public int getHip_rightX() {
		return hip_rightX;
	}
	public void setHip_rightX(int hip_rightX) {
		this.hip_rightX = hip_rightX;
	}
	public int getHip_rightY() {
		return hip_rightY;
	}
	public void setHip_rightY(int hip_rightY) {
		this.hip_rightY = hip_rightY;
	}
	public int getKnee_rightX() {
		return knee_rightX;
	}
	public void setKnee_rightX(int knee_rightX) {
		this.knee_rightX = knee_rightX;
	}
	public int getKnee_rightY() {
		return knee_rightY;
	}
	public void setKnee_rightY(int knee_rightY) {
		this.knee_rightY = knee_rightY;
	}
	public int getAnkle_rightX() {
		return ankle_rightX;
	}
	public void setAnkle_rightX(int ankle_rightX) {
		this.ankle_rightX = ankle_rightX;
	}
	public int getAnkle_rightY() {
		return ankle_rightY;
	}
	public void setAnkle_rightY(int ankle_rightY) {
		this.ankle_rightY = ankle_rightY;
	}
	public int getFoot_rightX() {
		return foot_rightX;
	}
	public void setFoot_rightX(int foot_rightX) {
		this.foot_rightX = foot_rightX;
	}
	public int getFoot_rightY() {
		return foot_rightY;
	}
	public void setFoot_rightY(int foot_rightY) {
		this.foot_rightY = foot_rightY;
	}
	public int getSpine_shouldedX() {
		return spine_shouldedX;
	}
	public void setSpine_shouldedX(int spine_shouldedX) {
		this.spine_shouldedX = spine_shouldedX;
	}
	public int getSpine_shouldedY() {
		return spine_shouldedY;
	}
	public void setSpine_shouldedY(int spine_shouldedY) {
		this.spine_shouldedY = spine_shouldedY;
	}
	public int getHand_tip_leftX() {
		return hand_tip_leftX;
	}
	public void setHand_tip_leftX(int hand_tip_leftX) {
		this.hand_tip_leftX = hand_tip_leftX;
	}
	public int getHand_tip_leftY() {
		return hand_tip_leftY;
	}
	public void setHand_tip_leftY(int hand_tip_leftY) {
		this.hand_tip_leftY = hand_tip_leftY;
	}
	public int getThumb_leftX() {
		return thumb_leftX;
	}
	public void setThumb_leftX(int thumb_leftX) {
		this.thumb_leftX = thumb_leftX;
	}
	public int getThumb_leftY() {
		return thumb_leftY;
	}
	public void setThumb_leftY(int thumb_leftY) {
		this.thumb_leftY = thumb_leftY;
	}
	public int getHand_tip_rightX() {
		return hand_tip_rightX;
	}
	public void setHand_tip_rightX(int hand_tip_rightX) {
		this.hand_tip_rightX = hand_tip_rightX;
	}
	public int getHand_tip_rightY() {
		return hand_tip_rightY;
	}
	public void setHand_tip_rightY(int hand_tip_rightY) {
		this.hand_tip_rightY = hand_tip_rightY;
	}
	public int getThumb_rightX() {
		return thumb_rightX;
	}
	public void setThumb_rightX(int thumb_rightX) {
		this.thumb_rightX = thumb_rightX;
	}
	public int getThumb_rightY() {
		return thumb_rightY;
	}
	public void setThumb_rightY(int thumb_rightY) {
		this.thumb_rightY = thumb_rightY;
	}
	
	public int getErrorvalue() {
		return errorvalue;
	}
	public void setErrorvalue(int errorvalue) {
		this.errorvalue = errorvalue;
	}
	public int getRehabilitationregion() {
		return rehabilitationregion;
	}
	public void setRehabilitationregion(int rehabilitationregion) {
		this.rehabilitationregion = rehabilitationregion;
	}
	public int getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(int symptoms) {
		this.symptoms = symptoms;
	}
	public int getErrorposture() {
		return errorposture;
	}
	public void setErrorposture(int errorposture) {
		this.errorposture = errorposture;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
