/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchSubsystem extends Subsystem {
  public DoubleSolenoid grabberPiston = new DoubleSolenoid(RobotMap.GRABBER_PISTON_A.value, RobotMap.GRABBER_PISTON_B.value);
  public DoubleSolenoid pusherPiston = new DoubleSolenoid(RobotMap.PUSHER_PISTON_A.value, RobotMap.PUSHER_PISTON_B.value);

  public boolean grabbed = false;
  public boolean pushed = false;

  //Grabber Methods
  public void openGrabber(){
    grabberPiston.set(DoubleSolenoid.Value.kForward);
    grabbed = true;
  }

  public void closeGrabber(){
    grabberPiston.set(DoubleSolenoid.Value.kReverse);
    grabbed = false;
  }

  public boolean isGrabbed(){
    return grabbed;
  }

  //Pusher Methods
  public void pushPusher(){
    pusherPiston.set(DoubleSolenoid.Value.kForward);
    pushed = true;
  }

  // public void pullPusher(){
  //   pusherPiston.set(DoubleSolenoid.Value.kReverse);
  //   pushed = false;
  // }
  
  // public boolean isPushed(){
  //   return pushed;
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
