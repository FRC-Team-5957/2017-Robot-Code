package org.usfirst.frc.team5957.robot.commands.winch;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that pushes or pulls a gear with the screw conveyor. By default,
 * expires after 3 seconds and pushes at full speed.
 */
public class WinchClimb extends Command {

	Timer timer = new Timer();
	double time = 3.0;
	double speed = 1.0;

	/**
	 * Creates and starts a new {@code WinchClimb} command.
	 */
	public WinchClimb() {
		requires(Robot.winch);
	}

	/**
	 * Creates and starts a new {@code WinchClimb} command.
	 * 
	 * @param time
	 *            How long to wait before ending.
	 */
	public WinchClimb(double time) {
		this();
		this.time = time;
	}

	/**
	 * Creates and starts a new {@code WinchClimb} command.
	 * 
	 * @param time
	 *            How long to wait before ending.
	 * @param speed
	 *            Speed to push, with a maximum of 1.0. Negative to pull.
	 */
	public WinchClimb(double time, double speed) {
		this(time);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.winch.stop();
		timer.reset();
		timer.start();
		SmartDashboard.putString((speed > 0 ? "Climbing " : "Lowering ") + "Winch",
				((Double) time).toString().concat(" seconds"));
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.winch.set(speed);
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
		Robot.winch.stop();
		SmartDashboard.putString((speed > 0 ? "Climbing " : "Lowering ") + "Winch", "Finished.");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.winch.stop();
		SmartDashboard.putString((speed > 0 ? "Climbing " : "Lowering ") + "Winch", "Interrupted.");
	}
}
