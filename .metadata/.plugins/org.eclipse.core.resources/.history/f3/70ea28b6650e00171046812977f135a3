package textExcel;

public class RealCell implements Cell{
	
	private String content;
	
	public String getCont(){
		return content;
	}
	public void setCont(String update){
		content=update;
	}
	
	public RealCell(String input){
		content=input;
	}
	public double getDoubleValue(){
		return 0;
	}
	public String abbreviatedCellText(){
		String fix=content+"                    ";
		return fix.substring(0,10);
	}

	@Override
	public String fullCellText(){
		return content;
	}

}
