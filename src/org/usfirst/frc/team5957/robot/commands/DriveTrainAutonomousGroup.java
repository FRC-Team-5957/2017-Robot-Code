package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.commands.drivetrain.DrivetrainDriveForward;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DrivetrainTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTrainAutonomousGroup extends CommandGroup {

    public DriveTrainAutonomousGroup() {
        addSequential(new DrivetrainDriveForward(3.88));
        addSequential(new DrivetrainTurn(-60));
        addSequential(new DrivetrainDriveForward(0.99));
    }
}