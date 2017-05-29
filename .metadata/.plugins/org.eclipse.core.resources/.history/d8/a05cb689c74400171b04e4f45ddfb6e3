package textExcel;

public class TextCell implements Cell {

	private String content;
	public TextCell(String initial){
		content=initial;
	}
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		String fix="";
		if(content.length()>1){
			fix=content.substring(1, content.length()-1);
		}
		fix+="                    ";
		return fix.substring(0,10);
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return content;
	}

}
