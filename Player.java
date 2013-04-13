import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class defines the player that you control and move to collect items.
 */

public class Player
{
	//position of player
  	private int xCoord, yCoord;
  	
	//creates the player Bob the Blob
	public Player(int x, int y)
	{
		xCoord = x;
		yCoord = y;
	}
	
  	public int getX()
  	{
    	return xCoord;
  	}
  	
  	public int getY()
  	{
  		return yCoord;
  	}
  	
  	public void move(int x, int y)
  	{
    	xCoord = x;
    	yCoord = y;
  	}
}