package textExcel;

public class RealCell implements Cell {

	private String userInput;
	
	//Constructs a new real cell
	public RealCell (String contents) {
		userInput = contents;
	}
	
	//Returns the contents of the cell truncated to ten spaces
	public String abbreviatedCellText() {
		String newCellContents = userInput;
		if(userInput.length() > 10) {
			return(userInput.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - userInput.length(); i++) {
				newCellContents += " ";
			}
			return newCellContents;
		}
	
	}

	//Returns the non-truncated contents of the cell
	public String fullCellText() {
		String newCellContents = userInput;
		return newCellContents;
	}
	
	//Returns the double value of a real cell
	public double getDoubleValue() {
		return Double.parseDouble(userInput);
	}
	
	//Returns the line of user input that was used to make the cell
	public String getUserInput() {
		return userInput;
	}
	
}
