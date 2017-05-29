package textExcel;

public class ValueCell extends RealCell {
	
	//Constructs a new value cell
	public ValueCell(String userInput) {
		super(userInput);
	}
	
	//Returns the contents of a value cell truncated to ten spaces
	public String abbreviatedCellText() {
		double doubleValue = this.getDoubleValue();
		String lengthCount = "" + doubleValue;
		String returnString = lengthCount;
		if(lengthCount.length() > 10) {
			return(lengthCount.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - lengthCount.length(); i++) {
				returnString += " ";
			}
		}
		return returnString;
	}

}
