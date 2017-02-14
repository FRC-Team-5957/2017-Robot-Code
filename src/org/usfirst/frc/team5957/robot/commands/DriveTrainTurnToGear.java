package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that turns the robot to face a gear using the camera.
 */
public class DriveTrainTurnToGear extends Command {

	private boolean isDone = false;

	public DriveTrainTurnToGear() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("DriveTrainTurnToGear");
		requires(Robot.driveTrain);
		// Does not require vision
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putNumber("Gear X", 0xDEADBEEF);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Double[] defaultCenter = {};
		double centerX = Robot.vision.vision.getNumberArray("Gear Centers", defaultCenter)[0];

		if(centerX == 0.0) {
			Robot.driveTrain.tankDrive(-0.25, 0.25);
			return;
		}
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
