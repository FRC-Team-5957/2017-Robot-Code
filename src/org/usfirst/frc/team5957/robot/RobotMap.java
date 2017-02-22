package org.usfirst.frc.team5957.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Motor Controller Ports
	public static final int LEFT_FRONT_MOTOR = 1;
	public static final int RIGHT_FRONT_MOTOR = 3;
	public static final int LEFT_BACK_MOTOR = 0;
	public static final int RIGHT_BACK_MOTOR = 2;
	public static final int DOOR_MOTOR = 4;
	public static final int GEAR_DRIVE = 5;

	// Axis Camera Stream URL
	public static final String cameraURL = "http://10.59.57.19/axis-cgi/mjpg/video.cgi";

	// DI/O Ports
	public static final int doorLimit = 0;

}
