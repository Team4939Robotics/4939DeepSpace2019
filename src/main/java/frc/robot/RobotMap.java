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
	LEFT_FRONT(1),
	LEFT_BACK(3),
	RIGHT_FRONT(2),
	RIGHT_BACK(4),
	
	LEFT_DRIVE_ENCODER_A(1),
	LEFT_DRIVE_ENCODER_B(2),
	RIGHT_DRIVE_ENCODER_A(3),
	RIGHT_DRIVE_ENCODER_B(4);
	
	public final int value;
	
	RobotMap(int value){
		this.value = value;
	}
}
