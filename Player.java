import java.util.*;

public class Player
{
	private String Name;
	private Deck hand;
	private Deck discard;
	private int pin;
	
	public Player()
	{
		Scanner temp=new Scanner(System.in);
		System.out.print("Welcome to Uno.  What is your name? ");
		Name=temp.nextLine();		
		hand=new Deck();
	}
	public Deck getHand()
	{
		return hand;
	}
	public String getName()
	{
		return Name;
	}
	public void setPin(int p)
	{
		pin=p;
	}
	public int getPin()
	{
		return pin;
	}
}