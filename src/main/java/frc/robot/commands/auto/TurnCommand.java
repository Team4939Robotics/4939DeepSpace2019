/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnCommand extends Command {
  private double angle;
  private double speed;
  private double timeOut;

  public TurnCommand(double angle, double speed, double timeOut) {
    this.angle = angle;
    this.speed = speed;
    this.timeOut = timeOut;

    requires(Robot.dt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.dt.resetGyroYaw();
    setTimeout(timeOut);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.dt.turnDrive(angle, speed, 1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.abs(Robot.m_oi.left()) > 0.1 || Math.abs(Robot.m_oi.right()) > 0.1){
      return true;
    }
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.dt.runLeftSideDrive(0);
    Robot.dt.runRightSideDrive(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
