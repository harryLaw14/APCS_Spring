package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class TextExcel{
	
	public static void main(String[] args) {
	    Spreadsheet Mainspreadsheet = new Spreadsheet();
	    Scanner answer = new Scanner (System.in);
	    String gotit = "";
	    while(!gotit.equals ("quit")) {
	    	gotit = Mainspreadsheet.processCommand(answer.nextLine());
	    		System.out.println(gotit);
	    }
	}
}

