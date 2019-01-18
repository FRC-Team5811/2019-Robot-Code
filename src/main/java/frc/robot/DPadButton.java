package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPadButton extends Button {
	Joystick joy2;
	int state;

	public DPadButton(Joystick joystick, int value) {
		joy2 = joystick;
		state = value;
	}

	public boolean get() {
		return (joy2.getPOV() == state);
	}
}