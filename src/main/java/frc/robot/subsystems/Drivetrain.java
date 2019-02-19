
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
  
  public static TalonSRX driveFrontRight, driveFrontLeft;
  public static VictorSPX driveBackRight, driveBackLeft;
  public static Compressor cp;
  public static PowerDistributionPanel pdp;
  private static AHRS navX;

  private static Encoder encL, encR;

  public static double arcadeSpeedMod = 1;
  private static double arcadeTurnMod = .7;

  private static final double PULSES_PER_METER = 4277.55018;

  public static double batteryVoltage;

  public Drivetrain(){

    driveFrontRight = RobotMap.rightF;
    driveBackRight = RobotMap.rightB;
    driveFrontLeft = RobotMap.leftF;
    driveBackLeft = RobotMap.leftB;

    navX = RobotMap.navx;
    cp = RobotMap.COMPRESSOR;
    pdp = RobotMap.PDP;

    encL = RobotMap.driveEncL;
    encR = RobotMap.driveEncR;
  }

  @Override
  public void initDefaultCommand() {
    
  }
  
  public void arcadeDrive(double turn, double throttle) {
    //simulates car-like driving
		// if (throttle <= -.02) {
		// 	turn = -turn;
    // }
    double leftSpeed = ((arcadeSpeedMod*throttle) + (arcadeTurnMod * turn)); //changed to .92 for practice
    double rightSpeed = ((arcadeSpeedMod*-throttle) + (arcadeTurnMod * turn));
    
    driveFrontLeft.set(ControlMode.PercentOutput, leftSpeed);
    driveBackLeft.follow(driveFrontLeft);
    driveFrontRight.set(ControlMode.PercentOutput, rightSpeed);
    driveBackRight.follow(driveFrontRight);
/*
    System.out.println("LF: "+RobotMap.PDP.getCurrent(0) + "\t" + "LB: "+RobotMap.PDP.getCurrent(1) + 
    "\t" + "RF: "+RobotMap.PDP.getCurrent(15) + "\t" + "RB: "+ RobotMap.PDP.getCurrent(14));
    */
  }

  public double grabAngleRadians() {
    double unrounded = -Math.toRadians(navX.getAngle());
    double rounded = Math.round(unrounded*1000);
		return rounded/1000;
  }
  
  public double getLeftEnc(){
    return encL.get();
  }

  public double getRightEnc(){
    return encR.get();
  }

  public double getLeftEncMeters(){
    double unrounded = encL.get()/PULSES_PER_METER;
    double rounded = Math.round(unrounded*1000);
    return rounded/1000;
  }

  public double getRightEncMeters(){
    double unrounded = encR.get()/PULSES_PER_METER;
    double rounded = Math.round(unrounded*1000);
    return rounded/1000;
  }

  public void resetEncoders() {
		encR.reset();
		encL.reset();
	}

  public void resetNAVX() {
		navX.reset();
  }
	
	public double reportTimeStamp() {
		return Timer.getFPGATimestamp();
  }
  
  public void changeSpeed(double throttle, double turn){
    arcadeSpeedMod = throttle;
    arcadeTurnMod = turn;
  }
	
	//Send an amount of voltage to the motors based on the PDP voltage reading. Doesn't account for voltage sag...
	public void voltageDrive(double leftVoltage, double rightVoltage) { 
		batteryVoltage = this.monitorBatteryVoltage();
		driveFrontLeft.set(ControlMode.PercentOutput, leftVoltage/batteryVoltage);
		driveBackLeft.follow(driveFrontLeft);
		driveFrontRight.set(ControlMode.PercentOutput,-1*rightVoltage/batteryVoltage);
		driveBackRight.follow(driveFrontRight);
	}
	
	public double motorVoltageRead(boolean left) {
		if(left) {
			return driveFrontLeft.getMotorOutputPercent()*this.monitorBatteryVoltage();
		}else {
			return driveFrontRight.getMotorOutputPercent()*this.monitorBatteryVoltage();
		}
	}
	
	public void motorReset() {
		driveFrontLeft.set(ControlMode.PercentOutput, 0);
		driveBackLeft.set(ControlMode.PercentOutput, 0);
		driveFrontRight.set(ControlMode.PercentOutput, 0);
		driveBackRight.set(ControlMode.PercentOutput, 0);
	}

	public double monitorBatteryVoltage() {
		return pdp.getVoltage();
	}

}
