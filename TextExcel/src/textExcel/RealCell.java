package textExcel;

public class RealCell implements Cell {

	private String stuff;
	public String getstuff(){
		return stuff;
	}
	public void setstuff(String newstuff){
		stuff=newstuff;
	}
	
	public RealCell(String theinput){
		stuff=theinput;
	}
	public double getDoubleValue(){
		return 0;
	}
	public String abbreviatedCellText(){

		
		String corr=stuff+"                    ";
		return corr.substring(0,10);
	}

	@Override
	public String fullCellText(){
		return stuff;
	}

}
