/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.NumberConstants;
import frc.robot.RobotMap;
// import frc.robot.commands.BallIntakeCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class BallIntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
   public static WPI_TalonSRX intakeMotorA = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_A.value);
   public static WPI_TalonSRX intakePivotA = new WPI_TalonSRX(RobotMap.INTAKE_PIVOT_A.value);
   public static WPI_TalonSRX intakePivotB = new WPI_TalonSRX(RobotMap.INTAKE_PIVOT_B.value);

  public PIDController pivotPID;
  // public static WPI_TalonSRX intakeMotorB = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_B.value);
  // public static Servo pushServo = new Servo(RobotMap.SERVO_MOTOR.value);
  // public static DoubleSolenoid hopperPiston = new DoubleSolenoid(0,RobotMap.HOPPER_PISTON_A.value, RobotMap.HOPPER_PISTON_B.value);
  public BallIntakeSubsystem(){
    intakePivotA.setNeutralMode(NeutralMode.Brake);
     intakePivotB.setNeutralMode(NeutralMode.Brake);
     intakePivotA.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

    pivotPID = new PIDController(NumberConstants.pivotKP, NumberConstants.pivotKI, NumberConstants.pivotKD, NumberConstants.pivotKF);
  }

  public void useIntake(double speed) {
     intakeMotorA.set(speed);
  }

  public void pivotIntake(double speed){
     intakePivotA.set(-speed);
     intakePivotB.set(-speed);
  }

  public void pivotToAngle(double angle, double speed, double epsilon){
    double print = pivotPID.calcPID(angle, getEncoderPosition(), epsilon);
    SmartDashboard.putNumber("PID Value: ", print);
     pivotIntake(print*speed);
  }

  
  //Encoder methods
  //
   public double getEncoderPosition(){
     return intakePivotA.getSelectedSensorPosition()*NumberConstants.pivotEncoderAnglePerCount;
   }

   public void resetEncoder(){
     intakePivotA.setSelectedSensorPosition(0);
   }

   public int getCount(){
     return intakePivotA.getSelectedSensorPosition();
   }

  // public void pushBall(int angle){
  //   pushServo.setAngle(angle);
  // }


  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new BallIntakeCommand());
  }

}
