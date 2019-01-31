/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import frc.robot.NumberConstants;

public class ProfileController {
    //proportional and derivative constants for position and velocity respectively
	private static double kP;
	private static double kD;
	
	//constants to scale velocity and acceleration feedforward terms
	private static double kV = 1/NumberConstants.TOP_SPEED;
	private static double kA = 1/NumberConstants.MAX_ACCELERATION;
	
	//prev position error for derivative control of velocity
	private static double prevPosError = 0;
	
	//output based on pos, vel, acc goals and current position
	public static double output(double posGoal, double velGoal, double accGoal, double currentPos) {
		//global constants
		kP = NumberConstants.kP;
		kD = NumberConstants.kD;
		
		//position error for proportional control
		double posError = posGoal - currentPos;
		
		//proportional position control
		double posOutput = kP * posError;
		
		//derivative and feedforward velocity control
		double velOutput = kD * ((posError - prevPosError)/(12 * NumberConstants.LOOPER_PERIOD) - velGoal) + kV * velGoal; 
		//delta error / 12*period to convert from error in inches to feet 
		
		//feedforward acceleration control
		double accOutput = kA * accGoal;
		
		//output is sum of all 3
		double output = posOutput + velOutput + accOutput;
		
		//scale output between -1 and 1
		if (output != 0.0)
			output = output / Math.abs(output) * (1.0 - Math.pow(0.1, (Math.abs(output))));
		
		//save previous position error for future calculations
		prevPosError = posError;
		
		return output;
	}
}
