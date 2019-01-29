package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;

public class RobotMap {

  public static final int MOTOR_0 = 0;
  public static final int MOTOR_1 = 1;
  public static final int MOTOR_2 = 2;
  public static final int MOTOR_3 = 3;
  public static final int MOTOR_4 = 4;
  public static final int MOTOR_5 = 5;
  public static final int MOTOR_6 = 6;

  public static final int COMPRESSOR_CAN_ID = 0; 

  public static PowerDistributionPanel PDP = new PowerDistributionPanel();
  public static Compressor COMPRESSOR = new Compressor(COMPRESSOR_CAN_ID);

  public static Victor motor0 = new Victor(MOTOR_0);
  public static Victor motor1 = new Victor(MOTOR_1);
  public static Victor motor2 = new Victor(MOTOR_2);
  public static Victor motor3 = new Victor(MOTOR_3);
  public static Spark motor4 = new Spark(MOTOR_4);
  public static Spark motor5 = new Spark(MOTOR_5);
  public static Spark motor6 = new Spark(MOTOR_6);

	public static DoubleSolenoid intakeRoller = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid solenoid2 = new DoubleSolenoid(2, 3);
  public static DoubleSolenoid solenoid3 = new DoubleSolenoid(4, 5);

  public static Ultrasonic u1 = new Ultrasonic(0, 1);
}
