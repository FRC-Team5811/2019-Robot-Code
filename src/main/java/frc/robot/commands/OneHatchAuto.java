/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.awt.dnd.DragSourceEvent;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
//import sun.java2d.cmm.ProfileActivator;

public class OneHatchAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public OneHatchAuto() {
   // requires(Robot.getDtSubsystem());
    //ProfileDrive path = new ProfileDrive("curvyboi");
    ProfileDrive straight = new ProfileDrive("str", 4.5);
    ProfileDrive backCurve = new ProfileDrive("back", 4.5);

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

   // addSequential(path);

    addSequential(new HatchExtend("OutOfPerimeter"));   //works best with just kp ang at 2 or 4.5
    addSequential(straight);
    addParallel(new HatchShoot());
    addSequential(backCurve);

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
