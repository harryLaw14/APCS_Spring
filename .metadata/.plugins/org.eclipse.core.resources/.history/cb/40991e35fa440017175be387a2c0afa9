package textExcel;

public class PercentCell extends RealCell {
	
	public PercentCell(String ques) {// makes percentcell
		super(ques);
	}

	public String abbreviatedCellText() {// 10 spaces
		String portion = getUserInput().substring(0, getUserInput().indexOf("."));
		portion += "%";
		String lineup = portion;
		for(int i = 0; i < 10 - portion.length(); i++) {
			lineup += " ";
		}
		return lineup;
	}
	

	public String fullCellText() {
		String returnString = "" + this.getDoubleValue();
		return returnString;
	}
	

	public double getDoubleValue() {//returns percent double value
		return (Double.parseDouble(getUserInput().substring(0, getUserInput().indexOf("%")))/100);
	}
}
