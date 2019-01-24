/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoShoot extends Command {
  
  private String zone;
  public CargoShoot(String passedZone) {
    zone = passedZone;
    requires(Robot.rollers);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (zone.equals("Zone3")) {
      // if getUltrasonic() //yields zone3 
      //   Robot.roller3.set(1); //assumes -1 is CCW
      //   Robot.roller124.set(-1); //assumes 1 is CW
        
      // if getUltrasonic() //yields zone2 or less
      //   Robot.roller124.set(1);
      //   Robot.roller3.set(1);
    // } else if (zone.equals("Zone2")) {
    //   if (getUltrasonic()) {
    //     Robot.roller3(-1);
    //     Robot.roller124(1);
    //   }
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
