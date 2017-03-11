package org.usfirst.frc.team5957.robot.commands.door;

import org.usfirst.frc.team5957.robot.OI;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
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
		boolean singleDriver = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kGamepadOneDriver);
		Joystick stick = singleDriver ? Robot.oi.leftStick : Robot.oi.rightStick;
		int angle = stick.getPOV();

		// The DPad angles start at 0 in the up direction, and increase clockwise
		if ((angle >= 0 && angle < 90) || (angle > 270 && angle <= 360)) {
			Robot.door.set(1.0);
		} else if (angle > 90 && angle < 270) {
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
