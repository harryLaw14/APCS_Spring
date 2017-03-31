package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class TextExcel{
	
	public static void main(String[] args){
		Spreadsheet push=new Spreadsheet();
		Scanner userInput=new Scanner(System.in);
		String input=userInput.nextLine();
		while(input.equals("quit")==false){
			String troll=push.processCommand(input);
			System.out.println(troll);
			input=userInput.nextLine();
		}
	}
}

