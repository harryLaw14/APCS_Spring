package textExcel;

public class PercentCell extends RealCell{
	public PercentCell(String percent){
		super(percent);
	}
	public String abbreviatedCellText(){
		String newContent = value.substring(0,value.indexOf('.'));
		if (newContent.length()>10){
			return value.substring(0,9)+"%";
		}
		newContent=newContent+"%";
		int spaces=10-newContent.length();
		for (int i=0;i<spaces;i++)
			newContent=newContent+" ";
		return newContent;
	}
	public String fullCellText(){
		String s=super.fullCellText();
		return Double.parseDouble(s.substring(0,s.length()-1))/100+"";
	}
	public double getDoubleValue(){
		String s=super.fullCellText();
		return Double.parseDouble(s.substring(0,s.length()-1))/100;

	}
}
