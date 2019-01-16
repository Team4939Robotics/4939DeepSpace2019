/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import java.util.ArrayList;

/**
 * Add your docs here.
 */
public class Trajectory {
    public ArrayList<ProfilePoint> trajectory;
    public double deltaT;

    public Trajectory(ArrayList<ProfilePoint> trajectory, double deltaT) {
        this.trajectory = trajectory;
        this.deltaT = deltaT;
    }

    public ProfilePoint point(int index){
        return trajectory.get(index);
    }

    public int length(){
        return trajectory.size();
    }

    public double deltaT(){
        return deltaT;
    }
}
