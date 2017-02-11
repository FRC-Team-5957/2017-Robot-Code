package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.OI.ControllerType;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that takes two joysticks' vertical values and drives the robot with them.
 */
public class DriveTrainTankDrive extends Command {

    public DriveTrainTankDrive() {
        // Use requires() here to declare subsystem dependencies
    	super("DriveTrainTankDrive");
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.brake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPower = 0;
    	double rightPower = 0;
    	
    	if(Robot.oi.joystick == ControllerType.kGamepad) {
    		leftPower = -Robot.oi.leftStick.getRawAxis(1); // left y
    		rightPower = -Robot.oi.leftStick.getRawAxis(5); // right y
    	} else if(Robot.oi.joystick == ControllerType.kFlightStick) {
    		leftPower = Robot.oi.leftStick.getY();
    		rightPower = Robot.oi.rightStick.getY();
    	}
	
    	Robot.driveTrain.tankDrive(leftPower, rightPower);
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
