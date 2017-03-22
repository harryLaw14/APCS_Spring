package textExcel;

public class TextCell implements Cell {
	private String content;
	public TextCell (String word){
		content=word;
	}
	public String abbreviatedCellText(){
		String newContent = content;
		if (content.length()>10){
			return content.substring(0,10);
		}
		int spaces=10-content.length();
		for (int i=0;i<spaces;i++)
			newContent=newContent+" ";
		return newContent;
	}
	public String fullCellText() {
		return "\""+content+"\"";
	}

}

