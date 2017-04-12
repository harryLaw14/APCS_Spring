package textExcel;

public class FormulaCell extends RealCell{
	private Spreadsheet mySpreadsheet;
	public FormulaCell(String formula){
		super(formula);
	}
	public FormulaCell(String formula, Spreadsheet excel){
		this(formula);
		mySpreadsheet=excel;
	}
	public double getDoubleValue (){
		String [] splitted = stuff.split(" ");
		double answer = 0;
		if (splitted[1].toUpperCase().equals("SUM")){
			answer = answer + this.getSumValue(splitted, 1);
		}
		else if (splitted[1].toUpperCase().equals("AVG")){
			answer = answer + this.getAvgValue(splitted, 1);
		}		
		else if (splitted[1].toUpperCase().charAt(0)<77&&splitted[1].toUpperCase().charAt(0)>64){
			SpreadsheetLocation cellLoc= new SpreadsheetLocation (splitted[1].toUpperCase());
			answer = answer + ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
		}
		else
			answer = (Double.parseDouble(splitted[1]));
		for (int i=2; i<splitted.length;i=i+2){
			if(splitted[i].equals("+"))
				if (splitted[i+1].toUpperCase().equals("SUM")){
					answer = answer + this.getSumValue(splitted, i+1);
					i++;
				}
				else if (splitted[i+1].toUpperCase().equals("AVG")){
					answer = answer + this.getAvgValue(splitted, i+1);
					i++;
				}
				else if(splitted[i+1].toUpperCase().charAt(0)<77&&splitted[i+1].toUpperCase().charAt(0)>64){
					SpreadsheetLocation cellLoc= new SpreadsheetLocation (splitted[i+1].toUpperCase());
					answer = answer + ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
				}

				else
					answer = answer + Double.parseDouble(splitted[i+1]);
			if(splitted[i].equals("-"))
				if (splitted[i+1].toUpperCase().equals("SUM")){
					answer = answer - this.getSumValue(splitted, i+1);
					i++;
				}
				else if (splitted[i+1].toUpperCase().equals("AVG")){
					answer = answer - this.getAvgValue(splitted, i+1);
					i++;
				}
				else if(splitted[i+1].toUpperCase().charAt(0)<77&&splitted[i+1].toUpperCase().charAt(0)>64){
					SpreadsheetLocation cellLoc= new SpreadsheetLocation (splitted[i+1].toUpperCase());
					answer = answer - ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
				}
				else
					answer = answer - Double.parseDouble(splitted[i+1]);
			if(splitted[i].equals("*"))
				if (splitted[i+1].toUpperCase().equals("SUM")){
					answer = answer * this.getSumValue(splitted, i+1);
					i++;
				}
				else if (splitted[i+1].toUpperCase().equals("AVG")){
					answer = answer * this.getAvgValue(splitted, i+1);
					i++;
				}
				else if(splitted[i+1].toUpperCase().charAt(0)<77&&splitted[i+1].toUpperCase().charAt(0)>64){
					SpreadsheetLocation cellLoc= new SpreadsheetLocation (splitted[i+1].toUpperCase());
					answer = answer * ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
				}
				else
					answer = answer * Double.parseDouble(splitted[i+1]);
			if(splitted[i].equals("/"))
				if (splitted[i+1].toUpperCase().equals("SUM")){
					answer = answer / this.getSumValue(splitted, i+1);
					i++;
				}
				else if (splitted[i+1].toUpperCase().equals("AVG")){
					answer = answer / this.getAvgValue(splitted, i+1);
					i++;
				}
				else if(splitted[i+1].toUpperCase().charAt(0)<77&&splitted[i+1].toUpperCase().charAt(0)>64){
					SpreadsheetLocation cellLoc= new SpreadsheetLocation (splitted[i+1].toUpperCase());
					answer = answer / ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
				}
				else
					answer = answer / Double.parseDouble(splitted[i+1]);
		}
		return answer;

	}
	public double getSumValue(String [] splitted, int start){
		double answer=0;
		SpreadsheetLocation cellLocStart= new SpreadsheetLocation (splitted[start+1].substring(0,splitted[start+1].indexOf('-')).toUpperCase());
		SpreadsheetLocation cellLocEnd= new SpreadsheetLocation (splitted[start+1].substring(splitted[start+1].indexOf('-')+1).toUpperCase());
		for(int j=cellLocStart.getRow()+1;j<=cellLocEnd.getRow()+1;j++){
			for (int k=cellLocStart.getCol();k<=cellLocEnd.getCol();k++){
				SpreadsheetLocation cellLoc= new SpreadsheetLocation((char)(k+65)+""+j);
				if((mySpreadsheet.getCell(cellLoc)) instanceof RealCell){
					answer=answer + ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
				}
			}
		}
		return answer;
	}
	public double getAvgValue(String [] splitted, int start){
		double answer=0;
		int counter=0;
		SpreadsheetLocation cellLocStart= new SpreadsheetLocation (splitted[start+1].substring(0,splitted[start+1].indexOf('-')).toUpperCase());
		SpreadsheetLocation cellLocEnd= new SpreadsheetLocation (splitted[start+1].substring(splitted[start+1].indexOf('-')+1).toUpperCase());
		for(int j=cellLocStart.getRow()+1;j<=cellLocEnd.getRow()+1;j++){
			for (int k=cellLocStart.getCol();k<=cellLocEnd.getCol();k++){
				SpreadsheetLocation cellLoc= new SpreadsheetLocation((char)(k+65)+""+j);
				if((mySpreadsheet.getCell(cellLoc)) instanceof RealCell){
					answer=answer + ((RealCell)(mySpreadsheet.getCell(cellLoc))).getDoubleValue();
					counter++;
				}
			}
		}
		return answer/counter;
	}
	public String abbreviatedCellText(){
		String formula=this.getDoubleValue()+"          ";
		return formula.substring(0,10);
	}
	public String fullCellText(){
		return value;
	}
}
