package tech.mia2b.cns.world;

import java.util.ArrayList;

import javafx.scene.input.KeyEvent;

public class Input {
	private static ArrayList<String> keys = new ArrayList<String>();

	public static void addKey(KeyEvent e) {
		String code = e.getCharacter();
         // only add once... prevent duplicates
         if ( !keys.contains(code) )
             keys.add( code );
	}

	public static void removeKey(KeyEvent e) {
		String code = e.getCharacter();
		keys.remove(code);
	}
	
	public static boolean hasKey(String code){
		return keys.contains(code);
	}
	
	
}
