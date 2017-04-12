package textExcel;
import java.io.*;
import java.util.Scanner;

public class Spreadsheet implements Grid{
	private Cell[][] spreadsheet;
	
	public Spreadsheet(){
		spreadsheet=new Cell [20][12];
		for(int i=0; i<20; i++){
			for(int j=0; j<12;j++){
				spreadsheet[i][j]=new EmptyCell();
			}
		}
	}
	
	public String processCommand(String command){
		
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
				return opener(slick[1]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(command.charAt(command.length()-1)=='%'){
			slick[1]=slick[1].toUpperCase();
			SpreadsheetLocation lowlife= new SpreadsheetLocation(slick[0]);
			spreadsheet[lowlife.getRow()][lowlife.getCol()]=new PercentCell(slick[2]);
			saver("water");
			return getGridText();
		}
		
		if(slick.length>1){
			if(slick[1].equals("=")){
				if(slick[2].substring(0,1).equals("\"")==false){
					SpreadsheetLocation lowlife= new SpreadsheetLocation(slick[0]);
				if (slick.length==3){
					spreadsheet[lowlife.getRow()][lowlife.getCol()]=new ValueCell(slick[2]);
					
				}else{
					String foodie="";
					for(int i=2;i<slick.length;i++){
						if(i>=3){
							foodie+=" ";
						}
						foodie+=slick[i];
						
					}
					spreadsheet[lowlife.getRow()][lowlife.getCol()]=new FormulaCell(foodie);
					
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
				spreadsheet[lowlife.getRow()][lowlife.getCol()]=new EmptyCell();
			}else{
				spreadsheet=new Cell [20][12];
				for(int i=0; i<20; i++){
					for(int j=0; j<12;j++){
						spreadsheet[i][j]=new EmptyCell();
					}
				}
			}
			saver("water");
			return getGridText();
		}else if(slick.length==1){
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
			
			spreadsheet[lowlife.getRow()][lowlife.getCol()]=new TextCell(foodie);
			saver("water");
			return getGridText();
		}
		
		return "";
	}

	private String opener(String filename) throws FileNotFoundException{
		Scanner input = new Scanner(new File(filename));
		while(input.hasNext()==true){
			String yell = input.nextLine();
			String[] divide=yell.split(",");
			divide[0]=divide[0].toUpperCase();
			SpreadsheetLocation lowlife=new SpreadsheetLocation(divide[0]);
			if(divide[1].equals("TextCell")){
				spreadsheet[lowlife.getRow()][lowlife.getCol()]=new TextCell(divide[2]);
			}
			if(divide[1].equals("PercentCell")){
				double omg=Double.parseDouble(divide[2]);
				omg*=100;
				String yeet=omg+"%";
				spreadsheet[lowlife.getRow()][lowlife.getCol()]=new PercentCell(yeet);
			}
			if(divide[1].equals("ValueCell")){
				spreadsheet[lowlife.getRow()][lowlife.getCol()]=new ValueCell(divide[2]);
			}
			if(divide[1].equals("FormulaCell")){
				spreadsheet[lowlife.getRow()][lowlife.getCol()]=new FormulaCell(divide[2]);
			}
			
		}
		return getGridText();
	}
	
	private String saver(String filename){
		String submit="";
		for(int i=0;i<20;i++){
			for(char j='A';j<'M';j++){
				Cell tester=spreadsheet[i][j-'A'];
				if(spreadsheet[i][j-'A'] instanceof FormulaCell){
					submit+=j+""+(i+1)+",FormulaCell,"+tester.fullCellText()+"\n";
				}
				if(spreadsheet[i][j-'A'] instanceof TextCell){
					submit+=j+""+(i+1)+",TextCell,"+tester.fullCellText()+"\n";
				}
				if(spreadsheet[i][j-'A'] instanceof ValueCell){
					submit+=j+""+(i+1)+",ValueCell,"+tester.fullCellText()+"\n";
				}
				if(spreadsheet[i][j-'A'] instanceof PercentCell){
					submit+=j+""+(i+1)+",PercentCell,"+tester.fullCellText()+"\n";
				}
				
				
			}
			
		}
		Writer type = null;
		try {
		    type = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filename), "lol-8"));
		    type.write(submit);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {type.close();} catch (Exception ex) {}
		}

			
		return "";
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
				grid=grid+"|"+ spreadsheet[i][j].abbreviatedCellText();
			}
			grid=grid+"|\n";
		}

		return grid;
	}

	@Override
	public  Cell getCell(Location location){
		int alpha=location.getRow();
		int beta=location.getCol();
		return spreadsheet[alpha][beta];
	}
	
	@Override
	public int getRows(){
		return 20;
	}

	@Override
	public int getCols(){
		return 12;
	}

}

