package frc.robot.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.AutoDataStorage;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.MotionProfiling.DCMotorTransmission;
import frc.robot.MotionProfiling.DifferentialDrivePeter;


public class ProfileDrive extends Command {
	
	 double mass = 40.0; // kg
	 double moi = 20.0; // kg * m^2   //this is a number 254 code had, I figure it's close-ish. Definitely need tuning. Trying to account for scrub with this, not so great
	 double wheelRadiusMeters = 0.0762; // m 
	 double wheelBaseWidth = 0.8128; // m   //this is the effective wheel base width empirically 4/3 that of the physical wheel base width (24in --> 32in)
	 double vIntercept = 0.67; //0.67 // V
	 double R = 0.09160305; // ohms
	 double kv = 46.51333;   // rad/s per V 
	 double kt = 0.0183969466;   // N*m per A
	 double g = 10.71; // gear reduction (g:1)
	 int nMotors = 2; //number of motors in a gearbox

	 double dt = 0.02;

	public double kPPos;//2.0;
	public double kPVel = 0.0;//2.0;
	public double kPAng;//4.5;
	public double kPAngVel = 0.0;//2.0;

	public String fileName;
	
    public ProfileDrive(String file, double kpAng, double kpPos) {
        // Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.fileName = file;
		this.kPAng = kpAng;
		this.kPPos = kpPos;
    	requires(Robot.getDtSubsystem());
	}
	
    public static ArrayList<Double> voltagesLeft;
	public static ArrayList<Double> voltagesRight;
	public static ArrayList<Double> poses;
	public static ArrayList<Double> vels;
	public static ArrayList<Double> angs;
	public static ArrayList<Double> angVels;
	
	DifferentialDrivePeter DD;
	
	Scanner profileReader;
	int index = 0;

	double disp, vel, ang, angVel;
	int i;
	boolean done;
	double deltaL, deltaR, deltaAng;
	double prevL = 0;
	double prevR = 0; //0.4787787m circum
	double prevAng = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
		i = 0;
		done = false;
		//prevL = 0;
		//prevR = 0;
		//prevAng = 0;
		Robot.getDtSubsystem().resetEncoders();

		AutoDataStorage.loadArrays(this.fileName);
		AutoDataStorage.loadArrays2(this.fileName);


		//System.out.println("in init");
		// String file = Filesystem.getDeployDirectory().getPath() + "/"+this.fileName + ".BOND";
    	// DD = new DifferentialDrivePeter(mass, wheelRadiusMeters, wheelBaseWidth, moi,
    	// 		new DCMotorTransmission(kv, kt, vIntercept,  R, g, nMotors), 
    	// 		new DCMotorTransmission(kv, kt, vIntercept,  R, g, nMotors));    	
    	// try {
    	// 	profileReader = new Scanner(new FileInputStream(file));
    	// 	while(profileReader.hasNextLine()) {
		// 		String [] line = profileReader.nextLine().split(" ");
    	// 		double voltRight = Double.parseDouble(line[0]);
		// 		double voltLeft = Double.parseDouble(line[1]);
		// 		disp = Double.parseDouble(line[2]);
		// 		vel = Double.parseDouble(line[3]);
		// 		ang = Double.parseDouble(line[4]);
		// 		angVel = Double.parseDouble(line[5]);
    	// 		voltagesRight.add(voltRight);
		// 		voltagesLeft.add(voltLeft);
		// 		poses.add(disp);
		// 		vels.add(vel);
		// 		angs.add(ang);
    	// 		angVels.add(angVel);
    	// 	}
    	// } catch(FileNotFoundException e) {
    	// 	e.printStackTrace();
		// }
    }

    // Called repeatedly when this Command is scheduled to run
   
	public static double posError, velError, angError, angVelError;
	double drivenDistanceSensor = 0;
	double outputLeftVoltage, outputRightVoltage;

	protected void execute() {
		
    	if(i<voltagesRight.size()) {
			deltaL = Robot.getDtSubsystem().getLeftEncMeters() - prevL;
			deltaR = Robot.getDtSubsystem().getRightEncMeters() - prevR;
			deltaAng = Robot.getDtSubsystem().grabAngleRadians() - prevAng;
			//System.out.println(DD.composeTransformFromArcs(deltaL, deltaR).toText());
			drivenDistanceSensor += (deltaL + deltaR)/2;
			posError = poses.get(i) - (drivenDistanceSensor);
			velError = vels.get(i) - ((deltaL+deltaR)/2/dt);
			angError = angs.get(i) + Robot.getDtSubsystem().storedAngProfile - Robot.getDtSubsystem().grabAngleRadians();
			angVelError = angVels.get(i) - (deltaAng/dt);
			
			outputLeftVoltage = voltagesLeft.get(i) + this.kPPos*posError + kPVel*velError - this.kPAng*angError - kPAngVel*angVelError;
			outputRightVoltage = voltagesRight.get(i)+ this.kPPos*posError + kPVel*velError + this.kPAng*angError + kPAngVel*angVelError;
			//System.out.println(outputRightVoltage + "\t" + outputLeftVoltage);
			Robot.getDtSubsystem().voltageDrive(outputLeftVoltage , outputRightVoltage);
			System.out.println("Left Volts: " + outputLeftVoltage + " Right Volts: " + outputRightVoltage);
			//System.out.println("LF: "+RobotMap.PDP.getCurrent(0) + "\t" + "LB: "+RobotMap.PDP.getCurrent(1) + "\t" + "RF: "+RobotMap.PDP.getCurrent(15) + "\t" + "RB: "+ RobotMap.PDP.getCurrent(14));
			prevL = Robot.getDtSubsystem().getLeftEncMeters();
			prevR = Robot.getDtSubsystem().getRightEncMeters();
			prevAng = Robot.getDtSubsystem().grabAngleRadians();
    		i++;
    	}else {
    		Robot.getDtSubsystem().motorReset();
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
