
package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArcadeSpeedMod;
import frc.robot.commands.AutoHatchCollection;
import frc.robot.commands.CargoIntake;
import frc.robot.commands.CargoShoot;
import frc.robot.commands.ClimbMovement;
import frc.robot.commands.DefenseMode;
import frc.robot.commands.HatchCollection;
import frc.robot.commands.HatchExtend;
import frc.robot.commands.HatchShoot;
import frc.robot.commands.MoveCargo;
import frc.robot.commands.MoveIntakeArm;
import frc.robot.commands.ResetRobot;
import frc.robot.commands.Switch;
import frc.robot.commands.TeleVision;
import frc.robot.commands.ToggleMotors;
import frc.robot.commands.Vision;

public class OI {
  private static final int LEFT_Y_AXIS = 1;
  private static final int LEFT_X_AXIS = 0;
  private static final int RIGHT_Y_AXIS = 3;
  private static final int RIGHT_X_AXIS = 2;
  private static final int BUTTON_A = 2;
  private static final int BUTTON_B = 3;
  private static final int BUTTON_X = 1;
  private static final int BUTTON_Y = 4;
  private static final int RIGHT_TRIG = 8;
  private static final int LEFT_TRIG = 7;

  private static final Joystick JOY_1 = new Joystick(0);
  private static final JoystickButton A_DRIVE = new JoystickButton(JOY_1, BUTTON_A);
  private static final JoystickButton B_DRIVE = new JoystickButton(JOY_1, BUTTON_B);
  private static final JoystickButton Y_DRIVE = new JoystickButton(JOY_1, BUTTON_Y);
  private static final JoystickButton X_DRIVE = new JoystickButton(JOY_1, BUTTON_X);
  private static final AxisButton DRIVE_Y_LEFT = new AxisButton(JOY_1, LEFT_Y_AXIS);
  private static final AxisButton DRIVE_X_LEFT = new AxisButton(JOY_1, LEFT_X_AXIS);
  private static final AxisButton DRIVE_Y_RIGHT = new AxisButton(JOY_1, RIGHT_Y_AXIS);
  private static final AxisButton DRIVE_X_RIGHT = new AxisButton(JOY_1, RIGHT_X_AXIS);
  private static final JoystickButton DRIVE_R_TRIGGER = new JoystickButton(JOY_1, RIGHT_TRIG);
  private static final DPadButton DRIVE_UP = new DPadButton(JOY_1, 0);
  private static final DPadButton DRIVE_RIGHT = new DPadButton(JOY_1, 90);
  private static final DPadButton DRIVE_DOWN = new DPadButton(JOY_1, 180);
  private static final DPadButton DRIVE_LEFT = new DPadButton(JOY_1, 270);
  private static final JoystickButton DRIVE_R_BUMP = new JoystickButton(JOY_1, 5);
  private static final JoystickButton DRIVE_L_BUMP = new JoystickButton(JOY_1, 6);
  //private static final ComboButton TRIGS = new ComboButton(JOY_1, 7, 8);

  private static final Joystick JOY_2 = new Joystick(1);
  private static final JoystickButton A_MANIP = new JoystickButton(JOY_2, BUTTON_A);
  private static final JoystickButton B_MANIP = new JoystickButton(JOY_2, BUTTON_B);
  private static final JoystickButton Y_MANIP = new JoystickButton(JOY_2, BUTTON_Y);
  private static final JoystickButton X_MANIP = new JoystickButton(JOY_2, BUTTON_X);
  private static final AxisButton MANIP_Y_LEFT = new AxisButton(JOY_2, LEFT_Y_AXIS);
  private static final AxisButton MANIP_X_LEFT = new AxisButton(JOY_2, LEFT_X_AXIS);
  private static final AxisButton MANIP_Y_RIGHT = new AxisButton(JOY_2, RIGHT_Y_AXIS);
  private static final AxisButton MANIP_X_RIGHT = new AxisButton(JOY_2, RIGHT_X_AXIS);
  private static final DPadButton MANIP_UP = new DPadButton(JOY_2, 0);
  private static final DPadButton MANIP_RIGHT = new DPadButton(JOY_2, 90);
  private static final DPadButton MANIP_DOWN = new DPadButton(JOY_2, 180);
  private static final DPadButton MANIP_LEFT = new DPadButton(JOY_2, 270);
  private static final JoystickButton MANIP_L_BUMP = new JoystickButton(JOY_2, 5);
  private static final JoystickButton MANIP_R_BUMP = new JoystickButton(JOY_2, 6);
  private static final JoystickButton MANIP_L_STICK_BTN = new JoystickButton(JOY_2, 11);
  private static final JoystickButton MANIP_BACK_BTN = new JoystickButton(JOY_2, 9);
  private static final JoystickButton MANIP_START_BTN = new JoystickButton(JOY_2, 10);

  public OI(){
     //if(!JOY_1.getName().equals("")|| DriverStation.getInstance().getMatchType() != MatchType.None){
        // DRIVE_L_BUMP.whileHeld(new TeleVision());
        DRIVE_L_BUMP.whileHeld(new TeleVision());
        DRIVE_Y_LEFT.whileHeld(new ArcadeDrive());
        DRIVE_X_RIGHT.whileHeld(new ArcadeDrive());
       
        DRIVE_R_BUMP.toggleWhenPressed(new ArcadeSpeedMod());
        
        DRIVE_R_TRIGGER.whileHeld(new DefenseMode());


      //}
      //if(!JOY_2.getName().equals("")|| DriverStation.getInstance().getMatchType() != MatchType.None){
      //Y_MANIP.whenPressed(new ResetRobot());

      //hatch
        Y_MANIP.toggleWhenPressed(new HatchShoot());
        X_MANIP.toggleWhenPressed(new MoveIntakeArm());
        A_MANIP.whenPressed(new HatchExtend("OutOfPerimeter"));
        B_MANIP.whenPressed(new HatchExtend("InsidePerimeter"));
        MANIP_R_BUMP.whenPressed(new HatchCollection("close"));
        MANIP_L_STICK_BTN.whenPressed(new HatchCollection("open"));
        MANIP_BACK_BTN.whileHeld(new AutoHatchCollection(false));
      //cargo

        MANIP_UP.whileHeld(new CargoShoot("Zone3"));
        MANIP_LEFT.whileHeld(new CargoShoot("Zone2"));
        MANIP_RIGHT.whileHeld(new CargoShoot("Zone2"));
        MANIP_Y_RIGHT.whileHeld(new MoveCargo());
        MANIP_DOWN.whileHeld(new CargoIntake("GroundIntaketo2"));
        MANIP_L_BUMP.whileHeld(new CargoIntake("LoadingStation"));
       // MANIP_START_BTN.toggleWhenPressed(new ToggleMotors());
     //}
//
  }

  public double getDriveLeftY() {
    return JOY_1.getRawAxis(LEFT_Y_AXIS); 
	}

	public double getDriveRightY() {
		return JOY_1.getRawAxis(RIGHT_Y_AXIS);
	}

	public double getDriveLeftX() {
		return JOY_1.getRawAxis(LEFT_X_AXIS);
	}

	public double getDriveRightX() {
    return JOY_1.getRawAxis(RIGHT_X_AXIS);
	}

	public double getManipLeftY() {
		return JOY_2.getRawAxis(LEFT_Y_AXIS);
  }
    
  public double getManipRightY() {
		return JOY_2.getRawAxis(RIGHT_Y_AXIS);
	}

	public int getManipPov() {
		return JOY_2.getPOV();
  }
    
  public int getDrivePov(){
      return JOY_1.getPOV();
  }
}
