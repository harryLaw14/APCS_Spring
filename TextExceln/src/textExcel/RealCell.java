//Brandon Nguyen, 2nd Period
package textExcel;

abstract class RealCell implements Cell {
	
	private String cellContent;
	
	public String getRealCell(){
		return cellContent;
	}
	public void setRealCell(String value){
		cellContent = value;
		
	}
	
	public String abbreviatedCellText() {
		
		//calls padAndTruncate to pad the value to ten spaces, or end the character by 10 spaces
		return padAndTruncate();
	}

	public String fullCellText() {

		String returnVal = cellContent;
		double holder = 0.0;
		
		//checks to see if the value is a percentage or just a number
		if(returnVal.contains("%")){
			holder = getDoubleValue();
			//if its a formula, just return the cell value, don't alter it
		}else if(returnVal.indexOf(")") == returnVal.length() - 1){
			return returnVal;
			//if the content of the cell is 0, dont change it, just return 0
		}else if(returnVal.equals("0")){
			return "0";
		}else{
			holder = Double.parseDouble(returnVal);
		}
		
		//converts the number to a string
		returnVal = Double.toString(holder);
		
		//checks to see if the number ends with a .0
		if(returnVal.substring(returnVal.length() - 2, returnVal.length()).equals(".0")){
			
			//checks to see if the number is negative, or if it only has 1 whole number in it
			if(returnVal.substring(0 , returnVal.indexOf(".0")).length() <= 1 || returnVal.contains("-")){
				return returnVal;
				
				//if the number isn't just one whole number or negative, then cuts off the .0
			}else{
				return returnVal.substring(0,returnVal.length() - 2);
			}
			
		//if it doesn't end in a .0, then checks for unnecessary zeroes to possibly cut out
		}else{
			return checkZeroes(returnVal);
		}

	}
	
	
	public String padAndTruncate(){
		String returnVal = cellContent;
		if(returnVal.charAt(0) == '('){
			returnVal = Double.toString(getDoubleValue());
			//System.out.println(returnVal);
		}
		//if its a whole number without a percent, make it a double by adding .0 to it
		if(!returnVal.contains(".") && !returnVal.contains("%") ){
			returnVal += ".0";
		}
		
		//gets rid of unnecessary zeroes at the end of the value
		returnVal = checkZeroes(returnVal);
		//System.out.println(returnVal);
		//checks for unnecessary zeroes again and cuts off the last one
		//to make it a .0 if there is
		if(returnVal.indexOf(".00") + 3 == returnVal.length()){
			returnVal = returnVal.substring(0, returnVal.length() - 1);
		}
		//System.out.println(returnVal);
		//checks to see if there is a percent sign and takes off the decimal point
		if(returnVal.contains("%")){
			returnVal = returnVal.substring(0, returnVal.indexOf('.'));
			returnVal += "%";
		}
		//System.out.println(returnVal);
		//checks to see if the value is longer than 10, 
		// if it is it cuts it off at the tenth character,
		// or adds spaces to fill it up to ten spaces
		//System.out.println(returnVal);
		if(returnVal.length() >= 10){
			returnVal = returnVal.substring(0, 10);
			
		}else{
			
			while(returnVal.length() != 10){
				returnVal += " ";
			}
		}
		//System.out.println(returnVal);
		return returnVal;
	}
	
	abstract double getDoubleValue();

	public String checkZeroes(String value){
		int continuousZero = 0;
		int countZero = 0;
		int count = value.length() - 1;
		
		// keeps going until the count hits the start of the value or zero
		while(count != 0){
			//checks for two zeroes in a row and saves the value to continuosZero
			if(countZero >= 2){
				continuousZero = countZero;

			}
			
			//checks for zeros or if the value is at a .0 (which it doesn't count)
			if(value.charAt(count) == '0' && value.charAt(count - 1) != '.'){
				countZero++;

			}else{
				// if its anything but a zero without a decimal point near it reset the count for zeroes
				countZero = 0;

			}
			//decrement by one to go down the value
			count--;

		}
		
		//returns the value after substring the amount of unnecessary zereos
		return value.substring(0, value.length() - continuousZero);
	}
	
}
