package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.OI.Location;
import org.usfirst.frc.team5957.robot.Robot;
//import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainAimAtLift;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DrivetrainDriveForward;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DrivetrainTurn;
//import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveExtend;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full autonomous command. Starts with the left side on the left tape and ends
 * at the left lift.
 */
public class AutonomousGroup extends CommandGroup {

	public AutonomousGroup() {
		if (Robot.oi.location == Location.kBoilerTape && Robot.oi.color == DriverStation.Alliance.Red) {
			addSequential(new DrivetrainDriveForward(3.88));
			addSequential(new DrivetrainTurn(-60));
			addSequential(new DrivetrainDriveForward(0.99));
			// addSequential(new DriveTrainAimAtLift());
			// addSequential(new GearDriveExtend());
			Timer.delay(3);
			addSequential(new DrivetrainDriveForward(0.99, -0.25));
			addSequential(new DrivetrainTurn(60));
			addSequential(new DrivetrainDriveForward(3.80, -0.25));
		} else if (Robot.oi.location == Location.kBoilerTape && Robot.oi.color == DriverStation.Alliance.Blue) {
			addSequential(new DrivetrainDriveForward(3.88));
			addSequential(new DrivetrainTurn(60));
			addSequential(new DrivetrainDriveForward(0.99));
			// addSequential(new DriveTrainAimAtLift());
			// addSequential(new GearDriveExtend());
			Timer.delay(3);
			addSequential(new DrivetrainDriveForward(0.99, -0.25));
			addSequential(new DrivetrainTurn(-60));
			addSequential(new DrivetrainDriveForward(3.80, -0.25));
		}
	}
}