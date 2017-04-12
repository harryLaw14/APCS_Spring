package textExcel;

public class SpreadsheetLocation implements Location{
    private int row;
    private int column; 
    
    public int getRow(){
        return row;
    }

    @Override
    public int getCol(){    
    	return column;
    }
    
    public SpreadsheetLocation(String cellName){
    	char dab=cellName.charAt(0);
    	column=dab-'A';
    	row=Integer.parseInt(cellName.substring(1))-1;
    }

}
