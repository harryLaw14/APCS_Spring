package textExcel;

public class ValueCell extends RealCell {
	
	public ValueCell(String ques) { // makes new value cell
		super(ques);
	}
	
	public String abbreviatedCellText() { //10 spaces
		double twovalue = this.getDoubleValue();
		String counter = "" + twovalue;
		String lineup = counter;
		if(counter.length() > 10) {
			return(counter.substring(0, 10));
		}else {
			for(int i = 0; i < 10 - counter.length(); i++) {
				lineup += " ";
			}
		}
		return lineup;
	}
}