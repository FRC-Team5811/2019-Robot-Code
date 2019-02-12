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

  double EncValueCurrent = Robot.getDtSubsystem().getLeftEnc() + Robot.getDtSubsystem().getRightEnc();
  double EncValueStored = Robot.getDtSubsystem().getLeftEnc() + Robot.getDtSubsystem().getRightEnc();
  private static final int EXTRA_DISTANCE = 23598; //CHANGE THIS WITH ACTUAL ROBOT

  public HatchShoot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.dt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.getHatchSubsystem().outakeHatch();
    EncValueStored = Robot.getDtSubsystem().getLeftEnc() + Robot.getDtSubsystem().getRightEnc();


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    EncValueCurrent = Robot.getDtSubsystem().getLeftEnc() + Robot.getDtSubsystem().getRightEnc();
    if(EncValueStored + EXTRA_DISTANCE >= EncValueCurrent ){
      end();
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getHatchSubsystem().intakeHatchArms();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
