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
  boolean done = false;

  public HatchShoot() {
    // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      counter = 0;
      requires(Robot.getHatchSubsystem());
      setInterruptible(false);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.getHatchSubsystem().intakeHatchArms();
    Robot.getHatchSubsystem().openBeak();
    Robot.getHatchSubsystem().outakeHatch();
    Robot.getLEDSubsystem().shooting();
    done = false;

   // EncValueStored = Robot.getDtSubsystem().getLeftEncMeters() + Robot.getDtSubsystem().getRightEncMeters();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    counter++;
    if(counter < 20){

    }else{
      Robot.getHatchSubsystem().intakeHatchArms();
      done = true;
    }

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
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    counter = 0;
    Robot.getHatchSubsystem().intakeHatchArms();
    System.out.println("Im ended");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
