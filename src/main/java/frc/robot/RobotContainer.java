// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.igniterobotics.robotbase.preferences.DoublePreference;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FullAuton;
import frc.robot.commands.OneBallAuton;
import frc.robot.commands.TwoBallAuton;
import frc.robot.commands.ThreeBallAuton;
import frc.robot.commands.TurnDegrees;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveDistance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Parameters.AutonPreferences;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain m_RomiDrivetrain = new RomiDrivetrain();
  private final XboxController m_controller = new XboxController(0);
  private final ArcadeDrive arcadeDriveCommand = new ArcadeDrive(m_RomiDrivetrain, () -> m_controller.getRawAxis(1),() -> m_controller.getRawAxis(3), () -> m_controller.getRawAxis(5));
  


  private final ExampleCommand m_autoCommand = new ExampleCommand(m_RomiDrivetrain);

  private JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
  private JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);

  private DoublePreference Drive = new DoublePreference("DriveDistanceCommand", -5);
  private DoublePreference Turn = new DoublePreference("TurnDistanceCommand", -90);
  
  DriveDistance driveDistanceCommand = new DriveDistance(m_RomiDrivetrain, Drive, 1);
  TurnDegrees turnDegreesCommand = new TurnDegrees(m_RomiDrivetrain, Turn, 1);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    setDefaultCommands();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    buttonA.whenHeld(driveDistanceCommand);
    buttonB.whenHeld(turnDegreesCommand);
  
  }

  private void setDefaultCommands(){
    m_RomiDrivetrain.setDefaultCommand(arcadeDriveCommand);
  }

  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Double Number = AutonPreferences.AutonType.getValue();
    // An ExampleCommand will run in autonomous
    if (Number == 1 ) return new OneBallAuton(m_RomiDrivetrain);

    else if (Number == 2) return new TwoBallAuton(m_RomiDrivetrain);

    else if (Number == 3) return new ThreeBallAuton(m_RomiDrivetrain);

    else if (Number == 4) return new FullAuton(m_RomiDrivetrain);

    else return new ExampleCommand(m_RomiDrivetrain);
  }
}
