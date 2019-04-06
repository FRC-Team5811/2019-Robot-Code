package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class Vision extends Command {
  double offset;
  double base_speed;
  double lessening;
  double leftVoltage;
  double rightVoltage;
  double kpang, kpZip, kiang, kdang;
  double errorDerivative, previousError, dt; 
  boolean loading;
  boolean done;
  double counter;
  double accumulatedError, maxAccumulatedError; 
  double minVoltage, minPercentVoltage, voltageProportion;
  double minShort, maxShort;
  double pipeline;
  DigitalInput switchH = RobotMap.hatchSensor;
  DigitalInput switchH2 = RobotMap.hatchSensor2;
  /**
   * HEY when loading is true it means its loading, false = shooting
   */
  public static double map(double x, double in_min, double in_max, double out_min, double out_max){
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

  public Vision(boolean loading, double kpAng, double baseSpeed, double pipeline) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.loading = loading;
    this.kpang = kpAng;
    base_speed = baseSpeed;
    this.pipeline = pipeline;
    

    kiang = 0.0000;
    kdang = 0.00;
    kpZip = 1;
    dt = 0.0666;
    errorDerivative = previousError = 0;
    counter = 0;
    maxAccumulatedError = 100;

    minVoltage = 2.5;
    minPercentVoltage = minVoltage / base_speed;

    minShort = 17.8;
    maxShort = 45;
    //voltageProportion = (maxShort - minShort) / (1 - minPercentVoltage); 
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.setPipeline(this.pipeline);
    done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   // Robot.setLeftSelectMode(1.0);
    offset = Robot.getHorizAngle();
    //System.out.println(offset);
    accumulatedError += offset;
    errorDerivative =  (offset - previousError) /  dt;

    leftVoltage = base_speed - offset * this.kpang - accumulatedError*kiang - kdang * errorDerivative ;
    rightVoltage = base_speed + offset * this.kpang + accumulatedError*kiang + kdang * errorDerivative ;
    
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
    */
    if(Robot.getShortSide() > minShort){
      kpZip = map(Robot.getShortSide(), minShort, maxShort, minPercentVoltage, 1);
      
    } else {
      kpZip = 1;
    }
    
    Robot.getDtSubsystem().voltageDrive(leftVoltage * kpZip, rightVoltage* kpZip);
    if(!this.loading){
      if(Robot.getShortSide() > maxShort){
        done = true;
        Robot.getDtSubsystem().motorReset();
      }
    } else if (this.loading){
      if(!switchH.get() || !switchH2.get()){
        done = true;
        Robot.getDtSubsystem().motorReset();
      }
    }


    previousError = offset;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   // Robot.setLeftSelectMode(0.0);
    Robot.getDtSubsystem().motorReset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}