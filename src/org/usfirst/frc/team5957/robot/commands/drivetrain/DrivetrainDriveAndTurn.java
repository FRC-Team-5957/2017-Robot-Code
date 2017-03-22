package org.usfirst.frc.team5957.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Test autonomous command.
 * 
 * @deprecated No longer needed for testing.
 * 
 * @author Corin Winston
 *
 */
public class DrivetrainDriveAndTurn extends CommandGroup {

	public DrivetrainDriveAndTurn() {

		addSequential(new DrivetrainDriveForward(5));
		addSequential(new DrivetrainTurn());
		addSequential(new DrivetrainDriveForward(3));
	}
}
