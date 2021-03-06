package frc.robot;

import javax.xml.transform.stream.StreamSource;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;

public class RobotMap {

  public static PowerDistributionPanel PDP = new PowerDistributionPanel(2);
  public static Compressor COMPRESSOR = new Compressor();

  public static TalonSRX rightF = new TalonSRX(3); //right forward
  public static VictorSPX rightB = new VictorSPX(5); //right back
  public static TalonSRX leftF = new TalonSRX(4); //left forward
  public static VictorSPX leftB = new VictorSPX(7); //left back
  public static VictorSPX cargo1 = new VictorSPX(9); //cargo 1
  public static VictorSPX cargo2 = new VictorSPX(8); //cargo 2
  public static VictorSPX cargo3 = new VictorSPX(6); //cargo 3

  //public static CANSparkMax vacuum = new CANSparkMax(10, MotorType.kBrushless);
  //public static CANSparkMax lifter1 = new CANSparkMax(11, MotorType.kBrushless);
  //public static CANSparkMax lifter2 = new CANSparkMax(12, MotorType.kBrushless);
  //public static CANSparkMax pressureMotor = new CANSparkMax(13, MotorType.kBrushed);

	public static DoubleSolenoid intakeRoller = new DoubleSolenoid(6, 7);  // should be 0,1 solenoid not behaving
  public static DoubleSolenoid hatchPunch = new DoubleSolenoid(4, 5);  // should be 2, 3
  public static DoubleSolenoid extendHatchMechanism = new DoubleSolenoid(1, 0);
  public static DoubleSolenoid beakMovement = new DoubleSolenoid(2, 3);

  //scp beter1.BOND lvuser@roborio-5811-frc.local:
  public static AnalogInput laser1 = new AnalogInput(1);
  public static AnalogInput laser2 = new AnalogInput(2); 
  public static AnalogInput laser3 = new AnalogInput(3); 

  public static DigitalInput hatchSensor = new DigitalInput(9);
  public static DigitalInput hatchSensor2 = new DigitalInput(8);
  
  public static Encoder driveEncL = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
  public static Encoder driveEncR = new Encoder(2, 3, true, Encoder.EncodingType.k4X);

  public static I2C arduino = new I2C(I2C.Port.kMXP, 58);
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
}
