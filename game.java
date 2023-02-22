import java.util.*;


public class game
{
	private Deck draw;
	private Deck pile;
	private ArrayList <Player> players;
	private int numplayers;

	public game()
	{
		players=new ArrayList <Player>(); //Creates an array of player objects
		// build the draw deck.	
		draw=new Deck(); //Creates the draw deck
		pile=new Deck(); //Creates the pile


		int v=0;
		for(int i=0; i<=1; i++){ //Adds standard cards to the deck twice
			v=0;
			if(i==1){ //Uno only has one color set of 0's and two of every other value card, so this stops the algorithm from making another set of 0's
				v++;
			}
			while(v<=9) //Creates a card of each color for value v
			{
				for(int c=0;c<=3;c++)
				{
					draw.addCard(new Card(c, v, false));
				}
				v++;
			}	
		}

		for(int i=0; i<=1; i++){ //Does this all twice
			for(int c=0; c<=3; c++){ //Adds reverse cards
				draw.addCard(new Card(c, 10));
			}

			for(int c=0; c<=3; c++){ //Adds +2 cards
				draw.addCard(new Card(c, 12));
			}

			for(int c=0; c<=3; c++){ //Adds skip cards
				draw.addCard(new Card(c, 11));
			}
		}
		
		for(int i=0; i<=3; i++){ //Adds wild cards to the deck
			draw.addCard(new Card(4, 14, false));
		}

		for(int i=0; i<=3; i++){ //Adds wild +4 cards to the deck
			draw.addCard(new Card(4, 13, false));
		}

		System.out.println(draw.getSize());
		System.out.println(draw.displayDeck());
		
		//End build deck---------


		draw.shuffleDeck(); //Shuffels draw deck
				
		numplayers=1;
				
		Scanner ans=new Scanner(System.in); //Creates new scanner object
		System.out.print("     Welcome to Uno!!!!\nHow many people are playing(2-6)?"); //Asks how many people are playing
		while(true) //Loops code until user enters an excepted value
		{
			numplayers=ans.nextInt();			
			if(numplayers>=2 && numplayers<=6)
			{
				for(int pl=0;pl<numplayers;pl++) //Populates player list and performs code within for each
				{
					players.add(new Player()); //Adds the player object to its index in players array
					drawCards(7,0,draw,players.get(players.size()-1).getHand()); //Gives each player 7 cards to start
				}
				break;
			}
			System.out.print("You must enter a value between 2 and 6. Try again."); //User didn't enter a value between 2 and 6, code loops back
		}

		drawCards(1, 0, draw, pile); //Takes top card from deck and puts it in the pile
		gameloop(); //Starts the gameloop
	}


	private boolean drawCards(int num, int indx, Deck from, Deck to) //Removes card at indx from from, and adds is to to at index 0
	{
		for(int c=0;c<num;c++)
		{
			if(from.getSize()>0)
			{
				to.addCard(0, from.removeCard(indx));
				indx++;
			}
			else
			{
				return false;				
			}			
		}
		return true;
	}

	private boolean isSpecial(Card card){ //Checks if a player has a wild or wild +4 card
		if(card.getValue()==13 || card.getValue()==14){
			return true;
		}
		return false;
	}

	private boolean isColor(String c){ //Makes sure the user entered a valid color when asked what color they would like if they placed a wild card
		c=c.toLowerCase();
		if(c.equals("red")){
			return true;
		}
		if(c.equals("blue")){
			return true;
		}
		if(c.equals("green")){
			return true;
		}
		if(c.equals("yellow")){
			return true;
		}
		return false;
	}	

	private boolean isMatching(Player turn, int i){
		if(turn.getHand().getCard(i).getColor()==pile.getCard(0).getColor() && turn.getHand().getCard(i).getColor()<=3 && pile.getCard(0).getColor()<=3) //Compares colors if cards are both standard value cards
		{
			return true;
		} 
		else if(turn.getHand().getCard(i).getValue()==pile.getCard(0).getValue() && turn.getHand().getCard(i).getColor()<=9 && pile.getCard(0).getColor()<=9) //Compares values if cards are both standard value cards
		{
			return true;
		} 
		else if(isSpecial(turn.getHand().getCard(i))) //Checks if card is a Wild ot Wild +4 because these cards can be placed on anything
		{
			return true;
		} 
			
		return false;
	}

	private boolean endGame() //Checks if end condition is met
	{
		for(Player p : players)
		{
		 	if(p.getHand().getSize()!=0)
			{
				return false;
			}
		}	 	 
		return true;
	}

	private void gameloop()
	{
		//Set this up for each player and their turns
		//until the end condition is met(victory)
		// End condition is one player is out of cards
		//
		//Player 1 goes
		//They should check if they have a card in their hand that matches the top card in the place pile
		//If the player has a card with the same value or color as the top card in the pile, they can add said card
		//If the player doesnt have the right card, they must take a card form the draw pile
		//Next players turn begins
		Player turn;
 		while(!endGame()) //Repeats until end condition is met
		{
			for(int i=0;i<numplayers;i++) //Gives every player a turn until the game ends
			{
				turn=players.get(i);

				turn.getHand().sortDeck(); //Sorts the players deck

				System.out.println(" \n \n \n"); //Space in terminal for ease of use
				System.out.println(turn.getName() + " it's your turn! \n Heres what you have in your hand: \n\n " + turn.getHand().displayDeck()); //Displays hand
				System.out.println("Match this card: " + pile.displayTopCard()); //Displays the top card in the pile that the player has to match

				boolean found=false;

				for(int x=0; x<turn.getHand().getSize(); x++){ //Iterates theough the players hand to see if they have a card that matches the top card in the pile
					if(isMatching(turn, i))
					{
						found=true;
					} 	
				}

				if(found=true){ //If the player has a matching card, do this

					while(true)
					{
						Scanner chooseCard = new Scanner(System.in); //Creats scanner (input)
						System.out.println("Please select a card to place down"); //Asks player which card they want to place down
						int choice = chooseCard.nextInt(); //Sets input to a variable

						if(choice-1>turn.getHand().getSize() || choice<1) //If the input is outside the range of cards that the user has
						{
							System.out.println("Sorry, the value you chose is outside the values of your available cards \n"); //Tell them to choose something else
						} 
						else
						{
							if(turn.getHand().getCard(choice-1).getColor() == pile.getCard(0).getColor())
							{
								if(turn.getHand().getCard(choice-1).getColor()<=3 && pile.getCard(0).getColor()<=3)
								{
									drawCards(1, choice-1, turn.getHand(), pile);
									System.out.println("You placed your " + turn.getHand().getCard(choice-1).displayCard() + " into the pile");
								}
							}

							if(turn.getHand().getCard(choice-1).getValue() == pile.getCard(0).getValue())
							{
								if(turn.getHand().getCard(choice-1).getValue()<=9 && pile.getCard(0).getColor()<=9)
								{
									drawCards(1, choice-1, turn.getHand(), pile);
									System.out.println("You placed your " + turn.getHand().getCard(choice-1).displayCard() + " into the pile");
								}
							}
	
							if(turn.getHand().getCard(choice-1).getValue()==14 || turn.getHand().getCard(choice-1).getColor()==13){ //Checks if card is wild and does wild card action
								String ans="";
								while(true)
								{
									Scanner ask = new Scanner(System.in);
									System.out.println("What color would you like to change it to?");
									ans = ask.nextLine();
	
									if(isColor(ans)){
										break;
									} else{
										System.out.println("Select a valid color");
									}
								}
	
								drawCards(1, choice-1, turn.getHand(), pile);
	
								ans=ans.toLowerCase();
	
								if(ans.equals("red"))
								{
									pile.getCard(0).setColor(0);
								}
								
								if(ans.equals("blue"))
								{
									pile.getCard(0).setColor(1);
								}
								
								if(ans.equals("green"))
								{
									pile.getCard(0).setColor(2);
								}
								
								if(ans.equals("yellow"))
								{
									pile.getCard(0).setColor(3);
								}
	
								System.out.println("You changed the color to " + ans.toLowerCase());
								break;
							}
						}
					}
				}
				else //The user doesn't have a card that matches the top card in the pile in any way
				{
					System.out.println("Sorry, you dont have a card that matches the top card in the pile, so you drew a card");
					drawCards(1, 0, draw, turn.getHand()); //Draws 1 card at index 0, from the pile to their hand
					System.out.println("\n \n \n"); //Space for ease of use
				}			
			}
		}
		
		int winner=0;
		for(int i=1;i<numplayers;i++) //Declares winner once end condition is met
		{
			if(players.get(i).getHand().getSize()<=0)
			{
				winner=i;					
			}
		}
		System.out.println(players.get(winner).getName()+" HAS WON THE GAME!!!");
	}
} 

//NOTES FOR NEXT TIME
//Finish method that checks if the player has a special card
//Add the method to the code and test, should allow a wild/draw 4 card to be placed no matter what the top card is
//Add method that allows a player to skip a turn, by typing skip