/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Vision extends Command {
  double offset;
  double base_speed;
  double leftVoltage;
  double rightVoltage;
  double kpang;

  public Vision() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    kpang = 0.09;
    base_speed = 0;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    offset = Robot.getOffset();

    leftVoltage = base_speed - offset * kpang;
    rightVoltage = base_speed + offset * kpang;

    System.out.print("angle: ");
    System.out.print(offset);
    System.out.print(" kpang: ");
    System.out.print(kpang);
    System.out.print(" base: ");
    System.out.print(base_speed);
    System.out.print(" Left: ");
    System.out.print(leftVoltage);
    System.out.print(" Right: ");
    System.out.println(rightVoltage);

    Robot.getDtSubsystem().voltageDrive(leftVoltage, rightVoltage);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getDtSubsystem().motorReset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
