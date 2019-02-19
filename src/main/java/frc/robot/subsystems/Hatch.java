/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {

  DoubleSolenoid hatchPunch = RobotMap.hatchPunch;
  DoubleSolenoid hatchExtension = RobotMap.extendHatchMechanism;
  DoubleSolenoid beakMovement = RobotMap.beakMovement;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  /**
   * Sets solenoids to make the pistons extend to "punch" out the hatch cover
   */
  public void outakeHatch(){
    hatchPunch.set(DoubleSolenoid.Value.kForward);
  }
  /**
   * Sets solenoids to make the pistons retract from shooting
   */
  public void intakeHatchArms(){
    hatchPunch.set(DoubleSolenoid.Value.kReverse);
  }
  /**
   *  Sets solenoids to make the pistons extend the hatch system at the start of the match
   */
  public void moveHatchToOut(){
    hatchExtension.set(DoubleSolenoid.Value.kReverse);
  }
  /**
   *  Sets solenoids to make the pistons move the hatch system back into the storing location inside the frame perimeter
   */
  public void moveHatchToIn(){
    hatchExtension.set(DoubleSolenoid.Value.kForward);
  }
  /**
   * Opening the center beak to hold the hatch in place
   */
  public void openBeak(){
    beakMovement.set(DoubleSolenoid.Value.kReverse);
  }
  /**
   * Closing the center beak to release the hatch for shooting
   */
  public void closeBeak(){
    beakMovement.set(DoubleSolenoid.Value.kForward);
  }
}
