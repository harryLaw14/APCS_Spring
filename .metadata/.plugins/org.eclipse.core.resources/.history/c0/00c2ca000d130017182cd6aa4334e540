package textExcel;

public class Spreadsheet implements Grid{	
	private Cell[][] excel= new Cell[this.getRows()][this.getCols()];
	public Spreadsheet(){
		for (int i=0;i<excel.length;i++){
			for (int j=0;j<excel[i].length;j++){
				excel[i][j]=new EmptyCell();
			}
	}
}
@Override
	public String processCommand(String command){	
		if(command.equals("")){//Clearing
			return command;
		}
		if (command.toUpperCase().equals("CLEAR")){//clear a cell
			for (int i=0;i<excel.length;i++){
				for (int j=0;j<excel[i].length;j++){
					excel[i][j]=new EmptyCell();
				}
			}
			return this.getGridText();
		}
		if (command.toUpperCase().startsWith("CLEAR")){
			excel[Integer.parseInt(command.substring(7))-1][command.toUpperCase().charAt(6)-65] = new EmptyCell();
			return this.getGridText();
		}
		command=command.substring(0,1).toUpperCase()+command.substring(1);
		if (command.charAt(0)>64&&command.charAt(0)<91){
		if(command.indexOf('=')==-1){
			String cellValue=excel[Integer.parseInt(command.substring(1))-1][command.charAt(0)-65].fullCellText();
			return cellValue;
		}	
		if (command.contains("\"")){
			if(command.charAt(2)==' '){
				excel[Integer.parseInt(command.substring(1,2))-1][command.charAt(0)-65]=new TextCell(command.substring(command.indexOf('"')+1,command.lastIndexOf('"')));
			}else{
				excel[Integer.parseInt(command.substring(1,3))-1][command.charAt(0)-65]=new TextCell(command.substring(command.indexOf('"')+1,command.lastIndexOf('"')));
			}
			return getGridText();
			
		}else if(command.indexOf('=')!=-1 && command.indexOf("(")!=-1&&command.indexOf(")")!=-1){
			if(command.charAt(2)==' '){//formulacell
				excel[Integer.parseInt(command.substring(1,2))-1][command.charAt(0)-65]=new FormulaCell(command.substring(command.indexOf('(')+1));
			}else{	
				excel[Integer.parseInt(command.substring(1,3))-1][command.charAt(0)-65]=new FormulaCell(command.substring(command.indexOf('(')+1));
			}
			String newSpreadsheet = this.getGridText();
			return newSpreadsheet;
		}else if(command.indexOf('=')!=-1 && command.indexOf("(")==-1&&command.indexOf(")")==-1&&command.indexOf("%")==-1){
			if(command.charAt(2)==' '){//valuecell
				excel[Integer.parseInt(command.substring(1,2))-1][command.charAt(0)-65]=new ValueCell(command.substring(command.indexOf('=')+2));
			}else{
				excel[Integer.parseInt(command.substring(1,3))-1][command.charAt(0)-65]=new ValueCell(command.substring(command.indexOf('=')+2));
			}
			return getGridText();
			}
		}else if(command.indexOf("%")!=-1){
			if(command.charAt(2)==' '){//percentcell
				excel[Integer.parseInt(command.substring(1,2))-1][command.charAt(0)-65]=new PercentCell(command.substring(command.indexOf('=')+2));
			}else{
				excel[Integer.parseInt(command.substring(1,3))-1][command.charAt(0)-65]=new PercentCell(command.substring(command.indexOf('=')+2));
			}
			String newSpreadsheet = this.getGridText();
			return newSpreadsheet;
		}
		return command;
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
		return excel[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText(){
		String grid="   |A         |B         |C         |D         |E         |F         |G         |H         |I         |J         |K         |L         |"+"\n";
		for (int i=0;i<this.getRows();i++){
			if(i<9){
				grid=grid+(i+1)+"  ";
			}else{
				grid=grid+(i+1)+" ";
			}
			for (int j=0;j<this.getCols();j++){
				grid=grid+"|"+ excel[i][j].abbreviatedCellText();
			}
			grid=grid+"|\n";
		}

		return grid;
	}

}

