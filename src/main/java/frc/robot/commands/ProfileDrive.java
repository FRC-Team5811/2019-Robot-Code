package frc.robot.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.MotionProfiling.DCMotorTransmission;
import frc.robot.MotionProfiling.DifferentialDrivePeter;


public class ProfileDrive extends Command {
	
	static double mass = 40.0; // kg
	static double moi = 20.0; // kg * m^2   //this is a number 254 code had, I figure it's close-ish. Definitely need tuning. Trying to account for scrub with this, not so great
	static double wheelRadiusMeters = 0.0762; // m 
	static double wheelBaseWidth = 0.8128; // m   //this is the effective wheel base width empirically 4/3 that of the physical wheel base width (24in --> 32in)
	static double vIntercept = 0.67; //0.67 // V
	static double R = 0.09160305; // ohms
	static double kv = 46.51333;   // rad/s per V 
	static double kt = 0.0183969466;   // N*m per A
	static double g = 10.71; // gear reduction (g:1)
	static int nMotors = 2; //number of motors in a gearbox

    public ProfileDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dt);
    }
    ArrayList<Double> voltagesLeft = new ArrayList();
	ArrayList<Double> voltagesRight = new ArrayList();
	
	DifferentialDrivePeter DD;
	
	Scanner profileReader;
	int index = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
    	DD = new DifferentialDrivePeter(mass, wheelRadiusMeters, wheelBaseWidth, moi,
    			new DCMotorTransmission(kv, kt, vIntercept,  R, g, nMotors), 
    			new DCMotorTransmission(kv, kt, vIntercept,  R, g, nMotors));    	
    	try {
    		profileReader = new Scanner(new FileInputStream("home/lvuser/beter1.BOND"));
    		while(profileReader.hasNextLine()) {
    			String [] line = profileReader.nextLine().split(" ");
    			double voltRight = Double.parseDouble(line[0]);
    			double voltLeft = Double.parseDouble(line[1]);
    			voltagesRight.add(voltRight);
    			voltagesLeft.add(voltLeft);
    			index++;
    		}
    	} catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    int i = 0;
	boolean done = false;
	double prevL = 0;
	double prevR = 0;
    protected void execute() {
    	if(i<index) {
    		Robot.dt.voltageDrive(voltagesLeft.get(i), voltagesRight.get(i));
			System.out.println(DD.composeTransformFromArcs(Robot.dt.getLeftEnc() - prevL, Robot.dt.getRightEnc() - prevR).toText());
			prevL = Robot.dt.getLeftEnc();
			prevR = Robot.dt.getRightEnc();
    		i++;
    	}else {
    		Robot.dt.motorReset();
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
