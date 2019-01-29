
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Rollers extends Subsystem {

  DoubleSolenoid intakeRoller = RobotMap.intakeRoller;

  private Spark rollerIntake;
  private Spark rollers124;
  private Spark roller3;

  Ultrasonic distanceSensor;

  public Rollers(){
    rollerIntake = RobotMap.motor4;
    rollers124 = RobotMap.motor5;
    roller3 = RobotMap.motor6;
    distanceSensor = RobotMap.u1;
  }
  
  public double getDistance(){
    return distanceSensor.getRangeInches();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  /**
   * Sets the solenoid to push the piston out and thus lower the intake arm
   */
  public void lowerRollerArm(){
    intakeRoller.set(DoubleSolenoid.Value.kForward);
  }
/**
 * Sets the solenoid to pull the piston in and thus raise the intake arm
 */
  public void raiseRollerArm(){
    intakeRoller.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Sets the motor speed of the Roller Intake 
   * 
   * @param speed power to give motor.  Range -1 to 1.
   */
  public void setRollerIntake(float speed) {
    rollerIntake.set(speed);
  }

  /**
   * Sets the motor speed of the Rollers 124
   * @param speed power to give motor. Range -1 to 1
   */
  public void setRollers124(float speed){
    rollers124.set(speed);
  }
  /**
  * Sets the motor speed of the Roller 3
  * @param speed power to give motor. Range -1 to 1
  */
  public void setRoller3(float speed){
    roller3.set(speed);
  }
  /**
   *  Sets roller motors in configuration to intake ball from ground
   */
  public  void startIntakeBallFromGround(){
    setRollerIntake(1);
    setRollers124(1);
  }
  /**
   *  Sets roller motors in configuration to intake ball from loading station
   */
  public  void startIntakeBallFromLoadingStation(){
    setRoller3(-1);
    setRollers124(1);
  }
  /**
   *  Sets roller motors in configuration to move ball from zone 1 to 2
   */
  public  void moveBallFromZone1to2(){
    setRoller3(1);
    setRollers124(1);
  }
  /**
   * Sets roller motors in configuration to move ball from zone 1 to 3
   */
  public  void moveBallFromZone1to3(){
    setRoller3(1);
    setRollers124(1);
  }
  /**
   * Sets roller motors to turn off in order hold ball in place
   */
  public  void holdBallInPlace(){
    setRoller3(0);
    setRollers124(0);
    setRollerIntake(0);
  }
  /**
   * Set roller motors in configuration to outake ball from Zone 2
   */
  public  void outakeBallFromZone2(){
    setRollers124(1);
    setRoller3(-1);
  }
    /**
   * Set roller motors in configuration to outake ball from Zone 3
   */
  public  void outakeBallFromZone3(){
    setRollers124(-1);
    setRoller3(1);
  }
  /**
   * set roller motors in configuration to move ball from zone 3 to zone 2
   */
  public  void moveBallFromZone3to2() {
    setRollers124(-1);
    setRoller3(-1);
  }
}
