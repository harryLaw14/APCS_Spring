package textExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormulaCell extends RealCell {

	public FormulaCell(String enter){
		super(enter);
	}
	public double getCellFile(String location) throws FileNotFoundException{
		Scanner input = new Scanner(new File("banana"));//directing where to find file
		double solution;
		while(input.hasNext()==true){
			String call = input.nextLine();
			String[] splitto=call.split(",");
			splitto[0]=splitto[0].toUpperCase();
			if(splitto[0].equals(location)){
				if(splitto[1].equals("ValueCell")||splitto[1].equals("PercentCell")){
				solution=Double.parseDouble(splitto[2]);
				return solution;
				}
				else{
					FormulaCell oy=new FormulaCell(splitto[2]);
					return oy.getDoubleValue();
				}
			}
		}
		return 0;
	}
	public double getDoubleValue(){
		String[] apple=fullCellText().split(" ");
		double answer=0;
		apple[1]=apple[1].toUpperCase();
		if(apple[1].equals("AVG")||apple[1].equals("SUM")){
			apple[2]=apple[2].toUpperCase();
			String[] locks =apple[2].split("-");
			int count=0;
			double total =0;
			//b1 = ( avG A2-a2 )
			int first=Integer.parseInt(locks[0].substring(1));
			int second=Integer.parseInt(locks[1].substring(1));
			
			char what=locks[1].charAt(0);
			for(char let=locks[0].charAt(0); let<=what; let++){
				for(int j=first;j<=second;j++){
					
					String cellLoc=Character.toString(let)+j;
							
					try {
						if(cellThere(cellLoc)){
							total+=getCellFile(cellLoc);
							count++;
							
							
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if(apple[1].equals("SUM")){
				return total;
			} else{
			return total/count;
			}
		} 
		if(Character.isLetter(apple[1].charAt(0))){
			try {
				answer=getCellFile(apple[1]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			answer =Double.parseDouble(apple[1]);
		
		}
		
		for(int i=3; i<apple.length; i+=2){
			double additional=0;
			if(Character.isLetter(apple[i].charAt(0))){
				try {
					additional=getCellFile(apple[i].toUpperCase());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				additional=Double.parseDouble(apple[i]);
			}
				if(apple[i-1].equals("+")){
					answer+=additional;
				}
				if(apple[i-1].equals("-")){
					answer-=additional;
				}
				if(apple[i-1].equals("*")){
					answer*=additional;
				}
				if(apple[i-1].equals("/")){
					answer/=additional;
				}
			}
		
		return answer;
	}
	public String abbreviatedCellText(){
		String output=getDoubleValue()+"                   ";
		return output.substring(0, 10);
	}
	public boolean cellThere(String location) throws FileNotFoundException{
		Scanner input = new Scanner(new File("banana"));//directing where to find file
		while(input.hasNext()==true){
			String call = input.nextLine();
			String[] splitto=call.split(",");
			splitto[0]=splitto[0].toUpperCase();
			if(splitto[0].equals(location)){
				
				return true;
				}
		}
		return false;
			
			
		
	}
}
