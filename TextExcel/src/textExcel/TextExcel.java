package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class TextExcel{
	
	public static void main(String[] args){
		Spreadsheet push=new Spreadsheet();
		Scanner userInput=new Scanner(System.in);
		String answer=userInput.nextLine();
		while(answer.equals("quit")==false){
			String troll=push.processCommand(answer);
			System.out.println(troll);
			answer=userInput.nextLine();
		}
	}
}

