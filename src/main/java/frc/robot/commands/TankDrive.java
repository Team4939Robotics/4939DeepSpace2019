/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TankDrive extends Command {
  
	/** The Constant DELTA_LIMIT. */
	private static final double DELTA_LIMIT = 0.40;
	
	/** The Constant RAMP_UP_CONSTANT. */
	private static final double RAMP_UP_CONSTANT = 0.01;
	
	/** The Constant RAMP_DOWN_CONSTANT. */
	private static final double RAMP_DOWN_CONSTANT = 0.01;
	
	/** Variables used for joystick ramping*/
	double deltaL = 0;
	double deltaR = 0;
	double prevInputL = 0;
	double inputL = 0;
	double prevInputR = 0;
	double inputR = 0;
	
    public TankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	inputL = Robot.m_oi.left()*-1;
		inputR = Robot.m_oi.right();
		deltaL = inputL - prevInputL;
		deltaR = inputR - prevInputR;
		
		if(deltaL >= DELTA_LIMIT)
			inputL += RAMP_UP_CONSTANT;
		else if(deltaL <= -DELTA_LIMIT)
			inputL -= RAMP_DOWN_CONSTANT;
		
		if(deltaR >= DELTA_LIMIT)
			inputR += RAMP_UP_CONSTANT;
		else if(deltaR <= -DELTA_LIMIT)
		  inputR -= RAMP_DOWN_CONSTANT;
		
		if(Robot.dt.reverse){
			Robot.dt.runLeftSideDrive(-inputR);
			Robot.dt.runRightSideDrive(-inputL);
		}
		else{
			Robot.dt.runLeftSideDrive(inputL);
			Robot.dt.runRightSideDrive(inputR);
		}
		
		prevInputL = inputL;
		prevInputR = inputR;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
