/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

public class PIDController {

    double pGain;
	double iGain;
	double dGain;

	double pOut;
	double iOut;
	double dOut;

	double error;
	double errorSum = 0;
	double lastError = 0;

	double dProcessVar;

	double output = 0;

    boolean atTarget = false;
    
    public PIDController(double p, double i, double d) {
		errorSum = 0;
		lastError = 0;
		pGain = p;
		iGain = i;
		dGain = d;
	}

    public double calcPID(double setPoint, double currentValue, double epsilon) {
		error = setPoint - currentValue;

		if (Math.abs(error) <= epsilon) {
			atTarget = true;
		} else {
			atTarget = false;
		}

		// P
		pOut = pGain * error;

		// I
		errorSum += error;
		iOut = iGain * errorSum;

		// D
		dProcessVar = (error - lastError);
		dOut = dGain * dProcessVar;

		lastError = error;

		// PID Output
		output = pOut + iOut + dOut;

		// Scale output to be between 1 and -1
		if (output != 0.0)
			output = output / Math.abs(output) * (1.0 - Math.pow(0.1, (Math.abs(output))));

		return output;
    }
    
    public void resetPID() {
		errorSum = 0.0;
		lastError = 0.0;
		atTarget = false;
	}


    public boolean isDone() {
		return atTarget;
	}
}
