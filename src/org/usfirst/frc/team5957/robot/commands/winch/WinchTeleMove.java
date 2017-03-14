package org.usfirst.frc.team5957.robot.commands.winch;

import org.usfirst.frc.team5957.robot.OI;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchTeleMove extends Command {

	public WinchTeleMove() {
		requires(Robot.winch);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.winch.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		boolean singleDriver = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kGamepadOneDriver);
		boolean flightStick = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kFlightStickTwoDrivers);
		Joystick stick = singleDriver ? Robot.oi.leftStick : Robot.oi.rightStick;

		if (flightStick && stick.getTrigger()) {
			Robot.winch.set(stick.getY());
		} else if (stick.getRawButton(5)) {
			Robot.winch.set(stick.getRawAxis(1));
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.winch.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.winch.stop();
	}
}
