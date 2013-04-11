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
    //Constants
    public static final int PREFERRED_WIDTH = 680;
    public static final int PREFERRED_HEIGHT = 600;
    public static final double MAX_ZOOM = 50.0;
    public static final double MIN_ZOOM = 11.0;
    public static final double ZOOM_INCREMENT = 3.0;
    
    //GUI components
    private JScrollPane scrollPane;
    private ZoomPane zoomPane;
    private MapScene map;
    
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

    
    //Session variables
    public ArrayList<Vertex> points;
    private String filePath = "Resources/purdue-map.jpg"; //Default map location
    //XML mapXML = new XML();
    
    double zoomValue = 20.00;
    
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
    		String response = null;
    		String tmpPath = filePath;
    		filePath = null;
    		double scale_feet_per_pixel = 0;
    		boolean done = false;
    		
    		while(!done)
    		{
    			filePath = JOptionPane.showInputDialog(null, "Enter a name for the new map: ", "New Map", JOptionPane.OK_CANCEL_OPTION); 
    			if(filePath != null && !filePath.equals(""))
    			{
    				done = true;
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(null, "Please enter a name for the map.", "Error", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    		
    		done = false;
    		if(filePath != null)
    		{
	    		while(!done)
	    		{
	    			response = JOptionPane.showInputDialog(null, "Enter the feet-per-pixel constant: ", "New Map", JOptionPane.OK_CANCEL_OPTION);
	    			
	    			if(response == null)
	    			{
	    				break;
	    			}
	    			
	    			try
	    			{
	    				scale_feet_per_pixel = Double.parseDouble(response);
	    				done = true;
	    			}
	    			catch(Exception e)
	    			{
	    				JOptionPane.showMessageDialog(null, "Invalid feet-per-pixel constant.", "Error", JOptionPane.ERROR_MESSAGE);
	    			}
	    		}
    		}
    		
    		if(response != null && filePath != null)
    		{
    			//mapXML.newMap(filePath, scale_feet_per_pixel);
    			JOptionPane.showMessageDialog(null, "New map successfully created!", "New Map", JOptionPane.PLAIN_MESSAGE);
    		}
    		else
    		{
    			filePath = tmpPath;
    		}
    	}
    	else if(evt.getSource().equals(openAction)) //Open existing XML 
    	{
    		
    	}
    	else if(evt.getSource().equals(saveAction)) //Save current XML
    	{
    		if(filePath != null)
    		{
    			//mapXML.saveMap();
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "No map is loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	else if(evt.getSource().equals(saveAsAction)) //Save current XML with different name
    	{
    		
    	}
    	
    	//Actions for map menu:
    	else if(evt.getSource().equals(zoomInAction)) //Zoom in
    	{
    		if(zoomValue < MAX_ZOOM)
    		{
    			double scale = (zoomValue + ZOOM_INCREMENT) / 20.0;
    			zoomValue+=ZOOM_INCREMENT;
    			zoomPane.zoom(scale);
    			zoomPane.repaint();
    		}
    	}
    	else if(evt.getSource().equals(zoomOutAction)) //Zoom out
    	{
    		if(zoomValue > MIN_ZOOM)
    		{
    			double scale = (zoomValue - ZOOM_INCREMENT) / 20.0;
    			zoomValue-=ZOOM_INCREMENT;
    			zoomPane.zoom(scale);
    			zoomPane.repaint();
    		}
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
    
    public boolean verifyFile(String fp)
    {
    	try
    	{
    		File tmp = new File(fp);
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    	return true;
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
		zoomInAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
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
		
		Image image = new ImageIcon(filePath).getImage();
		map = new MapScene(image);
	    zoomPane = new ZoomPane(map);
	    getContentPane().add(zoomPane);
	    
	    if(verifyFile(filePath))
	    {
	    	//mapXML.openMap(filePath);
	    }
	    else
	    {
	    	JOptionPane.showMessageDialog(null, "Default map could not be loaded.", "Warning", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    
    }
    
};

