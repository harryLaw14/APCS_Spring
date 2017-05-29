//Brandon Nguyen, 2nd Period
package textExcel;

public class PercentCell extends RealCell{
	
	private String percent;
	
	public PercentCell(String percent){
		this.percent = percent;
		// saves value into super class of RealCell
		setRealCell(percent);
	}
	
	public double getDoubleValue(){
		String returnVal = getRealCell();
		//takes out the percentage sign and changes it to a decimal, not a percentage
		returnVal = returnVal.substring(0, returnVal.length() - 1);
		return Double.parseDouble(returnVal) / 100.0;
	}
	
	
}