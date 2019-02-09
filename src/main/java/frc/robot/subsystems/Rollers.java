
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Rollers extends Subsystem {

  private static final double Zone1HeightBottom = 0;
  private static final double Zone1HeightTop = 18.242;
  private static final double Zone2HeightBottom = 18.242;
  private static final double Zone2HeightTop = 28.117;
  private static final double Zone3HeightBottom = 28.117;
  private static final double Zone3HeightTop = 37.992;

  DoubleSolenoid intakeRoller = RobotMap.intakeRoller;

  private VictorSPX rollerIntake;
  private VictorSPX rollers124;
  private VictorSPX roller3;

  Ultrasonic distanceSensor;
  AnalogInput laserTripWire1;
  AnalogInput laserTripWire2;

  public Rollers(){
    rollerIntake = RobotMap.cargo1;
    //rollers124 = RobotMap.cargo2;
    roller3 = RobotMap.cargo3;
    distanceSensor = RobotMap.u1;
    laserTripWire1 = RobotMap.laser1;
    laserTripWire2 = RobotMap.laser2;
  }
  
  public double getZone1HB(){
    return Zone1HeightBottom;
  }

  public double getZone1HT(){
    return Zone1HeightTop;
  }

  public double getZone2HB(){
    return Zone2HeightBottom;
  }

  public double getZone2HT(){
    return Zone2HeightTop;
  }

  public double getZone3HB(){
    return Zone3HeightBottom;
  }

  public double getZone3HT(){
    return Zone3HeightTop;
  }

  public double getDistance(){
    return distanceSensor.getRangeInches();
  }

  public boolean getLaserTripWire1() {
    if(laserTripWire1.getVoltage() < 1){
      return false;
    } else {
      return true;
    }
  }

  public boolean getLaserTripWire2() {
    if(laserTripWire2.getVoltage() < 1){
      return false;
    } else {
      return true;
    }
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
    rollerIntake.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Sets the motor speed of the Rollers 124
   * @param speed power to give motor. Range -1 to 1
   */
  /*
  public void setRollers124(float speed){
    rollers124.set(ControlMode.PercentOutput, speed);
  }
  */
  /**
  * Sets the motor speed of the Roller 3
  * @param speed power to give motor. Range -1 to 1
  */
  public void setRoller3(float speed){
    roller3.set(ControlMode.PercentOutput, speed);
  }
  /**
   *  Sets roller motors in configuration to intake ball from ground
   */
  public  void startIntakeBallFromGround(){
    setRollerIntake(1);
    //setRollers124(1);
  }
  /**
   *  Sets roller motors in configuration to intake ball from loading station
   */
  public  void startIntakeBallFromLoadingStation(){
    setRoller3(-1);
   // setRollers124(1);
  }
  /**
   *  Sets roller motors in configuration to move ball from zone 1 to 2
   */
  public  void moveBallFromZone1to2(){
    setRoller3(1);
   // setRollers124(1);
  }
  /**
   * Sets roller motors in configuration to move ball from zone 1 to 3
   */
  public  void moveBallFromZone1to3(){
    setRoller3(1);
   // setRollers124(1);
  }
  /**
   * Sets roller motors to turn off in order hold ball in place
   */
  public  void holdBallInPlace(){
    setRoller3(0);
    //setRollers124(0);
    setRollerIntake(0);
  }
  /**
   * Set roller motors in configuration to outake ball from Zone 2
   */
  public  void outakeBallFromZone2(){
    //setRollers124(1);
    setRoller3(-1);
  }
    /**
   * Set roller motors in configuration to outake ball from Zone 3
   */
  public  void outakeBallFromZone3(){
   // setRollers124(-1);
    setRoller3(1);
  }
  /**
   * set roller motors in configuration to move ball from zone 3 to zone 2
   */
  public  void moveBallFromZone3to2() {
   // setRollers124(-1);
    setRoller3(-1);
  }
}
