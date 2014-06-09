/*
 * @(#)FBAppInfo.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 03/05/14
 *
 * This class is the base class for the power measurement app.
 */
package lpms;

public class FBAppInfo
{

  public FBAppInfo()
  {
  }

  /**
   * Loads the application name.
   * @param Application Name
   */
  public static void setAppName(String Name)
  {
    AppName = Name;
  }

  /**
   * Loads the application description.
   * @param Application Description
   */
  public static void setAppDescription(String Description)
  {
    AppDescription = Description;
  }

  /**
   * Loads the application authers name.
   * @param Application Author
   */
  public static void setAppAuthor(String Author)
  {
    AppAuthor = Author;
  }

  /**
   * Loads the application version.
   * @param Application Version
   */
  public static void setAppVersion(int Version)
  {
    AppVersion = Version;
  }

  /**
   * Loads the application revision.
   * @param Application Revision
   */
  public static void setAppRevision(int Revision)
  {
    AppRevision = Revision;
  }

  /**
   * Loads the application date.
   * @param Application Date
   */
  public static void setAppDate(String Date)
  {
    AppDate = Date;
  }

  /**
   * Loads the application login name.
   * @param Application Login
   */
  public static void setAppLogin(String Login)
  {
    AppLogin = Login;
  }

  /**
   * Loads the application login ID.
   * @param Application Login ID
   */
  public static void setAppLoginID(int LoginID)
  {
    AppLoginID = LoginID;
  }


  /**
   * Returns the application name.
   * @return Application Name
   */
  public static String getAppName()
  {
    return(AppName);
  }

  /**
   * Returns the application description.
   * @return Application Description
   */
  public static String getAppDescription()
  {
    return(AppDescription);
  }

  /**
   * Returns the application author.
   * @return Application Author
   */
  public static String getAppAuthor()
  {
    return(AppAuthor);
  }

  /**
   * Returns the application version.
   * @return Application Version
   */
  public static int getAppVersion()
  {
    return(AppVersion);
  }

  /**
   * Returns the application revision.
   * @return Application Revision
   */
  public static int getAppRevision()
  {
    return(AppRevision);
  }

  /**
   * Returns the application Version and revision in the string format
   * of VX.X.
   * @return Application Version
   */
  public static String getAppVersionString()
  {
    return("V" + AppVersion + "." + AppRevision);
  }

  /**
   * Returns the application date.
   * @return Application Date
   */
  public static String getAppDate()
  {
    return(AppDate);
  }

  /**
   * Returns the application login name.
   * @return Application login
   */
  public static String getAppLogin()
  {
    return(AppLogin);
  }

  /**
   * Returns the application login ID.
   * @return Application login ID
   */
  public static int getAppLoginID()
  {
    return(AppLoginID);
  }


  private static String AppName;
  private static String AppDescription;
  private static String AppAuthor;
  private static int AppVersion;
  private static int AppRevision;
  private static String AppDate;
  private static String AppLogin;
  private static int AppLoginID;
}
