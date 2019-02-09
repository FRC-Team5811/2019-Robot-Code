
//import static Constants.*;
package frc.robot.MotionProfiling;
public class DCMotorTransmission {

//	Inspired by FRC254 The Cheesy Poofs
	
	final double kv;   // angular velocity per volt in  rad/s per V 
	final double kt;   // torque per amp in             N*m per A
	final double R; // The electrical resistance of the motor (calculated at stall)
	
	final double G; //gear ratio as a positive double for reduction (like g:1)
	
	final double frictionVoltage;   //Inefficiency of a gearbox (combined electrical and mechanical, empirical measure)
	final int nMotors; //number of motor in gearbox
	
	//Using actual 
	
	public DCMotorTransmission(double kv_, double kt_, double friction_voltage_, double R_, double g_, int nMotors_) {
		kv = kv_;
		kt = kt_;
		frictionVoltage = friction_voltage_;
		R = R_;
		G = g_;
		nMotors = nMotors_;
	}
	
	//return the voltage necessary to apply a specified torque at a specified angular velocity
	double outputVoltage(double w, double torque) {
		return (torque / G / kt * R) + (w * G / kv) + frictionVoltage;
	}
	
	double outputTorque(double w, double volts) {
		return (volts - frictionVoltage - w * G / kv) * G * kt / R;
	}
	
	double outputAngularVelocity(double volts, double torque) {
		return (volts - frictionVoltage - torque * R / (G * kt)) * kv / G;
	}
	
	
	
	
}