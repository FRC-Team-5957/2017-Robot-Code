package org.usfirst.frc.team5957.robot.subsystems;

import static org.usfirst.frc.team5957.robot.RobotMap.*;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This subsystem is used to interface with the camera.
 * 
 * @see Subsystem
 */
public class Vision extends Subsystem {

	public static final int IMG_HEIGHT = 480;
	public static final int IMG_WIDTH = 640;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	HttpCamera camera;

	public NetworkTable visionTable;
	public ITable retrotapeTable;
	public ITable gearTable;

	ITable axisCamera;

	public void init() {
		visionTable = NetworkTable.getTable("CameraPublisher");
		retrotapeTable = visionTable.getSubTable("Retrotape");
		gearTable = visionTable.getSubTable("Gear");
		ITable axisCamera = visionTable.getSubTable("Axis 5957");
		axisCamera.putStringArray("streams", new String[] { CAMERA_URL });
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("Gear 0 Center", gearTable.getNumber("Gear 0 Center", -1));
		SmartDashboard.putNumber("Tape One Center", gearTable.getNumber("Tape One Center", -1));
		SmartDashboard.putNumber("Tape Two Center", gearTable.getNumber("Tape Two Center", -1));	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
