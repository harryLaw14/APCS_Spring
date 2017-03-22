package textExcel;

public class ValueCell extends RealCell{
	public ValueCell(String number){
		super(number);
	}
	public String abbreviatedCellText() {
		String newStuff = thevalue;
		if(newStuff.indexOf('.')==-1)
			newStuff=newStuff+".0";
		if(newStuff.indexOf('.')!=-1)
		while (newStuff.endsWith("00"))
			newStuff=newStuff.substring(0,newStuff.length()-1);
		double numbers = Double.parseDouble(thevalue);
		String newotherstuff= numbers+ "";
		if (newStuff.length()>10){
			return newStuff.substring(0,10);
		}
		int spaces=10-newStuff.length();
		for (int i=0;i<spaces;i++)
			newStuff=newStuff+" ";
		return newStuff;
	}

}
