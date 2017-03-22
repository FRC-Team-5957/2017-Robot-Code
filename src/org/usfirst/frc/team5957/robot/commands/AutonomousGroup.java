package org.usfirst.frc.team5957.robot.commands;

//import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainAimAtLift;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DrivetrainDriveForward;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DrivetrainTurn;
//import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveExtend;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full autonomous command. Starts with the left side on the left tape and ends
 * at the left lift.
 */
public class AutonomousGroup extends CommandGroup {

	public AutonomousGroup() {
		addSequential(new DrivetrainDriveForward(3.88));
		addSequential(new DrivetrainTurn(-60));
		addSequential(new DrivetrainDriveForward(0.99));
		// addSequential(new DriveTrainAimAtLift());
		// addSequential(new GearDriveExtend());
		addSequential(new DrivetrainDriveForward(0.99, -0.25));
		addSequential(new DrivetrainTurn(60));
		addSequential(new DrivetrainDriveForward(3.80, -0.25));
	}
}