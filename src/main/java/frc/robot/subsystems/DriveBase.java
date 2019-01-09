/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class DriveBase extends Subsystem {
  
  public static WPI_TalonSRX leftDriveFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT.value);
  public static WPI_TalonSRX leftDriveBack = new WPI_TalonSRX(RobotMap.LEFT_BACK.value);
  public static WPI_TalonSRX rightDriveFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT.value);
  public static WPI_TalonSRX rightDriveBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK.value);
    
  private AHRS ahrs;

  public Encoder leftDriveEncoder;
  public Encoder rightDriveEncoder;
  
  public DriveBase() {
    try{
      ahrs = new AHRS(SerialPort.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
    } catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
    
    leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A.value,
			  RobotMap.LEFT_DRIVE_ENCODER_B.value);
    rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A.value,
			  RobotMap.RIGHT_DRIVE_ENCODER_B.value);
    
  }
  
  public void runLeftSideDrive(double leftDriveStick) {
    leftDriveFront.set(leftDriveStick);
    leftDriveBack.set(leftDriveStick);
  }
  public void runRightSideDrive(double rightDriveStick) {
    rightDriveFront.set(rightDriveStick);
    rightDriveBack.set(rightDriveStick);
  }
  
  //
  //Gyro Methods
  //
  public double angle() {
    return ahrs.getAngle();
  }
  
  public void resetGyroYaw() {
    ahrs.reset();
  }
  
  public AHRS getAhrs() {
    return ahrs;
  }
  
  public double rate() {
    return ahrs.getRate();
  }
  
  //
  //Encoder Methods
  //
  public double getLeftEncoderDist() {
    return leftDriveEncoder.getDistance();
  }
  
  public double getRightEncoderDist() {
    return rightDriveEncoder.getDistance();
  }
  
  public void resetEncoders() {
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }
  
  //initiate TankDrive
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }
}
