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
  double leftDiff;
  double rightDiff;
  /** Creates a new DriveDistance. */
  public DriveDistance(RomiDrivetrain InputedDrivetrain, double InputedDistance, double InputedSpeedReducer) {
    drivetrain = InputedDrivetrain;
    Distance = InputedDistance + Math.signum(InputedDistance);
    SpeedReducer = InputedSpeedReducer;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.stop();
    drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  leftDiff = Distance - drivetrain.getLeftDistanceInch();
  rightDiff = Distance - drivetrain.getRightDistanceInch();
  double leftSpeed = Math.signum(leftDiff) * SpeedReducer * Math.cbrt(Math.log(Math.abs(leftDiff))/Math.log(Math.abs(Distance)));
  double rightSpeed = Math.signum(rightDiff) * SpeedReducer * Math.cbrt(Math.log(Math.abs(rightDiff))/Math.log(Math.abs(Distance)));

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
    double AbsLeftDiff = Math.abs(Distance - drivetrain.getLeftDistanceInch());
    double AbsRightDiff = Math.abs(Distance - drivetrain.getRightDistanceInch());
    if(AbsLeftDiff < 1.1 && AbsLeftDiff > 0.9 && AbsRightDiff < 1.1 && AbsRightDiff > 0.9){
      return true;
    }
    return false;
  }
}
