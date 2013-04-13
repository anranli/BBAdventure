import javax.swing.*;

/**
 *  This class contains the help for the Bob's Adventure game
 */
public class GameHelp
{
	//Displays instructions for how to play Bob the Blob's Adventure game
	public static void showHelp()
	{
		JOptionPane.showMessageDialog(null,
    	
    	"In this game, you suddenly appear in an unknown environment on an " + 
    	"unknown planet in an unknown galaxy. \n" + 	
    	"Your name is Bob the Blob.  Your mission? \n" + 	
    	"To collect tasty items such as ice creams, candies, etc. " +
    	"to provide yourself with a day\'s worth of healthy nutrition.\n" +	
    	"Be sure not to collect harmful things such as whole grain bread, " +
    	"fruits, veggies, bombs, knives, etc.\n" +	
    	"After you eat enough you will move on to the next level, " +
    	"and after a level 5 and 1000 points in you will be able to leave\n" +
    	"for home.\n" +
    	"If you run out of health in your health display, you will lose " +
    	"and the game will be all over.\n\n"  +
    	"Press Start when you're ready to begin! " +	
    	"Remember, failure is not an option! Good Luck! \n\n" +
    	"The score increases each time you collect a good item. The health " +
    	"decrease each time you collect a bad item. The\n" + 
    	"missed items and collected items increases everytime you miss or " +
    	"collect a good item. You level up everytime you\n" +
    	"hit the 50, 100, 200, 500 score marks. Level ups will be notified " +
    	"by sounds. Don't forget to enable sound!!!\n\n" +	
    	"The character is moved with the arrow keys: " +
    	"left arrow key to move left, right arrow key to move right.\n\n" +	
    	"Scores:\n" +
    	"Good Stuff:\n" +
    	"Candy: 15 points, Ice Cream: 50 points, Chocolate: 35 points, " +
    	"Candy Cane: 20 points, Candy Corn: 10 points\n" +
    	"Bad Stuff:\n" +
    	"Bomb: -25 health, Whole Grain Bread: -50 health, Vitamin: -25 health",
        	"How to Play",       // Dialog title
        	JOptionPane.PLAIN_MESSAGE);
	}
	
	//Displays information about the creators and sources used
  	public static void showAbout()
  	{
  		JOptionPane.showMessageDialog(null,
        	"This game was made by Brian Ha, Anran Li, Jo Nathan Chang\n" +
        	"Do not copy this game, or we will personally hunt you down!!!\n" +
        	"blah blah blah\n" +
        	"You are NOT allowed to copy this game\n" +
        	"for ANYTHING!  'Cuz we're just evil like that!\n" +
        	"You are not allowed to change or remove this message.\n\n" +
        	"Help, sources and tools used from:\n" +
        	"Skylit Java Methods book and files, Maple Story, Java API/n" + 
        	"Java All-in-One Desk Reference for Dummies by Doug Lowe",
        		"About",       // Dialog title
        		JOptionPane.PLAIN_MESSAGE);
  	}
}
