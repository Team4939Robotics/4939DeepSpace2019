package frc.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;

import frc.robot.Robot;

public class UltrasonicCodeTesting extends Robot {
    
    AnalogInput ultra = new AnalogInput(0);
    
    // The scaling factor:  distance in inches = volts returned) / SCALING_FACTOR
    private final int SCALING_FACTOR = 512/5*24/23;
    
    /** Returns the distance measured in inches.  */
    public double getInches(){
        double volts = ultra.getVoltage();
        double inches = volts * SCALING_FACTOR;
        return inches;
    }

    public void readValue(){
        System.out.println(getInches());
    }
    
}
    
