
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Rollers extends Subsystem {
  public Spark rollerIntake;
  public Spark rollers124;
  public Spark roller3;
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
}
