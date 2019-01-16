package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.auto.*;

public class FollowProfile extends Command {
  private ProfilePoint profile;
  
  public FollowProfile() {
    requires(Robot.dt);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.dt.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double position = profile.getPos(); //might need to reference index
    double velocity = profile.getVel();
    double acceleration = profile.getAcc();

    double currentLeftPos = Robot.dt.getLeftEncoderDist();
    double currentRightPos = Robot.dt.getRightEncoderDist();

    Robot.dt.runLeftDrive(ProfileController.output(leftPos, leftVel, leftAcc, currentLeftPos));
    Robot.dt.runRightDrive(ProfileController.output(rightPos, rightVel, rightAcc, currentRightPos));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   if (index < profile.getProfile().length)
        	return false;
        else
        	return true;
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

