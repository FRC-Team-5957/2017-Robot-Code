package org.usfirst.frc.team5957.robot.commands.drivetrain;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot at the power given in the constructor. Default 25%.
 * 
 * @author Jakob Burgos
 */
public class DriveTrainDriveAtPower extends Command {

	double power = 0.25;
	
    public DriveTrainDriveAtPower() {
    	requires(Robot.driveTrain);
    }
    
    public DriveTrainDriveAtPower(double power) {
    	this();
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.brake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(power, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.brake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.brake();
    }
}
