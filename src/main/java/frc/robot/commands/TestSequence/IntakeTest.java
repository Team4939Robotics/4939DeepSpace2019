// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands.TestSequence;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;

// public class IntakeTest extends Command {
//   public IntakeTest() {
//     requires(Robot.BI);
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     setTimeout(1);
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//     Robot.BI.useIntake(0.5);
//     Robot.BI.pushBall(180);
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return isTimedOut();
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//     Robot.BI.useIntake(0);
//     Robot.BI.pushBall(0);
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//   }
// }
