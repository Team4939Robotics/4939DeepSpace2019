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
import edu.wpi.first.wpilibj.buttons.*;

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
	private Button HatchGrabber = new JoystickButton(DriverController, 6);
	// private Trigger ElevatorStage3 = new JoystickButton(DriverController, 3);
	// private Trigger ElevatorStage2 = new JoystickButton(DriverController, 2);
	private Button ElevatorStage1 = new JoystickButton(DriverController, 5);
	private Button EncoderReset = new JoystickButton(DriverController, 2);
	private Button DriveReverse = new JoystickButton(DriverController, 4);

	private Button ManualElevatorUp = new JoystickButton(OperatorController, 8);
	private Button ManualElevatorDown = new JoystickButton(OperatorController, 7);
	private Button HatchPusher = new JoystickButton(OperatorController, 2);
	private Button FrontClimbPiston = new JoystickButton(OperatorController, 11);
	private Button BackClimbPiston = new JoystickButton(OperatorController, 12);
	private Button BallFastOuttake = new JoystickButton(OperatorController, 1);
	private Button BallIntake = new JoystickButton(OperatorController, 4);
	private Button BallOuttake = new JoystickButton(OperatorController, 3);

	public OI() {
		TurnLeft.whenPressed(new TurnCommand(-90, 0.5, 1.5));
		TurnRight.whenPressed(new TurnCommand(90, 0.5, 1.5));
		DriveReverse.whenPressed(new ToggleDriveCommand());

		HatchGrabber.whenPressed(new HatchGrabberCommand());
		HatchPusher.whenPressed(new HatchPusherCommand());

		BallIntake.whenPressed(new PresetIntakeCommand());
		BallIntake.whenReleased(new StopIntakeCommand());
		BallOuttake.whenPressed(new PresetOuttakeCommand());
		BallOuttake.whenReleased(new StopIntakeCommand());
		BallFastOuttake.whenPressed(new BallFastOuttakeCommand());
		BallFastOuttake.whenReleased(new StopIntakeCommand());

		// ElevatorStage3.whenActive(new ElevatorStage3());
		// ElevatorStage2.whenActive(new ElevatorStage2());
		ElevatorStage1.whenPressed(new ElevatorStage1());
		EncoderReset.whenPressed(new resetEncoder());

		ManualElevatorUp.whenPressed(new ManualElevatorUp());
		ManualElevatorUp.whenReleased(new StopElevator());
		ManualElevatorDown.whenPressed(new ManualElevatorDown());
		ManualElevatorDown.whenReleased(new StopElevator());

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

	public boolean rightTrigger(){
		double rightTrig = DriverController.getRawAxis(3);
		if (Math.abs(rightTrig) < 0.5)
			return false;
		else
			return true;
	}
	public boolean leftTrigger(){
		double leftTrig = DriverController.getRawAxis(2);
		if (Math.abs(leftTrig) < 0.5)
			return false;
		else
			return true;
	}
	
	public double intake() {
		double intakeWheel = OperatorController.getRawAxis(3);
		if(Math.abs(intakeWheel) < 0.05)
			return 0.0;
		else
			return intakeWheel;
	}
}
