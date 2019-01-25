package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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
  public static final int SPX_1_ID = 1; 
  public static final int SPX_2_ID = 2;

  public static PowerDistributionPanel PDP = new PowerDistributionPanel();
  public static Compressor COMPRESSOR = new Compressor(COMPRESSOR_CAN_ID);

  public static Victor motor0 = new Victor(MOTOR_0);
  public static Victor motor1 = new Victor(MOTOR_1);
  public static Victor motor2 = new Victor(MOTOR_2);
  public static Victor motor3 = new Victor(MOTOR_3);
  public static Spark motor4 = new Spark(MOTOR_4);
  public static Spark motor5 = new Spark(MOTOR_5);
  public static Spark motor6 = new Spark(MOTOR_6);

	public static DoubleSolenoid solenoid1 = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid solenoid2 = new DoubleSolenoid(2, 3);
  public static DoubleSolenoid solenoid3 = new DoubleSolenoid(4, 5);

  public static AnalogInput d1 = new AnalogInput(0);
  public static DigitalOutput dO = new DigitalOutput(0);
  public static DigitalInput dI = new DigitalInput(1);
  public static Ultrasonic u1 = new Ultrasonic(dO, dI);

  public static VictorSPX spx1 = new VictorSPX(SPX_1_ID);
  public static VictorSPX spx2 = new VictorSPX(SPX_2_ID);
}
