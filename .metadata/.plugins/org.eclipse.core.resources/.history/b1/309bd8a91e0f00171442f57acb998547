package textExcel;

public class RealCell implements Cell {
	String value;
	public RealCell (String number){
		value=number;
	}
	public String abbreviatedCellText() {
		String newContent = value;
		if (value.length()>10){
			return value.substring(0,10);
		}
		int spaces=10-value.length();
		for (int i=0;i<spaces;i++)
			newContent=newContent+" ";
		return newContent;
	}
	public String fullCellText() {
		return value;
	}
	public double getDoubleValue(){
		return Double.parseDouble(value);
	}
}
