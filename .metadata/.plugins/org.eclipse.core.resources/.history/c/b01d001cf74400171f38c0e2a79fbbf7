package textExcel;

public class PercentCell extends RealCell {
	
	
	//Constructs a new percent cell
	public PercentCell(String userInput) {
		super(userInput);
	}
	
	//Returns the contents of a percent cell (in percent form) truncated to ten spaces
	public String abbreviatedCellText() {
		String percent = getUserInput().substring(0, getUserInput().indexOf("."));
		percent += "%";
		String returnString = percent;
		for(int i = 0; i < 10 - percent.length(); i++) {
			returnString += " ";
		}
		return returnString;
	}
	
	//Returns the non-truncated contents (in decimal form) of the cell
	public String fullCellText() {
		String returnString = "" + this.getDoubleValue();
		return returnString;
	}
	
	//Returns the double value of the percent
	public double getDoubleValue() {
		return (Double.parseDouble(getUserInput().substring(0, getUserInput().indexOf("%")))/100);
	}
}
