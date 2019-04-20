/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public CANSparkMax vacuum = RobotMap.vacuum;
  public CANSparkMax lifter1 = RobotMap.lifter1;
  public CANSparkMax lifter2 = RobotMap.lifter2;
  public CANSparkMax pressureMotor = RobotMap.pressureMotor;

  public final static double maxHeight = 42;
  
  /**
  * Raises pistons
  */
  public void liftClimbers(){
      lifter1.set(1);
      lifter2.set(1);
    //put the arm down (hopefull with pneumatics)
  }

  public void vacOn(){
    vacuum.set(1);
  }
   /**
  * lower pistonsn
  */
  public void lowerClimbers(){
    vacuum.getEncoder().setPosition(0);
    vacuum.set(0);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
