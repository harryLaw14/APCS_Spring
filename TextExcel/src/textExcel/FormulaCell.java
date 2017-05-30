package textExcel;

import java.util.Arrays;

public class FormulaCell extends RealCell {
	Cell [][] newSpreadsheet = Spreadsheet.sheet;
	public FormulaCell(String ques) {//makes formulacell
		super(ques);
	}
	
	public String abbreviatedCellText() { //10 spaces
		String stuff = "" + this.getDoubleValue();
		String lineup = stuff;
		if(stuff.length() > 10) {
			return(stuff.substring(0, 10));
		}
		else {
			for(int i = 0; i < 10 - stuff.length(); i++) {
				lineup += " ";
			}
			return lineup;
		}
	}
	
	public boolean number(String ques) {
		String tester;
		boolean returner = true;
		if(ques.charAt(0) == '-') {			
			tester = ques.substring(1);			
		}else {
			tester = ques;	
		}
		for(int i = 0; i < tester.length(); i ++) {
			if(tester.charAt(i) != '.') {
				if(!Character.isDigit(tester.charAt(i))) {
					return !returner;
				}
			}
		}
			return returner;
	}
	
	public double getDoubleValue() {
		String [] confirm = getUserInput().substring(2, getUserInput().length()-2).split(" ");
		double amount = 0.0;	
		double amount2 = 0.0;
		if(confirm[0].toLowerCase().equals("sum") || confirm[0].toLowerCase().equals("avg")) {
			int counter = 0;
			double avg = 0.0;
			String [] confirm2 = confirm[1].split("-");
			SpreadsheetLocation start = new SpreadsheetLocation(confirm2[0]);
			SpreadsheetLocation finish = new SpreadsheetLocation(confirm2[1]);
			int beginRow = start.getRow() + 1;
			int beginCol = start.getCol() + 1;
			for(int row = beginRow; row <= finish.getRow() + 1; row++) {
				for(int col = beginCol; col <= finish.getCol() + 1; col++) {
					if(newSpreadsheet[row][col] instanceof FormulaCell) {
						amount += ((RealCell) newSpreadsheet[row][col]).getDoubleValue();
					}else {
						amount += Double.valueOf(newSpreadsheet[row][col].fullCellText());
					}
					counter ++;
				}
			}
			avg = amount/counter;
			if(confirm[0].toLowerCase().equals("avg") && counter > 1) {
				amount = avg;
			}
		}else {
			if(number(confirm[0].substring(0, 1))) {
				amount = Double.valueOf(confirm[0]);
			}else {
				SpreadsheetLocation loc = new SpreadsheetLocation(confirm[0]);
				if(newSpreadsheet[loc.getRow() + 1][loc.getCol() + 1] instanceof FormulaCell) {
					amount = ((RealCell) newSpreadsheet[loc.getRow() + 1][loc.getCol() + 1]).getDoubleValue();
				}else {
					amount = Double.valueOf(newSpreadsheet[loc.getRow() + 1][loc.getCol() + 1].fullCellText());
				}
			}
			for(int i = 0; i < confirm.length - 1; i += 2) {
				if(number(confirm[i+2].substring(0, 1))) {
					amount2 = Double.valueOf(confirm[i+2]);
				}else {
					SpreadsheetLocation loc = new SpreadsheetLocation(confirm[i+2]);
					if(newSpreadsheet[loc.getRow() + 1][loc.getCol() + 1] instanceof FormulaCell) {
						amount2 = ((RealCell) newSpreadsheet[loc.getRow() + 1][loc.getCol() + 1]).getDoubleValue();
					}else {
						amount2 = Double.valueOf(newSpreadsheet[loc.getRow() + 1][loc.getCol() + 1].fullCellText());
					}
				}
					if(confirm[i+1].equals("+")) {
						amount += amount2;
					}
					else {
						if(confirm[i+1].equals("-")) {
							amount -= amount2;
						}
						else {
							if(confirm[i+1].equals("*")) {
								amount *= amount2;
							}
							else {
								if(confirm[i+1].equals("/")) {
									amount /= amount2;
								}
							}
						}	
					}
			}
		}
		return amount;
	}
}
