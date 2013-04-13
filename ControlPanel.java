import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  This class defines control panel for the Bob the Blob's Adventure game
 */
public class ControlPanel extends JPanel
	implements ScoreDisplay
{
  	private GamePanel whiteboard;

  	private JTextField clockField, scoreField, healthField, mItemsField, cItemsField, levelField;
  	private JPanel blah;
  	private JButton go;
  	long startTime;
  	
  	//tools and counters for background changing slideshow
  	private JButton forward, back;
  	private JComboBox cb;
  	private String[] imageNames = {"forest.jpg", "toyland.jpg", "city.jpg", "asian town.jpg", "lake.jpg", 
  		"mystic rock.jpg", "space center.jpg", "rocky area.jpg", "snowy house.jpg", "beach.jpg"};
  	private int imageCount = 0;
  	private String curImage = imageNames[imageCount];

  	public ControlPanel(GamePanel lpanel)
  	{
  		setPreferredSize(new Dimension(200, 600));
  		whiteboard = lpanel;

    	//everything is inside a vertical box
    	Box box = Box.createVerticalBox();
    	
    	//time, score, health, missed items, collected items, and level are all in a 7 by 2 JPanel
    	JPanel block = new JPanel();
    	block.setLayout(new GridLayout(7, 2, 5, 5));
    	JLabel time = new JLabel("Time:");
    	clockField = new JTextField();
    	clockField.setEditable(false);
    	block.add(time);
    	block.add(clockField);
    	JLabel score = new JLabel("Score:");
    	scoreField = new JTextField("0");
    	scoreField.setEditable(false);
    	block.add(score);
    	block.add(scoreField);
    	JLabel health = new JLabel("Health:");
    	healthField = new JTextField("100");
    	healthField.setEditable(false);
    	block.add(health);
    	block.add(healthField);
    	JLabel mItems = new JLabel("Missed Items:");
    	mItemsField = new JTextField("0");
    	mItemsField.setEditable(false);
    	block.add(mItems);
    	block.add(mItemsField);
    	JLabel cItems = new JLabel("Collected Items:");
    	cItemsField = new JTextField("0");
    	cItemsField.setEditable(false);
    	block.add(cItems);
    	block.add(cItemsField);
    	JLabel level = new JLabel("Level:");
    	levelField = new JTextField("1");
    	levelField.setEditable(false);
    	block.add(level);
    	block.add(levelField);
    	box.add(block);
    
    	box.add(Box.createVerticalStrut(20));
    	
    	//slide show  buttons and pull down menu are all inside of a 3 by 1 JPanel
    	blah = new JPanel();
		blah.setLayout(new GridLayout(3, 1, 1, 1));
		forward = new JButton("Next Background");
		forward.addActionListener(new FListener());
		back = new JButton("Previous Background");
		back.addActionListener(new BListener());
		curImage = imageNames[imageCount];
		cb = new JComboBox(imageNames);
		cb.addActionListener(new BoxListener());
		blah.add(back);
		blah.add(cb);
		blah.add(forward);
    	box.add(blah);
    	
    	//go button is added to box
    	go = new JButton("Start");
    	go.addActionListener(new GoButtonListener());
    	box.add(go);
    
    	this.add(box);
  	}

  	//starts a new game
  	public void newGame()
  	{
    	go.setText("Start");

    	clockField.setText("00:00");
    	startTime = System.currentTimeMillis();
    	Timer clock = new Timer(1000, new ClockListener());
    	clock.start();

    	whiteboard.newGame();
    	requestFocus();
  	}

  	//updates score everytime am item hits the player
  	public void update(int score, int health, int mitems, int citems, int level)
  	{
    	scoreField.setText(score + " ");
    	healthField.setText(health + " ");
    	mItemsField.setText(mitems + " ");
    	cItemsField.setText(citems + " ");
    	levelField.setText(level + " ");
    	repaint();
  	}
  	
  	public String getCurImage()
  	{
		return curImage;
  	}

  	//handles clock events
  	private class ClockListener implements ActionListener
  	{
    	public void actionPerformed(ActionEvent e)
    	{
      		int secs = (int) ((System.currentTimeMillis() - startTime) / 1000);
      		int mins = secs / 60;
      		secs %= 60;
      		mins %= 60;
      		clockField.setText(String.format("%02d:%02d", mins, secs));
    	}
  	}

  	//handles go button events
  	private class GoButtonListener implements ActionListener
  	{
    	public void actionPerformed(ActionEvent e)
    	{
      		String cmd = e.getActionCommand();
      		if ("Start".equals(cmd))
      		{
        		whiteboard.dropItem();
        		go.setText("Stop");
      		}
      		else
      		{
        		whiteboard.stopItem();
        		go.setText("Start");
      		}
      	requestFocus();
    	}
  	}
  	
  	//handles next background button on slideshow
  	private class FListener
  		implements ActionListener
  	{
  		public void actionPerformed(ActionEvent e)
  		{
  			if(imageCount == imageNames.length - 1)
				imageCount = 0;
			else
				imageCount++;
			curImage = imageNames[imageCount];
			cb.setSelectedItem(curImage);
			whiteboard.setCurImage(curImage);
			repaint();
			requestFocus();
		}
  	}
  	
  	//handles previous background button on slideshow
  	private class BListener
  		implements ActionListener
  	{
		public void actionPerformed(ActionEvent e)
		{
			if(imageCount == 0)
				imageCount = imageNames.length - 1;
			else
				imageCount--;
			curImage = imageNames[imageCount];
			cb.setSelectedItem(curImage);
			whiteboard.setCurImage(curImage);
			repaint();
			requestFocus();

		}
  	}
  	
  	//handles pulldown background dislay of slideshow
  	private class BoxListener
  		implements ActionListener
  	{
		public void actionPerformed(ActionEvent e)
		{
			imageCount = cb.getSelectedIndex();
			curImage = imageNames[imageCount];
			whiteboard.setCurImage(curImage);
			repaint();
			requestFocus();
		}
  	}
}
 