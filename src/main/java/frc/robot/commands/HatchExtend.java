/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchExtend extends Command {

 private String desiredLocation;

  public HatchExtend(String hatchLocation) {
    desiredLocation = hatchLocation;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.getHatchSubsystem());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (desiredLocation.equals("OutOfPerimeter")) {
      Robot.getHatchSubsystem().moveHatchToOut();
      Robot.compressorOn();

      
     } else if (desiredLocation.equals("InsidePerimeter")) {
      Robot.getHatchSubsystem().moveHatchToIn();
      Robot.compressorOff();
     }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {   
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
