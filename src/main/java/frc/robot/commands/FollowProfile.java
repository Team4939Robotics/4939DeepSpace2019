package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.frc.robot;
import frc.ProfilePoint;

public class FollowProfile extends Command {
  private ProfilePoint profile;
  
  public FollowProfile() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.DriveBase.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double position = profile.getPos(); //might need to reference index
    double velocity = profile.getVel();
    double acceleration = profile.getAcc();

    double currentLeftPos = Robot.DriveBase.getLeftEncoderDist;
    double currentRightPos = Robot.DriveBase.getRightEncoderDist;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

