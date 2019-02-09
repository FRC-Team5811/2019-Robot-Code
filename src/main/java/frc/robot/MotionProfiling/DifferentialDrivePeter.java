package frc.robot.MotionProfiling;

public class DifferentialDrivePeter {

//	Inspired by FRC254 The Cheesy Poofs
	
	final double mass;
	
	final double wheelRadius;
	
	final double wheelBaseWidth;
	
	final double angularInertia; // Angular inertia
	
	final DCMotorTransmission leftTransmission;   //using calculated specs from VexPro for now,
	final DCMotorTransmission rightTransmission;  //planing to add auto characterization later
	
	public DifferentialDrivePeter(double mass_, double wheelRadius_, double wheelBaseWidth_ , double angularInertia_, 
							 DCMotorTransmission leftTransmission_, DCMotorTransmission rightTransmission_) {
		mass = mass_;
		wheelRadius = wheelRadius_;
		wheelBaseWidth = wheelBaseWidth_;
		angularInertia = angularInertia_;
		leftTransmission = leftTransmission_;
		rightTransmission = rightTransmission_;
	}
	
//	Generate a rigid transform (pose) from two encoder deltas
//	Transform is described using the robot's previous position as the origin with robot forward pointed along the x axis
//	A subsequent rotation must therefore be performed before transforming the previous pose
	public RigidTransform composeTransformFromArcs(double leftDist, double rightDist) {
		double deltaTheta;
		double deltaPMag;
		double deltaX, deltaY; 
		
		if(leftDist == rightDist) {  //Avoid division by zero error, but still return a pose //perfectly linear
//			System.out.println("straight line distance: " + leftDist);
			return new RigidTransform(leftDist, 0, 0);
			
		} else if(leftDist == 0.0 || rightDist == 0.0) {  //Another way to avoid division by zero //forward or backward pivot about one wheel
			if(leftDist == 0.0) {
				deltaTheta = rightDist / wheelBaseWidth;
			} else {
				deltaTheta = -1 * leftDist / wheelBaseWidth;
			}
			deltaPMag = Math.abs(wheelBaseWidth * Math.sin(deltaTheta/2));
//			System.out.println("delta Theta : " + deltaTheta);
//			System.out.println("translation magnitude: " + deltaPMag);
			
			if(leftDist > 0.0 || rightDist > 0.0) {
				deltaX = deltaPMag * Math.cos(deltaTheta/2);
				deltaY = deltaPMag * Math.sin(deltaTheta/2);
//				System.out.println("X: " + deltaX);
//				System.out.println("Y: " + deltaY);
			} else {
				deltaX = -1 * deltaPMag * Math.cos(deltaTheta/2);
				deltaY = -1 * deltaPMag * Math.sin(deltaTheta/2);
//				System.out.println("X: " + deltaX);
//				System.out.println("Y: " + deltaY);
			}
			return new RigidTransform(deltaX, deltaY, deltaTheta);
			
		} else if(Math.signum(leftDist) == Math.signum(rightDist)) {  // Forward or backward arc
			double innerArc, outerArc, radialAddendum;
			if(Math.abs(rightDist) > Math.abs(leftDist)) {
				outerArc = rightDist;
				innerArc = leftDist;
				radialAddendum = Math.abs((innerArc * wheelBaseWidth) / (outerArc - innerArc));
				deltaTheta = outerArc / (radialAddendum + wheelBaseWidth);
			} else {
				outerArc = leftDist;
				innerArc = rightDist;
				radialAddendum = Math.abs((innerArc * wheelBaseWidth) / (outerArc - innerArc));
				deltaTheta = -1 * outerArc / (radialAddendum + wheelBaseWidth);
			}
//			System.out.println("radial addendum: " + radialAddendum);
//			System.out.println("delta Theta : " + deltaTheta);
			
			deltaPMag = 2 * (radialAddendum + wheelBaseWidth / 2) * Math.abs(Math.sin(deltaTheta / 2));
//			System.out.println("translation magnitude: " + deltaPMag);
			
			if(leftDist > 0.0 || rightDist > 0.0) {
				deltaX = deltaPMag * Math.cos(deltaTheta/2);
				deltaY = deltaPMag * Math.sin(deltaTheta/2);
//				System.out.println("X: " + deltaX);
//				System.out.println("Y: " + deltaY);
			} else {
//				System.out.println("backward");
				deltaX = -1 * deltaPMag * Math.cos(deltaTheta/2);
				deltaY = -1 * deltaPMag * Math.sin(deltaTheta/2);
//				System.out.println("X: " + deltaX);
//				System.out.println("Y: " + deltaY);
			}
			return new RigidTransform(deltaX, deltaY, deltaTheta);
			
		} else { // Turn centered within the wheel base
			double innerArc, outerArc, longRadius, turnRadius;
			if(Math.abs(rightDist) > Math.abs(leftDist)) {
				outerArc = rightDist;
				innerArc = leftDist;
				longRadius = wheelBaseWidth / (Math.abs(innerArc / outerArc) + 1);
				turnRadius = longRadius - wheelBaseWidth / 2;
				deltaTheta = outerArc / longRadius;
			} else if(Math.abs(rightDist) < Math.abs(leftDist)){
				outerArc = leftDist;
				innerArc = rightDist;
				longRadius = wheelBaseWidth / (Math.abs(innerArc / outerArc) + 1);
				turnRadius = longRadius - wheelBaseWidth / 2;
				deltaTheta = -1 * outerArc / longRadius;
			} else {
				deltaTheta = rightDist / (wheelBaseWidth / 2);
//				System.out.println("deltaTheta: " + deltaTheta);
				return new RigidTransform(0, 0, deltaTheta);
			} 
			deltaPMag = 2 * turnRadius * Math.abs(Math.sin(deltaTheta / 2));
//			System.out.println("translation magnitude: " + deltaPMag);
//			System.out.println("delta Theta : " + deltaTheta);
			
			if(outerArc > 0) {
				deltaX = deltaPMag * Math.cos(deltaTheta/2);
				deltaY = deltaPMag * Math.sin(deltaTheta/2);
//				System.out.println("X: " + deltaX);
//				System.out.println("Y: " + deltaY);
			} else {
//				System.out.println("backward");
				deltaX = -1 * deltaPMag * Math.cos(deltaTheta/2);
				deltaY = -1 * deltaPMag * Math.sin(deltaTheta/2);
//				System.out.println("X: " + deltaX);
//				System.out.println("Y: " + deltaY);
			}
			
			return new RigidTransform(deltaX, deltaY, deltaTheta);
			
		}
	}
	
}
