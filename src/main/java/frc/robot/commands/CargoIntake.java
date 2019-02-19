/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIntake extends Command {
  private String targetZone;
  private double distance; 
  private boolean trippedWire;  //in zone 1
  private boolean trippedWire2; //in zone 2
  private boolean trippedWire3; //in zone 3

  public CargoIntake(String passedZone) {
    targetZone = passedZone;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(targetZone.equals("GroundIntaketo2")){
      Robot.getRollersSubsystem().lowerRollerArm();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    distance = Robot.getRollersSubsystem().getDistance();
    trippedWire = Robot.getRollersSubsystem().getLaserTripWire1();
    trippedWire2 = Robot.getRollersSubsystem().getLaserTripWire2();
    trippedWire3 = Robot.getRollersSubsystem().getLaserTripWire3();
    if(targetZone.equals("LoadingStation")) {
     // Robot.getRollersSubsystem().startIntakeBallFromLoadingStation();
    
     if(trippedWire3 == false ) {
        Robot.getRollersSubsystem().startIntakeBallFromLoadingStation();
      } else if(trippedWire3 == true) {
        Robot.getRollersSubsystem().holdBallInPlace();
        Robot.getLEDSubsystem().we_got_it();
      }

      // if(trippedWire3 == false && trippedWire2 == false && trippedWire == false) {
      //   Robot.getRollersSubsystem().startIntakeBallFromLoadingStation();
      // } else if(trippedWire3 == true  && trippedWire2 == false && trippedWire == false) {
      //   Robot.getRollersSubsystem().holdBallInPlace();
      //   Robot.getLEDSubsystem().we_got_it();
      // }
    }
    if(targetZone.equals("GroundIntakeTo3")){
      // Robot.getRollersSubsystem().lowerRollerArm();
      // if( trippedWire3 == false && trippedWire2 == false && trippedWire == false) { 
      //   Robot.getRollersSubsystem().startIntakeBallFromGround();
      // } else if(trippedWire3 == false && trippedWire2 == true  || trippedWire == true){
      //   Robot.getRollersSubsystem().moveBallFromZone1to3();
      //   Robot.getRollersSubsystem().raiseRollerArm();
      // } else if(trippedWire2 == false && trippedWire3 == true  && trippedWire == false){
      //   Robot.getRollersSubsystem().holdBallInPlace();
      //   Robot.getLEDSubsystem().we_got_it();
      // }
    }
   if(targetZone.equals("GroundIntaketo2")){
      Robot.getRollersSubsystem().startIntakeBallFromGround();
      // if(trippedWire2 == false && trippedWire3 == false  && trippedWire == false){
      //   Robot.getRollersSubsystem().startIntakeBallFromGround();
      // } else if(trippedWire == true && trippedWire2 == false && trippedWire3 == false){
      //   Robot.getRollersSubsystem().moveBallFromZone1to2();
      //   Robot.getRollersSubsystem().raiseRollerArm(); 
      // } else if (trippedWire2 == true && trippedWire3 == false  && trippedWire == false){
      //   Robot.getRollersSubsystem().holdBallInPlace();
      //   Robot.getLEDSubsystem().we_got_it();
      // }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getRollersSubsystem().holdBallInPlace();
    Robot.getRollersSubsystem().raiseRollerArm();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
