
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
  Spark rollerIntake;
  Spark rollers124;
  Spark roller3;
  public Rollers(){
    rollerIntake = RobotMap.motor4;
    rollers124 = RobotMap.motor5;
    roller3 = RobotMap.motor6;
  }
  
  public void readUltra(){
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
