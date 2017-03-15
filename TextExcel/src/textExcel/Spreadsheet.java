package textExcel;

public class Spreadsheet implements Grid{
	private Cell[][] sheet;
	
	public Spreadsheet(){
		sheet=new Cell [20][12];
		for(int i=0; i<20; i++){
			for(int j=0; j<12;j++){
				sheet[i][j]=new EmptyCell();
			}
		}
	}
   	public String processCommand(String command)
	{
		if(command.equals("")){
			return "";
		}
		
		String[] spliff=command.split(" ");
		spliff[0]=spliff[0].toUpperCase();
		if(command.charAt(command.length()-1)=='%'){
			spliff[1]=spliff[1].toUpperCase();
			SpreadsheetLocation low= new SpreadsheetLocation(spliff[0]);
			sheet[low.getRow()][low.getCol()]=new PercentCell(spliff[2]);
			return getGridText();
		}
		if(spliff.length>1){
		if(spliff[1].equals("=")){
			if(spliff[2].substring(0,1).equals("\"")==false){
				
				SpreadsheetLocation low= new SpreadsheetLocation(spliff[0]);
				if (spliff.length==3){
					sheet[low.getRow()][low.getCol()]=new ValueCell(spliff[2]);
					
				}
				else{
					String putin="";
					for(int i=2;i<spliff.length;i++){
						if(i>=3){
							putin+=" ";
						}
						putin+=spliff[i];
						
					}
					sheet[low.getRow()][low.getCol()]=new FormulaCell(spliff[2]);
					
				}
				return getGridText();
			}
		}
		}
		
		if(spliff[0].equals("CLEAR")){
			if(spliff.length>1){
				spliff[1]=spliff[1].toUpperCase();
				SpreadsheetLocation low= new SpreadsheetLocation(spliff[1]);
				sheet[low.getRow()][low.getCol()]=new EmptyCell();
			}
			else{
				sheet=new Cell [20][12];
				for(int i=0; i<20; i++){
					for(int j=0; j<12;j++){
						sheet[i][j]=new EmptyCell();
					}
				}
			}
			return getGridText();
		}
		else if(spliff.length==1){
			SpreadsheetLocation low= new SpreadsheetLocation(spliff[0]);
			Cell aboutToInspect=getCell(low);
			return aboutToInspect.fullCellText();
		} else if(spliff[1].equals("=")){
			SpreadsheetLocation low= new SpreadsheetLocation(spliff[0]);
			String putin="";
			for(int i=2;i<spliff.length;i++){
				if(i>=3){
					putin+=" ";
				}
				putin+=spliff[i];
				
			}
			
			sheet[low.getRow()][low.getCol()]=new TextCell(putin);
			
			return getGridText();
		}
		
		return "";
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
		int a=loc.getRow();
		int b=loc.getCol();
		
		return sheet[a][b];
	}

	@Override
	public String getGridText(){
		String griddy="   ";
		for(char kkk='A'; kkk<='L'; kkk++){
			griddy+="|"+kkk+"         ";
		}
		griddy+="|\n";
		for(int i=0;i<20;i++){
			String n=(i+1)+"   ";
			griddy+=n.substring(0, 3);
			for(int j=0;j<12;j++){
				griddy+="|";
				String s =sheet[i][j].abbreviatedCellText();
				griddy+=s;
			}
			griddy+="|\n";	
		}

		return griddy;
	}

}
