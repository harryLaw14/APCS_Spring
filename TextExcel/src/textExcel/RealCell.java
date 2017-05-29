package textExcel;

public class RealCell implements Cell {

	private String stuff;
	
	//Constructs a new real cell
	public RealCell (String content) {
		stuff = content;
	}
	
	public String fullCellText() {
		String newStuff = stuff;
		return newStuff;
	}
	

	public double getDoubleValue() {
		return Double.parseDouble(stuff);
	}
	
	//Returns the line of user input that was used to make the cell
	public String getUserInput() {
		return stuff;
	}

	public String abbreviatedCellText() {
		String newStuff = stuff;
		if(stuff.length() > 10) {
			return(stuff.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - stuff.length(); i++) {
				newStuff += " ";
			}
			return newStuff;
		}
	
	}
}