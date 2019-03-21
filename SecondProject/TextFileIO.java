package SecondProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileIO {
	
	public void createNewFile(String fileName){
		PrintWriter outStream = null;
		try{
		outStream = new PrintWriter(fileName); //create a new blank file
		} catch(FileNotFoundException e) {
//			e.printStackTrace();
			System.err.println("Tried to create new blank file by the name of " + fileName);
			System.err.println("The exception message was  " + e.getMessage());
			
		}
		finally{
			if(outStream != null){
				outStream.close();//close the coonection to the file if not already null
			}
			System.out.println("The createNewFile Method is done the file was: "+ fileName);
		}
	}
	
	public void writeToNewFile(String fileName, String text){
		PrintWriter outStream = null;
		try {
			outStream  = new PrintWriter(new FileOutputStream(fileName,false));//create a new blank file
			outStream.println(text);
			outStream.flush();
		} catch (FileNotFoundException e) {
			System.err.println("Tried to write to a new file by the name of "+fileName);
			System.err.println(e.getMessage());
		}
		finally{
			if(outStream !=null){
				outStream.close();
			}
			System.out.println("The writeToNewFile method is done");
		}
	}
	
	public void writeToFile(String fileName, String text){
		PrintWriter outStream = null;
		try{
//			outStream= new PrintWriter(fileName);
			outStream = new PrintWriter(new FileOutputStream(fileName, true));//boolean true means preserve existing file content, if set true-> false it will not preserve 
			outStream.println(text);//append line to the end of the file
			outStream.flush();//send to destination
		}catch(FileNotFoundException e){
			System.err.println("Tried to create new blank file by the name of " + fileName);
			System.err.println("The exception message was  " + e.getMessage());
		}finally{
			if(outStream != null){
				outStream.close();//close the coonection to the file if not already null
			}
			System.out.println("The createNewFile Method is done the file was: "+ fileName +
					"The text was: "+ text);
		}
	}
	public void writeToFileFromKeyboard(String fileName) {
		System.out.println("We will write whatever you type to a file");
		Scanner kb = new Scanner(System.in);//declare and initialize inputStream from System.in
		PrintWriter outStream = null;//declare outputStream
		String stopwriting = "EOF";
		String lineIn = "";
		try {
			outStream = new PrintWriter(new FileOutputStream(fileName, true));
			do{
			System.out.println("Type a line:...");//guide
			lineIn = kb.nextLine();//get line from user
			outStream.println(lineIn);
			outStream.flush();
			}while (kb.hasNextLine() && !lineIn.equals(stopwriting));
			
		}
			catch(FileNotFoundException e){
				System.err.println("\nTried to write from keyboard to file - name of file " + fileName);
				System.err.println("The exception message was  " + e.getMessage());
			}
		finally{
			if(outStream != null){
				outStream.close();//close the coonection to the file if not already null
			}
			if(kb != null){
				kb.close();
			}
			System.out.println("The writeToFileFromKeyboard Method is done the file was: "+ fileName);
		}
	}
	public void readFileDisplayOnConsole(String fileName) {
		Scanner inStream = null;
		String lineIn = "";
		try {
			File theFile = new File(fileName);
//			theFile.
			if(theFile.exists() && theFile.canRead()){
				inStream = new Scanner(theFile);
				while(inStream.hasNextLine()){
					System.out.println(inStream.nextLine());//print the next line from the file to system.out
				}
			}
		}catch(FileNotFoundException e){
			System.err.println("PROBLEM INSIDE readFileDisplayOnConsole method ");
			System.err.println(e.getMessage());
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
			System.out.println("The readFileDisplayOnConsole Method is done the file was: "+ fileName);
		}
	}
	public void readFileDisplayFUNNYOnConsole(String fileName) {
		Scanner inStream = null;
		String lineIn = "";
		try {
			File theFile = new File(fileName);
//			theFile.
			if(theFile.exists() && theFile.canRead()){
				inStream = new Scanner(theFile);
				while(inStream.hasNextLine()){
					String modLine = inStream.nextLine();
					modLine = modLine.replaceAll("a", "z");
					modLine = modLine.replaceAll("", "*");
					System.out.println(modLine);//print the next line from the file to system.out
				}
			}
		}catch(FileNotFoundException e){
			System.err.println("PROBLEM INSIDE readFileDisplayOnConsole method ");
			System.err.println(e.getMessage());
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
			System.out.println("The readFileDisplayOnConsole Method is done the file was: "+ fileName);
		}
	}
	public boolean copyFile(String fileName) {
		Scanner inStream = null;
		PrintWriter outStream = null;
		String lineIn = "";
		String fileNameCopy = fileName.substring(0, fileName.lastIndexOf("."))+ "_copy"+fileName.substring(fileName.lastIndexOf("."));
		boolean status = false;
		try{
			inStream = new Scanner(new File(fileName));
			outStream =  new PrintWriter(new FileOutputStream(fileNameCopy,true));
			while(inStream.hasNextLine()){
				lineIn = inStream.nextLine();
				outStream.println();
			}
			
		}catch (FileNotFoundException e){
			System.err.println("Could not copy file "+ fileName);
			System.err.println(e.getMessage());
			return false;
		}
		finally{
			if(inStream != null){
				inStream.close();
				outStream.close();
			}
			if(outStream != null){
			}
		}
		status = true;//all went well
		return status;
	}
	public void readDelimitedFileDisplayOnConsole(String fileName) {
		Scanner inStream = null;
//		String lineIn = "";
		try {
			File theFile = new File(fileName);
			if(theFile.exists() && theFile.canRead()){
				inStream = new Scanner(theFile);
//				inStream.useDelimiter(",");//specify a comma as thedelimeter
				while(inStream.hasNextLine()){
					String line = inStream.nextLine();//get the line
					String[] splitLine = line.split(",");//specify a coma as the dlimeter
					for(int i=0; i<splitLine.length; i++){
						System.out.println(splitLine[i]+ "\t|\t");
					}
//					String token = inStream.nextLine();//get a line from the file
//					System.out.println(inStream.nextLine());//print the next line from the file to system.out
				}
			}
		}catch(FileNotFoundException e){
			System.err.println("PROBLEM INSIDE readDelimitedFileDisplayOnConsole method ");
			System.err.println(e.getMessage());
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
			System.out.println("The readDelimitedFileDisplayOnConsole Method is done the file was: "+ fileName);
		}
	}
}
