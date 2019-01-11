/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

public class ProfilePoint {

	private double pos; // position in inches
	private double vel; // velocity in feet/s
	private double acc; // acc in ft/s^2
	public double deltaTime; // change in time for trajectory (motion profiling)

	// trajectory point with pos, vel, acc, deltaTime and intake boolean
	public ProfilePoint(double pos, double vel, double acc, double deltaTime) {
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.deltaTime; 

	}

	public double getPos() {
		return pos;
	}

	public double getVel() {
		return vel;
	}

	public double getAcc() {
		return acc;
	}
	public double getdeltaTime(startTime, endTime){
		 deltaTime = endTime - startTime
		 return deltaTime;
	}
}