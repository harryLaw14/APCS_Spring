package textExcel;

public class ValueCell extends RealCell {
	

	public ValueCell(String stuff) {
		super(stuff);
	}
	

	public String abbreviatedCellText() {
		double number2 = this.getDoubleValue();
		String lengthcounter = "" + number2;
		String bounce = lengthcounter;
		if(lengthcounter.length() > 10) {
			return(lengthcounter.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - lengthcounter.length(); i++) {
				bounce += " ";
			}
		}
		return bounce;
	}

}
