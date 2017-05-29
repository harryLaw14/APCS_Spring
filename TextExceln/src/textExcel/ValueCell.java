//Brandon Nguyen, 2nd Period
package textExcel;

public class ValueCell extends RealCell{
	
	private String value;
	
	public ValueCell(String num){
		this.value = num;
		// saves value into super class of RealCell
		setRealCell(num);

	}
	
	public double getDoubleValue(){
		return Double.parseDouble(getRealCell());
	}

}
