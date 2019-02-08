
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
  
  public static TalonSRX driveFrontLeft, driveFrontRight;
  public static Victor driveBackRight, driveBackLeft;
  public static Compressor cp;
  public static PowerDistributionPanel pdp;
  public static AHRS navX = RobotMap.navx;

  public Drivetrain(){

    driveFrontLeft = RobotMap.motor1;
    driveBackLeft = RobotMap.motor2;
    driveFrontRight = RobotMap.motor3;
    driveBackRight = RobotMap.motor4;

    cp = RobotMap.COMPRESSOR;

    pdp = RobotMap.PDP;
  }
  
  public void arcadeDrive(double turn, double throttle) {
    //simulates car-like driving
		if (throttle >= .02) {
			turn = -turn;
		}
		if (throttle <= .02) {
			turn = turn;
    }
    
		driveFrontLeft.set(ControlMode.PercentOutput, -throttle + turn);
		driveBackLeft.set(-throttle + turn);
		driveFrontRight.set(ControlMode.PercentOutput, throttle + turn);
    driveBackRight.set(throttle + turn);
    
  }

  public float grabValues() {
		return (float) navX.getAngle();
	}

  @Override
  public void initDefaultCommand() {
    
  }

  public void reset() {
		navX.reset();
	}

}
