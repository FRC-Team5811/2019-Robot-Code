package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class Vision extends Command {
  double offset;
  double base_speed;
  double leftVoltage;
  double rightVoltage;
  double kpang, kpZip, kiang;
  boolean loading;
  boolean done;
  double counter;
  double accumulatedError, maxAccumulatedError; 
  DigitalInput switchH = RobotMap.hatchSensor;
  /**
   * HEY when loading is true it means its loading, false = shooting
   */
  public Vision(boolean loading) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.loading = loading;
    kpang = 0.020;
    kiang = 0.0000;
    kpZip = 1;
    base_speed = 4;
    counter = 0;
    maxAccumulatedError = 100;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    offset = Robot.getAngle();
    accumulatedError += offset; 

    if(Math.abs(offset) < 3){
      leftVoltage = base_speed;
      rightVoltage = base_speed;
      done = true;
      Robot.getDtSubsystem().resetNAVX();
      Robot.getDtSubsystem().motorReset();
    } else {
      leftVoltage = base_speed - offset * kpang * 1.05 - accumulatedError*kiang;
      rightVoltage = base_speed + offset * kpang + accumulatedError*kiang;
    }
    /*
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
    /*
    if(Robot.getGap() > 100){
      kpZip = 0.8;
    } else {
      kpZip = 1;
    }
    */
    System.out.println(Robot.getGap());
    Robot.getDtSubsystem().voltageDrive(leftVoltage * kpZip, rightVoltage* kpZip);
    if(!this.loading){
      if(Robot.getGap() > 150){
        done = true;
        Robot.getDtSubsystem().motorReset();
      }
    } else if (this.loading){
      if(Robot.getGap() > 105){
        done = true;
        Robot.getDtSubsystem().resetNAVX();
        Robot.getDtSubsystem().motorReset();
      }
      if(!switchH.get()){
        done = true;
        Robot.getDtSubsystem().resetNAVX();
        Robot.getDtSubsystem().motorReset();
      }
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
    Robot.getDtSubsystem().motorReset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}