package tech.mia2b.cns.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tech.mia2b.cns.entities.Entity;

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
	
	public static void quickSort(ArrayList<Entity> out, Entity ent) {
		mainQuickSort(out, 0, out.size() - 1, ent);
	}

	static void mainQuickSort(ArrayList<Entity> out, int left, int right, Entity ent) {
		int index = quickSortPartition(out, left, right,ent);
		if (left < (index - 1))
			mainQuickSort(out, left, index - 1,ent);
		if (right > index)
			mainQuickSort(out, index, right,ent);
	}

	static int quickSortPartition(ArrayList<Entity> out, int left, int right, Entity ent) {
		int center = out.get((left + right) / 2).getDistanceFrom(ent);
		while (left <= right) {
			while (out.get(left).getDistanceFrom(ent) < center) {
				left++;
			}
			while (out.get(right).getDistanceFrom(ent) > center) {
				right--;
			}
			if (left <= right) {
				Entity temp = out.get(left);
				out.set(left, out.get(right));
				out.set(right, temp);
				left++;
				right--;
			}
		}
		return left;
	}
	
}
