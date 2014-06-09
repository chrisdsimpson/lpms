/*
 * @(#)FBAbout.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 03/05/14
 *
 * This class is the base class for the power measurement app.
 */
package lpms;

import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class FBAbout
{
  /**
   * Builds and displays the About dialog box for the application
   * using the information from the static class LPCSAppInfo.
   */
  public FBAbout()
  {
    JOptionPane.showMessageDialog(frame,
                                  "Name: " +
                                  FBAppInfo.getAppName() + "\n " +
                                  "Description: " +
                                  FBAppInfo.getAppDescription() + "\n " +
                                  "Author: " +
                                  FBAppInfo.getAppAuthor() + "\n " +
                                  "Version: " +
                                  FBAppInfo.getAppVersionString()+"\n " +
                                  "Date: " +
                                  FBAppInfo.getAppDate(),
                                  FBAppInfo.getAppName() +
                                  " - About",
                                  JOptionPane.INFORMATION_MESSAGE);

  }

  JFrame frame;
}
