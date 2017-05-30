//Brandon Nguyen, 3/12/17, 2nd Period
package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row;
	private int col;
	
    @Override
    public int getRow()
    {
    	// for the zero indexing
        return row - 1;
    }

    @Override
    public int getCol()
    {
        return col;
    }
    
    public SpreadsheetLocation(String cellName)
    {
    	col = cellName.charAt(0);
    	
    	//subtracts the value of 'A' to get the location of the column
    	//based on the character used
    	col = Character.toUpperCase(col) - 'A';
    	
    	//changes the string number at the end to an integer
    	row = Integer.parseInt(cellName.substring(1));
    }
}