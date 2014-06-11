/*
 * @(#)FBLogging.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 06/10/14
 *
 * This class is the base class for the power measurement app.
 */

package lpms;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
	
public class FBLogging
{
  /**
   * Writes a log record to the LPCS system log file.
   * @param Msg The message logged to the log file using
   * using the error level 5(DEBUG).
   */
  public void LogMsg(String Msg)
  {
    WriteLog(5, Msg);
  }

  /**
   * Writes a log record to the LPCS system log file.
   * @param LogLevel Indicates the log level.
   * @param Msg The message written to the log file.
   */
  public void LogMsg(int LogLevel, String Msg)
  {
    WriteLog(LogLevel, Msg);
  }

  /**
   * Performs the INSERT statement to the LPCSLog table of the database.
   * @param Level Passed from LogMsg method and indicates the log level.
   * @param Message Passed from LogMsg method and contains the log message.
   */
  private void WriteLog(int Level, String Message)
  {
    String loglevel = "UNDEFINED";

    /* Convert the error level into the string varint */
    switch(Level)
    {
      case 0:
        loglevel = "FATAL";
      break;

      case 1:
        loglevel = "ERROR";
      break;

      case 2:
        loglevel = "WARNING";
      break;

      case 3:
      case 4:
        loglevel = "INFORMATION";
      break;

      case 5:
        loglevel = "DEBUG";
      break;
    }

    /* Get rid of any (') chars so the sql statment will be ok */
    Message = Message.replace((char)39, '-');

    String SQLStr = "INSERT INTO LPCSLog " +
	                "(RecordDateTime, AppName, AppVersion, LogLevelID, " +
	                "LogLevel, LoginName, Message) " +
	                "VALUES (" +
	                "getdate(), '" +
	                FBAppInfo.getAppName() + "', '" +
	                FBAppInfo.getAppVersionString() + "', '" +
	                Level + "', '" +
	                loglevel + "', '" +
	                FBAppInfo.getAppLogin() + "', '" +
	                Message + "')";

    //System.out.println("Current Date: " + curdate);
    //System.out.println("Current Time: " + curtime);
    //System.out.println("SQL: " + SQLStr);
    
  }

  
  /**
   * log Method 
   * enable to log all exceptions to a file and display user message on demand
   * @param ex
   * @param level
   * @param msg 
   */
  public static void log(Exception ex, String level, String msg)
  {
    FileHandler fh = null;
        
    try 
    {
      /* This block configure the logger with handler and formatter */
      fh = new FileHandler("lpms.log", true);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
            
    
      switch (level) 
      {
        case "severe":
          logger.log(Level.SEVERE, msg, ex);
          
          if(!msg.equals(""))
          {   
        	JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
          }
       	break;
      
        case "warning":
          logger.log(Level.WARNING, msg, ex);
          
          if(!msg.equals(""))
          {  
        	JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
          }
        break;
        
        case "info":
          logger.log(Level.INFO, msg, ex);
           
          if(!msg.equals(""))
          {    
        	JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
          }       
        break;
     
        case "config":
          logger.log(Level.CONFIG, msg, ex);
        break;
        
        case "fine":
          logger.log(Level.FINE, msg, ex);
        break;
        
        case "finer":
          logger.log(Level.FINER, msg, ex);
        break;
        
        case "finest":
          logger.log(Level.FINEST, msg, ex);
        break;
        
        default:
          logger.log(Level.CONFIG, msg, ex);
        break;
      }
    } 
    catch (IOException | SecurityException ex1) 
    {
      logger.log(Level.SEVERE, null, ex1);
    } 
    finally
    {
      if(fh!=null)
        fh.close();
    }
  }
   
  protected static final Logger logger=Logger.getLogger("FBLOG");
  static final String logfile = "com/firebird/lpms/lpms.log";
  
}

	

