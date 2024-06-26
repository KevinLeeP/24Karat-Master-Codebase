// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeDown extends Command {
  private Intake intake;
  //private DutyCycleEncoder encoder;
  private Encoder encoder;

  private TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(7.5, 4.25);
  private ProfiledPIDController controller = new ProfiledPIDController(.01, 0,  0, constraints); //p=0.9 i = 0.09

  // double setpoint;
  // double kP;
  // double Ki;
  // double Kd;
  double error;
  double lastError;
  // double integral;
  // double derivative;
  // double avgPos;
   double motorPower;

  boolean done = false;

  double encoderDistance;


  public IntakeDown(Intake intake) {
    this.intake = intake;
    this.encoder = intake.getArmEncoder();

    encoderDistance = encoder.getDistance();


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    // setpoint = Constants.IntakeConstants.kUpPosition;
    // kP = 0.07;
    // Ki = 0;
    // Kd = 0.01;
    // lastError = 0;
    // integral = 0;
    // derivative = 0;
    // avgPos = intake.getArmEncoder().getDistance();
    // motorPower = 0;

    //error = Constants.IntakeConstants.kArmUpPosition - encoder.getDistance();
    // integral = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //intake.setArmSpeed(Constants.IntakeConstants.kArmDownSpeed);


      controller.setGoal(Constants.IntakeConstants.kArmDownPosition);

      encoderDistance = encoder.getDistance();

      if (encoder.getDistance() > 0){
        encoderDistance = 0;
      }

      motorPower = controller.calculate(Math.abs(encoder.getDistance()));

      System.out.println(motorPower);

      if(motorPower < 0.5){
        motorPower = 0.5;
      }

      if (motorPower > 6){
        motorPower = 6;
      }


      if (encoder.getDistance() < -955){
      motorPower = 0.4;
      }



      intake.setArmSpeed(Math.abs(motorPower));

    //assume up is positive motor speed
    
    // if(!(Math.abs(error) > (Math.abs(Constants.IntakeConstants.kArmUpPosition) / 1.5))){
    //   done = true;
    // }
    //error = Constants.IntakeConstants.kArmUpPosition - encoderDistance;
    // if(encoder.getDistance()<Constants.IntakeConstants.kArmDownPosition+10 || encoder.getDistance()<Constants.IntakeConstants.kArmDownPosition-10){
    //   done = true;
    // }

    if (encoder.getDistance() < -1010){
      intake.setArmSpeed(-0.1);
      done = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setArmSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(done){
      return true;
    }    
    return false;
  }
}
