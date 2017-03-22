package textExcel;

public class RealCell implements Cell {
	String thevalue;
	
	public RealCell (String thenumber){
		thevalue=thenumber;
	}
	
	public double getDoubleValue(){
		return Double.parseDouble(thevalue);
	}
	
	public String abbreviatedCellText() {
		String newContent = thevalue;
		if (thevalue.length()>10){
			return thevalue.substring(0,10);
		}
		int spaces=10-thevalue.length();
		for (int i=0;i<spaces;i++)
			newContent=newContent+" ";
		return newContent;
	}
	
	public String fullCellText() {
		return thevalue;
	}
	
}
