package textExcel;

public class Spreadsheet implements Grid{
	
	private Cell[][] spreadsheet = new EmptyCell[12][20];
	
	@Override
	public String processCommand(String command){
		return "";
	}
	
	@Override
	public String getGridText(){
		String gridText = "";
		System.out.print("  ");
		for (char c = 'A'; c <= 'L'; c++){
			System.out.print("|" + c + "         ");
		}
		System.out.println("|");
		for (int i = 1; i <= 20; i++){
			System.out.print(i);
			if (i < 10){
				System.out.print(" ");
			}
			for (char a = 'A'; a <= 'L'; a++){
				System.out.print("|" + spreadsheet[(int)a - 65][i-1].abbreviatedCellText());
			}
		System.out.println("|");
		}
		return gridText;
	}

	@Override
	public int getRows(){
		return 20;
	}

	@Override
	public int getCols(){
		return 12;
	}

	@Override
	public Cell getCell(Location loc){
		return null;
	}
	
	public Spreadsheet(){
		for(int i = 0; i < spreadsheet.length; i++){
			for (int j = 0; j < spreadsheet[i].length; j++){
				spreadsheet[i][j] = new EmptyCell();
			}
		}
	}
	}
