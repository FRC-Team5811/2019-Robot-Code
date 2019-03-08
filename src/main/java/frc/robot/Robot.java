package frc.robot;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.CargoShoot;
import frc.robot.commands.OneHatchAuto;
import frc.robot.commands.ProfileDrive;
import frc.robot.commands.ResetRobot;
import frc.robot.commands.TwoCargoHatch;
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
  
  Command autonomousCommand;
  SendableChooser<Command> prototype_chooser = new SendableChooser<>();
  SendableChooser autoChooser;

  @Override
  public void robotInit() {
    rollers = new Rollers();
    hatch = new Hatch();
    dt = new Drivetrain();
    oi = new OI();
    LED = new LED();
 
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    cam1.setFPS(20);
    cam2.setFPS(20);
    // cam1.setResolution(2400, 144);
    // cam1.setBrightness(20);
    server = CameraServer.getInstance().getServer();
    Robot.getRollersSubsystem().holdBallInPlace();
    Robot.dt.motorReset();
    Robot.hatch.moveHatchToIn();
    Robot.hatch.intakeHatchArms();
    Robot.rollers.raiseRollerArm();
    Robot.dt.resetNAVX();
    Robot.dt.resetEncoders();
    Robot.hatch.closeBeak();
    autoChooser = new SendableChooser();
    autoChooser.addDefault ("2 Hatch Right", new TwoCargoHatch());
    //autoChooser.addObject("2 Hatch Left", new TwoCargoHatchL());
    autoChooser.addObject("One Hatch Auto", new OneHatchAuto());
    SmartDashboard.putData("Auto Mode", autoChooser);
    /*
    SmartDashboard.putNumber("KPAng", 0.0);
    SmartDashboard.putNumber("KPAngVel", 0.0);
    SmartDashboard.putNumber("KPPos", 0.0);
    SmartDashboard.putNumber("KPVel", 0.0);
    SmartDashboard.putString("File Name", "");
    */
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
    
  }

  @Override
  public void disabledInit() {
    Robot.getLEDSubsystem().disabled();
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Angle", Robot.dt.grabAngleRadians());
    SmartDashboard.putNumber("L enc", Robot.dt.getLeftEncMeters());
    SmartDashboard.putNumber("R enc", Robot.dt.getRightEncMeters());
   // System.out.println(Robot.getDtSubsystem().grabAngleRadians());
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = new TwoCargoHatch();
    autonomousCommand.start();
    Robot.getRollersSubsystem().holdBallInPlace();
    // ProfileDrive.kPAng = SmartDashboard.getNumber("KPAng", 0.0);
    // ProfileDrive.kPAngVel = SmartDashboard.getNumber("KPAngVel", 0.0);
    // ProfileDrive.kPPos = SmartDashboard.getNumber("KPPos", 0.0);
    // ProfileDrive.kPVel = SmartDashboard.getNumber("KPVel", 0.0);
    //ProfileDrive.fileName = SmartDashboard.getString("File Name", "");
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
    SmartDashboard.putNumber("Ang Error", ProfileDrive.angError);
    SmartDashboard.putNumber("AngVel Error", ProfileDrive.angVelError);
    SmartDashboard.putNumber("Vel Error", ProfileDrive.velError);
    SmartDashboard.putNumber("Pos Error", ProfileDrive.posError);
   
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
   //System.out.println(Robot.getRollersSubsystem().getLaserTripWire1() + "\t" + Robot.getRollersSubsystem().getLaserTripWire2() + "\t" + Robot.getRollersSubsystem().getLaserTripWire3());
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

  public static LED getLEDSubsystem(){
    return LED;
  }

  public static double getAngle() {
    return SmartDashboard.getNumber("angle", 0.0);
  }

  public static double getTotalArea(){
    return SmartDashboard.getNumber("total_area", 0);
  }

  public static double getDist(){
    return SmartDashboard.getNumber("gap_distance", 0);
  }
}
