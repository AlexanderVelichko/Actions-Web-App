package com.ovel;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppTest {
	
	private FirefoxDriver driver;
	private String user = "test.actions.web.app";
	private String password = "actionswebapp";
	public Page page;

	@BeforeMethod
	public void setUp(){
		String baseURL = "https://actions-web-app.appspot.com";
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseURL);		
	}
		
	@Test
	public void testApp(){
		page = new Page(driver);
		page.login(user, password);
		page.allow();
		String expectedName="ACTIONS-WEB-APP";

		AssertJUnit.assertTrue(page.getAppName().equals(expectedName));

		page.deleteAllCustomFolders(); //to get rid of any custom folders if there are any
		page.getFirstFolder(); //needed as Add button will disappear after folder(s) deletion
		page.createFolder();
		page.createChildFolder();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		page.deleteNewChildFolder();
		page.deleteNewFolder();
		
		AssertJUnit.assertEquals(3, page.checkIfCustomFoldersExist());		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();		
	}
	
}
