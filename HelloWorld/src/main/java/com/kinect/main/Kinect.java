package com.kinect.main;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Stack;

import com.kinect.hibernate.BodyNode;
import com.kinect.postureDetect.PendulumErrorCaculate;
import com.kinect.postureDetect.PendulumFlow;
import com.kinect.postureDetect.PostureFlow;

import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;
/**
 * 測試類別
 * @author JH
 *
 */
public class Kinect extends J4KSDK {

	private boolean switchOn = false;

	private Stack<BodyNode> bodyNodesStack = new Stack<BodyNode>();

	private int width;

	private int height;

	public Kinect(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void onColorFrameEvent(byte[] arg0) {

	}

	@Override
	public void onDepthFrameEvent(short[] arg0, byte[] arg1, float[] arg2, float[] arg3) {

	}

	@Override
	public void onSkeletonFrameEvent(boolean[] skeleton_tracked, float[] joint_positions, float[] joint_orientations,
			byte[] joint_state) {

		Skeleton skeleton = null;
		for (int i = 0; i < skeleton_tracked.length; i++) {
			boolean isTracked = skeleton_tracked[i];
			if (isTracked == true) {
//				System.out.println(i);
				skeleton = this.getSkeletons()[i];
				BodyNode bodyNode = new BodyNode(this.width, this.height);
				bodyNode.setEachNode(skeleton);
				
				if (bodyNodesStack.size() > 20) {
					bodyNodesStack.clear();
				}

				bodyNodesStack.push(bodyNode);
				break;
			}
		}

		// if (skeleton != null) {
		// BodyNode bodyNode = new BodyNode(this.width, this.height);
		// bodyNode.setEachNode(skeleton);
		// System.out.println(flow.startFlow(bodyNode));
		// }
	}

	// 啟動Kinect
	public void startKinectSensor() {
		if (switchOn == true)
			System.out.println("Kinect sensor has started");
		else {
			System.out.println("start successful");
			this.start(J4KSDK.COLOR | J4KSDK.DEPTH | J4KSDK.SKELETON);
			switchOn = true;
		}
	}

	public void stopKinectSensor() {
		System.out.println("Kinect sensor has stoped");
		if (switchOn == true) {
			this.stop();
			switchOn = false;
		}
	}

	@Override
	public void stop() {
		System.out.println("stop");
		super.stop();
	}

	public BodyNode getBodyNode() {

		if (bodyNodesStack.isEmpty())
			return null;

		return bodyNodesStack.pop();
	}

}
