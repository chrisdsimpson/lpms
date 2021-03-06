/*
 * @(#)sensorP.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 03/05/14
 *
 * This class is the base class for the power measurement app.
 */

package lpms;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import com.firebird.sensorP.*;


public class lpms extends JFrame implements ActionListener
{

  /**
   * sensorP constructor for LPCS development.
   */
  public lpms()
  {
  
   	/* Get the command menu icon */
	//this.setIconImage(Toolkit.
	//                  getDefaultToolkit().
	//                  getImage("firebird.gif"));

	/* Load the application name and version on the title bar */
	this.setTitle(FBAppInfo.getAppName() + " " +
	              FBAppInfo.getAppVersionString());

	 
	getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), 1));
	GridBagConstraints gbc;

	/* Get the grid info to setup the screen layout */
	TopPanel = new JPanel();
	TopPanel.setLayout(new GridBagLayout());
	TopPanel.setPreferredSize(new java.awt.Dimension(ScreenWidth, 60));
	TopPanel.setBorder(new javax.swing.border.BevelBorder(0));
	TopPanel.setMinimumSize(new java.awt.Dimension(ScreenWidth, 60));

	/* Build the text box label */
	//Label1 = new JLabel("Current Step (Ia): ");
	//Label1.setLabelFor(Current);
	//gbc = new GridBagConstraints();
	//gbc.gridx = 0;
	//gbc.gridy = 0;
	//TopPanel.add(Label1, gbc);

	/* Setup the text box */
	//Current = new JTextField(16);
	//Current.setPreferredSize(new java.awt.Dimension(60, 18));
	//Current.setMinimumSize(new java.awt.Dimension(60, 18));
	//Current.setEnabled(true);
	//Current.addActionListener(this);
	//Current.setActionCommand("Current");
	//gbc = new GridBagConstraints();
	//gbc.gridx = 1;
	//gbc.gridy = 0;
	//TopPanel.add(Current, gbc);

	/* Build the text box label */
	//Label2 = new JLabel("Time Step (Tm): ");
	//Label2.setLabelFor(Time);
	//gbc = new GridBagConstraints();
	//gbc.gridx = 2;
	//gbc.gridy = 0;
	//TopPanel.add(Label2, gbc);

	/* Setup the text box */
	//Time = new JTextField(16);
	//Time.setPreferredSize(new java.awt.Dimension(60, 18));
	//Time.setMinimumSize(new java.awt.Dimension(60, 18));
	//Time.setEnabled(true);
	//Time.addActionListener(this);
	//Time.setActionCommand("Time");
	//gbc = new GridBagConstraints();
	//gbc.gridx = 3;
	//gbc.gridy = 0;
	//TopPanel.add(Time, gbc);
	/* Setup the button */
	
	Process = new JButton("Process");
	Process.setPreferredSize(new java.awt.Dimension(80, 18));
	Process.setMinimumSize(new java.awt.Dimension(80, 18));
	Process.setBackground(Color.GREEN);
	Process.setText("Run");
	Process.setEnabled(true);
	Process.addActionListener(this);
	Process.setActionCommand("Process");
	gbc = new GridBagConstraints();
	gbc.gridx = 4;
	gbc.gridy = 0;
	TopPanel.add(Process, gbc);
	getContentPane().add(TopPanel);

	/* Add the center panel for spacing */
	CenterPanel = new JPanel();
	CenterPanel.setLayout(new GridBagLayout());
	CenterPanel.setPreferredSize(new java.awt.Dimension(ScreenWidth, 18));
	//CenterPanel.setBorder(new javax.swing.border.BevelBorder(0));
	CenterPanel.setMinimumSize(new java.awt.Dimension(ScreenWidth, 18));
	getContentPane().add(CenterPanel);

	FinalBottomPanel = new JPanel();
	FinalBottomPanel.setLayout(new java.awt.FlowLayout());
	FinalBottomPanel.setPreferredSize(new java.awt.Dimension(ScreenWidth, 200));
	getContentPane().add(FinalBottomPanel);

	StatusPanel = new JPanel();
	StatusPanel.setLayout(new GridBagLayout());
	StatusPanel.setPreferredSize(new java.awt.Dimension(ScreenWidth, 18));
	//StatusPanel.setBorder(new javax.swing.border.LineBorder(Color.gray));
	//StatusPanel.setBorder(new javax.swing.border.BevelBorder(0));
	StatusPanel.setMinimumSize(new java.awt.Dimension(ScreenWidth, 18));

	StatusBar = new JLabel(StatusStr);
	StatusBar.setPreferredSize(new java.awt.Dimension(ScreenWidth, 18));
	StatusBar.setMinimumSize(new java.awt.Dimension(ScreenWidth, 18));
	//StatusBar.setForeground(Color.RED);
	gbc = new GridBagConstraints();
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridwidth = 2;
	gbc.gridheight = 0;
	StatusPanel.add(StatusBar, gbc);
	getContentPane().add(StatusPanel);

	
    /* Create the menu bar. */
    JMenuBar menuBar = new JMenuBar();

    /* Add the menu bar items */
    JMenu menu = new JMenu("System");
    menuBar.add(menu);

    /* Add the items to each main menu element */
    JMenuItem item1 = new JMenuItem("LPMS" + " Help");
    item1.addActionListener(this);
    menu.add(item1);

    JMenuItem item2 = new JMenuItem("About");
    item2.addActionListener(this);
    menu.add(item2);

    menu.addSeparator();

    JMenuItem item3 = new JMenuItem("Exit");
    item3.addActionListener(this);
    menu.add(item3);

    /* Add the menu to the main window */
    this.setJMenuBar(menuBar);

    /* Get the screen info */
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /* Set the size and position of the window */
    this.setSize(580, 360);
    int w = this.getSize().width;
    int h = this.getSize().height;
    int x = (dim.width-w)/2;
    int y = (dim.height-h)/2;
    this.setBounds(x, y, w, h);

    /* Display the window */
    this.setVisible(true);

    /* Application code... */  
	  
	/* Catch window closing events and also set initial focus */
    this.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        /* Add any cleanup code here */
      }
      public void windowOpened(WindowEvent evt)
      {
        /* Add any code to set control focus here */
      }
    });
  }

  /**
   * Main application startup code.
   * 
   */
  public static void main(String[] args)
  {
    /* Load the command line args (login name from the LPCS Menu loader). */
    String cmdarg = (args.length > 0) ? args[0] : "Undefined";

    //if(cmdarg.equals("Undefined"))
    //{
    //  System.out.println("Can not start application without a valid login");
    //  System.exit(0);
    //}

    /* Loads the application specific info and connects to the database */
    FBInit init = new FBInit("LPMS",                         /* Application name */
                             "Laser Power Meter System",     /* Description */
                             "Firebird Sensors, Inc.",       /* Author */
                             1,                              /* Version number */
                             0,                              /* Revision number */
                             "06/10/14",                     /* Version/revision date */
                             cmdarg);                        /* Login name */
    
	/* Create the form */
	lpms mainframe = new lpms();
	
  }

  /* Process the messages from the menu items. */
  public void actionPerformed(ActionEvent e)
  {
    String Command = e.getActionCommand();

    if(Command.equals("LPMS" + " Help"))
    {
      JOptionPane.showMessageDialog(this, "Help system not yet " +
                                    "implemented!",
                                    FBAppInfo.getAppName(),
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    if(Command.equals("About"))
    {
      FBAbout about = new FBAbout();
    }
    if(Command.equals("Exit"))
    {
      System.exit(0);
    }

    /* RollKeyID/OrderID input commands */
    if(Command.equals("Process"))
    {
      
      if(Process.getBackground() == Color.GREEN)  	
      {
        /* Set the button color for processing */
        Process.setBackground(Color.RED);
      
        /* Get and validate the Current and Time fields */
        String currentval = Current.getText();
        String timeval = Time.getText();
      
        /* Update the status bar */
        StatusBar.setText(StatusStr + " "
        		                    + "Running test with " 
                                    + "Current step [" + currentval.toString()+ "] Amps "
                                    + "and "
                                    + "Time step [" + timeval.toString() + "] Minutes");
      }
      else
      {
    	/* Set the button color for processing */
        Process.setBackground(Color.GREEN);
        
        /* Get and validate the Current and Time fields */
        Current.setText("");
        Time.setText("");
        
        /* Update the status bar */
        StatusBar.setText(StatusStr);  
      }
    }
    
    /* Other menu commands */
  }

  
  /* Controls */
  JPanel TopPanel;
  JPanel CenterPanel;
  JPanel BottomPanel;
  JPanel FinalBottomPanel;
  JPanel StatusPanel;
  JPanel ButtonPanel;
  JFrame frame;
  JTextField Current;
  JTextField Time;
  JLabel Label1;
  JLabel Label2;
  JLabel StatusBar;
  JLabel BLDeptCode;
  JLabel BLDeptDesc;
  JLabel BLQty;
  JLabel Arrow;
  JButton Process;
  JButton NewButton;
 
  /* Vars */
  String StatusStr = "  Status: ";
  int ScreenWidth = 640;

}
