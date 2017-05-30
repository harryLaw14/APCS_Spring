package textExcel;

public class RealCell implements Cell {

	private String ques;
	

	public RealCell (String stuff) { //makes real cell
		ques = stuff;
	}
	

	public String abbreviatedCellText() { //10 spaces
		String newstuff = ques;
		if(ques.length() > 10) {
			return(ques.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - ques.length(); i++) {
				newstuff += " ";
			}
			return newstuff;
		}
	
	}

	public String fullCellText() {
		String newstuff = ques;
		return newstuff;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(ques);
	}
	
	public String getUserInput() {
		return ques;
	}
	
}
