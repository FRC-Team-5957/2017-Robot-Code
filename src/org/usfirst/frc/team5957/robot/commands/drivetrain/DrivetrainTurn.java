package org.usfirst.frc.team5957.robot.commands.drivetrain;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns the robot to a given degree. Default 90 degrees right.
 * 
 * @author Corin Winston
 */
public class DrivetrainTurn extends Command {
	double angle = 90;

	/**
	 * Turns 90 degrees right.
	 */
	public DrivetrainTurn() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	/**
	 * Turns {@code angle} degrees right.
	 * 
	 * @param angle Degrees to turn. Negative = Left, Positive = Right.
	 */
	public DrivetrainTurn(double angle) {
		this();
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putString("Turning", ((Double) angle).toString().concat(" degrees"));
		Robot.oi.gyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (angle < 0) {
			Robot.driveTrain.tankDrive(0.33, -0.33);
		} else {
			Robot.driveTrain.tankDrive(-0.33, 0.33);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(Robot.oi.gyro.getAngle()) > Math.abs(angle)) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("Turning", "Done");
		Robot.driveTrain.brake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		SmartDashboard.putString("Turning", "Interrupted");
		Robot.driveTrain.brake();
	}
}
