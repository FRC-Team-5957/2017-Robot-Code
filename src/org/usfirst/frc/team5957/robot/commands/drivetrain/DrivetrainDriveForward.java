package org.usfirst.frc.team5957.robot.commands.drivetrain;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the robot forward for a given time and speed. Default 10s, 25% power.
 * 
 * @author Corin Winston
 */
public class DrivetrainDriveForward extends Command {

	Timer timer = new Timer();
	double time = 10;
	double speed = 0.25;

	/**
	 * Drive forward for 10 seconds at 25% power.
	 */
	public DrivetrainDriveForward() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	/**
	 * Drives the robot forward for {@code time} seconds at 25% power.
	 * 
	 * @param time
	 *            Time in seconds to drive.
	 */
	public DrivetrainDriveForward(double time) {
		this();
		this.time = time;
	}

	/**
	 * Drives the robot forward for {@code time} seconds at {@code speed}%
	 * power.
	 * 
	 * @param time
	 *            Time in seconds to drive.
	 * @param speed
	 *            Percent of total speed at which to move.
	 */
	public DrivetrainDriveForward(double time, double speed) {
		this(time);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
		SmartDashboard.putString("Driving Forward", ((Double) time).toString().concat(" seconds"));
		Robot.oi.gyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.drive(speed, -Robot.oi.gyro.getAngle() * 0.03);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timer.get() > time) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("Drive Forward", "Done");
		Robot.driveTrain.brake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		SmartDashboard.putString("Drive Forward", "Interrupted");
	}
}
