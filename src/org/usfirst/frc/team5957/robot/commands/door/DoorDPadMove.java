package org.usfirst.frc.team5957.robot.commands.door;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DoorDPadMove extends Command {

	public DoorDPadMove() {
		requires(Robot.door);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.door.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int angle = Robot.oi.leftStick.getPOV();
		if (angle == 0) {
			Robot.door.set(1.0);
		} else if (angle == 180) {
			Robot.door.set(-1.0);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.door.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.door.stop();
	}
}
