package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainArcadeDrive;
import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveDPadMove;
import org.usfirst.frc.team5957.robot.commands.winch.WinchTeleMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full tele-operation command. Controls drive train in arcade scheme. Controls
 * winch by holding trigger or left bumper and moving (left) stick.
 */
public class TeleopArcadeGroup extends CommandGroup {

	public TeleopArcadeGroup() {
		addParallel(new DriveTrainArcadeDrive());
		addParallel(new GearDriveDPadMove());
		addParallel(new WinchTeleMove());
	}

}