/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class NumberConstants {

	//robot constants
	public static final double TOP_SPEED = 8.08; // {robot} / real world
	public static final double WHEELBASE = 24; // width between contact points of left and right wheel in inches
	public static final double MAX_TORQUE = 796.0467; // max torque motor can output in inch-lbs
	public static final double WHEEL_RADIUS = 2; // radius of drive wheel in inches
	public static final double MASS = 80; // mass of robot in pounds
	public static final double MAX_ACCELERATION = MAX_TORQUE / (WHEEL_RADIUS * MASS); // T/dm = a
	
	//drive encoder conversions
	public static final int drivePulsePerRotation = 1024;
	public static final double driveGearRatio = 1/1;
	public static final double driveEncoderPulsePerRot = drivePulsePerRotation*driveGearRatio; //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick =(Math.PI*2*WHEEL_RADIUS)/driveEncoderPulsePerRot;
	public static final double nativeToInches = 201.66;

	//elevator encoder
	public static final double elevatorEncoderMaxDist = 54.75;
	public static final double elevatorEncoderMaxCount = 24881;
	public static final double elevatorEncoderDistPerCount = elevatorEncoderMaxDist/elevatorEncoderMaxCount;
	
	//intake pivot encoder
	public static final double pivotEncoderMaxCount = 16485;
	public static final double pivotEncoderAnglePerCount = 1/pivotEncoderMaxCount;
	

	//control loop
	public static final double deltaT = 0.020;
	
	//profile controller constants
	public static final double kP = 0.01;
	public static final double kD = 0.1;

	//gyro PID constant
	public static double gyroKP = 0.09;
	public static double gyroKI = 0.0;
	public static double gyroKD = 0.17;
	//elevator PID constants
	public static double elevatorKP = 0.15;
	public static double elevatorKI = 0.0;
	public static double elevatorkD = 0.0;
	public static double elevatorkF = 0.0; 
	//intake pivot PID constants
	public static double pivotKP = 0.15;
	public static double pivotKI = 0.0;
	public static double pivotKD = 0.0;
	public static double pivotKF = 0.0;
}
