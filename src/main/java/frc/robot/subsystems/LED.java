/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
package org.usfirst.frc.team5811.robot.subsystems;
import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.RobotMap;
import edu.wpi.first.wpilibj.I2C;

public class LED extends Subsystem {
  I2C LEDArduino = RobotMap.arduino;
	byte[] toSend = new byte[1];

  @Override
  protected void initDefaultCommand() {
  }

  public void color() {
    toSend[0] = 3;
		//System.out.println("Sending RED");
		if (LEDArduino.writeBulk(toSend)) {
			//System.out.println("didnt send");
		} else {
			//System.out.println("sent");
    }
    }
    
  public void off() {
    toSend[0] = 10;
    //System.out.println("Sending OFF");
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("didnt send");
    } else {
      //System.out.println("sent");
    }
    }
  
  public void patternTest() {
    toSend[0] = 6;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
      //System.out.println("Sent");
    }
    }
  
  public void colorInward() {
    toSend[0] = 4;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
      //System.out.println("Sent");
    }
    }
  
 public void flash() {
    toSend[0] = 5;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
      //System.out.println("Sent");
    }
    }
  public void shooting() {
    toSend[0] = 6;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
     //System.out.println("Sent");
    }
    }
  public void autoColor() {
    toSend[0] = 7;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
      //System.out.println("Sent");
    }
    }
    public void blue() {
    toSend[0] = 8;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
      //System.out.println("Sent");
    }
    }
  public void orange() {
    toSend[0] = 9;
    if (LEDArduino.writeBulk(toSend)) {
      //System.out.println("Didn't send");
    } else {
      //System.out.println("Sent");
    }
    }
  public void light_blue() {
    toSend[0] = 11;
    if (LEDArduino.writeBulk(toSend)) {
     //System.out.println("Didn't   send");
    } else {
      //System.out.println("Sent");
    }
    }
  }
}
