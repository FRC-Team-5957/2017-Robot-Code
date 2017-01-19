package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainTurnToGear extends Command {

    public DriveTrainTurnToGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTrainTurnToGear");
    	requires(Robot.driveTrain);
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double centerX;
    	synchronized (Robot.vision.imgLock) {
    		centerX = Robot.vision.gearCenterX;
    	}
    	double turn = (centerX * Math.pow(Robot.vision.IMG_HEIGHT, -1) - 1); // Converts X position to number from -1.0 to 1.0
    	Robot.driveTrain.tankDrive(turn, -turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
