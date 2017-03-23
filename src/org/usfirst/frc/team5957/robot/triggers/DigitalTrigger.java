package org.usfirst.frc.team5957.robot.triggers;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This is a trigger created to model a digital input, specifically a limit
 * switch.
 * 
 * @author Jakob Burgos
 */
public class DigitalTrigger extends Trigger {

	private DigitalInput m_switch;

	/**
	 * Creates an instance of LimitSwitch.
	 * 
	 * @param port
	 *            Digital I/O port to which the switch is connected.
	 */
	public DigitalTrigger(int port) {
		m_switch = new DigitalInput(port);
	}

	public boolean get() {
		return m_switch.get();
	}
}
