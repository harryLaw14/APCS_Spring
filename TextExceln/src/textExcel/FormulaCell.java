//Brandon Nguyen, 2nd Period
package textExcel;

import java.util.Arrays;

public class FormulaCell extends RealCell{
	
	private String formula;
	private Cell[][] grid;

	public FormulaCell(String formula, Cell[][] spreadsheet) {
		this.formula = formula;
		this.grid = spreadsheet;
		setRealCell(formula);
	}

	public double getDoubleValue(){
		//takes out the spaces and parenthesis in the formula
		String input = getRealCell().substring(2, getRealCell().length() - 2);
		String[] formula = input.split(" ");
		
		// if the formula is just a number, or one value, return that
		if(formula.length == 1){
			return Double.parseDouble(formula[0]);
			
			//if the expression is an average or sum
		}else if((formula[0].toUpperCase().equals("AVG")) || (formula[0].toUpperCase().equals("SUM"))){
			
			//the inputs to the sum and average methods
			String cell = formula[1].toUpperCase().substring(0, formula[1].indexOf('-'));
			String ending = formula[1].toUpperCase().substring(formula[1].indexOf('-') + 1);

			if(formula[0].toUpperCase().equals("AVG")){
				String changeCell = formula[1].toUpperCase().substring(0, formula[1].indexOf('-'));
				//sets value to one in case beginning and ending cells are already same
				double count = 1.0;
				
				//counts the number of cells looked at to correctly average
				while(!(changeCell.equals(ending))){
					
					SpreadsheetLocation loc = new SpreadsheetLocation (changeCell);
					
					//when rows are the same change column
					 if (changeCell.substring(1).equals(ending.substring(1))){
						 changeCell = ((char)(changeCell.charAt(0) + 1)) + cell.substring(1);

						if(grid[loc.getRow()][loc.getCol()] instanceof RealCell){
							count++;
						}
					}else{
						//when rows aren't equal, move to next row
						changeCell = changeCell.charAt(0) + "" + (Integer.parseInt(changeCell.substring(1)) + 1);

						if(grid[loc.getRow()][loc.getCol()] instanceof RealCell){
							count++;
						}
					}
				}
				
				//averages
				return (sum(cell, cell, ending)) / count;
			}else{
				
				//sum
				return (sum (cell, cell, ending));
			}
			
		}else{
			
			//the first part of the expression and the storing number
			//checks to see if its a cell or number
			double sum;
			if(formula[0].toUpperCase().charAt(0) >= 'A' && formula[0].toUpperCase().charAt(0) <='L'){
				formula[0] = formula[0].toUpperCase();

				SpreadsheetLocation cell = new SpreadsheetLocation(formula[0]);
				if(grid[cell.getRow()][cell.getCol()] instanceof RealCell){
					sum = ((RealCell)grid[cell.getRow()][cell.getCol()]).getDoubleValue();
				}else{
					sum = 0.0;
				}
			}else{
				sum = Double.parseDouble(formula[0]);
			}
			
			//goes through the rest of the expression and carries out operands for thevalues
			//or simplifies the formula to return the value
			for(int i = 1; i < formula.length - 1; i += 2 ){
				
				//checks to see if its a cell or number
				double num;
				if(formula[i + 1].toUpperCase().charAt(0) >= 'A' && formula[i + 1].toUpperCase().charAt(0) <='L'){
						formula[i + 1] = formula[i + 1].toUpperCase();

						SpreadsheetLocation loc = new SpreadsheetLocation(formula[i + 1]);
						if(grid[loc.getRow()][loc.getCol()] instanceof RealCell){
							num = ((RealCell)grid[loc.getRow()][loc.getCol()]).getDoubleValue();
						}else{
							num = 0.0;
						}

				}else{
					num = Double.parseDouble(formula[i + 1]);
				}

				//checks for operators to see what to do
				if(formula[i].equals("*")){
					sum *=  num;
				}else if(formula[i].equals("/")){
					sum /= num;
				}else if(formula[i].equals("+")){
					sum += num;
				}else{
					sum -= num;
				}
			}
			
			// final value
			return sum;
		}
	}
	
	
	public double sum(String cell, String newCell, String end){	
		
		SpreadsheetLocation loc = new SpreadsheetLocation(newCell);
		
		//when they are the same, finishes and goes back through
		if(newCell.equals(end)){
			if(grid[loc.getRow()][loc.getCol()] instanceof RealCell){
				return ((RealCell)grid[loc.getRow()][loc.getCol()]).getDoubleValue();
			}else{
				return 0.0;
			}
		}
		//if the columns aren't the same, but the rows are, move horizontally
		if(Character.toUpperCase(cell.charAt(0)) != Character.toUpperCase(end.charAt(0)) 
				&& Integer.parseInt(cell.substring(1)) == Integer.parseInt(end.substring(1))){
			
			newCell = ((char)(newCell.charAt(0) + 1)) + cell.substring(1);
			
			//if the columns are the same,but the rows are not, move vertically
		}else if( Character.toUpperCase(cell.charAt(0)) == Character.toUpperCase(end.charAt(0))
				&& Integer.parseInt(cell.substring(1)) <= Integer.parseInt(end.substring(1))){
			
			newCell = Character.toString(cell.charAt(0)) + (Integer.parseInt(newCell.substring(1)) + 1);
			
			//if the columns and the rows aren't the same, move through all of the cells
		}else{
			
			//when row is less than the ending row, keep going down
			if(Integer.parseInt(newCell.substring(1)) < Integer.parseInt(end.substring(1))){
				
				newCell = Character.toString(newCell.charAt(0)) + (Integer.parseInt(newCell.substring(1)) + 1);

				//if the rows are the same, reset and move to next column
			}else if(Integer.parseInt(newCell.substring(1)) == Integer.parseInt(end.substring(1))){
				
				newCell = ((char)(newCell.charAt(0) + 1)) + "" + cell.substring(1);
				
			}
		}

		//gets the value of the cell and keeps it and returns it
		double first;
		if(grid[loc.getRow()][loc.getCol()] instanceof RealCell){
			 first = ((RealCell)grid[loc.getRow()][loc.getCol()]).getDoubleValue();
			return (first + sum(cell, newCell, end));
		}else{
			return sum(cell, newCell, end);
		}
	}
}
