package org.usfirst.frc.team5957.robot.commands.geardrive;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that pushes or pulls a gear with the screw conveyor. By default,
 * expires after 3 seconds and pushes at full speed.
 * 
 * @author Jakob Burgos
 */
public class GearDriveExtend extends Command {

	Timer timer = new Timer();
	double time = 3.0;
	double speed = 1.0;

	/**
	 * Creates and starts a new {@code GearDriveExtend} command.
	 */
	public GearDriveExtend() {
		requires(Robot.gearDrive);
	}

	/**
	 * Creates and starts a new {@code GearDriveExtend} command.
	 * 
	 * @param time
	 *            How long to wait before ending.
	 */
	public GearDriveExtend(double time) {
		this();
		this.time = time;
	}

	/**
	 * Creates and starts a new {@code GearDriveExtend} command.
	 * 
	 * @param time
	 *            How long to wait before ending.
	 * @param speed
	 *            Speed to push, with a maximum of 1.0. Negative to pull.
	 */
	public GearDriveExtend(double time, double speed) {
		this(time);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.gearDrive.stop();
		timer.reset();
		timer.start();
		SmartDashboard.putString((speed > 0 ? "Extending " : "Retracting ") + "Gear",
				((Double) time).toString().concat(" seconds"));
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gearDrive.set(speed);
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
		Robot.gearDrive.stop();
		SmartDashboard.putString((speed > 0 ? "Extending " : "Retracting ") + "Gear", "Finished.");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.gearDrive.stop();
		SmartDashboard.putString((speed > 0 ? "Extending " : "Retracting ") + "Gear", "Interrupted.");
	}
}
