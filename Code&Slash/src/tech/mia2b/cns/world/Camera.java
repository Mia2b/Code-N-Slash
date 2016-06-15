package tech.mia2b.cns.world;

import java.util.ArrayList;

import tech.mia2b.cns.entities.Entity;

public class Camera {
	private static double CameraX = getBufferWidth();
	private static double CameraY = getBufferHeight();

	private static int bufferWidth = 1920;
	private static int bufferHeight = 1080;

	private static int renderBuffer = 64;

	private static volatile ArrayList<Entity> visibleEntities = new ArrayList<Entity>();

	public static int getCameraX() {
		return (int) CameraX;
	}

	public static void setCameraX(double cameraX) {
		CameraX = cameraX;
	}

	public static int getCameraY() {
		return (int) CameraY;
	}

	public static void setCameraY(double cameraY) {
		CameraY = cameraY;
	}

	public static void setVisibleEntities(){
		visibleEntities.clear();

		for (Entity i : new ArrayList<Entity>(Entities.getEntities())) {
			if (i.getX() < getCameraX() + (getBufferWidth() + getRenderBuffer())
					&& i.getX() > getCameraX() - (getBufferWidth() + getRenderBuffer())
					&& i.getY() < getCameraY() + (getBufferHeight() + getRenderBuffer())
					&& i.getY() > getCameraY() - (getBufferHeight() + getRenderBuffer())) {
				visibleEntities.add(i);
			}
		}
		
	}
	public static ArrayList<Entity> getVisibleEntities() {
		return visibleEntities;
	}

	public static int getRenderBuffer() {
		return renderBuffer;
	}

	public static void setRenderBuffer(int renderBuffer) {
		Camera.renderBuffer = renderBuffer;
	}

	public static int getBufferWidth() {
		return bufferWidth;
	}

	public static void setBufferWidth(int bufferWidth) {
		Camera.bufferWidth = bufferWidth;
	}

	public static int getBufferHeight() {
		return bufferHeight;
	}

	public static void setBufferHeight(int bufferHeight) {
		Camera.bufferHeight = bufferHeight;
	}
}
