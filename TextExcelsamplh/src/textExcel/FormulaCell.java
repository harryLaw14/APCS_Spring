package textExcel;

import java.util.Arrays;

public class FormulaCell extends RealCell {
	
	Cell [][] newSpreadsheet = Spreadsheet.spreadsheet;
	//Constructs a new formula cell
	public FormulaCell(String userInput) {
		super(userInput);
	}
	
	//Returns the value of the evaluated formula truncated to ten spaces
	public String abbreviatedCellText() {
		String cellContents = "" + this.getDoubleValue();
		String returnString = cellContents;
		if(cellContents.length() > 10) {
			return(cellContents.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - cellContents.length(); i++) {
				returnString += " ";
			}
			return returnString;
		}
	}
	
	//Evaluates the formula of a formula cell
	public double getDoubleValue() {
		String [] arr = getUserInput().substring(2, getUserInput().length()-2).split(" ");
		double value = 0.0;	
		double value2 = 0.0;
		if(arr[0].toLowerCase().equals("sum") || arr[0].toLowerCase().equals("avg")) {
			int count = 0;
			double avg = 0.0;
			String [] arr2 = arr[1].split("-");
			SpreadsheetLocation startCell = new SpreadsheetLocation(arr2[0]);
			SpreadsheetLocation lastCell = new SpreadsheetLocation(arr2[1]);
			int startRow = startCell.getRow() + 1;
			int startCol = startCell.getCol() + 1;
			for(int row = startRow; row <= lastCell.getRow() + 1; row++) {
				for(int col = startCol; col <= lastCell.getCol() + 1; col++) {
					if(newSpreadsheet[row][col] instanceof FormulaCell) {
						value += ((RealCell) newSpreadsheet[row][col]).getDoubleValue();
					}
					else {
						value += Double.valueOf(newSpreadsheet[row][col].fullCellText());
					}
					count ++;
				}
			}
			avg = value/count;
			if(arr[0].toLowerCase().equals("avg") && count > 1) {
				value = avg;
			}
		}
		else {
			if(isNumeric(arr[0].substring(0, 1))) {
				value = Double.valueOf(arr[0]);
			}
			else {
				SpreadsheetLocation location = new SpreadsheetLocation(arr[0]);
				if(newSpreadsheet[location.getRow() + 1][location.getCol() + 1] instanceof FormulaCell) {
					value = ((RealCell) newSpreadsheet[location.getRow() + 1][location.getCol() + 1]).getDoubleValue();
				}
				else {
					value = Double.valueOf(newSpreadsheet[location.getRow() + 1][location.getCol() + 1].fullCellText());
				}
			}
			for(int i = 0; i < arr.length - 1; i += 2) {
				if(isNumeric(arr[i+2].substring(0, 1))) {
					value2 = Double.valueOf(arr[i+2]);
				}
				else {
					SpreadsheetLocation location = new SpreadsheetLocation(arr[i+2]);
					if(newSpreadsheet[location.getRow() + 1][location.getCol() + 1] instanceof FormulaCell) {
						value2 = ((RealCell) newSpreadsheet[location.getRow() + 1][location.getCol() + 1]).getDoubleValue();
					}
					else {
						value2 = Double.valueOf(newSpreadsheet[location.getRow() + 1][location.getCol() + 1].fullCellText());
					}
				}
					if(arr[i+1].equals("+")) {
						value += value2;
					}
					else {
						if(arr[i+1].equals("-")) {
							value -= value2;
						}
						else {
							if(arr[i+1].equals("*")) {
								value *= value2;
							}
							else {
								if(arr[i+1].equals("/")) {
									value /= value2;
								}
							}
						}	
					}
			}
		}
		return value;
	}
	//Tests if a string is numeric (only containing numbers, a '.', or a '-')
		public boolean isNumeric(String input) {
			String testString;
			boolean returnValue = true;
			if(input.charAt(0) == '-') {			
				testString = input.substring(1);			
			}
			else {
				testString = input;	
			}
		
			for(int i = 0; i < testString.length(); i ++) {
				if(testString.charAt(i) != '.') {
					if(!Character.isDigit(testString.charAt(i))) {
						return !returnValue;
					}
				}
			}
				return returnValue;
		}
}
