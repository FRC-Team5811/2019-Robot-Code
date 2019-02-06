/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIntake extends Command {
  private String targetZone;
  private double distance; 

  public CargoIntake(String passedZone) {
    targetZone = passedZone;
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
    distance = Robot.getRollersSubsystem().getDistance();
    if(targetZone.equals("LoadingStation")) {
      if(distance >  Robot.getRollersSubsystem().getZone3HB()) {
        Robot.getRollersSubsystem().startIntakeBallFromLoadingStation();
      } else if(distance < Robot.getRollersSubsystem().getZone3HT()) {
        Robot.getRollersSubsystem().holdBallInPlace();
      }
    }
    if(targetZone.equals("GroundIntakeTo3")){
      Robot.getRollersSubsystem().lowerRollerArm();
      if(distance > Robot.getRollersSubsystem().getZone3HT()){
        Robot.getRollersSubsystem().startIntakeBallFromGround();
      } else if(distance < Robot.getRollersSubsystem().getZone1HT()){
      Robot.getRollersSubsystem().moveBallFromZone1to3();
      Robot.getRollersSubsystem().raiseRollerArm();
      } else if(distance < Robot.getRollersSubsystem().getZone3HT() && distance > Robot.getRollersSubsystem().getZone3HB()){
        Robot.getRollersSubsystem().holdBallInPlace();
      }
    }
    if(targetZone.equals("GroundIntaketo2")){
      Robot.getRollersSubsystem().lowerRollerArm();
      if(distance > Robot.getRollersSubsystem().getZone3HT()){
        Robot.getRollersSubsystem().startIntakeBallFromGround();
      } else if(distance < Robot.getRollersSubsystem().getZone1HT()){
      Robot.getRollersSubsystem().moveBallFromZone1to2();
      Robot.getRollersSubsystem().raiseRollerArm(); 
      } else if(distance < Robot.getRollersSubsystem().getZone2HT() && distance > Robot.getRollersSubsystem().getZone3HB()){
        Robot.getRollersSubsystem().holdBallInPlace();
      }
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
