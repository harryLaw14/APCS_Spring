//Harry Law 2nd period
package textExcel;

public class EmptyCell implements Cell {

	public EmptyCell() {
		
	}
	public String abbreviatedCellText() {
		return "          ";
	}

	
	public String fullCellText() {
		return "";
		
	}

}
