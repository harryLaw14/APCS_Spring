package textExcel;
import java.util.*;
import java.io.FileNotFoundException;

public class TextExcel
{

	public static void main(String[] args)
	{	
		System.out.println("Ey!!!");
		Scanner input= new Scanner(System.in);
		Spreadsheet wetlikewater=new Spreadsheet();
		String command=input.nextLine();
		while (!command.equals("quit")){
			System.out.println(wetlikewater.processCommand(command));
			command=input.nextLine();
		}
	}
}

