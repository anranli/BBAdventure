import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  Represents  a panel on which items fall and Bob moves
 *  in the Bob the Blob's Adventure game
 */

public class GamePanel extends JPanel
	implements ActionListener
{
  	private Items item;
  	private Player bob;
  
  	private ControlPanel controlPanel;
  	String curImage; //string name of background image
  	private Image pImage, image, bg; //player image, item image, background image
  
  	private Game game;
  	private ScoreDisplay scoreDisplay;
  
  	private final int SIZE = 100; //size of item

  	private Timer t;
  	private final int DELAY = 30; //delay time between drops
  	private int yStep;	//how fast the item drops

  	private int score, health, missedItems, collectedItems, level;
	
	private EasySound good, bad, levelup;
  
  	//Constructor: Creates the Panel
  	public GamePanel(Game game)
  	{
  		this.game = game;
    	setPreferredSize(new Dimension(600, 600));
    	setBackground(Color.WHITE);
    
    	controlPanel = new ControlPanel(this);
    	curImage = controlPanel.getCurImage();
    
    	//sets sounds
    	bad = new EasySound("bzz.wav");
    	good = new EasySound("chomp.wav");
    	levelup = new EasySound("bells.wav");
    	
    	bob = new Player(500, 475);
    	
    	t = new Timer(DELAY, this);
    	
    	score = 0;
    	health = 100;
    	missedItems = 0;
    	collectedItems = 0;
    	level = 1;
    	
    	yStep = Math.max(1, ((level * 100) + 250) * DELAY / (1000 - (level * 20)));
    	repaint();
  	}
  
  	//sets up score display
  	public void setScoreDisplay(ScoreDisplay display)
  	{
    	scoreDisplay = display;
    	scoreDisplay.update(score, health, missedItems, collectedItems, level);
  	}
  
  	//Starts a new game
  	public void newGame()
  	{
    	stopItem();
    	score = 0;
    	health = 100;
    	missedItems = 0;
    	collectedItems = 0;
    	level = 1;
    	scoreDisplay.update(score, health, missedItems, collectedItems, level);
    	requestFocus();
    	repaint();
  	}
  
  	//sets speed of falling item
  	public void setSpeed()
  	{
  		yStep = Math.max(1, ((level * 100) + 250) * DELAY / (1000 - (level * 20)));
  	}
  
  	//drops a random item
  	public void dropItem()
  	{
    	int x = (int)(Math.random() * 400);
    	int y = -SIZE;          // above the board for smooth entry
    	item = new Items();
    	item.move(x, y);
    	t.start();
    	repaint();
  	}
  
  	//stops the item and moves it to above screen
  	public void stopItem()
  	{
    	t.stop();
    	item = new Items();
    	item.move(-SIZE, -SIZE);
    	repaint();
  	}

  	//item is falling
  	private boolean moveItemDown()
  	{
  		int x = item.getX();
    	int y = item.getY();
    	int px = bob.getX();
    	int py = bob.getY();
    
    	//if item is on falling above the player then keep on moving it down
    	if (y < 600 && y  + 100 < py)
    	{
      		item.move(x, y + yStep);
      		repaint();
      		return true;
    	}
    	//if the items hits the player
    	else if(y < 600 && y + 100 >= py && y + 100 <= py + 100 && 
    		((x <= px && x + 100 >= px) || (x <= px + 100 && x + 100 >= px + 100)))
    	{
    		//if the item is bad, then subtract health
      		if(item.getItem().equals("bomb") || item.getItem().equals("vitamin") || item.getItem().equals("bread"))
      		{
      			if (game.soundEnabled())
    				bad.play();
      			health += item.getValue();
      			scoreDisplay.update(score, health, missedItems, collectedItems, level);
      			repaint();
      		}
      		//if the item is good, then add score and collected items
      		else
      		{
      			if (game.soundEnabled())
    				good.play();
      			collectedItems++;
      			score += item.getValue();
      			levelUp();
      			scoreDisplay.update(score, health, missedItems, collectedItems, level);
      			repaint();
      		}
      		stopItem();
      		dropItem();
      		repaint();
      		return true;
    	}
    	//if not collected and not missed then keep on moving item down
    	else if (y < 600)
    	{
      		item.move(x, y + yStep);
      		repaint();
      		return true;
    	}
    	//else the item is missed, then add missed items if good
    	else
    	{
      		if (y >= 600)
      		{
      			if(item.getItem().equals("candy") || item.getItem().equals("candycorn") 
      				|| item.getItem().equals("choco") || item.getItem().equals("icecream") 
      				|| item.getItem().equals("candycane"))
      			{
      				missedItems++;
      				scoreDisplay.update(score, health, missedItems, collectedItems, level);
      				repaint();
      			}
      			stopItem();
      			dropItem();
        		repaint();
      		}
      		return false;
    	}
    
  	}
  
  	//moves the player left
  	public void movePlayerLeft()
  	{
  		int x = bob.getX();
    	int y = bob.getY();
    	if (y >= 0 && y < 500 && x >= 1)
    	{
    	  bob.move(x - 20, y);
    	  repaint();
    	}
  	}
  
  	//moves the player right
  	public void movePlayerRight()
  	{
  		int x = bob.getX();
    	int y = bob.getY();
    	if (y >= 0 && y < 500 && x < 500 - 1)
    	{
      		bob.move(x + 20, y);
      		repaint();
    	}
  	}
  
  	//level up when these specifications are met
  	public void levelUp()
  	{
  		if(level == 1 && score >= 100)
  		{
  			level++;
  			if (game.soundEnabled())
    			levelup.play();
  		}
  		else if(level == 2 && score >= 200)
  		{
  			level++;
  			if (game.soundEnabled())
    			levelup.play();
  		}
  		else if(level == 3 && score >= 400)
  		{
  			level++;
  			if (game.soundEnabled())
    			levelup.play();
  		}
  		else if(level == 4 && score >= 700)
  		{
  			level++;
  			if (game.soundEnabled())
    			levelup.play();
  		}
  		setSpeed();	//sets the new speed if leveled up
  		repaint();
  	}
  
  	//sets the current image
  	public void setCurImage(String image)
  	{
  		curImage = image;
  		repaint();
  	}
  
  	// Handles timer events
  	public void actionPerformed(ActionEvent e)
  	{
  		//if the item is not falling, stop and drop a new item
    	if (!moveItemDown())
   		{
    	 	stopItem();
      	dropItem();
    	}
    	repaint();
  	}

  	/***  Graphics display methods ***/
  
  	//Paints Bob and the Items
  	public void paintComponent(Graphics g)
  	{
    	super.paintComponent(g);
    	drawBackground(g, curImage);
   		drawPlayer(g);
    	drawItem(g);
    	gameOver(g);
  	}
  
  	//draws the item
  	public void drawItem(Graphics g)
  	{
    	drawItem(g, item.getX(), item.getY(), item.getItem());
  	}
  
  	//overloaded version to draw items with images
  	public void drawItem(Graphics g, int x, int y, String name)
  	{
    	image = (new ImageIcon(name + ".gif")).getImage();
    	if(image != null)
		{
			g.drawImage(image, x, y, null);
		}
  	}
  
  	//draws player with an image
  	public void drawPlayer(Graphics g)
  	{
    	pImage = (new ImageIcon("bobmove.gif")).getImage();
    	if(pImage != null)
		{
			g.drawImage(pImage, bob.getX(), bob.getY(), null);
		}
		repaint();
  	}
  
  	//draws the current background with an image
  	public void drawBackground(Graphics g, String name)
  	{
  		bg = (new ImageIcon(name)).getImage();
    	if(bg != null)
		{
			g.drawImage(bg, 0, 0, null);
		}
  	}
  
  	//draws a string if you lost or won the game
  	public void gameOver(Graphics g)
  	{	
  		if(health <= 0)
  		{
  			stopItem();
			g.setFont(new Font("Arial", Font.PLAIN, 100));
			g.setColor(Color.RED);
			g.drawString("Game Over", 50, 300);
			repaint();
  		}
  		else if(score >= 1000)
  		{
  			stopItem();
			g.setFont(new Font("Arial", Font.PLAIN, 100));
			g.setColor(Color.GREEN);
			g.drawString("You Win!!!", 50, 300);	
			repaint();
  		}
  	}
}

	
