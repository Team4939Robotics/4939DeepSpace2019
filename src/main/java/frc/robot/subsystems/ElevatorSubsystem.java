/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.NumberConstants;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends Subsystem {
  public static WPI_TalonSRX elevatorA = new WPI_TalonSRX(RobotMap.ELEVATOR_A.value);
  //public static WPI_TalonSRX elevatorB = new WPI_TalonSRX(RobotMap.ELEVATOR_B.value);

  // public Encoder elevatorEncoder;

  public PIDController elevatorPID;

  public ElevatorSubsystem(){
    elevatorA.setNeutralMode(NeutralMode.Brake);
    elevatorA.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    elevatorPID = new PIDController(NumberConstants.elevatorKP, 
        NumberConstants.elevatorKI, NumberConstants.elevatorkD, NumberConstants.elevatorkF);
  }

  public void runElevator(double speed){
    elevatorA.set(speed);
    //elevatorB.set(speed);
  }

  public void setElevatorHeight(double height, double speed, double epsilon){
    runElevator(elevatorPID.calcPID(height, getCount(), epsilon)*-speed);
  }

  // public void setNeutralToCoast(){
  //   elevatorA.setNeutralMode(NeutralMode.Coast);
  // }
  // public void setNeutralToBrake(){
  //   elevatorA.setNeutralMode(NeutralMode.Brake);
  // }
  //
  //Encoder methods
  //
  public double getEncoderDist(){
    return elevatorA.getSelectedSensorPosition();
  }

  public void resetEncoder(){
    elevatorA.setSelectedSensorPosition(0);
  }

  public int getCount(){
    return elevatorA.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
