package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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

public class RobotMap {

  public static final int COMPRESSOR_CAN_ID = 1; 

  public static PowerDistributionPanel PDP = new PowerDistributionPanel(2);
  public static Compressor COMPRESSOR = new Compressor();

  public static TalonSRX rightF = new TalonSRX(3); //right forward
  public static VictorSPX rightB = new VictorSPX(5); //right back
  public static TalonSRX leftF = new TalonSRX(4); //left forward
  public static VictorSPX leftB = new VictorSPX(7); //left back
  public static VictorSPX cargo1 = new VictorSPX(9); //cargo 1
  public static VictorSPX cargo2 = new VictorSPX(8); //cargo 2
  public static VictorSPX cargo3 = new VictorSPX(6); //cargo 3

	public static DoubleSolenoid intakeRoller = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid hatchPunch = new DoubleSolenoid(2, 3);
  public static DoubleSolenoid extendHatchMechanism = new DoubleSolenoid(4, 5);
  //public static DoubleSolenoid frontClimber = new DoubleSolenoid(6, 7);
  //public static DoubleSolenoid backClimber = new DoubleSolenoid(8, 9);


  public static Ultrasonic u1 = new Ultrasonic(0, 1);
  public static DigitalInput laser1 = new DigitalInput(2);
  public static DigitalInput laser2 = new DigitalInput(3);

  public static I2C arduino = new I2C(I2C.Port.kMXP, 58);
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
}
