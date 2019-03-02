/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import java.util.ArrayList;

public class PointTurn extends Command {

  double angAccMax = 4.0; //rad/s/s
  double angVelMax = 4.0; //rad/s
  double moi = 20.0; // kg * m^2

  double kPAng = 0.0;
  double kPAngVel = 0.0;
  
  ArrayList<Double> voltages = new ArrayList<>();
  ArrayList<Double> angles = new ArrayList<>();
  ArrayList<Double> angVels = new ArrayList<>();
  
  double desiredAngle; 
  double ang = 0.0; double angVel = 0.0; double angAcc = 0.0; double volts = 0.0;
  double accelTime; 
  double time;
  double accelDistance;
  boolean done;

  static double wheelRadiusMeters = 0.0762; // m 
	static double wheelBaseWidth = 0.6096; // m   //this is the effective wheel base width empirically 4/3 that of the physical wheel base width (24in --> 32in)
	static double vIntercept = 0.67; //0.67 // V
	static double R = 0.09160305; // ohms
	static double kv = 46.51333;   // rad/s per V 
	static double kt = 0.0183969466;   // N*m per A
	static double g = 10.71; // gear reduction (g:1)
  static int nMotors = 4; //total number of motors
  static double dt = 0.02;
  static int index, i;

  public static double voltsForMotion(double velocity, double force) {
		return force*wheelRadiusMeters*R/(g*kt)/nMotors  //Torque (I*R) term
			   + velocity/wheelRadiusMeters*g/kv         //Speed  (V*kv) term
			   + vIntercept;							 //Friction term
  }
  
  /**
   * Angle in RADIANS
   */
  public PointTurn(double d) {
    this.desiredAngle = d;
    // Use requires() here to declare subsystem dependencies
    requires(Robot.getDtSubsystem());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    voltages.clear();
    angles.clear();
    angVels.clear();
    accelDistance = (Math.pow(angVelMax, 2))/(2*angAccMax);
    time = index = i = 0;
    done = false;
    if(this.desiredAngle < accelDistance * 2){ //Triangle
      accelTime = Math.sqrt(this.desiredAngle/angAccMax);
      while(time < accelTime){
        angAcc = angAccMax;
        angVel = angVel + (angAcc*dt);
        ang = ang + (angVel * dt);
        angles.add(ang);
        angVels.add(angVel);
        volts = voltsForMotion(angVel*(wheelBaseWidth/2), (moi*angAcc)/wheelBaseWidth);
        voltages.add(volts);
        System.out.println(ang);
        index++;
        time+=dt;
      }
      while(time < 2*accelTime){
        angAcc = angAccMax;
        angVel = angVel - (angAcc*dt);
        ang = ang + (angVel * dt);
        angles.add(ang);
        angVels.add(angVel);
        volts = voltsForMotion(angVel*(wheelBaseWidth/2), (moi*angAcc)/wheelBaseWidth);
        voltages.add(volts);
        System.out.println(ang);
        index++;
        time+=dt;
      }
    }else{
      System.out.println("Run triangle please");
    } 
  }
  double deltaAng;
	double prevAng = 0;
	public static double angError, angVelError;
	
	double outputLeftVoltage, outputRightVoltage;
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(i < index){
      deltaAng = Robot.getDtSubsystem().grabAngleRadians() - prevAng;

      angError = angles.get(i) - Robot.getDtSubsystem().grabAngleRadians();
			angVelError = angVels.get(i) - (deltaAng/dt);
			
			outputLeftVoltage = voltages.get(i) + kPAng*angError + kPAngVel*angVelError;
			outputRightVoltage = -voltages.get(i) - kPAng*angError - kPAngVel*angVelError;
      Robot.getDtSubsystem().voltageDrive(outputLeftVoltage, outputRightVoltage);
      prevAng = Robot.getDtSubsystem().grabAngleRadians();
      i++;
    }else {
      Robot.getDtSubsystem().motorReset();
    	done = true;
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {

  }
}
