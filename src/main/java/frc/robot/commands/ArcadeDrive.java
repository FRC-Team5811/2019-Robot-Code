package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDrive extends Command {

  public ArcadeDrive() {
    setInterruptible(false);
    requires(Robot.getDtSubsystem());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.getDtSubsystem().arcadeDrive(Robot.oi.getDriveRightX(), -Robot.oi.getDriveLeftY());
    if(Robot.getDtSubsystem().arcadeSpeedMod == 1){
      Robot.getLEDSubsystem().driving();
    } else {
      Robot.getLEDSubsystem().slow_mode();
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
    Robot.getDtSubsystem().motorReset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
