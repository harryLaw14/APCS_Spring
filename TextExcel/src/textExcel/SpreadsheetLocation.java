package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location{
	
	
    @Override
    public int getRow(){
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCol(){
        // TODO Auto-generated method stub
        return 0;
    }
    
    public SpreadsheetLocation(String cellName){
    	String row = cellName.substring(1);
        char column = cellName.charAt(0);
        int rowNum = Integer.parseInt(row);
       	int colNum = (int)column - 64;
    }
}