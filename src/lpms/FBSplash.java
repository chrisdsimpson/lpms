/*
 * @(#)FBSplash.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 06/15/14
 */
package lpms;

import javax.swing.*;
import java.awt.*;

public class FBSplash extends JWindow
{
  /**
   * Builds the FB splash screen. The screen is displayed for the number
   * of milliseconds passed into the Period param.
   * @param Period the time in milliseconds the the window is displayed.
   */
  public FBSplash(long Period)
  {
    /* Get the screen info */
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    JButton okButton;

    this.getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    /* Add the buttons onto their own panel */
    c.gridx = 0;
    c.weighty = 0.0;
    c.weightx = 1.0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    JPanel p = new JPanel();
    this.getContentPane().add(p, c);

    /* Load the Firebird image */
    p.add(okButton = new JButton("", new ImageIcon(getClass().
                                 getResource("fbsplash.jpg"))), c);

    /* Set the position of the login dialog */
    this.setSize(604, 198);
    int w = this.getSize().width;
    int h = this.getSize().height;
    int x = (dim.width-w)/2;
    int y = (dim.height-h)/2;
    this.setBounds(x, y, w, h);
    this.setVisible(true);

    try
    {
      Thread.sleep(Period);
      this.setVisible(false);
    }
    catch(InterruptedException err)
    {
      System.out.println("Unable to put main thread to sleep " +
                         err.getMessage());
      //err.printStackTrace();
    }
  }
}