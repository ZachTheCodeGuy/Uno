import java.util.*;

public class Player
{
	private String Name;
	private Deck hand;
	private Deck discard;
	
	public Player()
	{
		Scanner temp=new Scanner(System.in);
		System.out.print("Welcome to Go Fish.  What is your name? ");
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
}