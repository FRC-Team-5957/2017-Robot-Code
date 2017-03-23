package org.usfirst.frc.team5957.robot.commands.winch;

import org.usfirst.frc.team5957.robot.OI;
import org.usfirst.frc.team5957.robot.Robot;
import org.usfirst.frc.team5957.robot.OI.ControlScheme;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command for controlling the winch during teleop.
 */
public class WinchTeleMove extends Command {

	public WinchTeleMove() {
		requires(Robot.winch);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		String oldControls = SmartDashboard.getString("Controls", "");
		String cont = "";
		if (Robot.oi.joystick == ControlScheme.kFlightStickOneDriver) {
			cont = "Winch Reel In: Stick 1 Button 3\n"
					+ "Winch Unreel: Stick 1 Button 5\n";
		} else if (Robot.oi.joystick == ControlScheme.kGamepadOneDriver) {
			cont = "Winch Reel In: Gamepad 1 Left Trigger (Variable Speed)\n"
					+ "Winch Unreel: Gamepad 1 Left Trigger + Bumper (Variable Speed)\n";
		} else if (Robot.oi.joystick == ControlScheme.kFlightStickTwoDrivers) {
			cont = "Winch Reel In: Stick 2 Button 3\n"
					+ "Winch Unreel: Stick 2 Button 5\n";
		} else if (Robot.oi.joystick == ControlScheme.kGamepadTwoDrivers) {
			cont = "Winch Reel In: Gamepad 2 Left Trigger (Variable Speed)\n"
					+ "Winch Unreel: Gamepad 2 Trigger + Bumper (Variable Speed)\n";
		}
		SmartDashboard.putString("Controls", oldControls + cont);
		Robot.winch.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		boolean singleDriver = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kGamepadOneDriver);
		boolean flightStick = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kFlightStickTwoDrivers);
		
		Joystick stick = singleDriver ? Robot.oi.leftStick : Robot.oi.rightStick;

		if (flightStick && stick.getRawButton(3)) {
			Robot.winch.set(1);
		} else if (flightStick && stick.getRawButton(5)) {
			Robot.winch.set(-1);
		} else if (stick.getRawButton(5)) {
			Robot.winch.set(-stick.getRawAxis(2));
		} else {
			Robot.winch.set(stick.getRawAxis(2));
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
