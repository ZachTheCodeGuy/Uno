import java.util.*;

public class Deck
{
	private ArrayList<Card> cards;
	
	
	public Deck()	
	{
		cards=new ArrayList<Card>();		
	}
	
	public void addCard(Card c)
	{
		cards.add(c);
	}
	public boolean addCard(int pos, Card c)
	{
		if(pos>=0 && pos <= cards.size())
		{
			cards.add(pos,c);
			return true;
		}
		return false;
	}
	public Card removeCard(int index)	
	{
		if(index >= 0 && index < cards.size() )
		{
			return cards.remove(index);
		}
		return null;
	}

	public Card moveToBottom(){
		addCard(cards.size(), removeCard(0));
		return null;
	}

	public Card getCard(int index)
	{
		return cards.get(index);
	}
	public int getValueIndex(int val)//will return the index of the first card with val	
	{
		for(int indx=0; indx<cards.size(); indx++)
		{
			if(cards.get(indx).getValue()==val)
			{
				return indx;
			}			
		}
		return -1;	
	}	
	public int getColorIndex(int s)//will return the index of the first card with s	
	{
		for(int indx=0; indx<cards.size(); indx++)
		{
			if(cards.get(indx).getColor()==s)
			{
				return indx;
			}			
		}
		return -1;	
	}	
	public int getCardIndex(int val, int Color)//will return the index of the first card with val and Color
	{
		for(int indx=0; indx<cards.size(); indx++)
		{
			if(cards.get(indx).getValue()==val && cards.get(indx).getColor()==Color)
			{
				return indx;
			}			
		}
		return -1;	
	}	
	public int getSize()
	{
		return cards.size();
	}	
	public String displayDeck()
	{
		String ret="";
		for(int indx=0; indx<cards.size(); indx++)
		{
			ret=ret+(indx+1)+". "+cards.get(indx).displayCard()+"\n";
		}
		return ret;
	}
	public String displayTopCard()
	{
		if(cards.get(0).getColor() >= 0 && cards.get(0).getColor() <= 3)
		{
			return cards.get(0).displayColor() +" "+ cards.get(0).displayValue();
		}
		return cards.get(0).displayColor() + " Card";
	}
	public void shuffleDeck()
	{
		Deck temp=new Deck();
		while(cards.size()>0)
		{
			temp.addCard(removeCard((int)(Math.random()*cards.size())));
		}
		while(temp.getSize()>0)
		{
			addCard(temp.removeCard((int)(Math.random()*temp.getSize())));
		}
	}
	public int getCount(int val)
	{
		int cnt=0;
		for(Card C : cards)
		{
			if(C.getValue()==val)
			{
				cnt++;
			}			
		}
		return cnt;
	}
	private int getSortValue(int indx)
	{
		return cards.get(indx).getValue()*10+cards.get(indx).getColor();		
	}
	public void sortDeck()	
	{
		//  idenitfy the first item in the list to be sorted as our lowest value.
		//	Look at all subsequent values for a lower value and set each lower value to the current lowest
		// 
		int start=0;
		int current;
		int lowest;

		while(start < cards.size()-1)
		{
			current=start+1;
			lowest=start;
			while(current < cards.size())
			{
				if(getSortValue(current) < getSortValue(lowest))
				{
					lowest=current;
				}				
				current++;
			}
			cards.add(start,cards.remove(lowest));
			start++;
		}
	}
}