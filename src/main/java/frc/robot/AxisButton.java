package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {

    private static final double DEAD_ZONE = 0.02;

	private Joystick joystick;
	private int axis;

    /**
     * Constructor that localizes the passed in fields
     * @param joystick
     * @param axis
     */
	public AxisButton(Joystick joystick, int axis) {
		this.joystick = joystick;
		this.axis = axis;
	}
    /**
     * Decides whether or not to activate axis polling of various joysticks.
     */
	public boolean get() {
		return (this.joystick.getRawAxis(this.axis) > DEAD_ZONE || joystick.getRawAxis(this.axis) < -DEAD_ZONE);
	}
}