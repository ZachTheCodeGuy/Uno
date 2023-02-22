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
	public int getColor()
	{
		return color; 
	}	
	public int getValue()
	{
		return value; 
	}	
	public boolean getVisible()
	{
		return visible; 
	}	
	public String displayColor()
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
	public String displayValue()
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
	public String displayCard()
	{
		return displayColor() + " " + displayValue();
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