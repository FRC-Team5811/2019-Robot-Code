package frc.robot.MotionProfiling;


public class RigidTransform {

//	Inspired by FRC254 The Cheesy Poofs
//	https://github.com/Team254/FRC-2018-Public/blob/master/src/main/java/com/team254/lib/geometry/Pose2d.java
	
//	A pose (rigid transform) is a combination of a translation and a rotation
	
	Translation translation;
	Rotation rotation;
	
	public RigidTransform(double x_, double y_, double angle_) {
		translation = new Translation(x_, y_);
		rotation = new Rotation(angle_);
	}
	
	public RigidTransform(final Translation t_, final Rotation r_) {
		translation = t_;
		rotation = r_;
	}
	
	
	public RigidTransform(final Translation t_) {
		translation = t_;
		rotation = rotation.fromTranslation(t_);
	}
	
	public RigidTransform transformBy(final RigidTransform p_) {
		return new RigidTransform(this.translation.x + p_.translation.x, 
						this.translation.y + p_.translation.y, 
						this.rotation.angle + p_.rotation.angle);
	}
	
	public RigidTransform transformBy(final Translation t_, final Rotation r_) {
		return new RigidTransform(this.translation.x + t_.x, 
						this.translation.y + t_.y, 
						this.rotation.angle + r_.angle);
	}
	
	public RigidTransform transformByDynamic(final RigidTransform p_) {
//		System.out.println("previousAngle " + previousAngle.toText());
//		System.out.println("Translation " + p_.translation.toText());
//		System.out.println("Rotated Translation " + (p_.translation.rotateBy(previousAngle)).toText());
		return this.transformBy(p_.translation.rotateBy(this.rotation),p_.rotation);
	}
	
	
	public String toText() {
		return(this.translation.x + " " + this.translation.y + " " + this.rotation.angle);
	}
	
}