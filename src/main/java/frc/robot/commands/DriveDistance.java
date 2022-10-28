// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.math.MathUtil;



public class DriveDistance extends CommandBase {
  RomiDrivetrain drivetrain;
  double Distance;
  double SpeedReducer;
  double Diff;
  double rightDiff;
  Supplier<Double> SupplierDistance;
  /** Creates a new DriveDistance. */
  public DriveDistance(RomiDrivetrain InputedDrivetrain, Supplier<Double> InputedDistance, double InputedSpeedReducer) {
    SupplierDistance = InputedDistance;
    drivetrain = InputedDrivetrain;
    SpeedReducer = InputedSpeedReducer;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.stop();
    drivetrain.resetEncoders();
    double Unsuplied = SupplierDistance.get();
    Distance = Unsuplied + Math.signum(Unsuplied);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  Diff = Distance - (drivetrain.getLeftDistanceInch() + drivetrain.getRightDistanceInch())/2;
  double Speed = Math.signum(Distance) * SpeedReducer * MathUtil.clamp((Math.log(Math.abs(Diff))/Math.log(Math.abs(Distance))), 0, 1);
  
  drivetrain.arcadeDrive(Speed, 0);;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double AbsDiff = Math.abs(Distance - (drivetrain.getLeftDistanceInch() + drivetrain.getRightDistanceInch())/2);
  
    if(AbsDiff < 1.1){
      return true;
    }
    return false;
  }
}
