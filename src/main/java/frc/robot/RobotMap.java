package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;

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

  public static final int COMPRESSOR_CAN_ID = 0; 

  public static PowerDistributionPanel PDP = new PowerDistributionPanel();
  public static Compressor COMPRESSOR = new Compressor(COMPRESSOR_CAN_ID);

  public static TalonSRX motor1 = new TalonSRX(1);
  public static TalonSRX motor2 = new TalonSRX(2);
  public static TalonSRX motor3 = new TalonSRX(3);
  public static TalonSRX motor4 = new TalonSRX(4);
  public static TalonSRX motor5 = new TalonSRX(5);
  public static TalonSRX motor6 = new TalonSRX(6);
  public static TalonSRX motor7 = new TalonSRX(7);

	public static DoubleSolenoid intakeRoller = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid hatchPunch = new DoubleSolenoid(2, 3);
  public static DoubleSolenoid extendHatchMechanism = new DoubleSolenoid(4, 5);

  public static Ultrasonic u1 = new Ultrasonic(0, 1);

  public static I2C arduino = new I2C(I2C.Port.kMXP, 58);
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
}
