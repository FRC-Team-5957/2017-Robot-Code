package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.commands.door.DoorDPadMove;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainArcadeDrive;
import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveDPadMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full tele-operation command. Controls drive train in arcade scheme. Controls
 * door with D-Pad.
 */
public class TeleopArcadeGroup extends CommandGroup {

	public TeleopArcadeGroup() {
		addParallel(new DriveTrainArcadeDrive());
		addParallel(new GearDriveDPadMove());
		addSequential(new DoorDPadMove());
	}

}