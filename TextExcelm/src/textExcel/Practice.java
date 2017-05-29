package textExcel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Spreadsheet grid=new Spreadsheet();
		
	            // Generate the saved file
	            grid.processCommand("A1 = 23.521822%");             // Percent
	            grid.processCommand("B3 = -52.5");                  // Value
	            grid.processCommand("J6 = 2.888");                  // Value
	            grid.processCommand("L20 = 0");                     // Value
	            grid.processCommand("D10 = \"ChocolateChocolateChipCrustedCookie\"");    // Text            
	            grid.processCommand("F4 = ( 2 + 1 * 7 )");         // Formula            
	            grid.processCommand("save ./Test/Practice");

	            // Open the file manually with a scanner to inspect its contents
	            Scanner file;
	            try
	            {
	                file = new Scanner(new File("./Test/Practice"));
	            }
	            catch (FileNotFoundException e)
	            {
	                assertEquals("Unable to open TestSaveFormat.csv: " + e.getMessage(), "0", "1");
	                return;
	            }

	            ArrayList<String> contents = new ArrayList<String>();
	            while (file.hasNextLine())
	            {
	                contents.add(file.nextLine());
	            }
	            file.close();

	            assertListContains(contents, "A1,PercentCell,0.23521822");
	            assertListContains(contents, "B3,ValueCell,-52.5");
	            assertListContains(contents, "F4,FormulaCell,( 2 + 1 * 7 )");
	            assertListContains(contents, "J6,ValueCell,2.888");
	            assertListContains(contents, "D10,TextCell,\"ChocolateChocolateChipCrustedCookie\"");
	            assertListContains(contents, "L20,ValueCell,0");
	        }
	 private static void assertListContains(Iterable<String> list, String text)
     {
		 String s="Unable to find '" + text + "' in saved file";
         for (String line : list)
         {
             if (line.equals(text))
             {
                s="pass";
             }
         }

        
         System.out.println(s);
     }
}