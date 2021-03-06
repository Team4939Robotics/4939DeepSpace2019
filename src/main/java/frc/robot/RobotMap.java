/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public enum RobotMap {
	//Drive Motors
	LEFT_FRONT(1),
	LEFT_MIDDLE(3),
	LEFT_BACK(5),
	RIGHT_FRONT(2),
	RIGHT_MIDDLE(4), 
	RIGHT_BACK(6),

	//Elevator Motors
	ELEVATOR_A(7),
	//ELEVATOR_B(8),

	//Ball Intake Motors
	INTAKE_MOTOR_A(8),
	// INTAKE_MOTOR_B(10),
	INTAKE_PIVOT_A(9),
	INTAKE_PIVOT_B(10),

	//Drive Encoders
	LEFT_DRIVE_ENCODER_A(1),
	LEFT_DRIVE_ENCODER_B(2),
	RIGHT_DRIVE_ENCODER_A(3),
	RIGHT_DRIVE_ENCODER_B(4),

	//Elevator Encoders
	ELEVATOR_ENCODER_A(5),
	ELEVATOR_ENCODER_B(6),

	//Solenoids
	GRABBER_PISTON_A(0),
	GRABBER_PISTON_B(1),

	PUSHER_PISTON_A(2),
	PUSHER_PISTON_B(3);

<<<<<<< HEAD
	//Servo Motor
	SERVO_MOTOR(0);
=======
	//HOPPER_PISTON_A(4),
	//HOPPER_PISTON_B(5),
	HOPPER_PISTON_A(6),
	HOPPER_PISTON_B(7),

	CLIMB_FRONT_A(0),
	CLIMB_FRONT_B(1),
	CLIMB_BACK_A(4),
	CLIMB_BACK_B(5),

	//Servo on PWM Port
	SERVO_MOTOR(9);
>>>>>>> 0c6d4d29e66a04d0d51e7df7eacae8fa2e0fdb3f
	
	public final int value;
	
	RobotMap(int value){
		this.value = value;
	}
}
