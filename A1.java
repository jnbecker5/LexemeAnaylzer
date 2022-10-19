/**
 * CSC 345-01 Assignment #1 (version 1.1)
 * 
 * On my honor, Josiah Nathaniel Becker, this assignment is my own work.  
 * I, Josiah Nathaniel Becker, will follow the instructor's rules and processes 
 * related to academic integrity as directed in the course syllabus.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class A1 {

	//constants declarations
	public static int nextToken;
	public static final int FLOATDCL = 0;
	public static final int INTDCL = 1;
	public static final int PRINT = 2;
	public static final int ID = 3;
	public static final int ASSIGN = 4;
	public static final int PLUS = 5;
	public static final int MINUS = 6;
	public static final int INUM = 7;
	public static final int FNUM = 8;

	public static void main(String[] args) {
		try {
			// Do NOT make any changes to the following TWO lines
			File file = new File(args[0]);		
			Scanner sc = new Scanner(file);		//*** Do not make any other Scanners ***//

			String testString = sc.nextLine();
			String lexeme = "";

			while (sc.hasNextLine()) { //transfers entire file into a single string
       			testString = testString + sc.nextLine();

			}
			testString = testString.replaceAll("\\s+",""); //removes all whitespace in string

			for(int i = 0; i < testString.length(); i++){ //iterates through each individual character of string

				//checks if character is letter
				if(Character.isLetter(testString.charAt(i)) && (testString.charAt(i) != 'f') && (testString.charAt(i) != 'p') && (testString.charAt(i) != 'i')){

					nextToken = ID;

				}

				//checks if character is a digit or period
				//if it is, adds them into new string until the number ends
				while(Character.isDigit(testString.charAt(i)) || testString.charAt(i) == '.'){

					lexeme += Character.toString(testString.charAt(i));
					if(testString.charAt(i) == '.' && testString.charAt(i + 1) == '.'){

						nextToken = FNUM;
						System.out.println("Next token is: " + nextToken + ", Next lexeme is " + lexeme);
						System.exit(0);
	
					}
					i++;
				}

				//checks if lexeme string matches regex pattern for decimals
				if(lexeme.matches("[0-9]*\\.[0-9]+")){

					nextToken = FNUM;
					System.out.println("Next token is: " + nextToken + ", Next lexeme is " + lexeme);
					lexeme = "";
					nextToken = ID;

				}

				//checks if lexeme string matches regex pattern for integers
				else if(lexeme.matches("[0-9]+")){

					nextToken = INUM;
					System.out.println("Next token is: " + nextToken + ", Next lexeme is " + lexeme);
					lexeme = "";
					nextToken = ID;

				}
				
				//switch-case for individual cases
				switch(testString.charAt(i)){
					case 'f':
						nextToken = FLOATDCL;
						break;
					case 'p':
						nextToken = PRINT;
						break;
					case 'i': 
						nextToken = INTDCL;
						break;

					case '=':
						nextToken = ASSIGN;
						break;
					case '+':
						nextToken = PLUS;
						break;
					case '-':
						nextToken = MINUS;
						break;
				}

				System.out.println("Next token is: " + nextToken + ", Next lexeme is " + testString.charAt(i));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - cannot open front.in \n");
		}
	}	
}