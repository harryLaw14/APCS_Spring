package textExcel;

public class TextCell implements Cell {

	private String cellContents;
	
	public TextCell(String contents) {
		cellContents = contents;
	}
	public String abbreviatedCellText() {
		String newCellContents = cellContents;
		if(cellContents.length() > 10) {
			return(cellContents.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - cellContents.length(); i++) {
				newCellContents += " ";
			}
			return newCellContents;
		}
	
	}

	
	public String fullCellText() {
		String newCellContents = "\"" + cellContents + "\"";
		return newCellContents;
	}
	
	public void setContents(String newContents){
		cellContents = newContents;
	}

}
