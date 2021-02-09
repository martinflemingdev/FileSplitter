package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSplitter {

	public static void main(String[] args) {

		try(Scanner userInput = new Scanner(System.in)) {
			System.out.print("Where is the input file (please include the path to the file)?");
			String inputFilePath = userInput.nextLine();

			// Check if file exists and if it is a file, not directory
			File inputFile = new File(inputFilePath);
			if(inputFile.exists() == false) { 
				System.out.println(inputFilePath + " does not exist");
				System.exit(1);
			} else if(inputFile.isFile() == false) {
				System.out.println(inputFilePath + " is not a file");
				System.exit(1);
			}

			System.out.print("How many lines of text (max) should there be in the split files?");
			int linesPerFile = userInput.nextInt();
			int numOfFiles = 1;

			// Start scanner and read the user's input file
			try(Scanner fileScanner = new Scanner(inputFile)) {
				while(fileScanner.hasNextLine()) {
					
					// create newFile with name based on input file's name and numOfFiles
					String newFileName = inputFile.getName();
					File newFile = new File(newFileName + "-" + numOfFiles);
					
					// Start PrintWriter which creates/overwrites new file to append to
					try(PrintWriter fileWriter = new PrintWriter(new FileOutputStream(newFile),true)){
						
					// iterate through lines up to user's linesPerFile and append them into new file
						for (int i = 0; i < linesPerFile; i++) {
							if(fileScanner.hasNextLine() ) {
								fileWriter.append(fileScanner.nextLine() + "\n");
							}
						}// print filenames to console and iterate numOfFiles
						System.out.println("Generating: " + newFile.getPath());
					}	numOfFiles++;
				}
			}catch(FileNotFoundException e) {
				System.out.println("File not found.");
			}catch(Exception ex) {
				System.out.println("A general error has occured :(");
			}
		}
	}
}
