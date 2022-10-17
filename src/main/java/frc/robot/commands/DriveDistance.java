// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;



public class DriveDistance extends CommandBase {
  RomiDrivetrain drivetrain;
  double Distance;
  double SpeedReducer;
  double k_power = 3;
  double leftDiff;
  double rightDiff;
  /** Creates a new DriveDistance. */
  public DriveDistance(RomiDrivetrain InputedDrivetrain, double InputedDistance, double InputedSpeedReducer) {
    drivetrain = InputedDrivetrain;
    Distance = InputedDistance;
    SpeedReducer = InputedSpeedReducer;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  leftDiff = Distance - drivetrain.getLeftDistanceInch();
  rightDiff = Distance - drivetrain.getRightDistanceInch();
  double leftSpeed = Math.signum(leftDiff) * SpeedReducer *  k_power * Math.log(Math.abs(leftDiff))/Math.log(Math.pow(Distance, k_power));
  double rightSpeed = Math.signum(rightDiff) * SpeedReducer * k_power * Math.log(Math.abs(rightDiff))/Math.log(Math.pow(Distance, k_power));

  drivetrain.Motors(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    leftDiff = Distance - drivetrain.getLeftDistanceInch();
    rightDiff = Distance - drivetrain.getRightDistanceInch();
    if(leftDiff < 0.1 && leftDiff > -0.1 && rightDiff < 0.1 && rightDiff > -0.1){
      return true;
    }
    return false;
  }
}
