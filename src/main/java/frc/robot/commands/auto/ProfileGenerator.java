/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;


import java.util.ArrayList;

import frc.robot.commands.auto.ProfilePoint;
import frc.robot.NumberConstants;

public class ProfileGenerator {

    private double maxV = NumberConstants.TOP_SPEED;
    private double maxA = NumberConstants.MAX_ACCELERATION;
    private double deltaT = NumberConstants.deltaT;

    public ArrayList<ProfilePoint> GenerateProfile(double distance){
        int direction; //Makes sure values are negative if distance is negative
        if (distance < 0)
            direction = -1;
        else
            direction = 1;
        
        ArrayList<ProfilePoint> trajectory = new ArrayList<ProfilePoint>();
        double triangleTime = maxV/maxA;
        double triangleDist = 0.5*triangleTime*maxV;
        double rectDist = Math.abs(distance) - 2*triangleDist;
        double rectTime = rectDist/maxV;

        double time = 0;
        while (time <= 2*triangleTime + rectTime){
            if (time <= triangleTime){
                double vel = maxA*time*direction;
                double acc = maxA*direction;
                double pos = 0.5*maxA*(Math.pow(time,2))*direction;
                trajectory.add(new ProfilePoint(pos, vel, acc));
            }
            else if (time <= triangleTime + rectTime){
                double vel = maxV*direction;
                double acc = 0;
                double pos = (triangleDist + maxV*(time - triangleTime))*direction;
                trajectory.add(new ProfilePoint(pos, vel, acc));
            }
            else if (time <= 2*triangleTime + rectTime){
                double vel = -1*maxA*(time-(2*triangleTime + rectTime))*direction;
                double acc = -1*maxA*direction;
                double pos = (triangleDist + rectDist + maxV*(time-triangleTime-rectTime) - 0.5*maxA*Math.pow((time - triangleTime - rectTime),2))*direction;
                trajectory.add(new ProfilePoint(pos, vel, acc));
            }
            time += deltaT;
        }
        return trajectory;
    }

}
