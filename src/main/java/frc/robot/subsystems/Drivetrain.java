
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
  
  public static Victor driveFrontLeft, driveBackLeft, driveFrontRight, driveBackRight;
  public static Compressor cp;
  public static PowerDistributionPanel pdp;

  public Drivetrain(){

    driveFrontLeft = RobotMap.motor0;
    driveBackLeft = RobotMap.motor1;
    driveFrontRight = RobotMap.motor2;
    driveBackRight = RobotMap.motor3;

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
    
		driveFrontLeft.set(-throttle + turn);
		driveBackLeft.set(-throttle + turn);
		driveFrontRight.set(throttle + turn);
    driveBackRight.set(throttle + turn);
    
  }
  @Override
  public void initDefaultCommand() {
    
  }

}
