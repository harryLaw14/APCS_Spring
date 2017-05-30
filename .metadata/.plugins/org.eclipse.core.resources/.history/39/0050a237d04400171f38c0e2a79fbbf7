package textExcel;


public class SpreadsheetLocation implements Location
{
	private int columnNumber;
	private int rowNumber;
    
    public int getRow() {
       return rowNumber;
    }

    
    public int getCol() {
        return columnNumber;
    }
    
    public SpreadsheetLocation(String cellName){
    	columnNumber = Character.getNumericValue(cellName.charAt(0)) - 10;
    	rowNumber = ParseInt(cellName.substring(1)) - 1;

    }

	private int ParseInt(String substring) {
		return Integer.valueOf(substring);
	}

}
