//Brandon Nguyen, 2nd Period
package textExcel;

public class TextCell implements Cell {
	
	private String value;

	public TextCell(String value) {
		this.value = value;
	}
	
	public String truncateAndPad(){
		
		//creates a new value so the original is not modified
		String returnVal = "";
		
		//gets rid of quotation marks
		if(value.contains("\"") == true){
			returnVal = value.substring(1, value.length() - 1);
		}
		
		// if the remaining length after removing the quotes is 0 then it
		// adds a space
		if(returnVal.length() == 0){
			returnVal += " ";
		}
		
		//only takes first 10 characters if string is longer than 10
		if(returnVal.length() > 10){
			returnVal = returnVal.substring(0, 10);
			return returnVal;
		}else{
			
			//fills in the spaces to make the value left aligned
			while(returnVal.length() != 10){
				returnVal += " ";

			}
			return returnVal;
		}
	}
	
	@Override
	public String abbreviatedCellText() {
		return truncateAndPad();
	}

	@Override
	public String fullCellText() {
		return value;
	}

}
