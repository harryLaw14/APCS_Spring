package textExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Spreadsheet implements Grid {
	
	private int numberOfRows = 20;
	private int numberOfColumns = 12;
	static Cell [][] spreadsheet = new Cell [21][13];
	
	//Constructs a new spreadsheet
	public Spreadsheet() {
		for(int i = 1; i < 21; i++) {
			for(int j = 1; j < 13; j++) {
				spreadsheet [i][j] = new EmptyCell();
			}
		}
	}
	
	//Processes the user's command and performs various actions depending on what the user's command was
	public String processCommand(String command) {
		int row, column;
		String returnString = "";
		//Quits the program
		if (command.equals("quit")) {
			returnString = "quit";
		}
		else {
			//Returns nothing if the command is blank
			if(command.equals("")) {
				returnString = "";
			}
			else {
				//Returns the non-truncated contents of a specified cell
				if (command.length() <= 3 && command.length() != 0) {
					column = Character.getNumericValue(command.charAt(0)) - 9;
					row = Integer.valueOf((command.substring(1))) ;
					
					returnString = spreadsheet[row][column].fullCellText();
				}
				else {
					//'=' means there is an assignment
					if (command.contains("=")) {
						String [] splitInput = command.split(" ", 3);
						SpreadsheetLocation location = new SpreadsheetLocation(splitInput[0]);
	
						//If the value the user is trying to assign is numeric, a value cell is made
						if (isNumeric(splitInput[2])) {					
								ValueCell valueCell = new ValueCell(splitInput[2]);
								spreadsheet[location.getRow() + 1][location.getCol() + 1] = valueCell;	
						}
						else {
							//If there is a '%' in the value, a percent cell is made
							if (splitInput[2].contains("%")) {
								PercentCell percentCell = new PercentCell(splitInput[2]);
								spreadsheet[location.getRow() + 1][location.getCol() + 1] = percentCell;	
							}
							else {
								//If there are operators in the value, a formula cell is made
								if(!splitInput[2].substring(0,1).equals("\"") && (command.contains("+") || command.contains("-") || command.contains("*") || command.contains("/"))) {
									FormulaCell formulaCell = new FormulaCell(splitInput[2]);
									spreadsheet[location.getRow() + 1][location.getCol() + 1] = formulaCell;
								}
								else {
									if(splitInput[2].charAt(0) == '\"') {
										String [] contentsWithoutQuotes = splitInput[2].split("\"", 3);
										TextCell cell = new TextCell(contentsWithoutQuotes[1]);
										spreadsheet[location.getRow() + 1][location.getCol() + 1] = cell;
									}
									else {
										if (command.contains("(") && isNumeric(splitInput[2].substring(splitInput[2].indexOf('(')+1, splitInput[2].indexOf(')')).trim())) {
											String value = splitInput[2].substring(splitInput[2].indexOf('(')+1, splitInput[2].indexOf(')')).trim();
											ValueCell valueCell = new ValueCell(value);
											spreadsheet[location.getRow() + 1][location.getCol() + 1] = valueCell;	
										}
										else {
											//If none of the other tests pass, a text cell is made
											//Create a new array to split, in order to get rid of the quotation marks.
											String [] contentsWithoutQuotes = splitInput[2].split("\"", 3);
											TextCell cell = new TextCell(contentsWithoutQuotes[1]);
											spreadsheet[location.getRow() + 1][location.getCol() + 1] = cell;
										}
									}
								}
										
							}
						}
						returnString = this.getGridText();
					}
					if(command.toLowerCase().equals("clear")) {
						Cell [][] clearedSpreadsheet = new Cell[21][13];
						for(int i = 1; i < 21; i++) {
						for(int j = 1; j < 13; j++) {
							clearedSpreadsheet [i][j] = new EmptyCell();
						}
						}
						spreadsheet = clearedSpreadsheet;
						returnString = this.getGridText();
					}
					if(command.toLowerCase().contains("clear ")) {
						String [] splitInput2 = command.split(" ", 2);
			
						SpreadsheetLocation location = new SpreadsheetLocation(splitInput2[1]);
						spreadsheet[location.getRow() + 1][location.getCol() + 1] = new EmptyCell();
						returnString = this.getGridText();	
					}
					if(command.substring(0,4).equals("save")){
						returnString = this.writeToFile(command.substring(5));
					}
					if(command.substring(0,4).equals("open")){
						returnString = this.readFromFile(command.substring(5));
					}
				}	
			}
		}
		
		return returnString;
	}
	
	//Saves a spreadsheet and its contents to a file
	private String writeToFile(String filename){ 
		
		PrintStream outputFile;

		try {
		outputFile = new PrintStream(new File(filename));
		}

		catch (FileNotFoundException e) {
			return "File not found: " + filename;
		}

		String line;
		char cellColumn = 'A';
		String cellName;
		String cellType;
		for (int col = 1; col < 13; col++) {
			for (int row = 1; row < 21; row++) {
				cellName = "" + cellColumn + row;
				if(!spreadsheet[row][col].fullCellText().equals("")) {
					if(spreadsheet[row][col].abbreviatedCellText().contains("%")) {
						cellType = "PercentCell";
					}
					else {
						if(spreadsheet[row][col].fullCellText().contains("\"")) {
							cellType = "TextCell";
						}
						else {
							if(isNumeric(spreadsheet[row][col].fullCellText())) {
								cellType = "ValueCell";
							}
							else {
								
								cellType = "FormulaCell";
							}
						}
					}
					line = cellName + "," + cellType + "," + spreadsheet[row][col].fullCellText();
					outputFile.println(line);
				}
			}
			cellColumn++;
		}
		
		outputFile.close();

		return this.getGridText();

		}

	//Reads an input file, and creates a spreadsheet from the information in the file
	private String readFromFile(String filename){

		Scanner inputFile;

		try {

		inputFile = new Scanner(new File(filename));

		}

		catch (FileNotFoundException e) {

		return "File not found: " + filename;

		}
		String line;
		String [] splitLine;
		while(inputFile.hasNextLine()) {
			line = inputFile.nextLine();
			splitLine = line.split(",", 3);
			SpreadsheetLocation location = new SpreadsheetLocation(splitLine[0]);
			if(splitLine[1].equals("TextCell")) {
				String [] contentsWithoutQuotes = splitLine[2].split("\"", 3);
				TextCell cell = new TextCell(contentsWithoutQuotes[1]);
				spreadsheet[location.getRow() + 1][location.getCol() + 1] = cell;	
			}
			else {
				if(splitLine[1].equals("PercentCell")) {
					PercentCell percentCell = new PercentCell("" + Double.parseDouble(splitLine[2])*100);
					spreadsheet[location.getRow() + 1][location.getCol() + 1] = percentCell;	
				}
				else {
					if(splitLine[1].equals("FormulaCell")) {
						FormulaCell formulaCell = new FormulaCell(splitLine[2]);
						spreadsheet[location.getRow() + 1][location.getCol() + 1] = formulaCell;	
					}
					else {
						ValueCell valueCell = new ValueCell(splitLine[2]);
						spreadsheet[location.getRow() + 1][location.getCol() + 1] = valueCell;	
					}
				}
			}
			
			
		}

		inputFile.close();
		
		return this.getGridText();

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

	//Returns the number of rows in the spreadsheet
	public int getRows() {
		return numberOfRows;
	}

	//Returns the number of columns in the spreadsheet
	public int getCols() {
		return numberOfColumns;
	}

	//Returns the cell at a given location in the spreadsheet
	public Cell getCell(Location loc) {
		return spreadsheet[loc.getRow() + 1][loc.getCol() + 1];
	}

	//Returns the entire spreadsheet
	public String getGridText() {
		String grid = "";
		char colCount = 'A';
		
		for(int row = 0; row < 21; row++) {
			for(int col = 0; col < 13; col++) {
				if(row == 0) {
					if(col == 0) {
						grid += "   |";
					}
					else {
					grid += "" + colCount + "         |";
					colCount++;	
					}
				}
				else {
					if (col == 0) {
						if(row < 10) {
							grid += "" + row + "  |";
						}
						else {
							grid += "" + row + " |";
						}
						
					}
					else {
						String test = spreadsheet[row][col].abbreviatedCellText();
						if(test.equals("")){
							grid += "          |";
						}
						else{
							grid += test + "|";
						}
					}
				
			}
		}
			
		grid += "\n";
	}
	return grid;
	}
}
