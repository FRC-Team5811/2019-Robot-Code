/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoubleRocketAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DoubleRocketAuto() {
    addSequential(new HatchExtend("OutOfPerimeter"));
    addSequential(new ProfileDrive("backRocket", 6.0, 8.0));
    addSequential(new Pause(10));
    addSequential(new EmpiricalPointTurn(0.4));
    addSequential(new Pause(10));
    addSequential(new Vision(false, 0.1, 7, 2));
    addSequential(new HatchShoot());

    addSequential(new ProfileDrive("backUp", 6.0, 8.0));
    addSequential(new EmpiricalPointTurn(-0.4));
    addSequential(new ProfileDrive("RockToLoad", 6.0, 8.0));
    addSequential(new Pause(20));
    addSequential(new Vision(true, 0.05, 5, 0));
    addSequential(new AutoHatchCollection(true));
    addSequential(new ProfileDrive("backToRock",6.0, 8.0));
    addSequential(new EmpiricalPointTurn(1.5));
    addSequential(new Pause(20));
    addSequential(new Vision(false, 0.1, 6, 2));
    addSequential(new HatchShoot());
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

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
