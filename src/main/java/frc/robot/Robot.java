package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Rollers;

public class Robot extends TimedRobot {
  public static OI oi;
  public static Drivetrain dt;
  private static Rollers rollers;
  private static Hatch hatch;

  Command prototype_final_auto;
  SendableChooser<Command> prototype_chooser = new SendableChooser<>();
 
  @Override
  public void robotInit() {
    rollers = new Rollers();
    dt = new Drivetrain();
    oi = new OI();
    RobotMap.u1.setAutomaticMode(true);  //DON"T DELETE
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
    prototype_final_auto = prototype_chooser.getSelected();

    // schedule the autonomous command (example)
    if (prototype_final_auto != null) {
      prototype_final_auto.start();
    }
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
    if (prototype_final_auto != null) {
      prototype_final_auto.cancel();
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
}
