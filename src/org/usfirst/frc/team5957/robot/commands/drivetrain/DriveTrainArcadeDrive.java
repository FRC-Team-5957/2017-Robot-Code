package org.usfirst.frc.team5957.robot.commands.drivetrain;

import org.usfirst.frc.team5957.robot.OI.ControlScheme;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that takes one joystick's vertical and horizontal values and drives
 * the robot with them.
 * 
 * @author Jakob Burgos
 */
public class DriveTrainArcadeDrive extends Command {

	public DriveTrainArcadeDrive() {
		// Use requires() here to declare subsystem dependencies
		super("DriveTrainArcadeDrive");
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		String oldControls = SmartDashboard.getString("Controls", "");
		String cont = "";
		if (Robot.oi.joystick == ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == ControlScheme.kFlightStickTwoDrivers) {
			cont = "Move Forwards/Backwards: Stick 1 Forwards/Backwards\n"
				 + "Turn Left/Right: Stick 1 Left/Right\n";
		} else if (Robot.oi.joystick == ControlScheme.kGamepadOneDriver
				|| Robot.oi.joystick == ControlScheme.kGamepadTwoDrivers) {
			cont = "Move Forwards/Backwards: Gamepad 1 Left Stick Forwards/Backwards\n"
				 + "Turn Left/Right: Gamepad 1 Left Stick Left/Right\n";
		}
		SmartDashboard.putString("Controls", oldControls + cont);
		Robot.driveTrain.brake();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double forward = 0;
		double turn = 0;

		if (Robot.oi.joystick == ControlScheme.kGamepadOneDriver
				|| Robot.oi.joystick == ControlScheme.kGamepadTwoDrivers) {
			// If using (a) game pad(s), get the X and Y from the left stick of
			// the first plugged in controller.
			forward = -Robot.oi.leftStick.getRawAxis(1); // left y
			turn = -Robot.oi.leftStick.getRawAxis(0); // left x
		} else if (Robot.oi.joystick == ControlScheme.kFlightStickOneDriver
				|| Robot.oi.joystick == ControlScheme.kFlightStickTwoDrivers) {
			// If using (a) flight stick(s), get the X and Y from the first one
			// plugged in.
			forward = -Robot.oi.leftStick.getY();
			turn = -Robot.oi.leftStick.getX();
		} else {
			SmartDashboard.putString("Arcade Drive", "Arcade Drive is not available on your scheme.");
		}

		Robot.driveTrain.arcadeDrive(forward, turn);
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
