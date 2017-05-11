package com.planit.aviva.libraries;


import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planit.aviva.locators.Locators;
import com.planit.framework.lib.Elements;
import com.planit.framework.lib.Messages;
import com.planit.framework.lib.Utilities;
import com.planit.framework.lib.Wait;

public class ApplicationFunc {
	public static boolean bStatus;
	public static String errormsg;
	public static String name;
	public static String program_name;
	public static String training_desp;
	
	public static Map<String, String> UserDetails = new HashMap<String, String>();
	
	//Login to Application
	public static boolean loginToApplication(String userName,String passWord){

		try{
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Loginpage.TextField.UserName,Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Username filed is not present";
				return false;
			}
			bStatus = Elements.enterText(Global.wDriver, Locators.Loginpage.TextField.UserName, userName);
			if(!bStatus){
				Messages.errorMsg = "Username field is not inputted";
				return false;
			}
			bStatus = Elements.enterText(Global.wDriver, Locators.Loginpage.TextField.Password,passWord);
			if(!bStatus){
				Messages.errorMsg = "Password field is not inputted";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Loginpage.Link.SignIn);
			if(!bStatus){
				Messages.errorMsg = "Sign In button is not clicked";
				return false;
			}
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Loginpage.text.loginText,Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "My Learing Plan text is not present in the after login";
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return bStatus;

	}

	//Logout to application
	public static boolean logout(){
		try{

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Logout.imagedropdown.logout_dropdown, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Dropdown Logout is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Logout.imagedropdown.logout_dropdown);
			if(!bStatus){
				Messages.errorMsg = "logout dropdown button is not clicked";
				return false;
			}

			WebDriverWait wait = new WebDriverWait(Global.wDriver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Logout.link.logout));

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Logout.link.logout, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Dropdown Logout is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Logout.link.logout);
			if(!bStatus){
				Messages.errorMsg = "logout dropdown link is not clicked";
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return bStatus;
	}
	
	// Clicking on create button and verifying the creare user
	public static boolean clickCreateButton(){
		try{
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Buttons.Create_button, Constants.pageToLoad);
			if(!bStatus){
				errormsg = "Create button is not visible";
				return false;
			}
			bStatus=Elements.clickButton(Global.wDriver, Locators.Users.Buttons.Create_button);
			if(!bStatus){
				errormsg = "create button is not clicked";
				return false;
			}
			bStatus = Wait.waitForElementVisibility(Global.wDriver,By.xpath("//div[@class='toast toast-success']"), Constants.waitforelement);
			if(bStatus){
				String gettext = Elements.getText(Global.wDriver, By.xpath("//div[@class='toast toast-success']"));
				System.out.println("SuccessMessage: "+gettext);
				bStatus = VerifyCreateUser(name);
				if(!bStatus){
					Messages.errorMsg = errormsg;
				}
			}else{
				String gettext = Elements.getText(Global.wDriver, By.xpath("//div[@class='toast toast-error']"));
				errormsg = ""+gettext;
			}

			Thread.sleep(3000);

		}catch(Exception e){
			e.printStackTrace();
		}

		return bStatus;
	}

	//Creating User
	public static boolean createUser(String filepath,String sheetname,String stestcasename) throws Exception{
		try{
			UserDetails = Utilities.readTestData(filepath,sheetname, stestcasename);
			name = UserDetails.get("Full Name")+Utilities.GetCurrentTimeStamp();

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.Navbar, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Navigation bar link is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.Navbar);
			if(!bStatus){
				Messages.errorMsg = "Navigation bar link is not clicked";
				return false;
			}
			
			WebDriverWait wait = new WebDriverWait(Global.wDriver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Users.Links.User));

			Thread.sleep(2000);
			
			/*bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.User);
			if(!bStatus){
				Messages.errorMsg = "User link is not clicked";
				return false;
			}*/
			
			WebElement User_Element = Global.wDriver.findElement(Locators.Users.Links.User);
			JavascriptExecutor UserExecutor = (JavascriptExecutor)Global.wDriver;
			UserExecutor.executeScript("arguments[0].click();", User_Element);
			
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.Manageuser, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Manageuser link is not visible";
				return false;
			}
			
			WebElement Manageuser_Element = Global.wDriver.findElement(Locators.Users.Links.Manageuser);
			JavascriptExecutor ManageuserExecutor = (JavascriptExecutor)Global.wDriver;
			ManageuserExecutor.executeScript("arguments[0].click();", Manageuser_Element);
			
			/*bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.Manageuser);
			if(!bStatus){
				Messages.errorMsg = "Manageuser link is not clicked";
				return false;
			}*/
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Text.Createuser, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Create User text is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.ArrowUp);
			if(!bStatus){
				Messages.errorMsg = "Arrowup link is not clicked";
				return false;
			}
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.TextField.Fullname, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Fullname textfield is not visible";
				return false;
			}
			if(UserDetails.get("Full Name")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Full Name"));
				String Fullname = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Full Name");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(Fullname), name);
				if(!bStatus){
					Messages.errorMsg = "Text is not entered in the Full name field";
					return false;
				}
			}

			if(UserDetails.get("Email")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Email"));
				String emailid = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Email");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emailid), UserDetails.get("Email")+Utilities.GetCurrentTimeStamp()+"@planittesting.com");
				if(!bStatus){
					Messages.errorMsg = "Text is not entered in the Email text field";
					return false;
				}
			}

			if(UserDetails.get("Select Role")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select Role"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Select Role"), UserDetails.get("Select Role"));

				if(!bStatus){
					Messages.errorMsg = "Select role drop down value is not selected";
					return false;
				}

			}
			if(UserDetails.get("Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Employee Code"));
				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Employee Code")+Utilities.GetCurrentTimeStamp());
				if(!bStatus){
					Messages.errorMsg = "Employee code is not entered into employee code text filed";
					return false;
				}
			}

			if(UserDetails.get("Date of Joining")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Date of Joining"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Date of Joining");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Date of Joining"));
				if(!bStatus){
					Messages.errorMsg = "Date of Joining is not entered into Date of Joining text filed";
					return false;
				}
			}

			if(UserDetails.get("Mobile No")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Mobile No"));
				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Mobile No");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Mobile No"));
				if(!bStatus){
					Messages.errorMsg = "Mobile No is not entered into Mobile No text filed";
					return false;
				}
			}
			if(UserDetails.get("Gender")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Gender"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Gender"), UserDetails.get("Gender"));

				if(!bStatus){
					Messages.errorMsg = "Gender is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Region")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Region"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Region"), UserDetails.get("Region"));
				if(!bStatus){
					Messages.errorMsg = "Region is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Zone")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Zone"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Zone"), UserDetails.get("Zone"));
				if(!bStatus){
					Messages.errorMsg = "Zone is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Branch")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Branch"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Branch"), UserDetails.get("Branch"));
				if(!bStatus){
					Messages.errorMsg = "Branch is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Department")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Department"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Department"), UserDetails.get("Department"));
				if(!bStatus){
					Messages.errorMsg = "Department is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Designation")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Designation"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Designation"), UserDetails.get("Designation"));
				if(!bStatus){
					Messages.errorMsg = "Designation is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("PEP Start Date")!=null){
				System.out.println("Enter:*******"+UserDetails.get("PEP Start Date"));

				String emp_code = Locators.Users.TextField.DateFiled_str.replace("fieldname", "PEP Start Date");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("PEP Start Date"));
				if(!bStatus){
					Messages.errorMsg = "PEP Start Date is not entered into PEP start date text filed";
					return false;
				}
			}
			if(UserDetails.get("Last Promotion Date")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Last Promotion Date"));

				String emp_code = Locators.Users.TextField.DateFiled_str.replace("fieldname", "Last Promotion Date");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Last Promotion Date"));
				if(!bStatus){
					Messages.errorMsg = "Last Promotion Date is not entered into Last promotion date text filed";
					return false;
				}
			}
			if(UserDetails.get("Band")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Band"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Band");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Band"));
				if(!bStatus){
					Messages.errorMsg = "Band is not entered into band text filed";
					return false;
				}
			}
			if(UserDetails.get("Confirmation Due Date")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Confirmation Due Date"));

				String emp_code = Locators.Users.TextField.DateFiled_str.replace("fieldname", "Confirmation Due Date");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Confirmation Due Date"));
				if(!bStatus){
					Messages.errorMsg = "Confirmation Due Date is not entered into Confirmation Due Date text filed";
					return false;
				}
			}
			if(UserDetails.get("Confirmation Actual Date")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Confirmation Actual Date"));

				String emp_code = Locators.Users.TextField.DateFiled_str.replace("fieldname", "Confirmation Actual Date");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Confirmation Actual Date"));
				if(!bStatus){
					Messages.errorMsg = "Confirmation Actual Date is not entered into Confirmation Actual Date text filed";
					return false;
				}
			}
			if(UserDetails.get("Function Type")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Function Type"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Function Type"), UserDetails.get("Function Type"));
				if(!bStatus){
					Messages.errorMsg = "Function Type is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Channel")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Channel"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Channel"), UserDetails.get("Channel"));
				if(!bStatus){
					Messages.errorMsg = "Channel is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Participant Type")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Participant Type"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Participant Type"), UserDetails.get("Participant Type"));
				if(!bStatus){
					Messages.errorMsg = "Participant Type is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Probation Status")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Probation Status"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Probation Status"), UserDetails.get("Probation Status"));
				if(!bStatus){
					Messages.errorMsg = "Probation Status is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("PEP Status")!=null){
				System.out.println("Enter:*******"+UserDetails.get("PEP Status"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "PEP Status"), UserDetails.get("PEP Status"));
				if(!bStatus){
					Messages.errorMsg = "PEP Status is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Regulatory Training Status")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Regulatory Training Status"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Regulatory Training Status"), UserDetails.get("Regulatory Training Status"));
				if(!bStatus){
					Messages.errorMsg = "Regulatory Training Status is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("L1 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("L1 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "L1 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("L1 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "L1 Employee Code is not entered into L1 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("L2 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("L2 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "L2 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("L2 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "L2 Employee Code is not entered into L2 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("L3 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("L3 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "L3 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("L3 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "L3 Employee Code is not entered into L3 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("L4 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("L4 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "L4 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("L4 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "L4 Employee Code is not entered into L4 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("L5 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("L5 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "L5 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("L5 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "L5 Employee Code is not entered into L5 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("L6 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("L6 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "L6 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("L6 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "L6 Employee Code is not entered into L6 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("T1 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("T1 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "T1 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("T1 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "T1 Employee Code is not entered into T1 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("T2 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("T2 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "T2 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("T2 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "T2 Employee Code is not entered into T2 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("T3 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("T3 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "T3 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("T3 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "T3 Employee Code is not entered into T3 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("T4 Employee Code")!=null){
				System.out.println("Enter:*******"+UserDetails.get("T4 Employee Code"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "T4 Employee Code");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("T4 Employee Code"));
				if(!bStatus){
					Messages.errorMsg = "T4 Employee Code is not entered into T4 employee code text filed";
					return false;
				}
			}
			if(UserDetails.get("Training Passport")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Training Passport"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Training Passport"), UserDetails.get("Training Passport"));
				if(!bStatus){
					Messages.errorMsg = "Training Passport is not selcted from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Employee Last Working Date")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Employee Last Working Date"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "Employee Last Working Date");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Employee Last Working Date"));
				if(!bStatus){
					Messages.errorMsg = "Employee Last Working Date is not entered into Employee Last Working Date text filed";
					return false;
				}
			}
			if(UserDetails.get("Aviva Employee")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Aviva Employee"));

				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.Dropdown.replace("fieldname", "Aviva Employee"), UserDetails.get("Aviva Employee"));
				if(!bStatus){
					Messages.errorMsg = "Aviva Employee is not selected from the drop down";
					return false;
				}
			}
			if(UserDetails.get("Date of Birth")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Date of Birth"));

				String emp_code = Locators.Users.TextField.DateFiled_str.replace("fieldname", "Date of Birth");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("Date of Birth"));
				if(!bStatus){
					Messages.errorMsg = "Date of Birth is not entered into Date of Birth filed";
					return false;
				}
			}
			if(UserDetails.get("PAN")!=null){
				System.out.println("Enter:*******"+UserDetails.get("PAN"));

				String emp_code = Locators.Users.TextField.TextField_Normal_str.replace("fieldname", "PAN");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(emp_code), UserDetails.get("PAN"));
				if(!bStatus){
					Messages.errorMsg = "PAN is not entered into PAN text filed";
					return false;
				}
			}

			bStatus = clickCreateButton();
			if(!bStatus){
				Messages.errorMsg = "Error is: "+ errormsg;
				return false;
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return bStatus;

	}

	// Verifying the created user
	public static boolean VerifyCreateUser(String name){

		try{
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Tables.Table_FullName, Constants.pageToLoad);
			if(!bStatus){
				errormsg = name+" is not visible in the web table";
			}
			String FullName_GetText = Elements.getText(Global.wDriver, Locators.Users.Tables.Table_FullName);
			System.out.println(name+ FullName_GetText);
			if(FullName_GetText.equalsIgnoreCase(name)){
				System.out.println(FullName_GetText+" Matches "+name);
			}else{
				errormsg= "Excepted Result "+FullName_GetText+ "is not with Actual Ressult "+name;
			}
			Thread.sleep(3000);

		}
		catch(Exception e){
			e.printStackTrace();

		}
		return bStatus;
	} 

	//Search functionality for manage classroom
	public static boolean SearchMangeClassRoom(){

		bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.Navbar, Constants.pageToLoad);
		if(!bStatus){
			Messages.errorMsg = "Navigation bar link is not visible";
			return false;
		}
		bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.Navbar);
		if(!bStatus){
			Messages.errorMsg = "Navigation bar link is not clicked";
			return false;
		}

		WebDriverWait wait = new WebDriverWait(Global.wDriver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Users.Links.manageClassroom));

		bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.manageClassroom);
		if(!bStatus){
			Messages.errorMsg = "Manage class room link is not clicked";

		}
		bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.manageClassROomTemplate, Constants.pageToLoad);
		if(!bStatus){
			Messages.errorMsg = "manage Class Room Template link is not visible";
			return false;
		}

		bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.manageClassROomTemplate);
		if(!bStatus){
			Messages.errorMsg = "manage Class Room Template link is not clicked";
			return false;
		}

		bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Text.createClassROomTemplate, Constants.pageToLoad);
		if(!bStatus){
			Messages.errorMsg = "Create class room template page is not visible";
			return false;
		}

		bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Buttons.showFilters);
		if(!bStatus){
			Messages.errorMsg = "Show filters button is not clicked";
			return false;
		}

		bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Buttons.search, Constants.pageToLoad);
		if(!bStatus){
			Messages.errorMsg = "Search button is not visible in create classroom template page";
			return false;
		}

		bStatus = Elements.clickButton(Global.wDriver, Locators.Users.TextField.searchField);
		if(!bStatus){
			Messages.errorMsg = "unable to click on the search text field";
			return false;
		}
		bStatus = Elements.enterText(Global.wDriver,Locators.Users.TextField.searchField, "Planit");
		if(!bStatus){
			Messages.errorMsg = "search text is not entered into search text filed";
			return false;
		}

		return bStatus;	
	}

	//Creating a manage training programs
	public static boolean ManageTrainingPrograms(String filepath,String sheetname,String stestcasename){


		try{
			UserDetails = Utilities.readTestData(filepath,sheetname, stestcasename);
			program_name = UserDetails.get("Program Name")+Utilities.GetCurrentTimeStamp();
			training_desp = UserDetails.get("Training Description")+Utilities.GetCurrentTimeStamp();
			name = UserDetails.get("Category Name");


			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.Navbar, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Navigation bar link is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.Navbar);
			if(!bStatus){
				Messages.errorMsg = "Navigation bar link is not clicked";
				return false;
			}

			WebDriverWait wait = new WebDriverWait(Global.wDriver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Users.Links.manageTraing));

			Thread.sleep(2000);
			
			/*bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.manageTraing);
			if(!bStatus){
				Messages.errorMsg = "Manage trainging link is not clicked";
				return false;
			}*/
			
			WebElement manageTraing_Element = Global.wDriver.findElement(Locators.Users.Links.manageTraing);
			JavascriptExecutor manageTraingExecutor = (JavascriptExecutor)Global.wDriver;
			manageTraingExecutor.executeScript("arguments[0].click();", manageTraing_Element);

			//*************Manage Training Category**********


			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.manageTrainingCategory, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Manage training caategory link is not visible";
				return false;
			} 

			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.manageTrainingCategory);
			if(!bStatus){
				Messages.errorMsg = "Manage training caategory link is not clicked";
				return false;
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Dropdown.selectParentCategoryText, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Select parent category dropdown is not visible";
				return false;
			}


			if(UserDetails.get("Select Parent Category")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select Parent Category"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.selectParentCategory, UserDetails.get("Select Parent Category"));
				//Thread.sleep(1000);
				if(!bStatus){
					Messages.errorMsg = "Select Parent Category option is not selected from the dropdown ";
					return false;
				}
			} 

			if(UserDetails.get("Category Name")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Category Name"));
				bStatus=Elements.clickButton(Global.wDriver, Locators.Users.TextField.categoryName);
				//Thread.sleep(1000);
				String categoryName = Locators.Users.TextField.txtcategoryName.replace("fieldname", "Category Name");
				bStatus = Elements.enterText(Global.wDriver,By.xpath(categoryName), name+Utilities.GetCurrentTimeStamp());
				if(!bStatus){
					Messages.errorMsg = "Text is not entered in the Category Name text field";
					return false;
				}
			}
			
			bStatus = clickCreateButton();
			if(!bStatus){
				Messages.errorMsg = "Error is: "+ errormsg;
				return false;
			}



			//***************Manage Training Program************************


			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.Navbar, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Navigation bar link is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.Navbar);
			if(!bStatus){
				Messages.errorMsg = "Navigation bar link is not clicked";
				return false;
			}

			//WebDriverWait wait = new WebDriverWait(Global.wDriver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Users.Links.manageTraing));
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.manageTraing, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Manage trainging link is not visible";
				return false;
			}

			Thread.sleep(2000);
			/*bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.manageTraing);
			if(!bStatus){
				Messages.errorMsg = "Manage trainging link is not clicked";
				return false;
			}*/
			
			WebElement manageTraing1_Element = Global.wDriver.findElement(Locators.Users.Links.manageTraing);
			JavascriptExecutor manageTraing1Executor = (JavascriptExecutor)Global.wDriver;
			manageTraing1Executor.executeScript("arguments[0].click();", manageTraing1_Element);


			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.manageTrainingProgram, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Manage trainging program link is not visible";
				return false;
			}

			/*bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.manageTrainingProgram);
			if(!bStatus){
				Messages.errorMsg = "Manage trainging program link is not clicked";
				return false;
			}*/
			
			WebElement manageTrainingProgram_Element = Global.wDriver.findElement(Locators.Users.Links.manageTrainingProgram);
			JavascriptExecutor manageTrainingProgramExecutor = (JavascriptExecutor)Global.wDriver;
			manageTrainingProgramExecutor.executeScript("arguments[0].click();", manageTrainingProgram_Element);

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Text.createTrainingprograms, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "create training program text is not visible";
				return false;
			}

			if(UserDetails.get("Training Category")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Training Category"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.trainingCategory, UserDetails.get("Training Category"));
				//Thread.sleep(1000);
				if(!bStatus){
					Messages.errorMsg = "Training Category option is not selected from the dropdown";
					return false;
				}
			}

			if(UserDetails.get("Program Name")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Program Name"));
				bStatus=Elements.clickButton(Global.wDriver, Locators.Users.TextField.programName);
				//Thread.sleep(1000);
				//public static String program_name = UserDetails.get("Program Name")+Utilities.GetCurrentTimeStamp();

				bStatus = Elements.enterText(Global.wDriver,Locators.Users.TextField.programName, program_name);
				if(!bStatus){
					Messages.errorMsg = "program name is not entered into program name text filed";
					return false;
				}
			}

			bStatus=Elements.clickButton(Global.wDriver, Locators.Users.Buttons.Create_button);
			if(!bStatus){
				Messages.errorMsg = "Create manage training program button is not clicked";
			}
			bStatus = Wait.waitForElementVisibility(Global.wDriver, By.xpath("//a[@id='creating_training_"+program_name+"']/i[contains(@class,'fa-spin blue-text')]"), Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Create manage training program spin image is not visible in create manage training page";
				return false;
			}
			bStatus=Elements.clickButton(Global.wDriver, By.xpath("//a[@id='creating_training_"+program_name+"']/i[contains(@class,'fa-spin blue-text')]"));
			if(!bStatus){
				Messages.errorMsg = "Create manage training program spin button is not clicked";
			}
			
			//*********************manage training**************************//
			bStatus = Wait.waitForElementVisibility(Global.wDriver,Locators.Users.Text.manage_Training, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Manage Training page is not visible";
				return false;
			}


			if(UserDetails.get("Training Description")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Training Description"));
				bStatus=Elements.clickButton(Global.wDriver, Locators.Users.TextField.trainingDescription);
				Thread.sleep(1000);

				bStatus = Elements.enterText(Global.wDriver,Locators.Users.TextField.trainingDescription, training_desp);
				if(!bStatus){
					Messages.errorMsg = "Training description is not entered into Training description text filed";
					return false;
				}
			}

			bStatus = Elements.clickButton(Global.wDriver,Locators.Users.Buttons.Create_button);
			if(!bStatus){
				Messages.errorMsg = "Create manage training  button is not clicked";
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver, By.xpath("//a[@id='configure_training_"+training_desp+"']/i[contains(@class,'fa-cog fa-spin orange-text')]"), Constants.pageToLoad);
			System.out.println(By.xpath("//a[@id='configure_training_"+training_desp+"']/i[contains(@class,'fa-cog fa-spin orange-text')]"));
			if(!bStatus){
				Messages.errorMsg = training_desp +" spin button is not visible";
				return false;
			}
			bStatus = Elements.clickButton(Global.wDriver, By.xpath("//a[@id='configure_training_"+training_desp+"']/i[contains(@class,'fa-cog fa-spin orange-text')]"));
			if(!bStatus){
				Messages.errorMsg = training_desp +" spin button is not clicked";
				return false;
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Text.training, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Training of the particular user name is not visible";
				return false;
			}
			//************************Classroom************************//
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.classRoom, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "classroom tab is not visible";
				return false;
			}

			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Links.classRoom);
			if(!bStatus){
				Messages.errorMsg = "classroom tab is not clicked";
				return false;
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Text.class_room, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "classroom header text is not visible in classrooms page";
				return false;
			}


			if(UserDetails.get("Classroom Template")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Classroom Template"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.classroomTemplate, UserDetails.get("Classroom Template"));
				Thread.sleep(1000);
				if(!bStatus){
					Messages.errorMsg = "Classroom Template is not entered into Classroom Template text filed";
					return false;
				}
			} 

			if(UserDetails.get("Select Support Trainers")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select Support Trainers"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.selectSupportTrainer, UserDetails.get("Select Support Trainers"));
				Thread.sleep(1000);
				if(!bStatus){
					Messages.errorMsg = "Select Support Trainers is not entered into text filed";
					return false;
				}
			}
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Buttons.Add_classroom, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Add button is not visible in classroom page";
				return false;
			}


			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Buttons.Add_classroom);
			Thread.sleep(1000);
			if(!bStatus){
				Messages.errorMsg = "Add button is not clicked in classroom page";
				return false;
			}



			bStatus = Wait.waitForElementVisibility(Global.wDriver,By.xpath(Locators.Users.Text.trainer.replace("fieldname", UserDetails.get("Select Support Trainers"))), Constants.pageToLoad);
			Thread.sleep(1000);
			System.out.println(Locators.Users.Text.trainer.replace("fieldname", UserDetails.get("Select Support Trainers")));

			if(!bStatus){
				Messages.errorMsg = " Support trainer is not added";

				return false;
			}
			//***************Courses********************

			bStatus= Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.addCourse, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Add tab is not visible";
				return false;
			}

			WebElement courseElement = Global.wDriver.findElement(Locators.Users.Links.addCourse);
			JavascriptExecutor courseExecutor = (JavascriptExecutor)Global.wDriver;
			courseExecutor.executeScript("arguments[0].click();", courseElement);

			if(UserDetails.get("Select Course Category")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select Course Category"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.selectCourseCategory,  UserDetails.get("Select Course Category"));
				if(!bStatus){
					Messages.errorMsg = "Select Course Category drop down value is not selected";
					return false;
				}
			}
			if(UserDetails.get("Select Course Template")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select Course Template"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.selectCourse,  UserDetails.get("Select Course Template"));
				if(!bStatus){
					Messages.errorMsg = "Select Course Template drop down value is not selected";
					return false;
				}
			}
			bStatus= Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Buttons.addCourse, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Add Course button is not visible";
				return false;
			}

			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Buttons.addCourse);

			if(!bStatus){
				Messages.errorMsg = "Add button is not clicked";
				return false;
			}
			bStatus= Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.courseStatus, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Course is not created";
				return false;
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver,By.xpath("//div[@class='toast toast-success']"), Constants.waitforelement);
			if(!bStatus){
				Messages.errorMsg = "Assessments is not added successfully.";
			}

			//**************Assessments*************************
		
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.Assessments, Constants.pageToLoad);
			Thread.sleep(1000);
			if(!bStatus){
				Messages.errorMsg = "Assessments tab is not visible";
				System.out.println(Messages.errorMsg);
				return false;
			}

			WebElement element = Global.wDriver.findElement(Locators.Users.Links.Assessments);
			JavascriptExecutor executor = (JavascriptExecutor)Global.wDriver;
			executor.executeScript("arguments[0].click();", element);

			System.out.println("***************************8");
			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Text.add_assessments, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Add Assessments name is not visible";
				return false;
			}

			if(UserDetails.get("Select Assessment Template")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select Assessment Template"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.selectAssessmentTemp, UserDetails.get("Select Assessment Template"));
				Thread.sleep(1000);
				if(!bStatus){
					Messages.errorMsg = "Assessment Template option is not selected from the dropdown ";
					return false;
				}
			} 

			bStatus = Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Buttons.Add_assessment, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "Add button is not visible in assignments page";
				return false;
			}


			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Buttons.Add_assessment);
			if(!bStatus){
				Messages.errorMsg = "Add button is not clicked in assignments page";
				return false;
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver,By.xpath("//div[@class='toast toast-success']"), Constants.waitforelement);
			if(!bStatus){
				Messages.errorMsg = "Assessments is not added successfully.";
			}

			//***********ListBox*************

			bStatus= Wait.waitForElementVisibility(Global.wDriver, Locators.Users.Links.listBox, Constants.pageToLoad);
			if(!bStatus){
				Messages.errorMsg = "List box tab is not visible";
				return false;
			}

			WebElement ListBoxElement = Global.wDriver.findElement(Locators.Users.Links.listBox);
			JavascriptExecutor ListBoxExecutor = (JavascriptExecutor)Global.wDriver;
			ListBoxExecutor.executeScript("arguments[0].click();", ListBoxElement);


			if(UserDetails.get("Select List Box")!=null){
				System.out.println("Enter:*******"+UserDetails.get("Select List Box"));
				bStatus = Elements.selectOptionByVisibleText(Global.wDriver, Locators.Users.Dropdown.selectListBox,  UserDetails.get("Select List Box"));
				if(!bStatus){
					Messages.errorMsg = "Select List Box drop down value is not selected";
					System.out.println(Messages.errorMsg);
					return false;
				}
			}
			
			bStatus = Elements.clickButton(Global.wDriver, Locators.Users.Buttons.addListBox);
			if(!bStatus){
				Messages.errorMsg = "add button is not clicked in List box page";
				return false;
			}

			bStatus = Wait.waitForElementVisibility(Global.wDriver,By.xpath("//div[@class='toast toast-success']"), Constants.waitforelement);
			if(!bStatus){
				Messages.errorMsg = "List Box is not added successfully.";

				//System.out.println(Messages.errorMsg);
			}

		}catch(Exception e){

			e.printStackTrace();
		}
		return bStatus;
	}
}