//Sam Dickson and Niraj Venkat
//CS251 Project 5--Map Editor


import javax.swing.*; 
import javax.swing.event.*; 
import javax.swing.text.*; 
import javax.swing.border.*; 
import javax.swing.colorchooser.*; 
import javax.swing.filechooser.*; 
import javax.accessibility.*; 
import javax.imageio.ImageIO;

import java.awt.*; 
import java.awt.event.*; 
import java.beans.*; 
import java.util.*; 
import java.io.*; 
import java.applet.*; 
import java.net.*;



public class MapEditor extends JFrame implements ActionListener, MouseListener
{
    // The preferred size of the demo
    public static final int PREFERRED_WIDTH = 680;
    public static final int PREFERRED_HEIGHT = 600;
    private JScrollPane scrollPane;
    
    //Menu items for file menu:
    private JMenuItem exitAction;
    private JMenuItem openAction;
    private JMenuItem saveAction;
    private JMenuItem saveAsAction;
    private JMenuItem newAction;
    
    //Menu items for map menu:
    private JMenuItem zoomInAction;
    private JMenuItem zoomOutAction;
    private JRadioButtonMenuItem insertLocationMode;
    private JRadioButtonMenuItem deleteLocationMode;
    private JRadioButtonMenuItem insertPathMode;
    private JRadioButtonMenuItem deletePathMode;
    
    //Menu items for help menu:
    private JMenuItem aboutAction;
    private JMenuItem helpAction;

    public static void main(String[] args) 
    { 
    	MapEditor mapEditor = new MapEditor(); 
    	mapEditor.setVisible(true);
    } 

    //Handle events for mouse actions
    public void mouseClicked(MouseEvent me)
    {
    	if(insertLocationMode.isSelected()) //Using "Insert Location" mode
    	{
    	
    	}
    	else if(deleteLocationMode.isSelected()) //Using "Delete Location" mode
    	{
    		
    	}
    	else if(insertPathMode.isSelected()) //Using "Insert Path" mode
    	{
    		
    	}
    	else if(deletePathMode.isSelected()) //Using "Delete Path" mode
    	{
    		
    	}
    }
    
    //Implemented methods from interface. Not used.
    public void mouseReleased(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void mousePressed(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    
    //Handle events for menu objects
    public void actionPerformed(ActionEvent evt)
    {
    	//Actions for file menu:
    	if(evt.getSource().equals(exitAction)) //Exit program
    	{
    		System.exit(0);
    	}
    	else if(evt.getSource().equals(newAction)) //Create new map
    	{
    		
    	}
    	else if(evt.getSource().equals(openAction)) //Open existing XML 
    	{
    		
    	}
    	else if(evt.getSource().equals(saveAction)) //Save current XML
    	{
    		
    	}
    	else if(evt.getSource().equals(saveAsAction)) //Save current XML with different name
    	{
    		
    	}
    	
    	//Actions for map menu:
    	else if(evt.getSource().equals(zoomInAction)) //Zoom in
    	{
    		
    	}
    	else if(evt.getSource().equals(zoomOutAction)) //Zoom out
    	{
    		
    	}
    	
    	//Actions for help menu:
    	else if(evt.getSource().equals(aboutAction)) //Display about dialog
    	{
    		JOptionPane.showMessageDialog(null, "Map Viewer:\n\nDeveloped by Sam Dickson and Niraj Venkat\nPurdue University, CS 251\nApril 2013", "About Map Viewer", JOptionPane.PLAIN_MESSAGE);
    	}
    	else if(evt.getSource().equals(helpAction)) //Display help dialog
    	{
    		JOptionPane.showMessageDialog(null, "Map Viewer Commands:\n\n", "Help", JOptionPane.PLAIN_MESSAGE);
    	}
    }
    
    public MapEditor() 
    {
		setTitle("Map Editor");
		setSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
		setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panel = new JPanel();
		panel.setLayout( new BorderLayout()); 
		getContentPane().add(panel);
		
		//Create and set up menubars:
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu mapMenu = new JMenu("Map");
		JMenu helpMenu = new JMenu("Help");
		
		//Menu items for file menu:
		exitAction = new JMenuItem("Exit");
		exitAction.addActionListener(this);
		exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		openAction = new JMenuItem("Open");
		openAction.addActionListener(this);
		openAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		saveAction = new JMenuItem("Save");
		saveAction.addActionListener(this);
		saveAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		newAction = new JMenuItem("New");
		newAction.addActionListener(this);
		newAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		saveAsAction = new JMenuItem("Save As...");
		saveAsAction.addActionListener(this);
		saveAsAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(exitAction);
		
		//Menu items for map menu:
		zoomInAction = new JMenuItem("Zoom In");
		zoomInAction.addActionListener(this);
		zoomInAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
		zoomOutAction = new JMenuItem("Zoom Out");
		zoomOutAction.addActionListener(this);
		zoomOutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		ButtonGroup modeOptions = new ButtonGroup();
		insertLocationMode = new JRadioButtonMenuItem("Insert Location Mode");
		deleteLocationMode = new JRadioButtonMenuItem("Delete Location Mode");
		insertPathMode = new JRadioButtonMenuItem("Insert Path Mode");
		deletePathMode = new JRadioButtonMenuItem("Delete Path Mode");
		modeOptions.add(insertLocationMode);
		modeOptions.add(deleteLocationMode);
		modeOptions.add(insertPathMode);
		modeOptions.add(deletePathMode);
		
		mapMenu.add(zoomInAction);
		mapMenu.add(zoomOutAction);
		mapMenu.addSeparator();
		mapMenu.add(insertLocationMode);
		mapMenu.add(deleteLocationMode);
		mapMenu.add(insertPathMode);
		mapMenu.add(deletePathMode);
		
		//Menu items for help menu:
		aboutAction = new JMenuItem("About");
		aboutAction.addActionListener(this);
		helpAction = new JMenuItem("Help");
		helpAction.addActionListener(this);
		helpMenu.add(aboutAction);
		helpMenu.add(helpAction);
		
		menubar.add(fileMenu);
		menubar.add(mapMenu);
		menubar.add(helpMenu);
		setJMenuBar(menubar);
	
		//Icon image = new ImageIcon("Resources/purdue-map.jpg");
		//JLabel label = new JLabel(image);
		MapCanvas mc = new MapCanvas();
		scrollPane = new JScrollPane();
		//scrollPane.getViewport().add(label);
		scrollPane.getViewport().add(mc);
		panel.add(scrollPane, BorderLayout.CENTER);
    }

    class MapCanvas extends JPanel
    {
    	public void paint(Graphics g)
    	{
    		Image image = null;
        	try
        	{
        		image = ImageIO.read(new File("Resources/purdue-map.jpg"));
        	}
        	catch(Exception e){}
        	g.drawImage(image,0,0, this);
    		g.drawOval(50, 50, 100, 100);
    	}
    }
};

