package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {
  private static final int LEFT_Y_AXIS = 1;
  private static final int LEFT_X_AXIS = 0;
  private static final int RIGHT_Y_AXIS = 3;
  private static final int RIGHT_X_AXIS = 2;
  private static final int BUTTON_A = 2;
  private static final int BUTTON_B = 3;
  private static final int BUTTON_X = 1;
  private static final int BUTTON_Y = 4;

  private static final Joystick JOY_1 = new Joystick(0);
  private static final JoystickButton A_DRIVE = new JoystickButton(JOY_1, BUTTON_A);
  private static final JoystickButton B_DRIVE = new JoystickButton(JOY_1, BUTTON_B);
  private static final JoystickButton Y_DRIVE = new JoystickButton(JOY_1, BUTTON_Y);
  private static final JoystickButton X_DRIVE = new JoystickButton(JOY_1, BUTTON_X);
  private static final AxisButton DRIVE_Y_LEFT = new AxisButton(JOY_1, LEFT_Y_AXIS);
  private static final AxisButton DRIVE_X_LEFT = new AxisButton(JOY_1, LEFT_X_AXIS);
  private static final AxisButton DRIVE_Y_RIGHT = new AxisButton(JOY_1, RIGHT_Y_AXIS);
  private static final AxisButton DRIVE_X_RIGHT = new AxisButton(JOY_1, RIGHT_X_AXIS);
  private static final DPadButton DRIVE_UP = new DPadButton(JOY_1, 0);
  private static final DPadButton DRIVE_RIGHT = new DPadButton(JOY_1, 90);
  private static final DPadButton DRIVE_DOWN = new DPadButton(JOY_1, 180);
  private static final DPadButton DRIVE_LEFT = new DPadButton(JOY_1, 270);

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

  public OI(){
      
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
