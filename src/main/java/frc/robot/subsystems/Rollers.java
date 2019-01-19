
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Rollers extends Subsystem {
  AnalogInput laser = RobotMap.d1;
  Ultrasonic ultrasonic = RobotMap.u1;
  
  public void readUltra(){
    //System.out.println((int)laser.getVoltage());
    System.out.println(ultrasonic.getRangeInches());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
