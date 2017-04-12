package textExcel;

public class RealCell implements Cell {

	private String stuff;
	
	public void setStuff(String newstuff){
		stuff=newstuff;
	}
	
	public String getStuff(){
		return stuff;
	}

	public double getDoubleValue(){
		return 0;
	}
	
	public RealCell(String theinput){
		stuff=theinput;
	}
	
	public String fullCellText(){
		return stuff;
	}
	
	public String abbreviatedCellText(){
		String corr=stuff+"                    ";
		return corr.substring(0,10);
	}
}