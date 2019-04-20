/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ComboButton extends Button {
	Joystick joy1;
	JoystickButton button1Raw;
    JoystickButton button2Raw;
    AxisButton axis1, axis2;
	int button1;
    int button2;
    boolean axis;

	public ComboButton(Joystick joystick, int button1ID, int button2ID) {
		joy1 = joystick;
		button1 = button1ID;
		button2 = button2ID;
		button1Raw = new JoystickButton(joy1, button1);
        button2Raw = new JoystickButton(joy1, button2);
        axis = false;
    }

    public ComboButton(AxisButton axis1Arg, AxisButton axis2Arg){
        axis1 = axis1Arg;
        axis2 = axis2Arg;
        axis = true;
    }


	public boolean get() {
        if(axis == false){
            if (button1Raw.get() == true && button2Raw.get() == true && axis == false) {
                return true;
            } else {
                return false;
            }
        } else {
            if(axis == true && axis1.get() && axis2.get()){
                return true;
            } else {
                return false;
            }
        }

	}

}

