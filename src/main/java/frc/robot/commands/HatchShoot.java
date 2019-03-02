/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchShoot extends Command {

  double EncValueCurrent = Robot.getDtSubsystem().getLeftEncMeters() + Robot.getDtSubsystem().getRightEncMeters();
  double EncValueStored = Robot.getDtSubsystem().getLeftEncMeters() + Robot.getDtSubsystem().getRightEncMeters();
  private static final int EXTRA_DISTANCE = 1; //CHANGE THIS WITH ACTUAL ROBOT
  int counter = 0;

  public HatchShoot() {
    // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.getHatchSubsystem());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.getHatchSubsystem().openBeak();
    Robot.getHatchSubsystem().outakeHatch();
    Robot.getLEDSubsystem().shooting();
    counter = 0;
    
    while (counter < 10){
      counter ++;
    }
    Robot.getHatchSubsystem().intakeHatchArms();
   // EncValueStored = Robot.getDtSubsystem().getLeftEncMeters() + Robot.getDtSubsystem().getRightEncMeters();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*
    EncValueCurrent = (Robot.getDtSubsystem().getLeftEncMeters() + Robot.getDtSubsystem().getRightEncMeters());
    if(EncValueStored + EXTRA_DISTANCE <= EncValueCurrent ){
      end();
    }
    */
    /*
    
    */
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
