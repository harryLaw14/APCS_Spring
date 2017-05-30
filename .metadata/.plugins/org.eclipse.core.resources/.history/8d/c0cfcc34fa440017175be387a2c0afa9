package textExcel;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;


public class TextExcel {

	public static void main(String[] args) {
	    Spreadsheet mainsheet = new Spreadsheet();
	    Scanner userInput  = new Scanner (System.in);
	    String input = "";
	    while(!input.equals ("quit")) {
	    	input = mainsheet.processCommand(userInput.nextLine());
	    		System.out.println(input);
	    }
	}
}
