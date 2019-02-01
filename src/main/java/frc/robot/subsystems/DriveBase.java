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
import edu.wpi.first.wpilibj.SPI;

import frc.robot.RobotMap;
import frc.robot.NumberConstants;
import frc.robot.commands.TankDrive;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.PIDController;
import frc.robot.NumberConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class DriveBase extends Subsystem {
  
  public static WPI_TalonSRX leftDriveFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT.value);
  public static WPI_TalonSRX leftDriveMiddle = new WPI_TalonSRX(RobotMap.LEFT_MIDDLE.value);
  public static WPI_TalonSRX leftDriveBack = new WPI_TalonSRX(RobotMap.LEFT_BACK.value);
  public static WPI_TalonSRX rightDriveFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT.value);
  public static WPI_TalonSRX rightDriveMiddle = new WPI_TalonSRX(RobotMap.RIGHT_MIDDLE.value);
  public static WPI_TalonSRX rightDriveBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK.value);
    
  private AHRS ahrs;

  public Encoder leftDriveEncoder;
  public Encoder rightDriveEncoder;

  public ProfileGenerator dtGenerator = new ProfileGenerator(NumberConstants.TOP_SPEED, 
      NumberConstants.MAX_ACCELERATION, NumberConstants.deltaT);

  public PIDController gyroPID;
  
  public DriveBase() {
    try{
      ahrs = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
    } catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
    
    //Initialize Encoders
    leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A.value,
        RobotMap.LEFT_DRIVE_ENCODER_B.value, false, Encoder.EncodingType.k4X);

    leftDriveEncoder.setDistancePerPulse(NumberConstants.driveEncoderDistPerTick);

    rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A.value,
        RobotMap.RIGHT_DRIVE_ENCODER_B.value, false, Encoder.EncodingType.k4X);
        
    rightDriveEncoder.setDistancePerPulse(NumberConstants.driveEncoderDistPerTick);
    
    gyroPID = new PIDController(NumberConstants.gyroKP, NumberConstants.gyroKI, NumberConstants.gyroKD);
  }
  
  public void runLeftSideDrive(double leftDriveStick) {
    leftDriveFront.set(leftDriveStick);
    leftDriveMiddle.set(leftDriveStick);
    leftDriveBack.set(leftDriveStick);
  }
  public void runRightSideDrive(double rightDriveStick) {
    rightDriveFront.set(rightDriveStick);
    rightDriveMiddle.set(rightDriveStick);
    rightDriveBack.set(rightDriveStick);
  }

  //
  //Turning using gyro
  //
  public void turnDrive(double setAngle, double speed, double epsilon){
    double angle = gyroPID.calcPID(setAngle, angle(), epsilon);
    
    //sides turning opposite directions goes forward; same direction turns
    runLeftSideDrive(angle*speed); 
    runRightSideDrive(angle*speed);
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

  public double getLeftEncoderRate(){
    return leftDriveEncoder.getRate();
  }

  public double getRightEncoderRate(){
    return rightDriveEncoder.getRate();
  }
  
  public void resetEncoders() {
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }

  //
  //Profiling
  //
  public Trajectory makeProfile(double distance){
    Trajectory trajectory = new Trajectory(dtGenerator.GenerateProfile(distance), NumberConstants.deltaT);
    return trajectory;
  }
  
  //initiate TankDrive
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }
}
