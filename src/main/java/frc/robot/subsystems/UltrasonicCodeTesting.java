package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

import frc.robot.Robot;

public class UltrasonicCodeTesting extends Robot {
    
    AnalogInput ultra = new AnalogInput(0);
    
    
    private final int SCALING_FACTOR = 60/7;
    
    /** Returns the distance measured in inches.  */
    public double getVoltage(){
        double volts = ultra.getVoltage();
        return volts;
    }

    public double getInches(){
        double volts = ultra.getVoltage();
        double inches = volts * SCALING_FACTOR;
        return inches;
    }

    public void readValue(){
        System.out.println(getInches());
        System.out.println(getVoltage());

    }
    
}
    
