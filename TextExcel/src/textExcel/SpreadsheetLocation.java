package textExcel;


public class SpreadsheetLocation implements Location{
	
	private String coordinates = "";
	
	public SpreadsheetLocation(String cellName){
		coordinates = cellName;
  }
	
  @Override
  public int getRow(){
  	int rowNum = Integer.parseInt(coordinates.substring(1)) - 1;
      return rowNum;
  }

  @Override
  public int getCol(){
  	int colNum = (int) (coordinates.charAt(0) - 65);
  	return colNum;
  }
}