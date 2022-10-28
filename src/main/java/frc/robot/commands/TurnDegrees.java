// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.MathUtil;


public class TurnDegrees extends CommandBase {
  /** Creates a new TurnDegrees. */
  double arcLength;
  double Limit;
  RomiDrivetrain m_drivetrain;

  public TurnDegrees(RomiDrivetrain drivetrain, Supplier<Double> degrees, double speedLimiter) {
    double unsuplied = degrees.get();
    double inchPerDegree = Math.PI * 5.551 / 360;
    arcLength = inchPerDegree * unsuplied + Math.signum(inchPerDegree * unsuplied);
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
    SmartDashboard.putNumber("ArcLength", arcLength);
    
    double Diff = arcLength - Math.signum(arcLength) * getEncoderAverage();
    double Rotation = Math.signum(arcLength) * Limit * MathUtil.clamp(Math.log(Math.abs(Diff))/Math.log(Math.abs(arcLength)), 0, 1) ;
    m_drivetrain.arcadeDrive(0, Rotation);
    SmartDashboard.putNumber("Diff", Diff);
    SmartDashboard.putNumber("Rotation", Rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double Diff = arcLength - getEncoderAverage();
    if (Diff < 1.1){
      return true;
    }
    return false;
  }

  private double getEncoderAverage(){
    double average = (Math.abs(m_drivetrain.getLeftDistanceInch()) + Math.abs(m_drivetrain.getRightDistanceInch()))/2;
    return average;
  }


  private double DetermineNegative (double number){
    if (number < 0){
      return 0;
    }

    else return 1;

  }
}