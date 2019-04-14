/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import java.util.ArrayList;

public class EmpiricalPointTurn extends Command {

  double desiredAngle;
  boolean done; 
  double outputVolts;
  double angDiff;
  double baseVoltage = 6.0;
  double kPAng = 0.5;
  double currentAng;
  double storedAng = 0;

  public EmpiricalPointTurn(double d) {
    
    this.desiredAngle = d;
    // Use requires() here to declare subsystem dependencies
    requires(Robot.getDtSubsystem());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    storedAng = Robot.getDtSubsystem().grabAngleRadians();
    //this.desiredAngle += storedAng;
    System.out.println("\n" + this.desiredAngle + "\n");
    done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAng = Robot.getDtSubsystem().grabAngleRadians();
    
    angDiff = Math.abs(storedAng + this.desiredAngle - currentAng);
    if(this.desiredAngle >= 0){ //right
      if(currentAng < this.desiredAngle + storedAng){
        //Move more positive
        
        outputVolts = (baseVoltage + kPAng*angDiff);
        Robot.getDtSubsystem().voltageDrive(-outputVolts, outputVolts);
      }else{
        Robot.getDtSubsystem().motorReset();
        done = true;
      }
    } else { //left
      if(currentAng > this.desiredAngle + storedAng){
        //Move more negative
        outputVolts = (baseVoltage + kPAng*angDiff);
        Robot.getDtSubsystem().voltageDrive(outputVolts, -outputVolts);
      }else{
        Robot.getDtSubsystem().motorReset();
        done = true;
      }
    }
    
    // Robot.getDtSubsystem().voltageDrive( , );
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
