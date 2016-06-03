package tech.mia2b.cns.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Util {
	
	public static String readFile(String filePath){
		StringBuilder stringOut = new StringBuilder();
		String check = null;
		try (BufferedReader fileIn = new BufferedReader(new FileReader(filePath))) {
			while ((check = fileIn.readLine()) != null) {
				stringOut.append(check);
				stringOut.append(System.getProperty("line.separator"));
	         } 
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			stringOut.setLength(0);
			stringOut.append("Error Loading File!");
		}
		return stringOut.toString();
	}
	public static String readFile(File file){
		StringBuilder stringOut = new StringBuilder();
		String check = null;
		try (BufferedReader fileIn = new BufferedReader(new FileReader(file))) {
			while ((check = fileIn.readLine()) != null) {
				stringOut.append(check);
				stringOut.append(System.getProperty("line.separator"));
	         } 
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			stringOut.setLength(0);
			stringOut.append("Error Loading File!");
		}
		return stringOut.toString();
	}

	public static void writeFile (String filePath, String text){
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(filePath, false))) {
			fileOut.write(text);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to save file!");
		}
	}
	
}
