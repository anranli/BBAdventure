import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  This class defines the menu bar for Bob the Blob's Adventure game
 */
 
public class GameMenu extends JMenuBar
	implements ActionListener
{
  	private Game game;
  	private JCheckBoxMenuItem sound;
  	private JMenuItem newgame, exit, howtoplay, about;
  	
  	public GameMenu(Game game)
  	{
   		this.game = game;
   		//File & Help main menus, small Preferences menu in File
   		JMenu file = new JMenu("File");
   		JMenu pref = new JMenu("Preferences");
   		JMenu help = new JMenu("Help"); 
   		file.add(pref); 
   		sound = new JCheckBoxMenuItem ("Allow Sound"); //Allow Sound option in Preferences
   		pref.add(sound);
   		sound.addActionListener(this);
   		file.addSeparator();
   		//Separator, New Game, and Exit options in File
   		newgame = new JMenuItem ("New Game");
   		file.add(newgame);
   		newgame.addActionListener(this);
   		exit = new JMenuItem ("Exit");
   		file.add(exit);
   		exit.addActionListener(this);
   		//Instructions and About Us options in Help
   		howtoplay = new JMenuItem("Instructions");
   		help.add(howtoplay);
   		howtoplay.addActionListener(this);
   		about = new JMenuItem("About Us");
   		help.add(about);
   		about.addActionListener(this);
   		
   		this.add(file);
   		this.add(help);
   	}

  	public boolean soundEnabled()
  	{
  		return sound.getState();
  	}

  	public void actionPerformed(ActionEvent e)
  	{
  	 	JMenuItem src = (JMenuItem)e.getSource();
  	 	//selects appropriate action for each option
		if(src == newgame)
			game.newGame();
		else if (src == exit)
			System.exit(0);
		else if (src == howtoplay)
		{
			GameHelp.showHelp();
			repaint();
		}
		else if (src == about)
		{
			GameHelp.showAbout();
			repaint();
		}
  	}
}

