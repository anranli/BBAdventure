import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class defines the different kinds of items avaliable and their values.
 * It also defines the item that is currently being drop and its value.
 */

public class Items
{
	//all the different kinds of items and their values
    private String item[] = {"candy", "icecream", "chocolate", "candycane", 
    	"candycorn", "bomb", "bread", "vitamin" };
    private int value[] = {15, 50, 35, 20, 10, -25, -50, -25 };
    
    //current item being drop and its value
    private String i;
    private int v;
    
    //position of item
    private int xCoord, yCoord;

    public Items()
    {
    	super();
        int j = (int)(Math.random() * 8); //randomizes an item
        
        i = item[j];
        v = value[j];
    }

    public String getItem()
    {
        return i;
    }

    public int getValue()
    {
        return v;
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
