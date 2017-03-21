package textExcel;

public class SpreadsheetLocation implements Location {
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
    	char temp=cellName.charAt(0);
    	column=temp-'A';
  		row=Integer.parseInt(cellName.substring(1))-1;
    }

}
