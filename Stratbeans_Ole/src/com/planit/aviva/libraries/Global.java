package com.planit.aviva.libraries;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Global {
	public static WebDriver wDriver;
	public static WebDriverWait wait;
	
	public static String sChromeBrowser = "chrome";
	public static String sFirefoxBrowser = "firefox";
	public static String sIEBrowser = "ie";
	public static String sURL = "http://training.stratbeans.com/planit/";
	
	public static File driverDir = new File(System.getProperty("user.dir"));
	public static String sPathOfChromeDriver = driverDir.getAbsolutePath()+"\\Drivers\\chromedriver.exe";
	public static String sPathOfIEDriver = driverDir.getAbsolutePath()+"\\Drivers\\IEDriverServer.exe";
	
	public static String sAdmin_username = "admin@stratbeans.com";
	public static String sAdmin_password = "123456";
	
	public static String sUser_username = "user@stratbeans.com";
	public static String sUser_password = "123456";
	
	
}
