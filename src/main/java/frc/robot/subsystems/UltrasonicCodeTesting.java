package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

import frc.robot.Robot;


public class UltrasonicCodeTesting {
    
    AnalogInput ultra = new AnalogInput(0);
    
    
    private final int SCALING_FACTOR = 10;
    
    /** Returns the distance measured in inches.  */

    public double getInches(){
        double volts = ultra.getVoltage();
        double inches = volts * SCALING_FACTOR;
        return inches;
    }

    public void readValue(){
        System.out.println("Distance: " + getInches());
        System.out.println("Voltage: " + getVoltage());

    }

    
}
    
