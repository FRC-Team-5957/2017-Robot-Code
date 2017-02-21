package org.usfirst.frc.team5957.robot.triggers;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class LimitSwitch extends Trigger {

	private DigitalInput m_switch;

	public LimitSwitch(int port) {
		m_switch = new DigitalInput(port);
	}

	public boolean get() {
		return m_switch.get();
	}
}
