// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;



public class TurnDegrees extends CommandBase {
  /** Creates a new TurnDegrees. */
  double arcLength;
  double Limit;
  RomiDrivetrain m_drivetrain;

  public TurnDegrees(RomiDrivetrain drivetrain,double degrees, double speedLimiter) {
    double inchPerDegree = Math.PI * 5.551 / 360;
    arcLength = inchPerDegree * degrees + Math.signum(inchPerDegree * degrees);
    Limit = speedLimiter;
    m_drivetrain = drivetrain;
    
    addRequirements(m_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.stop();
    m_drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double Diff = arcLength - getEncoderAverage();
    double Rotation = Math.signum(Diff) * Limit * Math.cbrt(Math.log(Math.abs(Diff))/Math.log(Math.abs(arcLength)));
    m_drivetrain.arcadeDrive(0, Rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double Diff = arcLength - getEncoderAverage();
    if (Diff < 1.1 && Diff > 0.9){
      return true;
    }
    return false;
  }

  private double getEncoderAverage(){
    double average = (m_drivetrain.getLeftDistanceInch() + m_drivetrain.getRightDistanceInch())/2;
    return average;
  }
}