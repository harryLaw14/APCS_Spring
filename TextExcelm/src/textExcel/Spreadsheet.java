package textExcel;
import java.io.*;
import java.util.Scanner;


//Takahiro Mollenkamp 3/8/17

// Update this file with your own code.

public class Spreadsheet implements Grid
{
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
	
		Scanner input = new Scanner(new File(filename));//directing where to find file
		while(input.hasNext()==true){
			String call = input.nextLine();
			String[] splitto=call.split(",");
			
			splitto[0]=splitto[0].toUpperCase();
			SpreadsheetLocation low=new SpreadsheetLocation(splitto[0]);
			if(splitto[1].equals("TextCell")){
				sheet[low.getRow()][low.getCol()]=new TextCell(splitto[2]);
			}
			if(splitto[1].equals("PercentCell")){
				double wtf=Double.parseDouble(splitto[2]);
				wtf*=100;
				String ehhh=wtf+"%";
				sheet[low.getRow()][low.getCol()]=new PercentCell(ehhh);
			}
			if(splitto[1].equals("ValueCell")){
				sheet[low.getRow()][low.getCol()]=new ValueCell(splitto[2]);
			}
			if(splitto[1].equals("FormulaCell")){
				sheet[low.getRow()][low.getCol()]=new FormulaCell(splitto[2]);
			}
			
		}
		return getGridText();
	}
	public String save(String filename){
		String enter="";
		for(int i=0;i<20;i++){
			for(char j='A';j<'M';j++){
				Cell testcase=sheet[i][j-'A'];
				if(sheet[i][j-'A'] instanceof FormulaCell){
					enter+=j+""+(i+1)+",FormulaCell,"+testcase.fullCellText()+"\n";
				}
				if(sheet[i][j-'A'] instanceof TextCell){
					enter+=j+""+(i+1)+",TextCell,"+testcase.fullCellText()+"\n";
				}
				if(sheet[i][j-'A'] instanceof ValueCell){
					enter+=j+""+(i+1)+",ValueCell,"+testcase.fullCellText()+"\n";
				}
				if(sheet[i][j-'A'] instanceof PercentCell){
					enter+=j+""+(i+1)+",PercentCell,"+testcase.fullCellText()+"\n";
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
				// TODO Auto-generated catch block
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
	public int getRows()
	{
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public int getCols()
	{
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public  Cell getCell(Location loc)
	{
		// TODO Auto-generated method stub
		int a=loc.getRow();
		int b=loc.getCol();
		
		
		return sheet[a][b];
	}

	@Override
	public String getGridText()
	{
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
		// TODO Auto-generated method stub
		return griddy;
	}
	

}
