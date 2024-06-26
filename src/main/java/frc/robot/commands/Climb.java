// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Climb extends Command {
  public Climber climber;
  /** Creates a new Climb. */
  
  //public BangBangController controller = new BangBangController();

  public Climb(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    climber.setSpeed(Constants.ClimberConstants.kClimberSpeed);

    /*if (climber.getLeftCurrent() >= 45 || climber.getRightCurrent() >= 45 ) {

      climber.setSpeed(0);

    }*/
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
    //climber.setRainbowBoolean(false);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    return false;
    
  }
}
