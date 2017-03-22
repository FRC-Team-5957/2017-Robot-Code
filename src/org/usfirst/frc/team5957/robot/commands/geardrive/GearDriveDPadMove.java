package org.usfirst.frc.team5957.robot.commands.geardrive;

import org.usfirst.frc.team5957.robot.OI;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearDriveDPadMove extends Command {

	public GearDriveDPadMove() {
		requires(Robot.gearDrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.gearDrive.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		boolean singleDriver = (Robot.oi.joystick == OI.ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == OI.ControlScheme.kGamepadOneDriver);
		Joystick stick = singleDriver ? Robot.oi.leftStick : Robot.oi.rightStick;
		int angle = stick.getPOV();
		
		if (stick.getRawButton(10)) {
			Robot.gearDrive.set(1.0);
		} else if (stick.getRawButton(12)) {
			Robot.gearDrive.set(-1.0);
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
