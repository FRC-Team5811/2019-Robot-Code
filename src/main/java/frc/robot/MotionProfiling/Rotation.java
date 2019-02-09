package frc.robot.MotionProfiling;

public class Rotation {

//	Inspired by FRC254 The Cheesy Poofs
//	https://github.com/Team254/FRC-2018-Public/blob/master/src/main/java/com/team254/lib/geometry/Rotation2d.java
	
	double angle; //angle measure in radians
	
	public Rotation(double angle_) {
		angle = angle_;
	}
	
	public Rotation(final Rotation other) {
		angle = other.angle;
	}
	
//	create a rotation of the angle between the
	public Rotation fromTranslation(final Translation t) {
		return new Rotation(Math.atan2(t.y, t.x));
	}
	
//	same magnitude in the opposite direction
	public Rotation inverse() {
		return new Rotation(angle + Math.PI);
	}
	
//	combine the rotation of two rotation objects
	public Rotation rotateBy(final Rotation other) {
		return new Rotation(angle + other.angle);
	}
	
	double cos() {
		return Math.cos(angle);
	}
	
	double sin() {
		return Math.sin(angle);
	}
	
	String toText() {
		String me = this.angle + " ";
		return me;
	}
}
