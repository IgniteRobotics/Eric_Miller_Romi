// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.Constants;

public class ArcadeDrive extends CommandBase {

  RomiDrivetrain m_drivetrain;
  Supplier<Double> m_XAxisSpeedSupplier;
  Supplier<Double> m_YAxisSpeedSupplier;
  Supplier<Double> m_SpeedMultiplierInput;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(RomiDrivetrain drivetrain, Supplier<Double> LeftXAxis, Supplier<Double> RightYAxis, Supplier<Double> SpeedMultiplier) {
    m_drivetrain = drivetrain;
    m_XAxisSpeedSupplier = LeftXAxis;
    m_YAxisSpeedSupplier = RightYAxis;
    m_SpeedMultiplierInput = SpeedMultiplier;
    addRequirements(drivetrain);

    // Use addRequirements() here to declare subsystem dependencies.
  

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double XAxisInput = m_XAxisSpeedSupplier.get();
    double YAxisInput = m_YAxisSpeedSupplier.get();
    SmartDashboard.putNumber("LeftStick", XAxisInput);
    SmartDashboard.putNumber("RightStick", YAxisInput);
    double XAxisSpeed = Math.signum(XAxisInput) * Math.pow(Math.abs(XAxisInput), Constants.k_XAxisExponent);
    double YAxisSpeed = Math.signum(YAxisInput) * Math.pow(Math.abs(YAxisInput), Constants.k_YAxisExponent);
    double blah = m_SpeedMultiplierInput.get();
    double m_SpeedMultiplier = 1/(2 + blah);

    XAxisSpeed = -XAxisSpeed * m_SpeedMultiplier;
    YAxisSpeed = -YAxisSpeed * m_SpeedMultiplier;
    
    
    m_drivetrain.arcadeDrive(XAxisSpeed, YAxisSpeed);
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
