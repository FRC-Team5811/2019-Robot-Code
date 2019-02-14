/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoShoot extends Command {
  
  private String targetZone;
  private double distance; 
  private boolean trippedWire;  //in zone 2
  private boolean trippedWire2; //in zone 3

  public CargoShoot(String passedZone) {
    targetZone = passedZone;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    distance = Robot.getRollersSubsystem().getDistance();
    trippedWire = Robot.getRollersSubsystem().getLaserTripWire1();
    trippedWire2 = Robot.getRollersSubsystem().getLaserTripWire2();
    if (targetZone.equals("Zone3")) {
      Robot.getRollersSubsystem().outakeBallFromZone3();
      // if(distance < Robot.getRollersSubsystem().getZone2HT()){
      //   Robot.getRollersSubsystem().moveBallFromZone1to3();
      // } else if (distance > Robot.getRollersSubsystem().getZone2HT() && distance < Robot.getRollersSubsystem().getZone3HT() && trippedWire2 == true){ 
      //   Robot.getRollersSubsystem().outakeBallFromZone3();
      // } else if (distance > Robot.getRollersSubsystem().getZone3HT() && trippedWire2 == false && trippedWire == false){
      //   Robot.getRollersSubsystem().holdBallInPlace();
      // }
    }
    if (targetZone.equals("Zone2")){
      Robot.getRollersSubsystem().outakeBallFromZone2();
      // if (distance < Robot.getRollersSubsystem().getZone2HB()){
      //   Robot.getRollersSubsystem().moveBallFromZone1to2();
      // } else if (distance > Robot.getRollersSubsystem().getZone2HT() && distance < Robot.getRollersSubsystem().getZone3HT() && trippedWire2 == true){
      //   Robot.getRollersSubsystem().moveBallFromZone3to2();
      // } else if (distance > Robot.getRollersSubsystem().getZone3HB() && distance < Robot.getRollersSubsystem().getZone2HT() && trippedWire == true){
      //   Robot.getRollersSubsystem().outakeBallFromZone2();
      // } else if (distance > Robot.getRollersSubsystem().getZone3HT() && trippedWire2 == false && trippedWire == false){
      //   Robot.getRollersSubsystem().holdBallInPlace();
        
      }
    } 
 // }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getRollersSubsystem().holdBallInPlace();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
} 
