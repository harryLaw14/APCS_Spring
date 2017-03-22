package textExcel;

public class TextCell implements Cell {
	private String stuff;
	public TextCell (String theword){
		stuff=theword;
	}
	public String abbreviatedCellText(){
		String newStuff = stuff;
		if (stuff.length()>10){
			return stuff.substring(0,10);
		}
		int spaces=10-stuff.length();
		for (int i=0;i<spaces;i++)
			newStuff=newStuff+" ";
		return newStuff;
	}
	public String fullCellText() {
		return "\""+stuff+"\"";
	}

}

