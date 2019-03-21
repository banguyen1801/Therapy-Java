package SecondProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import files.TextFileIO;

public class Therapy_GUI extends JFrame implements ActionListener, Therapy  {
	private JPanel mainPanel,StartPanel,questionPanel,questionButton,finishPanel,finishButtons;
	private JButton STARTSESSION, NEXTQUESTION, VIEWALLQUESTION, VIEWLONGESTWORD, VIEWSHORTESTWORD, SORTWORDALPHABETICALLY, VIEWANALYSIS;
	private JTextField answer;
	private JTextArea textarea;
	private JScrollPane scroll;
	JLabel welcome, questionInfo;
	
	int counter = 0;
	String[] question = new String[10];
	String[] statement;
	
	
	private Font title = new Font("Serif", Font.BOLD, 40);
	private Font questionI = new Font("Serif", Font.PLAIN,20);
	
	
	
	TextFileIO file;
	
	BufferedReader reader = null;
    BufferedWriter writer = null;
    ArrayList<String> lines;
    
	public Therapy_GUI() {
		
		//question list
//		question[0]= "Question 1: How are you feeling?";
//		question[1]= "Question 2: How do you describe yourself in 1 words?";
//		question[2]= "Question 3: What is your favorite ice scream flavor?";
//		question[3]= "Question 4: What animal will you choose as your only pet?";
//		question[4]= "Question 5: What is your favourite dish?";
//		question[5]= "Question 6: Would you rather a boring ethernal life or a happy short life?";
//		question[6]= "Question 7: What kind of people do you hate the most?";
//		question[7]= "Question 8: Name your favourite hobby";
//		question[8]= "Question 9: How do you describe your best friend in 1 word?";
//		question[9]= "Question 10: Would you like to live with your parents or your girlfriend/boyfriend?";
		question[0]= "Question 1: Which three words describe you best?";
		question[1]= "Question 2: Which is your best feature?";
		question[2]= "Question 3: Which common saying or phrase describes you?";
		question[3]= "Question 4: What’s the best thing that’s happened to you this week?";
		question[4]= "Question 5: Who was your role model when you were a child?";
		question[5]= "Question 6: Who was your favorite teacher and why?";
		question[6]= "Question 7: What was your favorite subject at school?";
		question[7]= "Question 8: What did you want to be when you grew up?";
		question[8]= "Question 9: If you could have one wish come true what would it be?";
		question[9]= "Question 10: Which would you prefer — three wishes over five years or one wish right now?";

		
		
		//statement list
		statement = new String[10];
		statement[0]= "";
		statement[1]= "";
		statement[2]= "";
		statement[3]= "";
		statement[4]= "";
		statement[5]= "";
		statement[6]= "";
		statement[7]= "";
		statement[8]= "";
		statement[9]= "";
		
		//add mainPanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		//Label for start session
		welcome = new JLabel("Welcome to my artificial therapy project!");
		welcome.setFont(title);
		questionInfo = new JLabel("");

		
		//add StartPanel and STARTSESSON BUTTON
		StartPanel = new JPanel();
		mainPanel.add(StartPanel, BorderLayout.PAGE_END);
		STARTSESSION = new JButton("Start Session");
		STARTSESSION.setPreferredSize(new Dimension(256,128));
		STARTSESSION.addActionListener(this);
		StartPanel.add(STARTSESSION, BorderLayout.SOUTH);
		
		//JTextField for answer
		answer = new JTextField("");
		file = new TextFileIO();
		
		//final panel
		VIEWALLQUESTION = new JButton("View all Q & A");
		VIEWLONGESTWORD = new JButton("View longest word");
		SORTWORDALPHABETICALLY = new JButton("Sort answer alphabetically");
		VIEWALLQUESTION.addActionListener(this);
		VIEWLONGESTWORD.addActionListener(this);
		SORTWORDALPHABETICALLY.addActionListener(this);
		textarea = new JTextArea();
		textarea.setEditable(false);
		
		VIEWSHORTESTWORD = new JButton("View shortest word");
		VIEWANALYSIS = new JButton("View analysis");
		VIEWSHORTESTWORD.addActionListener(this);
		VIEWANALYSIS.addActionListener(this);
	
		
		add(mainPanel);
		mainPanel.add(welcome, BorderLayout.PAGE_START);
		
//		create patient session file
//		file = new TextFileIO();
//		file.createNewFile("Patient");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton)e.getSource();
		String btnClicked = e.getActionCommand();
		if(btnClicked.equalsIgnoreCase("Start Session")){
			source.setText("Next");
			welcome.setVisible(false);
			questionInfo.setText(question[counter]);
			questionInfo.setFont(questionI);
			mainPanel.add(questionInfo, BorderLayout.PAGE_START);
			mainPanel.add(answer, BorderLayout.CENTER);
			System.out.println(counter);
			counter++;
			
		}
		else if(btnClicked.equalsIgnoreCase("Next")){
			file.writeToFile("Patient.txt", questionInfo.getText());
			file.writeToFile("Patient.txt", answer.getText());
			file.writeToFile("PatientAnswer.txt", answer.getText());
			if(counter < question.length){
			System.out.println(counter);
			questionInfo.setText(question[counter]);


			answer.setText("");
			counter++;
			
			}
			else{
				
				answer.setText("");
				source.setText("Finish");
				
				
				int rep = JOptionPane.showConfirmDialog(null, "Questions are over, are you ready for the result?", "Result", JOptionPane.YES_NO_OPTION);
				if (rep == JOptionPane.YES_OPTION) {
					answer.setVisible(false);
					questionInfo.setVisible(false);
					
				} else {
					int repFinal = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "WHAT?!!", JOptionPane.YES_NO_OPTION);
					if (repFinal == JOptionPane.YES_OPTION){
						counter = 0;
						JOptionPane.showMessageDialog(null, "GOODBYE");
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(null, "Please Press Finish Button!");
					}
				}
				
			}
		}//end Start Session
		if(btnClicked.equalsIgnoreCase("Finish")){
			STARTSESSION.setVisible(false); //hide next/finish button
			mainPanel.add(textarea, BorderLayout.CENTER);
			StartPanel.add(VIEWALLQUESTION);
			StartPanel.add(VIEWLONGESTWORD);
			StartPanel.add(VIEWSHORTESTWORD);
			StartPanel.add(SORTWORDALPHABETICALLY);
			StartPanel.add(VIEWANALYSIS);
			
			scroll = new JScrollPane(textarea);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			mainPanel.add(scroll);
		}
		if(btnClicked.equalsIgnoreCase("View all Q & A")){
			textarea.setText(ReadFile("Patient.txt"));
		}
		if(btnClicked.equalsIgnoreCase("View longest word")){
			try {
				textarea.setText(getLongestWords("PatientAnswer.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if(btnClicked.equalsIgnoreCase("View shortest word")){
			try {
				textarea.setText(getShortestWords("PatientAnswer.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if(btnClicked.equalsIgnoreCase("Sort answer alphabetically")){
			SortingAlphabetically("PatientAnswer.txt");
			textarea.setText(ReadFile("PatientAnswer.txt"));
		}
		if(btnClicked.equalsIgnoreCase("View analysis")){
			String l = null;
			try {
				l = getLongestWords("PatientAnswer.txt");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String s = null;
			try {
				s = getShortestWords("PatientAnswer.txt");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String analysis = "Wow "+ "[" + l + "]" + " and " + "[" + s + "]" + " seemed to be very important to you!"  ;
			JOptionPane.showMessageDialog(null, analysis);
		}
		
		//Start Sesson Button
//		if(btnClicked.equals("STARTSESSON")){
////			welcome.setVisible(false);
////			welcome.setEnabled(false);
//			StartPanel.setVisible(false);
//			StartPanel.setEnabled(false);
//			
//			questionPanel.add(welcome, BorderLayout.PAGE_START);
//			questionPanel.setVisible(true);
//			questionPanel.setEnabled(true);
//			questionButton.setVisible(true);
//			questionButton.setEnabled(true);
//			
//			
//				
//		}
//		if(btnClicked.equals("NEXTQUESTION")){
//			System.out.println(counter);
//			
//			if(counter <10 ){
//				welcome.setText(question[counter]);
//				file.writeToFile("Patient.txt", welcome.getText());
//				file.writeToFile("Patient.txt", answer.getText());
////				questionInfo.setText(question[counter]);
//				
//				counter++;
//				answer.setText("");
//				
//				
//			}
//			 if(counter == 10){
//				 NEXTQUESTION.setText("Finish Session");
//				 System.out.println("Stop");
//				 
//			 
//			 if(btnClicked.equalsIgnoreCase("NEXTQUESTION")){
//				 	questionPanel.setVisible(false);
//					questionPanel.setEnabled(false);
//					questionButton.setVisible(false);
//					questionButton.setEnabled(false);
//					
//					finishPanel.setVisible(true);
//					finishPanel.setEnabled(true);
//			 }
//			 }
//		}
//				int rep = JOptionPane.showConfirmDialog(null, "Questions are over, are you ready?", "Result", JOptionPane.YES_NO_OPTION);
//				if (rep == JOptionPane.YES_OPTION) {
//					counter = 0;
//					questionPanel.setVisible(false);
//					questionPanel.setEnabled(false);
//					questionButton.setVisible(false);
//					questionButton.setEnabled(false);
//					
//					finishPanel.setVisible(true);
//					finishPanel.setEnabled(true);
//				} else {
//					int repFinal = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "WHAT?!!", JOptionPane.YES_NO_OPTION);
//					if (repFinal == JOptionPane.YES_OPTION){
//						counter = 0;
//						JOptionPane.showMessageDialog(null, "GOODBYE");
//						System.exit(0);
//					}
//					else{
//						counter = 0;
//						JOptionPane.showMessageDialog(null, "VERY GOOD!");
//						questionPanel.setVisible(false);
//						questionPanel.setEnabled(false);
//						questionButton.setVisible(false);
//						questionButton.setEnabled(false);
//					}
//				}
//			}
//		}
		
       
	}
	
	@Override
	public void newSession() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getLongestWords(String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		 String longestWord = "";
		    String current;
		    Scanner scan = new Scanner(new File(fileName));


		    while (scan.hasNext()) {
		        current = scan.next();
		        if (current.length() > longestWord.length()) {
		            longestWord = current;
		        }

		    }
		    System.out.println(longestWord);
		            return longestWord;
		
	}


	@Override
	public String getShortestWords(String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		 String shortestWord ;
		    String current= getLongestWords("PatientAnswer.txt");
		    Scanner scan = new Scanner(new File(fileName));


		    while (scan.hasNext()) {
		        shortestWord = scan.next();
		        if (current.length() > shortestWord.length()) {
		            current = shortestWord;
		        } 

		    }
		    System.out.println(current);
		            return current;
		
	}


	@Override
	public void SortingAlphabetically(String fileName) {
		// TODO Auto-generated method stub
		ArrayList<String> lines = new ArrayList<String>();
        
        try
        {
            //Creating BufferedReader object to read the input file
             
            reader = new BufferedReader(new FileReader(fileName));
             
            //Reading all the lines of input file one by one and adding them into ArrayList
             
            String currentLine = reader.readLine();
             
            while (currentLine != null) {
                lines.add(currentLine);
                 
                currentLine = reader.readLine();
            }
             
            //Sorting the ArrayList
             
            Collections.sort(lines);
             
            //Creating BufferedWriter object to write into output file
             
            writer = new BufferedWriter(new FileWriter(fileName));
             
            //Writing sorted lines into output file
             
            for (String line : lines){
                writer.write(line);
                 
                writer.newLine();
            }
        } 
        catch (IOException e){
            e.printStackTrace();
        }
        finally
        {
            //Closing the resources
             
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
                 
                if(writer != null)
                {
                    writer.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }    
		
	

	@Override
	public String questionInfo() {
//		// TODO Auto-generated method stub
//		Random r = new Random();
//		int questionI = r.nextInt(10);//1 is min and 10 is max
//		String questionInF = question[questionI];
//		return questionInt;
		return null;
	}

	@Override
	public String ReadFile(String fileName) {
		Scanner inStream = null;
        String fileContents = "";
        try {
                inStream = new Scanner( new File ( fileName ));
                while(inStream.hasNextLine()){//get all lines and append to string
                        fileContents += inStream.nextLine() +"\n";//read one line from file and write it next to the original line in file
                }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        finally{
                if(inStream != null){
                        inStream.close();
                }
        }

        return fileContents;
	}

	

}
