package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that turns towards a lift, using the camera and the retroreflective
 * tape one either side of the lift.
 */
public class DriveTrainAimAtLift extends Command {

	private boolean isDone = false;

	public DriveTrainAimAtLift() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("DriveTrainAimAtLift");
		requires(Robot.driveTrain);
		// Does not require vision
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double centerOneX;
		double centerTwoX;
		double centerX;

		synchronized (Robot.vision.tapeLock) {
			centerOneX = Robot.vision.tapeOneCenterX;
			centerTwoX = Robot.vision.tapeTwoCenterX;
		}
		centerX = (centerOneX + centerTwoX) / 2;
		// Converts X position to number from -1.0 to 1.0
		double turn = (centerX * Math.pow(Robot.vision.IMG_HEIGHT, -1) - 1);

		if (turn > 0.25 || turn < -0.25) {
			Robot.driveTrain.tankDrive(turn, -turn);
		} else if (turn < 0) {
			Robot.driveTrain.tankDrive(-0.25, 0.25);
		} else if (turn > 0) {
			Robot.driveTrain.tankDrive(0.25, -0.25);
		}

		if (turn < 0.1 && turn > -0.1) {
			isDone = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (isDone) {
			return true;
		}

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
