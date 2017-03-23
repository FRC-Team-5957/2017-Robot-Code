package org.usfirst.frc.team5957.robot.subsystems;

import static org.usfirst.frc.team5957.robot.RobotMap.*;
import org.usfirst.frc.team5957.robot.commands.geardrive.GearDriveStop;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the screw conveyor that holds and moves gears.
 * 
 * @see Subsystem
 */
public class GearDrive extends Subsystem {

	private VictorSP m_driveMotor;

	/**
	 * Initializes the robot's screw drive. Called in
	 * {@link org.usfirst.frc.team5957.robot.Robot#robotInit()
	 * Robot.robotInit()}.
	 */
	public void init() {
		m_driveMotor = new VictorSP(GEAR_DRIVE);
	}
	
	/**
	 * Sets the screw drive's speed.
	 * 
	 * @param speed Speed at which the motor is set.
	 */
	public void set(double speed) {
		m_driveMotor.set(speed);
	}
	
	/**
	 * Stops the screw.
	 */
	public void stop() {
		set(0.0);
	}

	public void initDefaultCommand() {
		new GearDriveStop();
	}
}
