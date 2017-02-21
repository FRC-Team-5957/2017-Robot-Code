package org.usfirst.frc.team5957.robot.subsystems;

import org.usfirst.frc.team5957.robot.RobotMap;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * The subsystem used to interface with the camera.
 * 
 * @see Subsystem
 */
public class Vision extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	HttpCamera camera;

	public NetworkTable visionTable;
	public ITable retrotapeTable;
	public ITable gearTable;

	ITable axisCamera;

	public final int IMG_WIDTH = 640;
	public final int IMG_HEIGHT = 480;

	public void init() {
		visionTable = NetworkTable.getTable("CameraPublisher");
		retrotapeTable = visionTable.getSubTable("Retrotape");
		gearTable = visionTable.getSubTable("Gear");
		ITable axisCamera = visionTable.getSubTable("Axis 5957");
		axisCamera.putStringArray("streams", new String[] { RobotMap.cameraURL });
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
