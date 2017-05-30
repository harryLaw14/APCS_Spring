package textExcel;

public class SpreadsheetLocation implements Location {
	
	private int col;
	private int row;
    
    public int getRow() {
       return row;
    }

    
    public int getCol() {
        return col;
    }
    
    public SpreadsheetLocation(String input){
    	col = Character.getNumericValue(input.charAt(0)) - 10;
    	row = ParseInt(input.substring(1)) - 1;

    }

	private int ParseInt(String rep) {
		return Integer.valueOf(rep);
	}

}
