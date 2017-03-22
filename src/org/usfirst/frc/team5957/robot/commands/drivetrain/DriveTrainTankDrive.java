package org.usfirst.frc.team5957.robot.commands.drivetrain;

import org.usfirst.frc.team5957.robot.OI.ControlScheme;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that takes two joysticks' vertical values and drives the robot with
 * them.
 * 
 * @author Jakob Burgos
 */
public class DriveTrainTankDrive extends Command {

	public DriveTrainTankDrive() {
		// Use requires() here to declare subsystem dependencies
		super("DriveTrainTankDrive");
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		String oldControls = SmartDashboard.getString("Controls", "");
		String cont = "";
		if (Robot.oi.joystick == ControlScheme.kFlightStickOneDriver) {
			cont = "Left Side: Stick 1 Forwards/Backwards\n"
				 + "Right Side: Stick 2 Forwards/Backwards\n";
		} else if (Robot.oi.joystick == ControlScheme.kGamepadOneDriver
				|| Robot.oi.joystick == ControlScheme.kGamepadTwoDrivers) {
			cont = "Left Side: Gamepad 1 Left Stick Forwards/Backwards\n"
				 + "Right Side: Gamepad 1 Right Stick Forwards/Backwards\n";
		}
		SmartDashboard.putString("Controls", oldControls + cont);
		Robot.driveTrain.brake();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftPower = 0;
		double rightPower = 0;

		if (Robot.oi.joystick == ControlScheme.kGamepadOneDriver
				|| Robot.oi.joystick == ControlScheme.kGamepadTwoDrivers) {
			leftPower = -Robot.oi.leftStick.getRawAxis(1); // left y
			rightPower = -Robot.oi.leftStick.getRawAxis(5); // right y
		} else if (Robot.oi.joystick == ControlScheme.kFlightStickOneDriver) {
			// Can't drive using both sticks if there's another driver.
			leftPower = -Robot.oi.leftStick.getY();
			rightPower = -Robot.oi.rightStick.getY();
		} else {
			SmartDashboard.putString("Tank Drive", "Tank Drive is not available on your scheme.");
		}

		Robot.driveTrain.tankDrive(leftPower, rightPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.brake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.brake();
	}
}
