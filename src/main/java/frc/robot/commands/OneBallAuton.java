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
public class OneBallAuton extends SequentialCommandGroup {
  /** Creates a new OneBallAuton. */
  public OneBallAuton(RomiDrivetrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new TurnDegrees(drivetrain, AutonPreferences.autonAngle1, 1),
    new DriveDistance(drivetrain, AutonPreferences.autonDistance1, 1),
    new TurnDegrees(drivetrain, AutonPreferences.autonAngle2, 1),
    new DriveDistance(drivetrain, AutonPreferences.autonDistance2, 1)
    );

  }
}


