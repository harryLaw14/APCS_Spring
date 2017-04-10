package textExcel;

public class ValueCell extends RealCell {
	public ValueCell(String enter){
		super(enter);
	}
	public double getDoubleValue(){
		String roller=getStuff();
		
		double banger=Double.parseDouble(roller);
		return banger;
	}
	
	public String fullTextCell(){
		String roller=getStuff();
		
		return  getDoubleValue()+"";
	}
	
	public String abbreviatedCellText(){
		String roller=getStuff();		
		double banger=Double.parseDouble(roller);
		roller=banger+"";
		if(roller.indexOf(".")==-1){
			roller+=(".0");
		}
		roller+="               ";
		return roller.substring(0,10);
	}
}
