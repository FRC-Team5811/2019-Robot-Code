/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Camera extends Subsystem {
  UsbCamera camera = RobotMap.camera;
  UsbCamera camera2 = RobotMap.camera2;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void camOn() {
		camera = CameraServer.getInstance().startAutomaticCapture();
    camera2 = CameraServer.getInstance().startAutomaticCapture();
    
	}
}
