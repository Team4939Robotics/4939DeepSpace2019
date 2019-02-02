/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.BallIntakeCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class BallIntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static WPI_TalonSRX intakeMotorA = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_A.value);
  public static WPI_TalonSRX intakeMotorB = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_B.value);
  
  public void useIntake(double speed) {
    intakeMotorA.set(speed);
    intakeMotorB.set(-speed);
  }

  public void useOuttake(double speed) {
    intakeMotorA.set(-speed);
    intakeMotorB.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new BallIntakeCommand());
  }
}
