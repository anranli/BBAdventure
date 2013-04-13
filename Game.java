import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class is the main driver JFrame class that contains a GameMenu, ControlPanel, and GamePanel.
 */

public class Game extends JFrame
{
	private GameMenu menuBar;
  	private ControlPanel controlpanel;
  	private GamePanel whiteboard;
	
	//creates the game
	public Game()
	{
		super("Bob the Blob's Adventure");

    	menuBar = new GameMenu(this);
    	setJMenuBar(menuBar);

    	whiteboard = new GamePanel(this);
    	controlpanel = new ControlPanel(whiteboard);
    	whiteboard.setScoreDisplay(controlpanel);
    	controlpanel.addKeyListener(new PlayerListener());
    	requestFocus();
	
    	Box box = Box.createHorizontalBox();
    	box.add(whiteboard);
    	box.add(controlpanel);
    	getContentPane().add(box);

    	newGame();
	}
	
	//starts new game
	public void newGame()
  	{
  		controlpanel.newGame();
  	}
  	
  	//enables sound
  	public boolean soundEnabled()
  	{
  		return menuBar.soundEnabled();
  	}
  	
  	public static void main(String[] args)
  	{
  		//set system look and feel
    	try
    	{
   	 		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
   		}
    	catch(Exception ex)
    	{
    		System.out.println("It is not installed!!!");
    	}
    	
    	//runs program
    	Game window = new Game();
    	window.setBounds(0, 0, 810, 655);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
	}
	
	//Private inner class is key listener that moves the player
	private class PlayerListener implements KeyListener
	{
		//if the string version of the pressed key is right or left, move the player accordingly
		public void keyPressed(KeyEvent e) 
		{
			int kC = e.getKeyCode();
			String key = KeyEvent.getKeyText(kC);
			if(key.equalsIgnoreCase("Right"))
			{
				whiteboard.movePlayerRight();
			}
			else if(key.equalsIgnoreCase("Left"))
			{	
				whiteboard.movePlayerLeft();
			}
			else
			{
				
			}
		}
		public void keyReleased(KeyEvent e) 
		{
			
		}
		public void keyTyped(KeyEvent e)
		{
			
		}
		
	}
}