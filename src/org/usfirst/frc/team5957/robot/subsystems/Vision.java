package org.usfirst.frc.team5957.robot.subsystems;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5957.robot.camera.GearFinderPipeline;
import org.usfirst.frc.team5957.robot.camera.TapeFinderPipeline;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *
 */
public class Vision extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	HttpCamera camera;

	public final int IMG_WIDTH = 640;
	public final int IMG_HEIGHT = 480;
	
	public NetworkTable vision;

	VisionThread gearFinder;
	public ArrayList<Double> gearXs = new ArrayList<Double>();
	public double gearCenterX = 0.0;
	public final Object gearLock = new Object();

	VisionThread tapeFinder;
	public double tapeOneCenterX = 0.0;
	public double tapeTwoCenterX = 0.0;
	public final Object tapeLock = new Object();

	public void init() {
		
		vision = NetworkTable.getTable("Vision");
		
		camera = new HttpCamera("Axis", // Unique Identifier String
				"http://10.59.57.19/axis-cgi/mjpg/video.cgi", // URL to camera
																// feed
				HttpCameraKind.kAxis); // Using Axis M1011 Camera

		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		gearFinder = new VisionThread(camera, new GearFinderPipeline(), pipeline -> {
			if (!pipeline.convexHullsOutput().isEmpty()) {
				ArrayList<MatOfPoint> hulls = pipeline.convexHullsOutput();
				for (int i = 0; i < pipeline.convexHullsOutput().size(); i++) {
					Rect rec = Imgproc.boundingRect(hulls.get(i));
					gearXs.set(i, (double) rec.x + (rec.width / 2));
				}
				
				vision.putNumberArray("Gear Centers", (Double[]) gearXs.toArray());
			}
		});
		gearFinder.start();

		tapeFinder = new VisionThread(camera, new TapeFinderPipeline(), pipeline -> {
			if (!pipeline.convexHullsOutput().isEmpty()) {
				Rect tapeOne = Imgproc.boundingRect(pipeline.convexHullsOutput().get(0));
				Rect tapeTwo = Imgproc.boundingRect(pipeline.convexHullsOutput().get(1));
				synchronized (tapeLock) {
					tapeOneCenterX = tapeOne.x + (tapeOne.width / 2);
					tapeTwoCenterX = tapeTwo.x + (tapeTwo.width / 2);
				}
			}
		});
		tapeFinder.start();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
