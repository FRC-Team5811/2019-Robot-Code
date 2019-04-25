
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
  static public double storedAngProfile = 0;
  public static double batteryVoltage;
  // private double visionCorrectionLeft = 0;
  // private double visionCorrectionRight = 0;
  private double visionCorrectionTurn = 0;

  private double leftSpeed = 0;
  private double rightSpeed = 0;

  private boolean defenseMode = false;

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

  public void setVisionCorrection(double visionCorrectionTurn) {
    this.visionCorrectionTurn = visionCorrectionTurn;
  }
  
  public void arcadeDrive(double turn, double throttle) {
    //simulates car-like driving
		// if (throttle <= -.02) {
		// 	turn = -turn;
    // }
    
    if (visionCorrectionTurn == 0) {  // drive normally if vision correction is turned off
      leftSpeed = ((arcadeSpeedMod*throttle) + (arcadeTurnMod * turn)); //changed to .92 for practice
      rightSpeed = ((arcadeSpeedMod*-throttle) + (arcadeTurnMod * turn));
      //TODO modify left/right speed based on vision correction
      //leftSpeed = ((arcadeSpeedMod*throttle) + (visionCorrectionLeft)); //changed to .92 for practice
      //rightSpeed = ((arcadeSpeedMod*-throttle) + (visionCorrectionRight));
    } else {  // substitute vision correction for turn 
      if (throttle >= 0) {
        leftSpeed = ((arcadeSpeedMod*throttle) + (throttle * arcadeTurnMod * visionCorrectionTurn)); //changed to .92 for practice
        rightSpeed = ((arcadeSpeedMod*-throttle) + (throttle * arcadeTurnMod * visionCorrectionTurn));
      } else {
        leftSpeed = ((arcadeSpeedMod*throttle) - (throttle * arcadeTurnMod * visionCorrectionTurn)); //changed to .92 for practice
        rightSpeed = ((arcadeSpeedMod*-throttle) - (throttle * arcadeTurnMod * visionCorrectionTurn));
      }
      
    }
    
    driveFrontLeft.set(ControlMode.PercentOutput, leftSpeed);
    driveBackLeft.follow(driveFrontLeft);
    driveFrontRight.set(ControlMode.PercentOutput, rightSpeed);
    driveBackRight.follow(driveFrontRight);
    storedAngProfile = 0;
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
    navX.resetDisplacement();
  }

  public double getNavXRoll(){
    return navX.getRoll();
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
  
  public void enableDefenseMode(){
    if(!defenseMode){
      defenseMode = true;
      int peak = 10;
      int cont = 10;
      int dur = 100;
      double openLoopSec = .15;

      driveFrontRight.configPeakCurrentDuration(dur);
      driveFrontRight.configPeakCurrentLimit(peak);
      driveFrontRight.configContinuousCurrentLimit(cont);
      driveFrontRight.enableCurrentLimit(true);
      
      driveFrontRight.configOpenloopRamp(openLoopSec);
      driveFrontLeft.configOpenloopRamp(openLoopSec);

      driveFrontLeft.configPeakCurrentDuration(dur);
      driveFrontLeft.configPeakCurrentLimit(peak);
      driveFrontLeft.configContinuousCurrentLimit(cont);
      driveFrontLeft.enableCurrentLimit(true);

      //talon.ConfigPeakCurrentLimit(35, 10); /* 35 A /
      //talon.ConfigPeakCurrentDuration(200, 10); / 200ms /
      //talon.ConfigContinuousCurrentLimit(30, 10); / 30A /
      //talon.EnableCurrentLimit(true); / turn it on */

      System.out.println("defense on");
    }

  }

  public void disableDefenseMode() {
        //talon.EnableCurrentLimit(false); / turn it off */
        driveFrontLeft.enableCurrentLimit(false);
        driveFrontRight.enableCurrentLimit(false);
        driveFrontRight.configOpenloopRamp(0);
        driveFrontLeft.configOpenloopRamp(0);
        defenseMode = false;
        System.out.println("defense off");
  }

}
