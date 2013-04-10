//Sam Dickson and Niraj Venkat
//CS251 Project 5--Map Editor


import javax.swing.*; 
import javax.swing.event.*; 
import javax.swing.text.*; 
import javax.swing.border.*; 
import javax.swing.colorchooser.*; 
import javax.swing.filechooser.*; 
import javax.accessibility.*; 

import java.awt.*; 
import java.awt.event.*; 
import java.beans.*; 
import java.util.*; 
import java.io.*; 
import java.applet.*; 
import java.net.*;

class MapEditor extends JFrame{
    // The preferred size of the demo
    public static final int PREFERRED_WIDTH = 680;
    public static final int PREFERRED_HEIGHT = 600;
    private JScrollPane scrollPane;

    public static void main(String[] args) { 
 	MapEditor mapEditor = new MapEditor(); 
	mapEditor.setVisible(true);
    } 

    MapEditor() {
	setTitle("Map Editor");
	setSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	setBackground(Color.gray);

	JPanel panel = new JPanel();
	panel.setLayout( new BorderLayout()); 
	getContentPane().add(panel);

	Icon image = new ImageIcon("Resources/purdue-map.jpg");
	
	JLabel label = new JLabel(image);
	scrollPane = new JScrollPane();
	scrollPane.getViewport().add(label);
	panel.add(scrollPane, BorderLayout.CENTER);
    }

};

