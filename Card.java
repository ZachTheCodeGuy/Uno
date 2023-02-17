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
			return "Red Reverse";
		}
		if(color==5){
			return "Blue Reverse";
		}
		if(color==6){
			return "Green Reverse";
		}
		if(color==7){
			return "Yellow Reverse";
		}
		if(color==8)
		{
			return "Red +2";
		}
		if(color==9)
		{
			return "Blue +2";
		}
		if(color==10)
		{
			return "Green +2";
		}
		if(color==11)
		{
			return "Yellow +2";
		}
		if(color==12){
			return "Wild";
		}
		if(color==13){
			return "Wild +4";
		}
		if(color==14){
			return "Red Skip";
		}
		if(color==15){
			return "Blue Skip";
		}
		if(color==16){
			return "Green Skip";
		}
		if(color==17){
			return "Yellow Skip";
		}
		return null;
	}
	public int displayValue()
	{
		return value;
	}	
	public String displayCard()
	{
		if(color>=0 && color<=3){
			return displayColor() + " " + displayValue();
		}
		return displayColor() + " Card";
	}	
	public boolean setValue(int v)
	{
		if(v>=0 && v <=9)
		{
			value=v;
			return true;
		}
		return false;	
	}
	public boolean setColor(int c)
	{
		if(c>=0 && c<=12)
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