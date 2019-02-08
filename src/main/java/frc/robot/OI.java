/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick DriverController = new Joystick(0);
	public Joystick OperatorController = new Joystick(1);
	// declares the two controllers

	private Button TurnLeft = new JoystickButton(DriverController, 3);
	private Button TurnRight = new JoystickButton(DriverController, 1);
	private Button HatchGrabber = new JoystickButton(DriverController, 5);
	//private Button BallIntake = new JoystickButton(DriverController, 3);
	// private Button BallOuttake = new JoystickButton(DriverController, 1);
	private Trigger ElevatorStage3 = new JoystickButton(DriverController, 3);
	private Trigger ElevatorStage2 = new JoystickButton(DriverController, 2);
	private Button ElevatorStage1 = new JoystickButton(DriverController, 4);

	private POVButton HatchPusher = new POVButton(OperatorController, 0, 0);
	
	public OI() {
		TurnLeft.whenPressed(new TurnCommand(-90, 0.5, 1));
		TurnRight.whenPressed(new TurnCommand(90, 0.5, 1)); //for testing
		HatchGrabber.whenPressed(new HatchGrabberCommand());

		//BallIntake.whenPressed(new PresetIntakeCommand());
		//BallIntake.whenReleased(new StopIntakeCommand());

		// BallOuttake.whenPressed(new PresetOuttakeCommand());
		// BallOuttake.whenReleased(new StopIntakeCommand());

		ElevatorStage3.whenActive(new ElevatorStage3());
		ElevatorStage2.whenActive(new ElevatorStage2());
		ElevatorStage1.whenPressed(new ElevatorStage1());

		HatchPusher.whenPressed(new HatchPusherCommand());
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
	
	public double intake() {
		double intakeWheel = OperatorController.getRawAxis(3);
		if(Math.abs(intakeWheel) < 0.05)
			return 0.0;
		else
			return intakeWheel;
	}
}
