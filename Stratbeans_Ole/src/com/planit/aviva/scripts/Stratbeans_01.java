package com.planit.aviva.scripts;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import com.planit.aviva.libraries.ApplicationFunc;
import com.planit.aviva.libraries.GeneralFunctions;
import com.planit.aviva.libraries.Global;

import com.planit.framework.lib.Messages;
import com.planit.framework.lib.Utilities;
import com.planit.framework.reporting.Reporting;


public class Stratbeans_01 {
	public static File screenshotDir;
	private static boolean bStatus;
	public static String sTestCaseName;
	
	@BeforeMethod
	public static void setup() {
		try {
			Reporting.Functionality = "Setup Application";
			Reporting.Testcasename = "Open Browser and Navigate to Application";
			screenshotDir = new File(Global.driverDir.getAbsolutePath()+"\\Screenshots\\"+Utilities.GetCurrentTimeStamp());
			//Global.wDriver = GeneralFunctions.openBrowser(Global.sIEBrowser, Global.sURL, Global.sPathOfIEDriver);
			Global.wDriver = GeneralFunctions.openBrowser(Global.sChromeBrowser, Global.sURL, Global.sPathOfChromeDriver);
			System.out.println(Global.sPathOfChromeDriver);
			
			if (Global.wDriver == null) {
				Reporting.logResults("Fail", "Open Web Application","Application not opened.");
				Assert.fail("Application not opened.");
			}
			Reporting.logResults("Pass", "Open Web Application","Application opened successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public static void CreateUserDetails() {
		try {
			Reporting.Functionality = "Learning Management System";
			sTestCaseName = "Stratbeans";
		
			Map<String, Map<String, String>> CreateuserDetails = Utilities.readMultipleTestData("TestData\\SB_CreateUser.xls", "TestData", sTestCaseName);
			
			System.out.println("Size: "+CreateuserDetails.size());
			
			for (int iRowValue = 0; iRowValue < CreateuserDetails.size(); iRowValue++) {
				Map<String, String> userDetails = CreateuserDetails.get("Row"+ (iRowValue + 1));
				String sCurentTCName = userDetails.get("TestCaseNumber");
				Reporting.Testcasename = sCurentTCName;
				Reporting.Iterator = iRowValue + 1;
				if (userDetails.get("TestCaseName")!= null) 
			
					bStatus = ApplicationFunc.loginToApplication(Global.sAdmin_username,Global.sAdmin_password);
					if (!bStatus) {
						screenshotDir.mkdir();
						Utilities.takeWebScreenshot(Global.wDriver,screenshotDir.getAbsolutePath()+"\\Login to application failed");
						//Reporting.logResults("Fail", "Login to Application","Login to application failed." + Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Login to application failed.png");
						Reporting.logResults("Fail", "Login to Application",screenshotDir.getAbsolutePath()+"\\Login to application failed" + Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Login to application failed.png");
						continue;
						
					}
					Reporting.logResults("Pass", "Login to Application","Logged in to application successfully");

					if(userDetails.get("ModuleName").equalsIgnoreCase("UserData")){
					bStatus = ApplicationFunc.createUser("TestData\\SB_CreateUser.xls", "UserData", userDetails.get("TestCaseNumber"));
					if (!bStatus) {
						screenshotDir.mkdir();
						Utilities.takeWebScreenshot(Global.wDriver,screenshotDir.getAbsolutePath()+"\\Create User failed");
						Reporting.logResults("Fail", "Create User",screenshotDir.getAbsolutePath()+"Create User failed"+ Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Create User failed.png");
						bStatus = ApplicationFunc.logout();
						if (!bStatus) {
							screenshotDir.mkdir();
							Utilities.takeWebScreenshot(Global.wDriver,screenshotDir.getAbsolutePath()+"\\Logout to application failed");
							Reporting.logResults("Fail", "Logout application",screenshotDir.getAbsolutePath()+"Logout to application failed"+ Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Logout to application failed.png");
							continue;
							}
						continue;
					}
					Reporting.logResults("Pass", "Create User","Create User successfully done");
				}
					
					
					if(userDetails.get("ModuleName").equalsIgnoreCase("ManageTrainingPrgm")){
						bStatus = ApplicationFunc.ManageTrainingPrograms("TestData\\SB_CreateUser.xls", "ManageTrainingPrgm", userDetails.get("TestCaseNumber"));
						if (!bStatus) {
							screenshotDir.mkdir();
							Utilities.takeWebScreenshot(Global.wDriver,screenshotDir.getAbsolutePath()+"\\Manage Training Programs failed");
							Reporting.logResults("Fail", "Manage Training Programs",screenshotDir.getAbsolutePath()+"Manage Training Programs failed."+ Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Manage Training Programs failed.png");
							bStatus = ApplicationFunc.logout();
							if (!bStatus) {
								screenshotDir.mkdir();
								Utilities.takeWebScreenshot(Global.wDriver,screenshotDir.getAbsolutePath()+"\\Logout application failed");
								Reporting.logResults("Fail", "Logout application",screenshotDir.getAbsolutePath()+"Logout to application failed"+ Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Logout to application failed.png");
								continue;
								}
							continue;
						}
						Reporting.logResults("Pass", "Manage Training Programs","Manage Training Programs successfully");
					}					
					
					bStatus = ApplicationFunc.logout();
					if (!bStatus) {
						screenshotDir.mkdir();
						Utilities.takeWebScreenshot(Global.wDriver,screenshotDir.getAbsolutePath()+"\\Click on new loan application failed");
						Reporting.logResults("Fail", "Logout application","Logout application failed."+ Messages.errorMsg,screenshotDir.getAbsolutePath()+"\\Logout application failed.png");
						continue;
					}
					Reporting.logResults("Pass", "Logout application","Logout done successfully");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@AfterMethod
	public static void tearDown(){
		Reporting.Functionality = "Shutdown Application";
		Reporting.Testcasename = "Close Application and Browser";
		try {
			Global.wDriver.quit();
			Reporting.logResults("Pass", "Close Application and browser", "Application and browser closed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.logResults("Fail", "Close Application and browser", "Application close failed."+e);
		}
	}
}
