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
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoHatchCollection;
import frc.robot.commands.CargoShoot;
import frc.robot.commands.DoubleRocketAuto;
import frc.robot.commands.FeedBackTest;
import frc.robot.commands.OneHatchAuto;
import frc.robot.commands.ProfileDrive;
import frc.robot.commands.ResetRobot;
import frc.robot.commands.TwoCargoHatch;
import frc.robot.commands.TwoCargoHatchLeft;
import frc.robot.subsystems.Climber;
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
  public static Climber climber;
  private double vision_kpang;

  
  
  Command autonomousCommand;
  SendableChooser<Command> prototype_chooser = new SendableChooser<>();
  SendableChooser autoChooser;

 // private static NetworkTableInstance table;

  @Override
  public void robotInit() {
    rollers = new Rollers();
    hatch = new Hatch();
    dt = new Drivetrain();
    oi = new OI();
    LED = new LED();
    climber = new Climber();
 
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    cam1.setFPS(20);
    cam2.setFPS(20);
    // cam1.setResolution(2400, 144);
    cam1.setBrightness(50);
    cam1.setResolution(144, 81);
    cam2.setResolution(144, 81);
    //server = CameraServer.getInstance().getServer();
    Robot.getRollersSubsystem().holdBallInPlace();
    Robot.dt.motorReset();
    Robot.hatch.moveHatchToIn();
    Robot.hatch.intakeHatchArms();
    Robot.rollers.raiseRollerArm();
    Robot.dt.resetNAVX();
    Robot.dt.resetEncoders();
    Robot.hatch.closeBeak();
    Robot.climber.lowerClimbers();
    
    autoChooser = new SendableChooser();
    autoChooser.addDefault ("2 Hatch Right", new TwoCargoHatch());
    autoChooser.addObject("2 Hatch Left", new TwoCargoHatchLeft());
    autoChooser.addObject("One Hatch Auto Right", new OneHatchAuto(2));
    autoChooser.addObject("One Hatch Auto Left", new OneHatchAuto(1));
    autoChooser.addObject("Double Rocket Right", new DoubleRocketAuto());
    autoChooser.addObject("Test Auto", new FeedBackTest());
    SmartDashboard.putData("Auto Mode", autoChooser);

    //table = NetworkTableInstance.getDefault().getTable("limelight");

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
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    Robot.getLEDSubsystem().disabled();
    //setLimelightLeds(1.0);
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
    autonomousCommand = (Command) autoChooser.getSelected();
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
    dt.storedAngProfile = 0;
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
    setLimelightLeds(1.0);
    // climber.lifter1.getEncoder().setPosition(0);
    // climber.lifter2.getEncoder().setPosition(0);
    // climber.vacuum.getEncoder().setPosition(0);
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

  public static Climber getClimberSubsystem() {
    return climber;
  }
/*
  public static double getAngle() {
    return SmartDashboard.getNumber("angle", 0.0);
  }
*/
  public static double getTotalArea(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  public static double getHorizAngle() {
    return -NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public static double getShortSide(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort").getDouble(0);
  }

  public static void setLimelightLeds(double state){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(state);
  }

  public static void setPipeline(double pipeline){
    //0 = center
    //1 = left
    //2 = right
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setDouble(pipeline);
  }

  public static void compressorOn() {
    RobotMap.COMPRESSOR.setClosedLoopControl(true);
  }

  public static void compressorOff() {
    RobotMap.COMPRESSOR.setClosedLoopControl(false);
  }
  
  // public static double getTotalArea(){
  //   return SmartDashboard.getNumber("total_area", 0);
  // }

  // public static double getDist(){
  //   return SmartDashboard.getNumber("gap_distance", 0);
  // }
  // public static void setLeftSelectMode(double state) {
  //   SmartDashboard.putNumber("left_select_mode", state);
  // }
  // public static double getVisionKpang(double kpang) {
  //   return SmartDashboard.getNumber("vision_kpang", 0.0);
  // }
}
