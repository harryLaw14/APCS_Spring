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
		    writer.write(enter);
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
		
		String[] spliff=command.split(" ");
		spliff[0]=spliff[0].toUpperCase();
		if(spliff[0].equals("SAVE")){
			return save(spliff[1]);
		}
		if(spliff[0].equals("OPEN")){
			try {
				return open(spliff[1]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(command.charAt(command.length()-1)=='%'){
			spliff[1]=spliff[1].toUpperCase();
			SpreadsheetLocation low= new SpreadsheetLocation(spliff[0]);
			sheet[low.getRow()][low.getCol()]=new PercentCell(spliff[2]);
			save("banana");
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
					sheet[low.getRow()][low.getCol()]=new FormulaCell(putin);
					
				}
				save("banana");
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
			save("banana");
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
			save("banana");
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