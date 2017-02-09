
public class Ticket {
	public int number;
	private double price;
		
	public double getprice(){//
		return price;
	}
	
	
	public class walkupTicket extends Ticket {
		//$50
	}

	public class advancedTicket extends Ticket {
		//if days >= 10
			//$30
		//else 
			//$40
	}
	
	public class studentAdvancedTicket extends Ticket {
		//if days >= 10
			//$15
		//else
			//$20
	}
	
}
