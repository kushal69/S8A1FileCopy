package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

//	Main Class implementing File copying logic
public class FileCopy {
	//	Method containing logic to copy file and displaying its content
	public void copyFile(String filePath) {
		InputStream inputStream = null;											//	Declaring input stream to read the file
		OutputStream outputStream = null;										//	Declaring output stream to write the file

		try {
			File originalFile = new File(filePath);								//	Creating the file object of the original File
			String originalFileName = originalFile.getName();					//	Getting the original File Name
			//	Creating the copy File name and its path
			String copyFileName = filePath.substring(0, filePath.lastIndexOf(File.separator)) + File.separator 							
									+ (originalFileName).substring(0, originalFileName.lastIndexOf(".")) + "_COPY"
									+ "." + (originalFileName).substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
			File copyFile = new File(copyFileName);								//	Creating the file object of the copy file

			inputStream = new FileInputStream(originalFile);					//	input stream with original file object
			outputStream = new FileOutputStream(copyFile);						//	output stream with copy file object

			byte[] buffer = new byte[4096];										//	byte buffer to hold the file

			int length;
			while ((length = inputStream.read(buffer)) > 0) {					//	Loop to write the file in output stream from the buffer
				outputStream.write(buffer, 0, length);									
			}
			
			System.out.println("Entered File Path : " + filePath);
			System.out.println("Copied File Path : " + copyFileName);
			
			System.out.println("File Content of the copied file is below -> ");
			
			Scanner fileContent = new Scanner(copyFile);						//	Scanner object to read the file contents and using it to display
			
			while(fileContent.hasNextLine()){									//	Loop to display file contents from the scanner
				System.out.println(fileContent.nextLine());
			}
			
			fileContent.close();												//	Closing the scanner stream
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {																// Closing the streams to avoid memory leak
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//	Main method
	public static void main (String [] args){
		FileCopy copy = new FileCopy();											//	Class object
		
		Scanner userInput = new Scanner(System.in);								//	Scanner object to get the user input					
		
		System.out.print("Enter the file path to copy : ");
		
		copy.copyFile(userInput.nextLine());									//	Calling copy function from the class onject
		
		userInput.close();														//	Closing the scanner stream
	}
}