package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextExcel{

	public static void main(String[] args){
		Spreadsheet spreadsheet = new Spreadsheet();
		Scanner scannerInput = new Scanner(System.in);
		String command = scannerInput.next();
	    spreadsheet.getGridText();
	    while(!(command.equals("quit"))){
	    System.out.println(spreadsheet.processCommand(command));
	    command = scannerInput.next();
	    }
	}
}

