package org.usfirst.frc.team5957.robot.subsystems;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5957.robot.camera.GearFinderPipeline;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	HttpCamera camera;
	
	public final int IMG_WIDTH  = 640;
	public final int IMG_HEIGHT = 480;
	
	VisionThread gearFinder;
	public double gearCenterX = 0.0;
	public final Object imgLock = new Object();
	
	public void init() {
        camera = new HttpCamera("Axis",                       // Unique Identifier String
        		"http://10.59.57.19/axis-cgi/mjpg/video.cgi", // URL to camera feed
        		HttpCameraKind.kAxis);                        // Using Axis M1011 Camera
        
        camera.setResolution(640, 480);
        
        gearFinder = new VisionThread(camera, new GearFinderPipeline(), pipeline -> {
            if (!pipeline.convexHullsOutput().isEmpty()) {
                Rect r = Imgproc.boundingRect(pipeline.convexHullsOutput().get(0));
                synchronized (imgLock) {
                    gearCenterX = r.x + (r.width / 2);
                }
            }
        });
        gearFinder.start();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

