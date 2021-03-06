//Harry Law 2nd period
package textExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Spreadsheet implements Grid {
	
	private int rows = 20;
	private int columns = 12;
	static Cell [][] sheet = new Cell [21][13];
	
	//makes a new spreadsheet
	public Spreadsheet() {
		for(int i = 1; i < 21; i++) {
			for(int j = 1; j < 13; j++) {
				sheet [i][j] = new EmptyCell();
			}
		}
	}
	
	public int getRows() {//returns rows
		return rows;
	}

	
	public int getCols() {//returns columns
		return columns;
	}


	public Cell getCell(Location loc) {//returns spreadsheetlocation
		return sheet[loc.getRow() + 1][loc.getCol() + 1];
	}

	public String getGridText() {//returns spreadsheet
		String table = "";
		char colnum = 'A';
		
		for(int r= 0; r < 21; r++) {
			for(int c = 0; c < 13; c++) {
				if(r == 0) {
					if(c == 0) {
						table += "   |";
					}else {
						table += "" + colnum + "         |";
						colnum++;	
					}
				}else {
					if (c == 0) {
						if(r < 10) {
							table += "" + r + "  |";
						}else {
							table += "" + r + " |";
						}
					}else {
						String test = sheet[r][c].abbreviatedCellText();
						if(test.equals("")){
							table += "          |";
						}else{
							table += test + "|";
						}
					}
				}
		}
			table += "\n";
	}
	return table;
	}

	public boolean number(String answer) {//checks number or not
		String tester;
		boolean value = true;
		if(answer.charAt(0) == '-') {			
			tester = answer.substring(1);			
		}
		else {
			tester = answer;	
		}
	
		for(int i = 0; i < tester.length(); i ++) {
			if(tester.charAt(i) != '.') {
				if(!Character.isDigit(tester.charAt(i))) {
					return !value;
				}
			}
		}
			return value;
	}
	
	public String processCommand(String pointer) {
		int row;
		int column;
		String feedback = "";
		if (pointer.equals("quit")) {//Quits the program
			feedback = "quit";
		}else {
			if(pointer.equals("")) {
				feedback = "";
			}else {
				if (pointer.length() <= 3 && pointer.length() != 0) {
					column = Character.getNumericValue(pointer.charAt(0)) - 9;
					row = Integer.valueOf((pointer.substring(1))) ;
					
					feedback = sheet[row][column].fullCellText();
				}else {
					if (pointer.contains("=")) {//valuecell
						String [] splitter = pointer.split(" ", 3);
						SpreadsheetLocation place = new SpreadsheetLocation(splitter[0]);
						if (number(splitter[2])) {					
								ValueCell valueCell = new ValueCell(splitter[2]);
								sheet[place.getRow() + 1][place.getCol() + 1] = valueCell;	
						}else {
							if (splitter[2].contains("%")) {//percentcell
								PercentCell percentCell = new PercentCell(splitter[2]);
								sheet[place.getRow() + 1][place.getCol() + 1] = percentCell;	
							}else {
								if(!splitter[2].substring(0,1).equals("\"") && (pointer.contains("+") || pointer.contains("-") || pointer.contains("*") || pointer.contains("/"))) {//formulacell
									FormulaCell formulaCell = new FormulaCell(splitter[2]);
									sheet[place.getRow() + 1][place.getCol() + 1] = formulaCell;
								}else {
									if(splitter[2].charAt(0) == '\"') {
										String [] quotes = splitter[2].split("\"", 3);
										TextCell cell = new TextCell(quotes[1]);
										sheet[place.getRow() + 1][place.getCol() + 1] = cell;
									}else {
										if (pointer.contains("(") && number(splitter[2].substring(splitter[2].indexOf('(')+1, splitter[2].indexOf(')')).trim())) {
											String value = splitter[2].substring(splitter[2].indexOf('(')+1, splitter[2].indexOf(')')).trim();
											ValueCell valueCell = new ValueCell(value);
											sheet[place.getRow() + 1][place.getCol() + 1] = valueCell;	
										}else {
											String [] quotes = splitter[2].split("\"", 3);
											TextCell cell = new TextCell(quotes[1]);
											sheet[place.getRow() + 1][place.getCol() + 1] = cell;
										}
									}
								}
										
							}
						}
						feedback = this.getGridText();
					}
					if(pointer.toLowerCase().equals("clear")) {//clearing
						Cell [][] clearer = new Cell[21][13];
						for(int i = 1; i < 21; i++) {
						for(int j = 1; j < 13; j++) {
							clearer [i][j] = new EmptyCell();
						}
						}
						sheet = clearer;
						feedback = this.getGridText();
					}
					if(pointer.toLowerCase().contains("clear ")) {
						String [] splitterint2 = pointer.split(" ", 2);
			
						SpreadsheetLocation location = new SpreadsheetLocation(splitterint2[1]);
						sheet[location.getRow() + 1][location.getCol() + 1] = new EmptyCell();
						feedback = this.getGridText();	
					}
					if(pointer.substring(0,4).equals("save")){
						feedback = this.writer(pointer.substring(5));
					}
					if(pointer.substring(0,4).equals("open")){
						feedback = this.reader(pointer.substring(5));
					}
				}	
			}
		}
		
		return feedback;
	}
	
	private String writer(String file){ 
		
			PrintStream output;

			try {
				output = new PrintStream(new File(file));
			}

			catch (FileNotFoundException e) {
				return "File not found: " + file;
			}
			String liner;
			char side = 'A';
			String name;
			String type;
			for (int c = 1; c < 13; c++) {
				for (int r = 1; r < 21; r++) {
					name = "" + side + r;
					if(!sheet[r][c].fullCellText().equals("")) {
						if(sheet[r][c].abbreviatedCellText().contains("%")) {
							type = "PercentCell";
						}
						else {
							if(sheet[r][c].fullCellText().contains("\"")) {
								type = "TextCell";
							}
							else {
								if(number(sheet[r][c].fullCellText())) {
									type = "ValueCell";
								}
								else {
								
									type = "FormulaCell";
								}
							}
						}
						liner = name + "," + type + "," + sheet[r][c].fullCellText();
						output.println(liner);
					}
				}
				side++;
			}
		
			output.close();

			return this.getGridText();

			}

	private String reader(String file){
		Scanner input;
		try {
			input = new Scanner(new File(file));
		}
		catch (FileNotFoundException e) {
		return "File not found: " + file;
		}
		String liner;
		String [] splits;
		while(input.hasNextLine()) {
			liner = input.nextLine();
			splits = liner.split(",", 3);
			SpreadsheetLocation loc = new SpreadsheetLocation(splits[0]);
			if(splits[1].equals("TextCell")) {
				String [] contentsWithoutQuotes = splits[2].split("\"", 3);
				TextCell cell = new TextCell(contentsWithoutQuotes[1]);
				sheet[loc.getRow() + 1][loc.getCol() + 1] = cell;	
			}else {
				if(splits[1].equals("PercentCell")) {
					PercentCell percentCell = new PercentCell("" + Double.parseDouble(splits[2])*100);
					sheet[loc.getRow() + 1][loc.getCol() + 1] = percentCell;	
				}else {
					if(splits[1].equals("FormulaCell")) {
						FormulaCell formulaCell = new FormulaCell(splits[2]);
						sheet[loc.getRow() + 1][loc.getCol() + 1] = formulaCell;	
					}else {
						ValueCell valueCell = new ValueCell(splits[2]);
						sheet[loc.getRow() + 1][loc.getCol() + 1] = valueCell;	
					}
				}
			}
		}
		input.close();
		return this.getGridText();
	}

}
