package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.commands.door.DoorDPadMove;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainTankDrive;
import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveDPadMove;
import org.usfirst.frc.team5957.robot.commands.winch.WinchTeleMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full tele-operation command. Controls drive train in arcade scheme. Controls
 * door with D-Pad.
 */
public class TeleopTankGroup extends CommandGroup {

	public TeleopTankGroup() {
		addParallel(new DriveTrainTankDrive());
		addParallel(new GearDriveDPadMove());
		addParallel(new WinchTeleMove());
		addSequential(new DoorDPadMove());
	}

}