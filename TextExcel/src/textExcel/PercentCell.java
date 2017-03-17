package textExcel;

public class PercentCell extends RealCell {
	public PercentCell(String enter){
		super(enter);
	}
	public double getDoubleValue(){

		String ball=getCont();
		ball=ball.substring(0,ball.length()-1);
		double kkkkkk=Double.parseDouble(ball);
		return kkkkkk/100;
	}
	public String abbreviatedCellText(){
		String ball=getCont();
		if(ball.indexOf(".")!=-1){
			ball=ball.substring(0,ball.indexOf("."))+"%";
		}
		ball+="                    ";
		return ball.substring(0,10);
	}
	public String fullCellText(){
		return ""+getDoubleValue();
		
	}
}
