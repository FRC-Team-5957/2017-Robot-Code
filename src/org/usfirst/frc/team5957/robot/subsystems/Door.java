package org.usfirst.frc.team5957.robot.subsystems;

import org.usfirst.frc.team5957.robot.RobotMap;
import org.usfirst.frc.team5957.robot.commands.door.DoorHold;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem used to control the door that holds fuel in.
 * 
 * @see Subsystem
 */
public class Door extends Subsystem {

	private VictorSP m_doorMotor;

	/**
	 * Initializes the robot's door motor. Call in
	 * {@link org.usfirst.frc.team5957.robot.Robot#robotInit()
	 * Robot.robotInit()}.
	 */
	public void init() {
		m_doorMotor = new VictorSP(RobotMap.doorMotor);
	}

	/**
	 * Sets the door motor's power.
	 * @param speed Speed value from -1.0 to 1.0.
	 */
	public void set(double speed) {
		m_doorMotor.set(speed);
	}

	/**
	 * This method stops the door from moving.
	 */
	public void stop() {
		this.set(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DoorHold());
	}
}
