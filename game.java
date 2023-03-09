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
		while(pile.getCard(0).isWild() || pile.getCard(0).isWildP4() || pile.getCard(0).isP2())
		{
			drawCards(1, 0, draw, pile);
		}
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

	private boolean endGame() //Checks if end condition is met
	{
		for(Player p : players)
		{
		 	if(p.getHand().getSize()==0)
			{
				return true;
			}
		}	 	 
		return false;
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
		int dir=0;
 		while(!endGame()) //Repeats until end condition is met
		{
			for(int i=0;i<numplayers;i++) //Gives every player a turn until the game ends
			{
				turn=players.get(i);
				turn.getHand().sortDeck(); //Sorts the players deck

				System.out.println(" \n \n \n"); //Space in terminal for ease of use
				System.out.println(turn.getName() + " it's your turn! \n Heres what you have in your hand: \n\n " + turn.getHand().displayDeck()); //Displays hand
				
				if(!pile.getCard(0).isP2() && !pile.getCard(0).isWildP4())
				{
					System.out.println("Match this card: " + pile.displayTopCard()); //Displays the top card in the pile that the player has to match

					boolean found=false;

					for(int x=0; x<turn.getHand().getSize(); x++) //Iterates theough the players hand to see if they have a card that matches the top card in the pile
					{
						if(turn.getHand().getCard(x).isMatching(pile.getCard(0)))
						{
							found=true;
						} 	
					}

					if(found) //If the player has a matching card, do this
					{
						while(true)
						{
							Scanner chooseCard = new Scanner(System.in); //Creats scanner (input)
							System.out.println("Please select a card to place down"); //Asks player which card they want to place down
							int choice = chooseCard.nextInt(); //Sets input to a variable
							choice--;

							if(choice>=turn.getHand().getSize() || choice<0) //If the input is outside the range of cards that the user has
							{
								System.out.println("Sorry, the value you chose is outside the range of your available cards \n"); //Tell them to choose something else
							} 

							else if(!turn.getHand().getCard(choice).isMatching(pile.getCard(0)))
							{
								System.out.println("That doesnt match, try again");
							}

							else if(turn.getHand().getCard(choice).isMatching(pile.getCard(0)))
							{
								if(turn.getHand().getCard(choice).isWild() || turn.getHand().getCard(choice).isWildP4())
								{
									Scanner ans = new Scanner(System.in);
									System.out.println("New color: ");
									String x = ans.nextLine();
									while(!isColor(x)){
										System.out.println("Enter red, blue, green, or yellow:");
										x = ans.nextLine();
									}

									if(x.toLowerCase().equals("red"))
									{
										turn.getHand().getCard(choice).setColor(0);
									}
									else if(x.toLowerCase().equals("blue"))
									{
										turn.getHand().getCard(choice).setColor(1);
									}
									else if(x.toLowerCase().equals("green"))
									{
										turn.getHand().getCard(choice).setColor(2);
									}
									else if(x.toLowerCase().equals("yellow"))
									{
										turn.getHand().getCard(choice).setColor(3);
									}

									drawCards(1, choice, turn.getHand(), pile);
									System.out.println("You placed your " + pile.displayTopCard() + " into the pile");
									break;
								}

								else if(turn.getHand().getCard(choice).isSkip())
								{
									drawCards(1, choice, turn.getHand(), pile);
									System.out.println("You placed your " + pile.displayTopCard() + " into the pile");
									System.out.println(players.get(i+1).getName() + ", your turn was skipped");
									i++;
									break;
								}

								else if(turn.getHand().getCard(choice).isReverse()) //Code within needs to be replaced
								{
									drawCards(1, choice, turn.getHand(), pile);
									System.out.println("You placed your " + pile.displayTopCard() + " into the pile");
									break;
								}

								else
								{
									drawCards(1, choice, turn.getHand(), pile);
									System.out.println("You placed your " + pile.displayTopCard() + " into the pile");
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
				else
				{
					System.out.println("The top card was a " + pile.displayTopCard() + " so you drew cards!");

					if(pile.getCard(0).isP2())
					{
						drawCards(2, 0, draw, turn.getHand());
					}
					else if(pile.getCard(0).isWildP4())
					{
						drawCards(4, 0, draw, turn.getHand());
					}

					pile.getCard(0).setValue(0);
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

//NEXT TIME
//Implement reverse card functionality