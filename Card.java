public class Card
{
	// The card class represents each card in a card deck 
	// Each card has a color and a value
	//
	private int color;
	private int value;
    private boolean visible;// true = you can see the card, false=card is face down

	public Card (int c, boolean x){
		color=c;
		visible=x;
	}

	public Card(int c, int v)
	{
		color=c;
		value=v;
		visible=false;
	}
	public Card(int c, int v, boolean x)
	{
		color=c;
		value=v;
		visible=x;
	}
	public int getColor() //gets color
	{
		return color; 
	}	
	public int getValue() //Gets value
	{
		return value; 
	}	
	public boolean getVisible() //Gets visable
	{
		return visible; 
	}	
	public String displayColor() //Displays color
	{
		if(color==0)
		{
			return "Red";
		}
		if(color==1)
		{
			return "Blue";
		}
		if(color==2)
		{
			return "Green";
		}
		if(color==3)
		{
			return "Yellow";
		}
		if(color==4){
			return "";
		}
		return null;
	}
	public String displayValue() //Displays value
	{
		if(value>=0 && value <=9){
			return value+"";
		}
		if(value==10){
			return "reverse";
		}
		if(value==11){
			return "skip";
		}
		if(value==12){
			return "+2";
		}
		if(value==13){
			return "wild +4";
		}
		if(value==14){
			return "wild";
		}
		return null;
	}	

	public String displayCard() //Displays card
	{
		return displayColor() + " " + displayValue();
	}	

	public boolean isWild()
	{
		return value==14;
	}

	public boolean isWildP4()
	{
		return value==13;
	}

	public boolean isP2()
	{
		return value==12;
	}

	public boolean isSkip()
	{
		return value==11;
	}

	public boolean isReverse()
	{
		return value==10;
	}

	public boolean isMatching(Card other) //Other should be to top card on the pile
	{
		return color == other.getColor() || value == other.getValue() || isWild() || isWildP4();
	}

	public boolean setValue(int v)
	{
		if(v>=0 && v <=14)
		{
			value=v;
			return true;
		}
		return false;	
	}

	public boolean setColor(int c)
	{
		if(c>=0 && c<=4)
		{
			color=c;
			return true;
		}
		return false;		
	}

	public boolean toggleVisible()
	{
		visible = !visible;
		return visible;
	}
	
	public void setVisible(boolean v)
	{
		visible = v;
	}
}