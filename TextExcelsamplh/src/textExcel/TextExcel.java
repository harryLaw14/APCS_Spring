// TextExcel.java Tyler Hackman 4-8-17
package textExcel;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;


public class TextExcel {

	public static void main(String[] args) {
	    Spreadsheet Mainspreadsheet = new Spreadsheet();
	    Scanner userInput  = new Scanner (System.in);
	    String input = "";
	    while(!input.equals ("quit")) {
	    	input = Mainspreadsheet.processCommand(userInput.nextLine());
	    		System.out.println(input);
	    }
	}
}
