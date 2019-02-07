/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

 // DoubleSolenoid frontClimber = RobotMap.frontClimber;
  //DoubleSolenoid backClimber = RobotMap.backClimber;
  /**
  * Raises front pistons
  */
  public void raiseFrontClimber(){
    //frontClimber.set(DoubleSolenoid.Value.kForward);
  }
  /**
  * Raises back pistons
  */
  public void raiseBackClimber(){
    //backClimber.set(DoubleSolenoid.Value.kForward);
  }
  /**
   * Retracts front pistons
   */
  public void lowerFrontClimber(){
    //frontClimber.set(DoubleSolenoid.Value.kReverse);
  }
  /**
   * Retracts back pistons
   */
  public void lowerBackClimber(){
   // backClimber.set(DoubleSolenoid.Value.kReverse);
  } 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
