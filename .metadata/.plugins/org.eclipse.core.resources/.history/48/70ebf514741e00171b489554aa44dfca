package textExcel;

public class PercentCell extends RealCell {
	public PercentCell(String enter){
		super(enter);
	}
	public double getDoubleValue(){

		String roller=getStuff();
		roller=roller.substring(0,roller.length()-1);
		double banger=Double.parseDouble(roller);
		return banger/100;
	}
	public String fullCellText(){
		return ""+getDoubleValue();
		
	}
	public String abbreviatedCellText(){
		String roller=getStuff();
		if(roller.indexOf(".")!=-1){
			roller=roller.substring(0,roller.indexOf("."))+"%";
		}
		roller+="                    ";
		return roller.substring(0,10);
	}
}
