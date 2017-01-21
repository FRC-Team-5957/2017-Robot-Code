package org.usfirst.frc.team5957.robot.subsystems;

import org.usfirst.frc.team5957.robot.RobotMap;
import org.usfirst.frc.team5957.robot.commands.DriveTrainBrake;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP leftFront;
	VictorSP rightFront;
	VictorSP leftBack;
	VictorSP rightBack;
	RobotDrive roboDrive;
	
	public void init() {
		leftFront  = new VictorSP(RobotMap.leftFrontMotor);
		rightFront = new VictorSP(RobotMap.rightFrontMotor);
		leftBack   = new VictorSP(RobotMap.leftBackMotor);
		rightBack  = new VictorSP(RobotMap.rightBackMotor);
		roboDrive      = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
	}
	
	public void brake() {
		roboDrive.drive(0, 0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTrainBrake());
    }
}

