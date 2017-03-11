package org.usfirst.frc.team5957.robot.commands.door;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DoorMove extends Command {

	Timer timer = new Timer();
	double speed = 1.0;
	boolean opening = true;

	public DoorMove() {
		requires(Robot.door);
	}

	public DoorMove(double speed) {
		this();
		if (Math.abs(speed) > 1.0)
			throw new RuntimeException("Door speed cannot be greater than +/- 1.0.");
		this.opening = (speed >= 0.0) ? true : false;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putString("Moving Door", "Starting...");
		Robot.door.stop();
		timer.reset();
		Robot.door.set(1.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putString("Moving Door", "" + timer.get() + " seconds; " + this.speed + " speed.");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (opening && Robot.oi.doorOpened.get()) {
			return true;
		} else if (!opening && Robot.oi.doorClosed.get()) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("Moving Door", "Done. " + timer.get() + " seconds.");
		Robot.door.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		SmartDashboard.putString("Moving Door", "Interrupted at " + timer.get() + " seconds.");
		Robot.door.stop();
	}
}
