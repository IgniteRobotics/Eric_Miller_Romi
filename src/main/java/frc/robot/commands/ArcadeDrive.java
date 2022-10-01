// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class ArcadeDrive extends CommandBase {

  RomiDrivetrain m_drivetrain;
  Supplier<Double> m_XAxisSpeedSupplier;
  Supplier<Double> m_YAxisSpeedSupplier;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(RomiDrivetrain drivetrain, Supplier<Double> LeftXAxis, Supplier<Double> RightYAxis) {
    m_drivetrain = drivetrain;
    m_XAxisSpeedSupplier = LeftXAxis;
    m_YAxisSpeedSupplier = RightYAxis;
    addRequirements(drivetrain);

    // Use addRequirements() here to declare subsystem dependencies.
  

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_XAxisSpeedSupplier.get(), m_YAxisSpeedSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
