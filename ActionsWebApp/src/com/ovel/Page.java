package com.ovel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

	public class Page {
		
		private WebDriver driver;

		public Page(FirefoxDriver driver) {
			this.driver = driver;
		}

		public Page(String email, String password) {
			// TODO Auto-generated constructor stub
		}

		public void login(String email, String password) {
			driver.findElement(By.id(UIData.EmailFieldId)).sendKeys(email);
			driver.findElement(By.id(UIData.PasswordFieldId)).sendKeys(password);
			driver.findElement(By.id(UIData.SignInButtonId)).click();
		}	
	
		public void allow() {
			driver.findElement(By.id(UIData.AllowButtonId)).click();	
		}	
	
		public void createFolder() {
			driver.findElement(By.xpath(UIData.AddButtonXpath)).click();			
			
		}	
	
		public void createChildFolder() {
			driver.findElement(By.xpath(UIData.AddChildButtonXpath)).click();			
		}	

		public void deleteNewFolder() {
			getNewFolder();
			driver.findElement(By.xpath(UIData.EditButtonXpath)).click();
			driver.findElement(By.id(UIData.DeleteButtonId)).click();
			driver.findElement(By.xpath(UIData.ConfirmDeleteButtonXpath)).click();
			driver.findElement(By.xpath(UIData.ConfirmDeleteButtonXpath)).click();
		}
		
		public void deleteNewChildFolder() {
			getNewChildFolder();
			driver.findElement(By.xpath(UIData.EditButtonXpath)).click();
			driver.findElement(By.id(UIData.DeleteButtonId)).click();
			driver.findElement(By.xpath(UIData.ConfirmDeleteButtonXpath)).click();
			driver.findElement(By.xpath(UIData.ConfirmDeleteButtonXpath)).click();
		}	
		
		public void getNewFolder() {
			List<WebElement> li = driver.findElements(By.xpath(UIData.NewFolder));
			li.get(1).click();			
		}	
		
		public void getNewChildFolder() {
			List<WebElement> li = driver.findElements(By.xpath(UIData.NewFolder));
			li.get(2).click();
		}
		
		public int checkIfCustomFoldersExist(){
			List<WebElement> li = driver.findElements(By.xpath(UIData.NewFolder));
			return li.size();
		}
		
		public void deleteAllCustomFolders(){
			while(checkIfCustomFoldersExist()>3){
				deleteNewFolder();					
			}
		}
		
		public void getFirstFolder() {
			List<WebElement> li = driver.findElements(By.xpath(UIData.NewFolder));
			li.get(0).click();			
		}	
		
		public String getAppName() {
			String appName = driver.findElement(By.xpath(UIData.AppNameXpath)).getText();
			return appName;
		}
			
}
