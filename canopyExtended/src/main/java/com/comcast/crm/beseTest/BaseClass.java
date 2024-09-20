package com.comcast.crm.beseTest;
/**
 * This class is created to configure all our pre and post condition to for all script
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;
import com.comcast.crm.generic.webDriverUtility.WebDriverUtility_Mine;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class BaseClass extends WebDriverUtility_Mine {
	public  WebDriver driver = null;
	public static WebDriver sdriver = null;
    //public LogStatus log=new LogStatus();
	public DataBaseUtility dlib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility_Mine wlib = new WebDriverUtility_Mine();
/**
 * This configuration store the connect to database code
 * @throws Exception
 */
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBs() throws Exception {
		dlib.getDbConnection();
		System.out.println("--Connect to DB, Report config--");
	}
	/**
	 * This configuration store the launching browser code
	 * @throws Exception
	 */
	
	 //For parallel execution we have to uncomment the parameter related things
//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC(/*String browser*/) throws Exception {

		String BROWSER = System.getProperty("browser",flib.getDataFromProperties("browser"));
/*Used for parallel Execution */	//browser;
/*Get DataFromProperties */ //flib.getDataFromProperties("browser");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		System.out.println("Launch the browser");
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	/**
	 * This configuration store login to the valid credential code
	 * @throws Exception
	 */
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Exception {
		LoginPage lp = new LoginPage(driver);
	/*	String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
		String PASSWORD = flib.getDataFromProperties("password");*/
		String URL = System.getProperty("url",flib.getDataFromProperties("url"));
		String USERNAME = System.getProperty("username",flib.getDataFromProperties("username"));
		String PASSWORD =System.getProperty("password",flib.getDataFromProperties("password"));
		waitForPageLoad(driver);
		driver.manage().window().maximize();
		driver.get(URL);
		lp.loginOperation(USERNAME, PASSWORD);
		System.out.println("--Login--");
	}
	/**
	 * This configuration store logout code
	 * @throws Exception
	 */
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		HomePage hp = new HomePage(driver);
		hp.logoutOperation();
		System.out.println("--Logout--");
	}
	/**
	 * This configuration store close browser code
	 * @throws Exception
	 */
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() {
		driver.quit();
		System.out.println("--Close the Browser--");
	}
	/**
	 * This configuration store close from the database code
	 * @throws Exception
	 */
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws Exception {
		//dlib.closeDbconnection();
		System.out.println("--Close DB,Report backUP--");
	}
}
