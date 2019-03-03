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
import frc.robot.commands.OneHatchAuto;
import frc.robot.commands.ProfileDrive;
import frc.robot.commands.ResetRobot;
import frc.robot.commands.Vision;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.Rollers;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  public static OI oi;
  private static Drivetrain dt;
  private static Rollers rollers;
  private static Hatch hatch;
  private static LED LED;
  public static UsbCamera cam1, cam2;
  public static VideoSink server;
  public static MjpegServer mjpegServer1;

  public static double kpang;
  public static double base_speed;
  
  Command autonomousCommand;
  SendableChooser<Command> prototype_chooser = new SendableChooser<>();
  SendableChooser autoChooser;

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("kpang", 0.0);
    SmartDashboard.putNumber("base_speed", 0.0);
    rollers = new Rollers();
    hatch = new Hatch();
    dt = new Drivetrain();
    oi = new OI();
    LED = new LED();
    RobotMap.u1.setAutomaticMode(true);  //DON"T DELETE

    
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    cam1.setResolution(2400, 144);
    cam1.setBrightness(20);
    server = CameraServer.getInstance().getServer();
    Robot.getRollersSubsystem().holdBallInPlace();
    Robot.dt.motorReset();
    Robot.hatch.moveHatchToIn();
    Robot.hatch.intakeHatchArms();
    Robot.rollers.raiseRollerArm();
    Robot.dt.resetNAVX();
    Robot.dt.resetEncoders();
    Robot.hatch.closeBeak();
    // autoChooser = new SendableChooser();
    // autoChooser.addDefault ("default ToM auto", new ArcadeDrive());
    // autoChooser.addObject("TOm Auto", new ResetRobot());
    // SmartDashboard.putData("Auto Mode", autoChooser);

    SmartDashboard.putNumber("KPAng", 0.0);
    SmartDashboard.putNumber("KPAngVel", 0.0);
    SmartDashboard.putNumber("KPPos", 0.0);
    SmartDashboard.putNumber("KPVel", 0.0);
    SmartDashboard.putString("File Name", "");
    // SmartDashboard.putNumber("Ang Error", ProfileDrive.angError);
    // SmartDashboard.putNumber("AngVel Error", ProfileDrive.angVelError);
    // SmartDashboard.putNumber("Vel Error", ProfileDrive.velError);
    // SmartDashboard.putNumber("Pos Error", ProfileDrive.posError);
  }

 /**
  * This is a function comment
  */
  @Override
  public void robotPeriodic() {
    // System.out.println(dt.navx);
  }

  @Override
  public void disabledInit() {
    Robot.getLEDSubsystem().disabled();
    
  }

  @Override
  public void disabledPeriodic() {
    System.out.println(SmartDashboard.getNumber("offest", 0));
    SmartDashboard.putNumber("Angle", Robot.dt.grabAngleRadians());
    SmartDashboard.putNumber("L enc", Robot.dt.getLeftEncMeters());
    SmartDashboard.putNumber("R enc", Robot.dt.getRightEncMeters());
   // System.out.println(Robot.getDtSubsystem().grabAngleRadians());
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    // autonomousCommand = new Vision();
    // autonomousCommand.start();
    Robot.getRollersSubsystem().holdBallInPlace();
    // ProfileDrive.kPAng = SmartDashboard.getNumber("KPAng", 0.0);
    // ProfileDrive.kPAngVel = SmartDashboard.getNumber("KPAngVel", 0.0);
    // ProfileDrive.kPPos = SmartDashboard.getNumber("KPPos", 0.0);
    // ProfileDrive.kPVel = SmartDashboard.getNumber("KPVel", 0.0);
    // ProfileDrive.fileName = SmartDashboard.getString("File Name", "");
    // SmartDashboard.putNumber("Ang Error", ProfileDrive.angError);
    // SmartDashboard.putNumber("AngVel Error", ProfileDrive.angVelError);
    // SmartDashboard.putNumber("Vel Error", ProfileDrive.velError);
    // SmartDashboard.putNumber("Pos Error", ProfileDrive.posError);
    Robot.dt.motorReset();
    Robot.dt.resetNAVX();
    Robot.dt.resetEncoders();
    Robot.hatch.closeBeak();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Angle", Robot.dt.grabAngleRadians());
    SmartDashboard.putNumber("L enc", Robot.dt.getLeftEncMeters());
    SmartDashboard.putNumber("R enc", Robot.dt.getRightEncMeters());
    // SmartDashboard.putNumber("Ang Error", ProfileDrive.angError);
    // SmartDashboard.putNumber("AngVel Error", ProfileDrive.angVelError);
    // SmartDashboard.putNumber("Vel Error", ProfileDrive.velError);
    // SmartDashboard.putNumber("Pos Error", ProfileDrive.posError);
   
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
    Robot.getRollersSubsystem().holdBallInPlace();
    Robot.dt.motorReset();
    Robot.rollers.raiseRollerArm();


  }

  @Override 
  public void teleopPeriodic() {
   Scheduler.getInstance().run();
  // System.out.println(Robot.getRollersSubsystem().getLaserTripWire1());
  
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

  public static LED getLEDSubsystem(){
    return LED;
  }

  public static double getOffset() {
    return SmartDashboard.getNumber("angle", 0.0);
  }
}
