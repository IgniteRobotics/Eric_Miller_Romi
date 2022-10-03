// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.Constants;

public class ArcadeDrive extends CommandBase {

  RomiDrivetrain m_drivetrain;
  Supplier<Double> m_XAxisSpeedSupplier;
  Supplier<Double> m_YAxisSpeedSupplier;
  Supplier<Double> m_SpeedMultiplier;

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
    double XAxisSpeed = m_XAxisSpeedSupplier.get();
    double YAxisSpeed = m_YAxisSpeedSupplier.get();
    double XFinalSpeed = 0.7 * Math.pow(XAxisSpeed, Constants.k_XAxisExponent);
    double YFinalSpeed = 0.7 * Math.pow(YAxisSpeed, Constants.k_YAxisExponent);
    m_drivetrain.arcadeDrive(XFinalSpeed, YFinalSpeed);
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
