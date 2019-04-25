/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbMovement extends Command {
  static int pressureCounter = 0;
  public ClimbMovement() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pressureCounter = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   // System.out.println(Robot.getClimberSubsystem().lifter1.getEncoder().getPosition());
//vacuum is in place of lifter 1 for testing
    // if(Robot.getClimberSubsystem().vacuum.getEncoder().getPosition() < Robot.getClimberSubsystem().maxHeight){
    //   Robot.getClimberSubsystem().vacuum.set(0.2+((Robot.getClimberSubsystem().maxHeight - Robot.getClimberSubsystem().vacuum.getEncoder().getPosition())/ Robot.getClimberSubsystem().maxHeight));
    // } else {
    //   Robot.getClimberSubsystem().vacuum.set(0.05);
    // }
    /*
    if(Robot.getClimberSubsystem().lifter1.getEncoder().getPosition() < Robot.getClimberSubsystem().maxHeight){
      Robot.getClimberSubsystem().lifter1.set(0.2+((Robot.getClimberSubsystem().maxHeight - Robot.getClimberSubsystem().lifter1.getEncoder().getPosition())/ Robot.getClimberSubsystem().maxHeight));
      Robot.getClimberSubsystem().lifter2.set(-0.2-((Robot.getClimberSubsystem().maxHeight - Robot.getClimberSubsystem().lifter1.getEncoder().getPosition())/ Robot.getClimberSubsystem().maxHeight));
    } else {
      pressureCounter++;
      Robot.getClimberSubsystem().lifter1.set(0.05);
      Robot.getClimberSubsystem().lifter2.set(-0.05);
      if(pressureCounter < 100){
        Robot.getClimberSubsystem().pressureMotor.set(-1);
      } else {
        Robot.getClimberSubsystem().pressureMotor.set(0);
      }
      Robot.getClimberSubsystem().vacuum.set(1);
    }
    
    */
    // if(pressureCounter < 100){
    //   Robot.getClimberSubsystem().pressureMotor.set(-1);
    // }
    // pressureCounter++;
   // Robot.getClimberSubsystem().vacuum.set(1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getClimberSubsystem().lowerClimbers();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
