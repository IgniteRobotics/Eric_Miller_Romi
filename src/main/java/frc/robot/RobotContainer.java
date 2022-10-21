// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.OneBallAuton;
import frc.robot.commands.TurnDegrees;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveDistance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



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
  private final ArcadeDrive arcadeDriveCommand = new ArcadeDrive(m_RomiDrivetrain, () -> m_controller.getLeftY(),() -> m_controller.getRightX(), () -> m_controller.getRawAxis(5));
  


  private final ExampleCommand m_autoCommand = new ExampleCommand(m_RomiDrivetrain);

  private JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
  private JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
  
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
    // An ExampleCommand will run in autonomous
    return new OneBallAuton(m_RomiDrivetrain);
  }
}
