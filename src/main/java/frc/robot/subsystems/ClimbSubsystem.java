/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {

  // public DoubleSolenoid frontPiston = new DoubleSolenoid(RobotMap.CLIMB_FRONT_A.value, RobotMap.CLIMB_FRONT_B.value);
  public DoubleSolenoid backPiston = new DoubleSolenoid(RobotMap.CLIMB_BACK_A.value, RobotMap.CLIMB_BACK_B.value);

  // private boolean frontUp = false;
  private boolean backUp = false;

  // public void frontUp(){
  //   frontPiston.set(DoubleSolenoid.Value.kForward);
  //   frontUp = true;
  // }

  // public void frontDown(){
  //   frontPiston.set(DoubleSolenoid.Value.kReverse);
  //   frontUp = false;
  // }

  // public boolean frontIsUp(){
  //   return frontUp;
  // }

  public void backUp(){
    backPiston.set(DoubleSolenoid.Value.kForward);
    backUp = true;
  }

  public void backDown(){
    backPiston.set(DoubleSolenoid.Value.kReverse);
    backUp = false;
  }

  public boolean backIsUp(){
    return backUp;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
