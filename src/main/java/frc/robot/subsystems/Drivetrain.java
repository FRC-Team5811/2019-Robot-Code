
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
  
  public static TalonSRX driveFrontRight, driveFrontLeft;
  public static VictorSPX driveBackRight, driveBackLeft;
  public static Compressor cp;
  public static PowerDistributionPanel pdp;
  public static AHRS navX = RobotMap.navx;

  public Drivetrain(){

    driveFrontRight = RobotMap.rightF;
    driveBackRight = RobotMap.rightB;
    driveFrontLeft = RobotMap.leftF;
    driveBackLeft = RobotMap.leftB;

    cp = RobotMap.COMPRESSOR;

    pdp = RobotMap.PDP;
  }
  
  public void arcadeDrive(double turn, double throttle) {
    //simulates car-like driving
		if (throttle >= .02) {
			turn = -turn;
    }
    
    driveFrontLeft.set(ControlMode.PercentOutput, throttle + turn);
    driveBackLeft.follow(driveFrontLeft);
    driveFrontRight.set(ControlMode.PercentOutput, -throttle + turn);
    driveBackRight.follow(driveFrontRight);
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
  
  public void motorReset() {
    driveFrontRight.set(ControlMode.PercentOutput, 0);
    driveBackRight.follow(driveFrontRight);
    driveFrontLeft.set(ControlMode.PercentOutput, 0);
    driveBackLeft.follow(driveFrontLeft);
	}

}
