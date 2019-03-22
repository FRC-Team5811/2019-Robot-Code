package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SerialPort;;

public class VisionSource extends Subsystem {

    private SerialPort serial;
    private double something;
    private double something2;

    public VisionSource(){
        serial = new SerialPort(115280, SerialPort.Port.kOnboard);
        serial.setReadBufferSize(1);
    }

    @Override
    protected void initDefaultCommand() {

    }


    public void readAndUpdate(){
        String buf = serial.readString();
        String[] bufs = buf.split("\n");

        if(bufs.length > 0){
            String last = bufs[bufs.length -1];
            String[] vals = last.split("|");
            if(vals.length == 2){
                try{
                    something = Double.parseDouble(vals[0]);
                    something2 = Double.parseDouble(vals[1]);
                }catch(Exception e){
                    //sucks - set sentinal for this loop - no action
                    something2 = 0;
                    something = 0;
                }
            }
        }
    }

    public double getVal1(){
        return something;
    }

    public double getVal2(){
        return something2;
    }

}

