/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoHatchCollection extends Command {
  DigitalInput hatchCollect = RobotMap.hatchSensor;
  DigitalInput hatchCollect2 = RobotMap.hatchSensor2;
  boolean done = false;
  private boolean auto;
  private double baseVoltage;
  
  public AutoHatchCollection(boolean auto) {
    this.auto = auto;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done = false;
    if(this.auto){
      baseVoltage = 0;
    }else{
      baseVoltage = 0;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!hatchCollect.get() || !hatchCollect2.get()){
      Robot.getHatchSubsystem().closeBeak();
      Robot.getLEDSubsystem().we_got_it();
      Robot.getDtSubsystem().storedAngProfile = Robot.getDtSubsystem().grabAngleRadians();
      done = true;
    }else{
      Robot.getDtSubsystem().voltageDrive(baseVoltage, baseVoltage);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
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
