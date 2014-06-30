/*
 * @(#)FBSerial.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 06/25/14
 *
 * This class is the base class Serial interface.
 */

package lpms;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

public class FBSerial
{
    
  public FBSerial()
  {
    super();
  }

  /* Configure the serial port */
  void connect(String portName) throws Exception
  {
    CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
    if(portIdentifier.isCurrentlyOwned())
    {
      System.out.println("Error: Port is currently in use");
    }
    else
    {
      System.out.println("Connect 1/2");
      CommPort commPort = portIdentifier.open(this.getClass().getName(),6000);

      if(commPort instanceof SerialPort)
      {
        System.out.println("Connect 2/2");
        
        serialPort = (SerialPort) commPort;
        //SerialPort serialPort = (SerialPort) commPort;
        
        System.out.println("BaudRate: " + serialPort.getBaudRate());
        System.out.println("DataBIts: " + serialPort.getDataBits());
        System.out.println("StopBits: " + serialPort.getStopBits());
        System.out.println("Parity: " + serialPort.getParity());
        System.out.println("FlowControl: " + serialPort.getFlowControlMode());
        serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_ODD);
        //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
        System.out.println("BaudRate: " + serialPort.getBaudRate());
        System.out.println("DataBIts: " + serialPort.getDataBits());
        System.out.println("StopBits: " + serialPort.getStopBits());
        System.out.println("Parity: " + serialPort.getParity());
        System.out.println("FlowControl: " + serialPort.getFlowControlMode());
        
        in = serialPort.getInputStream();
        out = serialPort.getOutputStream();
        
        (TSR = new Thread(SR = new SerialReader(in))).start();
        //(TSW = new Thread(SW = new SerialWriter(out))).start();
      }
      else
      {
        System.out.println("Error: Only serial ports are handled by this example.");
      }
    }     
  }
  
  
  /* Close the com port */
  public static void close()
  {
    if(serialPort != null)
    {
      /* Close serial port */
      serialPort.close(); 
    }
   
    in = null;        //close input and output streams
    out = null;
  }

  /** */
  public static class SerialReader implements Runnable 
  {
    InputStream in;

    public SerialReader(InputStream in)
    {
      this.in = in;
    }

    public void run()
    {
      byte[] buffer = new byte[1024];
      int len = -1;
      
      try
      {
        while((len = this.in.read(buffer)) > -1)
        {
          //System.out.println("Received a signal.");
          System.out.print(new String(buffer, 0, len));
          
          if(buffer.length > 10)
          {	  
            ReceiveBuffer = new String(buffer);
          }  
        }
        
        
        
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }            
    }
  }

  /** */
  public static class SerialWriter implements Runnable 
  {
    OutputStream out;
    private volatile boolean running;
    
    public SerialWriter(OutputStream out)
    {
      this.out = out;
    }

    public void run()
    {
      this.running = true;
      try
      {                
        //byte[] array = {0x1B, 0x50, 0x0D, 0x0A};
        while(this.running == true)
        {
          this.out.write("*IDN?\r\n".getBytes());
          //this.out.write(new byte[]{0x2A, 0x49, 0x44, 0x4E, 0x3F, 0xA, 0xD});
          this.out.flush();
          Thread.sleep(1000);  
        }                
      }
      catch(IOException | InterruptedException e)
      {
        e.printStackTrace();
      }            
    }
    
    public void stopExecuting() 
    {
      this.running = false;
    }
  }
  
  /* Write a string to serial port */
  public static void SerialWriter(String str)  
  {
    str = str + "\r\n";
	  
    try
    {                
      //out.write(new byte[]{0x2A, 0x49, 0x44, 0x4E, 0x3F, 0xA, 0xD});
      out.write(str.getBytes());
      out.flush();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }            
  }
  
  /* */
  public static ArrayList<String> getCommPorts()
  {
    CommPortIdentifier ports = null;      
    ArrayList<String> comport = new ArrayList<String>();
        
    /* Store all available ports */
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers(); 
        
    while(portEnum.hasMoreElements())
    {  
      /* Browse through available ports */
      ports = (CommPortIdentifier)portEnum.nextElement();
      //System.out.println("Port: " + ports.getName());
          
      /* Following line checks whether there is the port i am looking for and whether it is serial */
      //if(ports.getPortType() == CommPortIdentifier.PORT_SERIAL&&ports.getName().equals("COM3"))
      if(ports.getPortType() == CommPortIdentifier.PORT_SERIAL)
      { 
    	comport.add(ports.getName());
    	//System.out.println("Port: " + ports.getName());  
      }
    }
	
    return comport;
  }
  
   
  /* Get the receive buffer */
  public static String getReceiveBuffer()
  {
	return(ReceiveBuffer); 
  }
  
  /* Get the receive buffer */
  public static void setReceiveBuffer(String tstr)
  {
	ReceiveBuffer = tstr; 
  }
  
  /* Gets the state of the serial port */
  public static Boolean getPortState()
  {
	return(PortOpen);  
  }
  
  
  /* Unit testing main */
  public static void main(String[] args)
  {
    try
    {
      (new FBSerial()).connect("COM3");
      FBSerial.SerialWriter("*IDN?");
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  
  private static Boolean PortOpen = false;
  
  private static String ReceiveBuffer;
  static InputStream in;
  static OutputStream out;
  
  static SerialPort serialPort = null;
  
  public static SerialWriter SW = null;
  public static SerialReader SR = null;
  
  public static Thread TSR = null;
  public static Thread TSW = null;
  
}