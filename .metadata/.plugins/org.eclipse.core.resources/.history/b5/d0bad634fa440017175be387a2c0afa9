package textExcel;

public class TextCell implements Cell {

	private String cellStuff;
	
	public TextCell(String stuff) {
		cellStuff = stuff;
	}
	public String abbreviatedCellText() {
		String newStuff = cellStuff;
		if(cellStuff.length() > 10) {
			return(cellStuff.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - cellStuff.length(); i++) {
				newStuff += " ";
			}
			return newStuff;
		}
	
	}

	public String fullCellText() {
		String newStuff = "\"" + cellStuff + "\"";
		return newStuff;
	}
	
	public void setContents(String newthings){
		cellStuff = newthings;
	}
}
