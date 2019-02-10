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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class DriveBase extends Subsystem {
  
  public static WPI_TalonSRX leftDriveFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT.value);
  public static WPI_TalonSRX leftDriveMiddle = new WPI_TalonSRX(RobotMap.LEFT_MIDDLE.value);
  public static WPI_TalonSRX leftDriveBack = new WPI_TalonSRX(RobotMap.LEFT_BACK.value);
  public static WPI_TalonSRX rightDriveFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT.value);
  public static WPI_TalonSRX rightDriveMiddle = new WPI_TalonSRX(RobotMap.RIGHT_MIDDLE.value);
  public static WPI_TalonSRX rightDriveBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK.value);

  // Shows the joystick configurations for left and right drive/

    
  private AHRS ahrs;

  public Encoder leftDriveEncoder;
  public Encoder rightDriveEncoder;
  // initialize two encoders leftDrive and rightDrive

  public ProfileGenerator dtGenerator = new ProfileGenerator(NumberConstants.TOP_SPEED, 
      NumberConstants.MAX_ACCELERATION, NumberConstants.deltaT);
  public ProfileController profilePID = new ProfileController(NumberConstants.TOP_SPEED, 
      NumberConstants.MAX_ACCELERATION, NumberConstants.kP, NumberConstants.kD);

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
    //sets the multiplier used to determine the distance driven based on the count value from the leftDriveEncoder.

    rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A.value,
        RobotMap.RIGHT_DRIVE_ENCODER_B.value, false, Encoder.EncodingType.k4X);
        
    rightDriveEncoder.setDistancePerPulse(NumberConstants.driveEncoderDistPerTick);
        //sets the multiplier used to determine the distance driven based on the count value from the RightDriveEncoder.

    
    gyroPID = new PIDController(NumberConstants.gyroKP, NumberConstants.gyroKI, NumberConstants.gyroKD, 0);
  }
  
  public void runLeftSideDrive(double leftDriveStick) {
    leftDriveFront.set(leftDriveStick);
    leftDriveMiddle.set(leftDriveStick);
    leftDriveBack.set(leftDriveStick);

    //Runs left drive 
  }
  public void runRightSideDrive(double rightDriveStick) {
    rightDriveFront.set(rightDriveStick);
    rightDriveMiddle.set(rightDriveStick);
    rightDriveBack.set(rightDriveStick);

  // Runs right drive
  }

  
  //Turning using gyro
  
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
    //returns the number of degrees for rotation
  }
  
  public void resetGyroYaw() {
    ahrs.reset();
    // resets the gyro axis to 0

    ahrs.zeroYaw();

    ahrs.zeroYaw();

  }
  
  public AHRS getAhrs() {
    return ahrs;
    //used to call objects in other classes
  }
  
  public double rate() {
    return ahrs.getRate();
    //returns rate of rotation of the gyro in deegrees per second
  }
  
  
  //Encoder Methods
  
  public double getLeftEncoderDist() {
    return leftDriveEncoder.getDistance();
  //gets the distance from the robot from the last given distance given from the left encoderand returns the same distance from the last reset
  
  }
  
  public double getRightEncoderDist() {
    return rightDriveEncoder.getDistance();
    //gets the distance from the robot from the last given distance given from the left encoder and returns the same distance from the last reset

  }

  public double getLeftEncoderRate(){
    return leftDriveEncoder.getRate();
    //gets the current rate of the left encoder and returns the value
  }

  public double getRightEncoderRate(){
    return rightDriveEncoder.getRate();
     //gets the current rate of the left encoder and returns the value 
  }
  
  public void resetEncoders() {
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
    // resets the enocder distance and the current count to zero on the encoder
  }

  //
  //Profiling
  //
  public Trajectory makeProfile(double distance){
    Trajectory trajectory = new Trajectory(dtGenerator.GenerateProfile(distance), NumberConstants.deltaT);
    return trajectory;
    // finds the distance between the two points
  }
  public double PIDoutput(double pos, double vel, double acc, double currentPos){
    return profilePID.output(pos, vel, acc, currentPos);
  }
  
  //initiate TankDrive
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }
}
