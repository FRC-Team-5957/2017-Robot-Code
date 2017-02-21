package org.usfirst.frc.team5957.robot.commands.drivetrain;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainDriveForward extends Command {
	
	Timer timer = new Timer();
	double time = 10;

    public DrivetrainDriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
    
    public DrivetrainDriveForward(double time) {
    	this();
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	SmartDashboard.putString("Driving Forward", ((Double) time).toString().concat(" seconds"));
    	Robot.oi.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(0.25, -Robot.oi.gyro.getAngle()*0.03);
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timer.get() > time)
    	{ 
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Drive Forward", "Done");
    	Robot.driveTrain.brake();
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("Drive Forward", "Interrupted");
    }
}
