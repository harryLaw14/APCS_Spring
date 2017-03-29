package textExcel;

public class TextCell implements Cell{

	private String stuff;
	public TextCell(String init){
		stuff=init;
	}
	public String abbreviatedCellText(){
		String corr="";
		if(stuff.length()>1){
			corr=stuff.substring(1, stuff.length()-1);
		}
		corr+="                    ";
		return corr.substring(0,10);
	}

	@Override
	public String fullCellText(){
		return stuff;
	}

}

