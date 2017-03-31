package textExcel;
import java.io.*;
import java.util.Scanner;

public class Spreadsheet implements Grid{
	private  Cell[][] sheet;
	
	public Spreadsheet(){
		sheet=new Cell [20][12];
		for(int i=0; i<20; i++){
			for(int j=0; j<12;j++){
				sheet[i][j]=new EmptyCell();
			}
		}
	}
	public String open(String filename) throws FileNotFoundException{
		Scanner input = new Scanner(new File(filename));
		while(input.hasNext()==true){
			String yell = input.nextLine();
			String[] splitter=yell.split(",");
			
			splitter[0]=splitter[0].toUpperCase();
			SpreadsheetLocation low=new SpreadsheetLocation(splitter[0]);
			if(splitter[1].equals("TextCell")){
				sheet[low.getRow()][low.getCol()]=new TextCell(splitter[2]);
			}
			if(splitter[1].equals("PercentCell")){
				double omg=Double.parseDouble(splitter[2]);
				omg*=100;
				String yeet=omg+"%";
				sheet[low.getRow()][low.getCol()]=new PercentCell(yeet);
			}
			if(splitter[1].equals("ValueCell")){
				sheet[low.getRow()][low.getCol()]=new ValueCell(splitter[2]);
			}
			if(splitter[1].equals("FormulaCell")){
				sheet[low.getRow()][low.getCol()]=new FormulaCell(splitter[2]);
			}
			
		}
		return getGridText();
	}
	public String saver(String filename){
		String submit="";
		for(int i=0;i<20;i++){
			for(char j='A';j<'M';j++){
				Cell testcase=sheet[i][j-'A'];
				if(sheet[i][j-'A'] instanceof FormulaCell){
					submit+=j+""+(i+1)+",FormulaCell,"+testcase.fullCellText()+"\n";
				}
				if(sheet[i][j-'A'] instanceof TextCell){
					submit+=j+""+(i+1)+",TextCell,"+testcase.fullCellText()+"\n";
				}
				if(sheet[i][j-'A'] instanceof ValueCell){
					submit+=j+""+(i+1)+",ValueCell,"+testcase.fullCellText()+"\n";
				}
				if(sheet[i][j-'A'] instanceof PercentCell){
					submit+=j+""+(i+1)+",PercentCell,"+testcase.fullCellText()+"\n";
				}
				
				
			}
			
		}
		Writer writer = null;
		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filename), "utf-8"));
		    writer.write(submit);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

			
		return "";
	}
	public String processCommand(String command)
	{
		
		if(command.equals("")){
			return "";
		}
		
		String[] slick=command.split(" ");
		slick[0]=slick[0].toUpperCase();
		if(slick[0].equals("SAVE")){
			return (slick[1]);
		}
		if(slick[0].equals("OPEN")){
			try {
				return open(slick[1]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(command.charAt(command.length()-1)=='%'){
			slick[1]=slick[1].toUpperCase();
			SpreadsheetLocation low= new SpreadsheetLocation(slick[0]);
			sheet[low.getRow()][low.getCol()]=new PercentCell(slick[2]);
			saver("water");
			return getGridText();
		}
		if(slick.length>1){
		if(slick[1].equals("=")){
			if(slick[2].substring(0,1).equals("\"")==false){
				
				SpreadsheetLocation lowlife= new SpreadsheetLocation(slick[0]);
				if (slick.length==3){
					sheet[lowlife.getRow()][lowlife.getCol()]=new ValueCell(slick[2]);
					
				}
				else{
					String foodie="";
					for(int i=2;i<slick.length;i++){
						if(i>=3){
							foodie+=" ";
						}
						foodie+=slick[i];
						
					}
					sheet[lowlife.getRow()][lowlife.getCol()]=new FormulaCell(foodie);
					
				}
				saver("water");
				return getGridText();
			}
		}
		}
		
		if(slick[0].equals("CLEAR")){
			if(slick.length>1){
				slick[1]=slick[1].toUpperCase();
				SpreadsheetLocation lowlife= new SpreadsheetLocation(slick[1]);
				sheet[lowlife.getRow()][lowlife.getCol()]=new EmptyCell();
			}
			else{
				sheet=new Cell [20][12];
				for(int i=0; i<20; i++){
					for(int j=0; j<12;j++){
						sheet[i][j]=new EmptyCell();
					}
				}
			}
			saver("water");
			return getGridText();
		}
		else if(slick.length==1){
			SpreadsheetLocation lowlife= new SpreadsheetLocation(slick[0]);
			Cell aboutToInspect=getCell(lowlife);
			return aboutToInspect.fullCellText();
		} else if(slick[1].equals("=")){
			SpreadsheetLocation lowlife= new SpreadsheetLocation(slick[0]);
			String foodie="";
			for(int i=2;i<slick.length;i++){
				if(i>=3){
					foodie+=" ";
				}
				foodie+=slick[i];
				
			}
			
			sheet[lowlife.getRow()][lowlife.getCol()]=new TextCell(foodie);
			saver("water");
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
	public  Cell getCell(Location location){
		int alpha=location.getRow();
		int beta=location.getCol();
		return sheet[alpha][beta];
	}

	@Override
	public String getGridText()
	{
		String grid="   |A         |B         |C         |D         |E         |F         |G         |H         |I         |J         |K         |L         |"+"\n";
		for (int i=0;i<this.getRows();i++){
			if(i<9)
				grid=grid+(i+1)+"  ";
			else
				grid=grid+(i+1)+" ";
			for (int j=0;j<this.getCols();j++){
				grid=grid+"|"+ sheet[i][j].abbreviatedCellText();
			}
			grid=grid+"|\n";
		}

		return grid;
	}
}