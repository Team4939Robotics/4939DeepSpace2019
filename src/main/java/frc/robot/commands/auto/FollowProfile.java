package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class FollowProfile extends Command {
  
  private Trajectory profile;
  private int index = 0;
  Timer timer = new Timer();

  public FollowProfile(double distance){
    profile = Robot.dt.makeProfile(distance);
    requires (Robot.dt);
  }
  
  

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.dt.resetEncoders();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double position = profile.point(index).getPos();
    double velocity = profile.point(index).getVel();
    double acceleration = profile.point(index).getAcc();

    double currentLeftPos = Robot.dt.getLeftEncoderDist();
    double currentRightPos = Robot.dt.getRightEncoderDist();

    Robot.dt.runLeftSideDrive(ProfileController.output(position, velocity, acceleration, currentLeftPos));
    Robot.dt.runRightSideDrive(ProfileController.output(position, velocity, acceleration, currentRightPos));

    if (timer.get() >= profile.deltaT()) {
    		timer.reset();
    		index++;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   if (index < profile.length())
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

