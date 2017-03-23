package org.usfirst.frc.team5957.robot.subsystems;

import static org.usfirst.frc.team5957.robot.RobotMap.*;

import org.usfirst.frc.team5957.robot.commands.winch.WinchStop;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the winch that climbs the rope.
 * 
 * @see Subsystem
 */
public class Winch extends Subsystem {

	private VictorSP m_driveMotor;

	/**
	 * Initializes the robot's winch. Called in
	 * {@link org.usfirst.frc.team5957.robot.Robot#robotInit()
	 * Robot.robotInit()}.
	 */
	public void init() {
		m_driveMotor = new VictorSP(WINCH_DRIVE);
	}

	/**
	 * Sets the winch's speed.
	 * 
	 * @param speed
	 *            Speed at which the motor is set.
	 */
	public void set(double speed) {
		m_driveMotor.set(speed);
	}

	/**
	 * Stops the winch.
	 */
	public void stop() {
		set(0.0);
	}

	public void initDefaultCommand() {
		new WinchStop();
	}
}
