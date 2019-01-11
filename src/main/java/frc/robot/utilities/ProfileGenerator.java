/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;


import java.util.ArrayList;

import frc.robot.commands.auto.ProfilePoint;
import frc.robot.NumberConstants;

public class ProfileGenerator {
    public ArrayList<ProfilePoint> GenerateProfile(double distance, double deltat, double maxV, double maxA){
        
        ArrayList<ProfilePoint> trajectory = new ArrayList<ProfilePoint>();
        double triangleTime = maxV/maxA;
        double triangleDist = 0.5*triangleTime*maxV;
        double rectDist = distance - 2*triangleDist;
        double rectTime = rectDist/maxV;

        double time = 0;
        while (time <= 2*triangleTime + rectTime){
            if (time <= triangleTime){
                double vel = maxA*time;
                double acc = maxA;
                double pos = 0.5*maxA*(Math.pow(time,2));
                trajectory.add(new ProfilePoint(pos, vel, acc));
            }
            else if (time <= triangleTime + rectTime){
                double vel = maxV;
                double acc = 0;
                double pos = triangleDist + maxV*(time - triangleTime);
                trajectory.add(new ProfilePoint(pos, vel, acc));
            }
            else if (time <= 2*triangleTime + rectTime){
                double vel = -1*maxA*(time-(2*triangleTime + rectTime));
                double acc = -1*maxA;
                double pos = triangleDist + rectDist + maxV*(time-triangleTime-rectTime) - 0.5*maxA*Math.pow((time - triangleTime - rectTime),2);
                trajectory.add(new ProfilePoint(pos, vel, acc));
            }
            time += deltat;
        }
        return trajectory;
    }

}
