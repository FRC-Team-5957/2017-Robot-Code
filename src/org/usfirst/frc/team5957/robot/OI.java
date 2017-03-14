package org.usfirst.frc.team5957.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5957.robot.commands.door.DoorMove;
import org.usfirst.frc.team5957.robot.triggers.DigitalTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.

	public ControlScheme joystick = ControlScheme.kFlightStickOneDriver;

	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);

	// Sensors
	public Gyro gyro;
	public DigitalTrigger doorOpened;
	public DigitalTrigger doorClosed;

	public OI() {
		gyro = new ADXRS450_Gyro();
		doorOpened = new DigitalTrigger(RobotMap.DOOR_OPEN);
		doorClosed = new DigitalTrigger(RobotMap.DOOR_CLOSE);

		doorClosed.whileActive(new DoorMove(0.5));
		doorOpened.whileActive(new DoorMove(-0.5));
	}

	/**
	 * Pushes sensor data to the SmartDashboard.
	 */
	public void dashboardUpdate() {
		SmartDashboard.putNumber("Left Y", leftStick.getY());
		SmartDashboard.putNumber("Left X", leftStick.getX());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}

	/**
	 * Changes control scheme if the argument {@code joystickType} is different
	 * from the OI's field {@code joystickType}.
	 * 
	 * @param joystickType
	 *            The desired control scheme.
	 */
	public void changeJoystick(ControlScheme joystickType) {
		if (this.joystick != joystickType) {
			this.joystick = joystickType;
		}
	}

	public enum ControlScheme {
		kFlightStickOneDriver(0), kGamepadOneDriver(1), kFlightStickTwoDrivers(2), kGamepadTwoDrivers(3);

		public final int value;

		private ControlScheme(int value) {
			this.value = value;
		}
	}
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
