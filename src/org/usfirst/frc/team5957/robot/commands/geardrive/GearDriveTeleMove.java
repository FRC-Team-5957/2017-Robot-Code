package org.usfirst.frc.team5957.robot.commands.geardrive;

import org.usfirst.frc.team5957.robot.OI;
import org.usfirst.frc.team5957.robot.Robot;
import org.usfirst.frc.team5957.robot.OI.ControlScheme;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command for controlling the gear coil during teleop.
 */
public class GearDriveTeleMove extends Command {

	public GearDriveTeleMove() {
		requires(Robot.gearDrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		String oldControls = SmartDashboard.getString("Controls", "");
		String cont = "";
		if (Robot.oi.joystick == ControlScheme.kFlightStickOneDriver) {
			cont = "Extend Coil: Stick 1 Button 2\n"
					+ "Retract Coil: Stick 1 Button 4\n";
		} else if (Robot.oi.joystick == ControlScheme.kGamepadOneDriver) {
			cont = "Extend Coil: Gamepad 1 Right Trigger (Variable Speed)\n"
					+ "Retract Coil: Gamepad 1 Right Trigger + Bumper (Variable Speed)\n";
		} else if (Robot.oi.joystick == ControlScheme.kFlightStickTwoDrivers) {
			cont = "Extend Coil: Stick 2 Button 2\n"
					+ "Retract Coil: Stick 2 Button 4\n";
		} else if (Robot.oi.joystick == ControlScheme.kGamepadTwoDrivers) {
			cont = "Extend Coil: Gamepad 2 Right Trigger (Variable Speed)\n"
					+ "Retract Coil: Gamepad 2 Trigger + Bumper (Variable Speed)\n";
		}
		SmartDashboard.putString("Controls", oldControls + cont);
		Robot.gearDrive.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		boolean singleDriver = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kGamepadOneDriver);
		boolean flightStick = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kFlightStickTwoDrivers);
		
		Joystick stick = singleDriver ? Robot.oi.leftStick : Robot.oi.rightStick;

		if (flightStick && stick.getRawButton(4)) {
			Robot.winch.set(1);
		} else if (flightStick && stick.getRawButton(6)) {
			Robot.winch.set(-1);
		} else if (stick.getRawButton(6)) {
			Robot.winch.set(-stick.getRawAxis(3));
		} else {
			Robot.winch.set(stick.getRawAxis(3));
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.gearDrive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.gearDrive.stop();
	}
}
