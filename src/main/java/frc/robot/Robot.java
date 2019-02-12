package frc.robot;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.CargoShoot;
import frc.robot.commands.ProfileDrive;
import frc.robot.commands.ResetRobot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Rollers;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  public static OI oi;
  public static Drivetrain dt;
  private static Rollers rollers;
  private static Hatch hatch;
  public static UsbCamera cam1, cam2;
  public static VideoSink server;
  public static MjpegServer mjpegServer1;

  Command autonomousCommand;
  SendableChooser<Command> prototype_chooser = new SendableChooser<>();
  //SendableChooser autoChooser;

  @Override
  public void robotInit() {
    rollers = new Rollers();
    dt = new Drivetrain();
    oi = new OI();
    RobotMap.u1.setAutomaticMode(true);  //DON"T DELETE

    
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    server = CameraServer.getInstance().getServer();
    

    // autoChooser = new SendableChooser();
    // autoChooser.addDefault ("default ToM auto", new ArcadeDrive());
    // autoChooser.addObject("TOm Auto", new ResetRobot());
    // SmartDashboard.putData("Auto Mode", autoChooser);
  }

 /**
  * This is a function comment
  */
  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = new ProfileDrive();
		autonomousCommand.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    RobotMap.COMPRESSOR.clearAllPCMStickyFaults();
    RobotMap.COMPRESSOR.setClosedLoopControl(true);
    RobotMap.PDP.clearStickyFaults();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }

  public static Rollers getRollersSubsystem(){
    return rollers;
  }

  public static Hatch getHatchSubsystem(){
    return hatch;
  }

  public static Drivetrain getDtSubsystem(){
    return dt;
  }
}
