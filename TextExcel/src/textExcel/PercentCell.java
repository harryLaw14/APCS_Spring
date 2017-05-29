package textExcel;

public class PercentCell extends RealCell {
	
	
	//Constructs a new percent cell
	public PercentCell(String stuff) {
		super(stuff);
	}
	

	public String abbreviatedCellText() {
		String percentage = getUserInput().substring(0, getUserInput().indexOf("."));
		percentage += "%";
		String bounce = percentage;
		for(int i = 0; i < 10 - percentage.length(); i++) {
			bounce += " ";
		}
		return bounce;
	}
	

	public String fullCellText() {
		String bounce = "" + this.getDoubleValue();
		return bounce;
	}
	

	public double getDoubleValue() {
		return (Double.parseDouble(getUserInput().substring(0, getUserInput().indexOf("%")))/100);
	}
}