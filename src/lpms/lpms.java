/*
 * @(#)lpms.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 06/10/14
 *
 * This class is the base class for the power measurement app.
 */

package lpms;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.ImageIcon;


public class lpms extends JFrame implements ActionListener
{
    
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
   * lpms constructor for LPCS development.
   */
  public lpms()
  {
  
    this.FirebirdIcon = new ImageIcon("firebird.jpg");
	  
	  /* Get the command menu icon */
	  this.setIconImage(Toolkit.
	                    getDefaultToolkit().
	                    getImage("lasersign_04.jpg"));

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
	  //TopPanel.add(Process, gbc);
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
    JMenu menuF = new JMenu("File");
    menuBar.add(menuF);
    
    itemF1 = new JMenuItem("Connect Meter");
    itemF1.addActionListener(this);
    menuF.add(itemF1);
    
    /* Add the Acquire menu */
    itemF2 = new JMenu("Acquire Power Data");
    //itemF2.addActionListener(this);
    itemF2.setEnabled(false);
    menuF.add(itemF2);
    
    JMenuItem itemAPD1 = new JMenuItem("Plot Power Data");
    itemAPD1.addActionListener(this);
    itemF2.add(itemAPD1);
    
    JMenuItem itemAPD2 = new JMenuItem("Save Power Data");
    itemAPD2.addActionListener(this);
    itemAPD2.setEnabled(false);
    itemF2.add(itemAPD2);
      
    /* Add the Plot menu item */
    itemF3= new JMenuItem("Upload Tables");
    itemF3.addActionListener(this);
    itemF3.setEnabled(false);
    menuF.add(itemF3);
        
    /* Add the Plot menu item */
    itemF4 = new JMenuItem("Upload Meter Firmware");
    itemF4.addActionListener(this);
    itemF4.setEnabled(false);
    menuF.add(itemF4);
    
    menuF.addSeparator();

    JMenuItem itemF10 = new JMenuItem("Exit");
    itemF10.addActionListener(this);
    menuF.add(itemF10);
        
    /* Add the meter control menu items */
    JMenu menuM = new JMenu("Meter Control");
    menuM.setEnabled(false);
    menuBar.add(menuM);
       
    /* Add the menu bar items */
    JMenu menuH = new JMenu("Help");
    menuBar.add(menuH);
    
    /* Add the items to each main menu element */
    JMenuItem itemH1 = new JMenuItem("LPMS" + " Help");
    itemH1.addActionListener(this);
    menuH.add(itemH1);

    JMenuItem itemH2 = new JMenuItem("About");
    itemH2.addActionListener(this);
    menuH.add(itemH2);
    
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
    	  System.exit(0);
	
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
    
    /* Display the LPCS splash screen for 5 seconds */
    FBSplash splash = new FBSplash(5000);
    
    /* Create the form */
	  lpms mainframe = new lpms();
		
  }

  /* Process the messages */
  public void actionPerformed(ActionEvent e)
  {
    String Command = e.getActionCommand();

    if(Command.equals("Connect Meter"))
    {
      /* If we acre not already connected to a meter */
      if(!MeterConnectFlag)
      {	  
        /* Get the available serial ports */      
    	  Object[] possibilities = FBSerial.getCommPorts().toArray();
                
        String s = (String)JOptionPane.showInputDialog(frame,
    	                                               "Select the Meter USB port:\n",
    	                                               "Attach Meter",
    	                                               JOptionPane.PLAIN_MESSAGE,
    	                                               FirebirdIcon,
    	                                               possibilities,
    	                                               " ");

        //If a port was selected */
        if((s != null) && (s.length() > 0)) 
        {
    
   	      /* Try to connect to the serial port */
          try 
          {
  		      (Serial= new FBSerial()).connect(s);
          	FBSerial.SerialWriter("*RST");
    	      FBSerial.SerialWriter("*TST");
           
  		      /* Loop for a few times and try to get the connection string from the meter */
  		      for(int i = 0; i < 10 && !FBSerial.getReceiveBuffer().contains("Firebird"); i++)
  		      {
  		      	FBSerial.SerialWriter("*IDN?");
  		      	
  		      	try 
    		      {
    			      Thread.sleep(250);
    		      } 
    		      catch(InterruptedException ie) 
    		      {
    			      ie.printStackTrace();  
    		      }
  		      		      	
  		      }
    		      
  		      String LPMVersion = FBSerial.getReceiveBuffer();
  		      
  		      if(LPMVersion != null && LPMVersion.contains("Firebird"))
  		      {
  			
  			      /* Set the meter connected flag */
  			      MeterConnectFlag = true;
  			  		
  			      /* Enable and disable the menus based on the meter connection */
  			      itemF1.setEnabled(false);
  			      itemF2.setEnabled(true);
  			      itemF3.setEnabled(true);
  			      itemF4.setEnabled(true);
  			      
  			      LPMVersion = LPMVersion.replaceAll("(\\r|\\n)", "").trim();
  			
  			      /* Update the status bar with the meter version */
  	          StatusBar.setText(StatusStr + " "
  	               	                      + "Connected to: " 
  	                                      + "[" + LPMVersion.toString() + "]");
  	 	  
  	          FBLogging.log(null, "info","Connected to meter on port: " + s);
  		      }
  		      else
  		      {
  			      /* Set the meter connected flag */
  			      MeterConnectFlag = false;
  			  		
  			      FBSerial.close();	  
  			      
  			      /* Update the status bar with the meter version */
    	        StatusBar.setText(StatusStr + " "
    	            	                      + "Not Connected to Meter"); 
    	    
    	        FBLogging.log(null, "severe","Unable to connnect to meter on port: " + s);
  		      }
  		  
          } 
          catch(Exception e1) 
          {
  		      e1.printStackTrace();
  		    }
    	     	 
    	    return;
        }
      } 
      else
      {
    	  FBLogging.log(null, "warning","Already connected to meter!");	
      }
    }
    
    if(Command.equals("Plot Power Data"))
    {
      if(MeterConnectFlag)
    	{
    	  /* Log the plot */
        FBLogging.log(null, "info","Opening new plot."); 
        
        FBSerial.setReceiveBuffer("");
        
        try 
        {
        	/* Arm the meter for continuse readings */
        	FBSerial.SerialWriter("ARM:POW");
        	
        	/* Start the data stream */
        	FBSerial.SerialWriter("INIT:CONT ON");
        }
        catch(Exception e1) 
        {
		      e1.printStackTrace();
		    }
        //FBGraph newplot = new FBGraph();
        FBGraph.plot();
    	}
    }
    
    if(Command.equals("Save Power Data"))
    {
      final JFileChooser fc = new JFileChooser();	
      int returnVal = fc.showSaveDialog(this);	
    
      if (returnVal == JFileChooser.APPROVE_OPTION) 
      {
        File file = fc.getSelectedFile();
        
        //This is where a real application would open the file.
        //log.append("Opening: " + file.getName() + "." + newline);
        FBLogging.log(null, "info","Saving file " + file.getName() + ".");      
                 
      } 
      else 
      {
        //log.append("Open command cancelled by user." + newline);
    	  FBLogging.log(null, "warning","Save command cancelled by user.");
      }
    }
    
    if(Command.equals("Upload Tables"))
    {
      final JFileChooser fc = new JFileChooser();	
      int returnVal = fc.showOpenDialog(this);	
    
      if (returnVal == JFileChooser.APPROVE_OPTION) 
      {
        // Get the file handle file.
      	File file = fc.getSelectedFile();
        
        // Log the event
        FBLogging.log(null, "info","Opening file " + file.getName() + ".");  
        
        // Erase all the tables.
        FBSerial.SerialWriter("MEM:ERAS:ALL");
                
        // Tell the meter that a table file is coming.
        //FBSerial.SerialWriter("MEM:COPY:TABL");
        
        String text;
				try
				{
					text = new Scanner(file).useDelimiter("\\A").next();
					//FBSerial.SerialWriter(text); 				
				} 
				catch(FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        
        
      } 
      else 
      {
        //FBLogging.log(null, "warning","Open command cancelled by user.");
      }
    }
       
    if(Command.equals("Upload Meter Firmware"))
    {
      final JFileChooser fc = new JFileChooser();	
      int returnVal = fc.showOpenDialog(this);	
    
      if (returnVal == JFileChooser.APPROVE_OPTION) 
      {
        File file = fc.getSelectedFile();
        
        //This is where a real application would open the file.
        //log.append("Opening: " + file.getName() + "." + newline);
        FBLogging.log(null, "info","Opening file " + file.getName() + ".");      
      } 
      else 
      {
        //log.append("Open command cancelled by user." + newline);
    	  FBLogging.log(null, "warning","Open command cancelled by user.");
      }
    }
    
    
    
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

    /* Button input commands */
    if(Command.equals("Process"))
    {
      if(Process.getBackground() == Color.GREEN)  	
      {
        /* Set the button color for processing */
        Process.setBackground(Color.RED);
        
        /* Update the status bar */
        //StatusBar.setText(StatusStr + " ");
      }
      else
      {
    	  /* Set the button color for processing */
        Process.setBackground(Color.GREEN);
                
        /* Update the status bar */
        StatusBar.setText(StatusStr);  
      }
    }
    
    /* Other commands */
  }
  
  
  /* Read all file into a string */
  static String readFile(String path) throws IOException 
  {
  	byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded);
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
  
  JMenuItem itemF1;
  JMenuItem itemF2;
  JMenuItem itemF3;
  JMenuItem itemF4;
  
  /* Vars */
  static FBSerial Serial = null;
  Boolean MeterConnectFlag = false;
  ImageIcon FirebirdIcon;
  
  FBLogging lpmslog;
  //static private final String newline = "\n";
  String StatusStr = "  Status: ";
  int ScreenWidth = 640;

}
