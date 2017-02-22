package org.usfirst.frc.team5957.robot.commands;

import org.usfirst.frc.team5957.robot.OI.ControllerType;
import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that takes one joystick's vertical and horizontal values and
 * drives the robot with them.
 */
public class DriveTrainArcadeDrive extends Command {

    public DriveTrainArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
    	super("DriveTrainArcadeDrive");
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.brake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double forward = 0;
    	double turn = 0;
    	
    	if(Robot.oi.joystick == ControllerType.kGamepad) {
    		forward = -Robot.oi.leftStick.getRawAxis(1); // left y
    		turn    = -Robot.oi.leftStick.getRawAxis(0); // left y
    	} else if(Robot.oi.joystick == ControllerType.kFlightStick) {
    		forward = -Robot.oi.leftStick.getY();
    		turn = -Robot.oi.leftStick.getX();
    	}
    	
    	Robot.driveTrain.arcadeDrive(forward, turn);
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
