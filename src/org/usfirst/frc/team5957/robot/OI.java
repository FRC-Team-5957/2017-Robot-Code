package org.usfirst.frc.team5957.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	/**
	 * The current ControlScheme being used by the driver(s).
	 */
	public ControlScheme joystick = ControlScheme.kFlightStickOneDriver;

	// Current Alliance
	public DriverStation.Alliance color;
	
	// Current Auto Location
	public Location location;

	// Ports for Joysticks/Controllers
	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);

	// Sensors
	public Gyro gyro;

	public OI() {
		gyro = new ADXRS450_Gyro();
		color = DriverStation.getInstance().getAlliance();
	}

	/**
	 * Pushes sensor data to the SmartDashboard.
	 * 
	 * @author Jakob Burgos
	 */
	public void dashboardUpdate() {
		// Robot.vision.updateDashboard();
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
	 * 
	 * @author Jakob Burgos
	 */
	public void changeJoystick(ControlScheme joystickType) {
		if (this.joystick != joystickType) {
			this.joystick = joystickType;
		}
	}
	
	/**
	 * Changes location if the argument {@code location} is different
	 * from the OI's field {@code location}.
	 * 
	 * @param joystickType
	 *            The desired control scheme.
	 * 
	 * @author Jakob Burgos
	 */
	public void changeLocation(Location location) {
		if (this.location != location) {
			this.location = location;
		}
	}

	/**
	 * Enum holding possible methods of controlling the robot.
	 * 
	 * @author Jakob Burgos
	 */
	public enum ControlScheme {
		kFlightStickOneDriver(0), kGamepadOneDriver(1), kFlightStickTwoDrivers(2), kGamepadTwoDrivers(3);

		public final int value;

		private ControlScheme(int value) {
			this.value = value;
		}
	}

	public enum Location {
		kBoilerTape(0), kLoadingStationTape(1), kMiddle(2);

		public final int value;

		private Location(int value) {
			this.value = value;
		}
	}
}
