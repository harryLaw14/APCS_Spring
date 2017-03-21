package textExcel;

public class ValueCell extends RealCell{
	public ValueCell(String number){
		super(number);
	}
	public String abbreviatedCellText() {
		String newContent = value;
		if(newContent.indexOf('.')==-1)
			newContent=newContent+".0";
		if(newContent.indexOf('.')!=-1)
		while (newContent.endsWith("00"))
			newContent=newContent.substring(0,newContent.length()-1);
		double numbers = Double.parseDouble(value);
		String newContent= numbers+ "";
		if (newContent.length()>10){
			return newContent.substring(0,10);
		}
		int spaces=10-newContent.length();
		for (int i=0;i<spaces;i++)
			newContent=newContent+" ";
		return newContent;
	}

}
