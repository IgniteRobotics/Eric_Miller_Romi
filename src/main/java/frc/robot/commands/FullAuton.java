// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.Parameters.AutonPreferences;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FullAuton extends SequentialCommandGroup {
  /** Creates a new FullAuton. */
  public FullAuton(RomiDrivetrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ThreeBallAuton(drivetrain),
      new DriveDistance(drivetrain, AutonPreferences.autonDistance8, 1),
      new TurnDegrees(drivetrain, AutonPreferences.autonAngle7, 1),
      new DriveDistance(drivetrain, AutonPreferences.autonDistance9, 1),
      new TurnDegrees(drivetrain, AutonPreferences.autonAngle8, 1)

    );
  }
}
