package com.planit.aviva.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.planit.framework.lib.Messages;

public class GeneralFunctions {
	public static WebDriver openBrowser(String browserName, String sUrl, String sPathOfDriver)
	{
		WebDriver wDriver;
		try
		{
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", sPathOfDriver);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("chrome.switches","--disable-extensions");
				
				//capabilities.setCapability("chrome.binary","<<your chrome path>>");
				capabilities.setCapability("chrome.binary",sPathOfDriver);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				wDriver = new ChromeDriver(capabilities);
			} else if(browserName.equals("ie")) {
				System.setProperty("webdriver.ie.driver", sPathOfDriver);
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("requireWindowFocus", true);
				wDriver = new InternetExplorerDriver(capabilities);

			} else {
				wDriver = new FirefoxDriver();
			}
			wDriver.get(sUrl);

			wDriver.manage().window().maximize();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			Messages.errorMsg = e.getMessage();
			return null;
		}
		return wDriver;
	}

}
