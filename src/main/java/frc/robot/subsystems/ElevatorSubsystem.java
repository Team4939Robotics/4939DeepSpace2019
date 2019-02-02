/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends Subsystem {
  public static WPI_TalonSRX elevatorA = new WPI_TalonSRX(RobotMap.ELEVATOR_A.value);
  public static WPI_TalonSRX elevatorB = new WPI_TalonSRX(RobotMap.ELEVATOR_B.value);

  public Encoder elevatorEncoder;

  public ElevatorSubsystem(){
    elevatorEncoder = new Encoder(RobotMap.ELEVATOR_ENCODER_A.value, 
        RobotMap.ELEVATOR_ENCODER_B.value, false, Encoder.EncodingType.k4X);
    
    //elevatorEncoder.setDistancePerPulse();
  }

  public void runElevator(double speed){
    elevatorA.set(speed);
    elevatorB.set(speed);
  }

  //
  //Encoder methods
  //
  public double getEncoderDist(){
    return elevatorEncoder.getDistance();
  }

  public double getEncoderRate(){
    return elevatorEncoder.getRate();
  }

  public void resetEncoder(){
    elevatorEncoder.reset();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}