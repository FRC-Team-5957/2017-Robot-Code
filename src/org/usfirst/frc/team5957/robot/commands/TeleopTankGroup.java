package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainTankDrive;
import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveTeleMove;
import org.usfirst.frc.team5957.robot.commands.winch.WinchTeleMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full tele-operation command. Controls drive train in arcade scheme. Controls
 * winch by holding trigger or left bumper and moving (left) stick.
 */
public class TeleopTankGroup extends CommandGroup {

	public TeleopTankGroup() {
		addParallel(new DriveTrainTankDrive());
		addParallel(new GearDriveTeleMove());
		addParallel(new WinchTeleMove());
	}

}