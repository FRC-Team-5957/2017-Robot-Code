package org.usfirst.frc.team5957.robot.commands.door;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DoorOpen extends Command {

	Timer timer = new Timer();
	double speed = 1.0;
	
    public DoorOpen() {
        requires(Robot.door);
    }
    
    public DoorOpen(double speed) {
    	this();
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Opening Door", "Starting...");
    	Robot.door.stop();
    	timer.reset();
    	Robot.door.set(1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Opening Door", "" + timer.get() + " seconds...");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.oi.doorLimit.get()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Opening Door", "Done. " + timer.get() + " seconds.");
    	Robot.door.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("Opening Door", "Interrupted at " + timer.get() + " seconds.");
    	Robot.door.stop();
    }
}
