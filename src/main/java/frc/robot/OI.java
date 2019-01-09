/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick DriverController = new Joystick(1);
	
	public OI() {
		
	}
	
	public double left() {
		double leftdrivestick = DriverController.getRawAxis(1);
		if (Math.abs(leftdrivestick) < 0.05)
			return 0.0;
		else
			return leftdrivestick;
	}

	public double right() {
		double rightdrivestick = DriverController.getRawAxis(5);
		if (Math.abs(rightdrivestick) < 0.05)
			return 0.0;
		else
			return rightdrivestick;
	}
}
