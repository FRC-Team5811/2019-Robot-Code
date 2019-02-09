package frc.robot.MotionProfiling;
public class Translation {
	
//	Inspired by FRC254 The Cheesy Poofs 
//	https://github.com/Team254/FRC-2018-Public/blob/master/src/main/java/com/team254/lib/geometry/Translation2d.java
	
	double x;
	double y;
	
	public Translation(double x_, double y_) {
		x = x_;
		y = y_;
	}
	
	public Translation(final Translation other) {
		x = other.x;
		y = other.y;
	}
	
//	add the effect of another translation
	public Translation translateBy(final Translation other) {
		return new Translation(x + other.x, y + other.y);
	}
	
//	multiply by a scalar
	public Translation scale(double scale) {
		return new Translation(x * scale, y * scale);
	}
	
//	same magnitude in the opposite direction
	public Translation inverse() {
		return new Translation(-x, -y);
	}

//	return the magnitude of the vector, the straight line distance from Pythagorean theorem
	double mag() {
		return Math.sqrt(x*x + y*y);
	}
	
//	multiply a translation by a rotation matrix to rotate the vector
	public Translation rotateBy(final Rotation r_) {
		return new Translation(this.x * r_.cos() - this.y * r_.sin(), this.x * r_.sin() + this.y * r_.cos());
	}
	
	
	
//	I don't know how to fill in the built-in toString() method that all classes have 
	String toText() {
		String me = this.x + ", " + this.y;
		return me;
	}
}
