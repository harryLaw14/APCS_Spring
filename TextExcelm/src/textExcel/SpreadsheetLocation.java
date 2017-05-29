package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    
    private int row;
    private int column; 
    public int getRow()
    {
        // TODO Auto-generated method stub
        return row;
    }

    @Override
    public int getCol()
    {
        // TODO Auto-generated method stub
        return column;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        // TODO: Fill this out with your own code
    	char temp=cellName.charAt(0);
    	column=temp-'A';
    	row=Integer.parseInt(cellName.substring(1))-1;
    }

}
