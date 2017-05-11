package com.planit.aviva.locators;

import org.openqa.selenium.By;

public class Locators {
	
	public static class Loginpage{
		public static class TextField{
			//input[@id = 'LoginForm_login_id']
			//public static By UserName = By.xpath("//input[@name = 'LoginForm[login_id]']");
			public static By UserName = By.xpath("//input[@id = 'LoginForm_login_id']");

			public static By Password = By.xpath("//input[@name = 'LoginForm[password]']");
		}
		public static class Link{
			public static By SignIn = By.xpath("//button[@class='submitLink hoverunderline']//h5[text()='Sign In']");
		}
		public static class text{
			public static By loginText = By.xpath("//div[@class='content']/h4[text()='My Learning Plan']");
		}
	}
	
	public static class Logout{
		public static class imagedropdown{
			public static By logout_dropdown = By.xpath("//i[@class='mdi-navigation-expand-more right']");
		}
		public static class link{
			public static By logout = By.xpath("//i[@class='fa fa-sign-out' and contains(text(),Logout)]");
		}
		//i[@class='fa fa-sign-out' and contains(text(),Logout)]
	}
	
	public static class Users{
		public static class Sidenavigation{
			public static By Sidebar = By.xpath("//div[@class='nano']/div[@class='nano-content']");
		}
		public static class Links{
			public static By Navbar = By.xpath("//a[@id='navbarTitle']");
			public static By User = By.xpath("//a[@id='user']/i[@class='fa fa-user-plus' and contains(text(),User)]");
			public static By Manageuser = By.xpath("//a[@id='createuser']/i[@class='fa fa-user-plus'][contains(text(),Manage)]");
			public static By ArrowUp = By.xpath("//i[@class='fa fa-arrow-up']");
			public static By manageClassroom = By.xpath("//a[@id='user' and text()=' Manage Classroom']/i[@class='fa fa-book']");
			public static By manageClassROomTemplate = By.xpath("//a[@id='createassessmenttemplate' and contains(text(),'Manage Classroom Template')]");
			public static By manageTrainingProgram = By.xpath("//a[text()='Manage Training Programs']");
			public static By manageTraing = By.xpath("//a[text()=' Manage Training']");
			public static By classRoom = By.xpath("//a[@id='classroomTabLink']");
			public static By Assessments = By.xpath("//div[@id='trainingObject']/ul/li/a[@id='assessmentTabLink' and @title='Assessment' and contains(text(),'Assessment')]");
			public static By manageTraining = By.xpath("//a[@id='course' and contains(text(),'Manage Training')]");
			public static By manageTrainingCategory = By.xpath("//a[@class='waves-effect waves-blue' and contains(text(),'Manage Training Category')]");
			public static By addCourse = By.xpath("//a[@id='courseTabLink']");
			public static By courseStatus = By.xpath("//div[@id='toast-container']/div");
			public static By listBox = By.xpath("//a[@id='listboxTabLink']");
		}
		public static class Text{
			public static By Createuser = By.xpath("//h4[text()='Create User']");
			public static By Totalusers = By.xpath("//h4[contains(text(),'Total User :')]");
			public static By emailIdMustBeUnique = By.xpath("//div[text()='Email Id must be unique']");
			public static By createClassROomTemplate = By.xpath("//h4[text()='Create Classroom Template']");
			
			public static By createTrainingprograms = By.xpath("//h4[text()='Create Training Programs']");
			public static By manage_Training = By.xpath("//h4[text()='Manage Training']");
			public static By training = By.xpath("//h4[contains(text(),'Training :')]");
			public static By class_room = By.xpath("//h4[contains(text(),'Classroom/ILT')]");
			public static By add_assessments = By.xpath("//h4[contains(text(),'Assessment')]");
			public static String trainer = "//a[contains(@id,'supportTrainer_') and contains(text(),'fieldname')]";
		}
		public static class TextField{
			public static By Fullname = By.xpath("//input[@name = 'inFullName']");
			public static By TextField_Normal = By.xpath("//h4[text()='Create User']/ancestor::div//label[contains(.,'fieldname')]/ancestor::div[contains(@class,'input-field')]/input");
			public static String TextField_Normal_str = "//h4[text()='Create User']/ancestor::div//label[contains(.,'fieldname')]/ancestor::div[contains(@class,'input-field')]/input";
			public static By DateFiled = By.xpath("//h4[text()='Create User']/ancestor::div//*[contains(.,'fieldname')]/ancestor::div[contains(@class,'input-field') or contains(@class,'col l6 s12')]/child::div/input");
			public static String DateFiled_str = "//h4[text()='Create User']/ancestor::div//*[contains(.,'fieldname')]/ancestor::div[contains(@class,'input-field') or contains(@class,'col l6 s12')]/child::div/input";
			public static By Email=By.xpath("//input[@name='inLoginId']");
			public static By emp_code = By.xpath("//input[@name='emp_code']");
			public static By searchField = By.xpath("//input[@placeholder='Search by Classroom template']");
			public static By programName = By.xpath("//input[@id='inName']");
			public static String programName_str = "//input[@id='inName']/preceding::label[text()='fieldname']";
			public static By trainingDescription = By.xpath("//input[@id='inDescription']");
			public static String txtcategoryName = "//input[@name='inName']";
			public static By categoryName=By.xpath("//div[@class='input-field']"); 
			
		}
		public static class Dropdown{
			//public static By Dropdown = By.xpath("//h4[text()='Create User']/ancestor::div//*[contains(.,'fieldname')]/ancestor::div/div[contains(@class,'input-')]/select");
			//public static String Dropdown = "//h4[text()='Create User']/ancestor::div//*[contains(.,'fieldname')]/ancestor::div/div[contains(@class,'input-')]/select";
			public static String Dropdown = "//h4[text()='Create User']/ancestor::div//h5[contains(.,'fieldname')]/ancestor::div/div[contains(@class,'input-')]/select";
			public static By selectRoleDropdown = By.xpath("//h4[text()='Create User']/ancestor::div//*[contains(.,'Role')]/ancestor::div/div[contains(@class,'input-')]/select");
			public static By selectdropdownValue = By.xpath("//h4[text()='Create User']/ancestor::div//*[contains(.,'')]/ancestor::div/div[contains(@class,'input-')]/select/option[contains(text(),'employee')]");
			
			public static String trainingCategory = "//select[@id='selectBranch']";
			//public static By trainingcategory_value = By.xpath("//select[@id='selectBranch']//option[contains(.,'Common-Category')]");
			public static String classroomTemplate = "//select[@name='selectClassroomTemplate']";
			public static String selectSupportTrainer = "//select[@name='selectSupportTrainers']";
			public static String selectAssessmentTemp = "//select[@name='selectAssessment']";
			public static String selectParentCategory = "//div[@class='content']//div[@class='input-field']/select";
			public static By selectParentCategoryText = By.xpath("//select[@id='selectParentCategory']/option[contains(text(),'Common-Category')]");
			public static String selectCourseCategory = "//div[@class='content']//div[@class='input-field']//select[@name='selectImage']";
			public static String selectCourse = "//div[@class='content']//select[@name='selectCourse']";
			public static String selectListBox = "//div[@class='content']//select[@name='selectListbox']";
			
		}
		public static class Buttons{
			
			public static By showFilters = By.xpath("//a[text()='Show Filters']");
			public static By search = By.xpath("//button[text()='Search']");
			public static By Create_button = By.xpath("//button[contains(text(),'Create')]");
			
			//public static By createTrainingProgram = By.xpath("//button[@id='createTrainingProgram']");
			//public static By trainingCreateEdit = By.xpath("//button[@id='trainingCreateEdit']");
			public static By Add_classroom = By.xpath("//button[contains(text(),'Add') and @ng-click='main.addSupportTrainer()']");
			public static By Add_assessment = By.xpath("//button[@id='assessmentObjectAdd']");
			public static By createTrainingCategory = By.xpath("//button[@id='createTrainingCategory' and contains(text(),'Create')]");
			public static By addCourse = By.xpath("//button[@id='courseObjectAdd' and contains(text(),'Add')]");
			public static By addListBox = By.xpath("//button[@id='listboxObjectAdd']");
			
		}
		public static class Tables{
			public static By UserTable = By.xpath("//div[@class='card-panel']//table[@class='bordered responsive-table highlight table-hover']");
			public static By Table_FullName = By.xpath("//table[contains(@class,'bordered responsive-table highlight table-hover')]//tr[1]/td[2]/a");
		}
	}
	
	//textfield
	//h4[text()='Create User']/ancestor::div//label[contains(.,'')]/ancestor::div[contains(@class,'input-field')]
	//h4[text()='Create User']/ancestor::div//label[contains(.,'PAN')]/ancestor::div[contains(@class,'input-field')]
	//h4[text()='Create User']/ancestor::div//label[contains(.,'PAN')]/ancestor::div[contains(@class,'input-field')]/input
	
	//date
	//h4[text()='Create User']/ancestor::div//*[contains(.,'')]/ancestor::div[contains(@class,'input-field') or contains(@class,'col l6 s12')]/child::div/input
	
	//dropdown
	 //h4[text()='Create User']/ancestor::div//*[contains(.,'')]/ancestor::div/div[@class='input-label']/select****
	
	 //h4[text()='Create User']/ancestor::div//*[contains(.,'')]/ancestor::div/div[contains(@class,'input-')]/select
	 //h4[text()='Create User']/ancestor::div//*[contains(.,'Aviva Employee')]/ancestor::div/div[contains(@class,'input-')]/select
	
	
	//h4[text()='Create User']/ancestor::div//*[contains(.,'')]/ancestor::div[contains(@class,'input-field') or contains(@class,'col l6 s12') or @class]/child::div/input
	
	//div[text()='Please Select Role']
	//div[@class='toast toast-error']
	//div[@class='toast toast-success']
	
}
