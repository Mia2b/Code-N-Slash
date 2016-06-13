package tech.mia2b.cns.world;

import java.util.ArrayList;

import javafx.scene.input.KeyEvent;

public class Input {
	
	private static double mouseX = 0;
	private static double mouseY = 0;
	private static boolean mousePressed = false;
	private static ArrayList<String> keys = new ArrayList<String>();

	public static void addKey(KeyEvent e) {
		String code = e.getCode().toString();
         // only add once... prevent duplicates
         if ( !keys.contains(code) )
             keys.add( code );
	}

	public static void removeKey(KeyEvent e) {
		String code = e.getCode().toString();
		keys.remove(code);
	}
	
	public static boolean hasKey(String code){
		return keys.contains(code);
	}
	
	public static ArrayList<String> getKeys(){
		return keys;
	}

	public static boolean isMousePressed() {
		return mousePressed;
	}

	public static void setMousePressed(boolean mousePressed) {
		Input.mousePressed = mousePressed;
	}

	public static double getMouseX() {
		return mouseX;
	}

	public static void setMouseX(double mouseX) {
		Input.mouseX = mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}

	public static void setMouseY(double mouseY) {
		Input.mouseY = mouseY;
	}
	
	
}
