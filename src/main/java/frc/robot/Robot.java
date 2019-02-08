/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.*;
import edu.wpi.first.networktables.*;

//import edu.wpi.first.wpilibj.Preferences;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveBase dt = new DriveBase();
  public static HatchSubsystem hatch = new HatchSubsystem();
  public static BallIntakeSubsystem BI = new BallIntakeSubsystem();
  public static ElevatorSubsystem elevator = new ElevatorSubsystem();
  public static OI m_oi;
  public static UltrasonicCodeTesting ultrasonic = new UltrasonicCodeTesting();

  //Create Network Table Objects
  NetworkTableEntry x;
  NetworkTableEntry y;
  NetworkTableEntry isDetected;


  //Preferences prefs;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    
    // NumberConstants.gyroKP = prefs.getDouble("gyroKP", 0.0);
    // NumberConstants.gyroKI = prefs.getDouble("gyroKI", 0.0);
    // NumberConstants.gyroKD = prefs.getDouble("gyroKD", 0.0);

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(160, 120);

    AxisCamera axiscamera = new AxisCamera("axisCamera","http://10.49.39.93/mjpg/video.mjpg");

    m_chooser.setDefaultOption("Do Nothing", new DoNothing());
    m_chooser.addOption("Sample Auto", new SampleAuto());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

      // Set up and populate the networkTable
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("SmartDashboard");
    x = table.getEntry("X");
    y = table.getEntry("Y");
    isDetected= table.getEntry("isDetected");

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Accumulated angle: ", dt.angle());
    SmartDashboard.putNumber("Yaw: ", dt.getAhrs().getYaw());
    SmartDashboard.putNumber("Roll: ", dt.getAhrs().getRoll());
    SmartDashboard.putNumber("Pitch: ", dt.getAhrs().getPitch());
    SmartDashboard.putNumber("Ultrasonic Distance: ", ultrasonic.getInches());
    SmartDashboard.putNumber("Ultrasonic Voltage: ", ultrasonic.getVoltage());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}