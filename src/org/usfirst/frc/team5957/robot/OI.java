package org.usfirst.frc.team5957.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5957.robot.commands.door.DoorHold;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainArcadeDrive;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainBrake;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainTankDrive;
import org.usfirst.frc.team5957.robot.triggers.LimitSwitch;

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

	public ControllerType joystick = ControllerType.kFlightStick;

	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);
	Button buttonThree = new JoystickButton(leftStick, 3);
	Button buttonFour = new JoystickButton(leftStick, 4);
	Button buttonFive = new JoystickButton(leftStick, 5);
	Button buttonNine = new JoystickButton(leftStick, 9);
	Button buttonTen = new JoystickButton(leftStick, 10);
	Button[] buttons = new Button[] { buttonThree, buttonFour, buttonFive, buttonNine, buttonTen };
	// Sensors
	public Gyro gyro;
	public LimitSwitch doorLimit;

	public OI() {
		gyro = new ADXRS450_Gyro();
		doorLimit = new LimitSwitch(RobotMap.doorLimit);

		buttonThree.whenPressed(new DriveTrainTankDrive());
		buttonFour.whenPressed(new DriveTrainArcadeDrive());
		buttonFive.whenPressed(new DriveTrainBrake());
		doorLimit.whenActive(new DoorHold());
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
	 * Changes control scheme if the argument {@code joystick} is different from
	 * the OI's field {@code joystick}.
	 * 
	 * @param joystick
	 *            The desired control scheme.
	 */
	public void changeJoystick(ControllerType joystickType) {
		if (this.joystick != joystickType) {
			this.joystick = joystickType;
		}
	}

	public enum ControllerType {
		kFlightStick(0), kGamepad(1);

		public final int value;

		private ControllerType(int value) {
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
