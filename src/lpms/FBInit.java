/*
 * @(#)FBInit.java
 *
 * Copyright (c) 2014-2014 Firebird Sensors, Inc. All Rights Reserved.
 *
 * @author  Christopher D. Simpson
 * @version 1.00 03/05/14
 *
 * This class is the base class for the power measurement app.
 */
package lpms; 


public class FBInit
{

  /**
   * The constructor is passed the application specific information
   * which popluates the appinfo class. Also it opens the database.
   * @param Name The application name.
   * @param Description The application description.
   * @param Author The name of the application developer.
   * @param Version The main version of the application.
   * @param Revision The revison number of the application.
   * @param Data The application version/revision date.
   * @param Login The login name used to start the application.
   */
  public FBInit(String Name,
                String Description,
                String Author,
                int Version,
                int Revision,
                String Date,
                String Login)
  {

    /* Set the application information */
    appinfo = new FBAppInfo();
    appinfo.setAppName(Name);
    appinfo.setAppDescription(Description);
    appinfo.setAppAuthor(Author);
    appinfo.setAppVersion(Version);
    appinfo.setAppRevision(Revision);
    appinfo.setAppDate(Date);
    appinfo.setAppLogin(Login);

  }

  static FBAppInfo appinfo;

}

