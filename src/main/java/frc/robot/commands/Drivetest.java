
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drivetest extends Command {
  public Drivetest() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.dt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.dt.driveFrontLeft.set (.5);
    Robot.dt.driveBackLeft.set (.5);
    Robot.dt.driveFrontRight.set (-.5);
    Robot.dt.driveBackRight.set (-.5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.dt.driveFrontLeft.set (0);
    Robot.dt.driveBackLeft.set (0);
    Robot.dt.driveFrontRight.set (-0);
    Robot.dt.driveBackRight.set (-0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
