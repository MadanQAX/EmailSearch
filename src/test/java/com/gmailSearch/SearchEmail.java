package com.gmailSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchEmail {
	
WebDriver driver = null;
	
	@BeforeTest
	public void Login() {
		System.setProperty("webdriver.chrome.driver","C:/Users/91995/Desktop/QAX/SeleniumTest/gmailEmail/gmailSearch/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		String url = "https://mail.google.com/mail";
        driver.get(url);
        driver.manage().window().maximize();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='identifierId']"))).sendKeys("testkovan11@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys("Kovan@1234");
        driver.findElement(By.id("passwordNext")).click();
        System.out.println("Logged into Gmail");
	}

	@Test
	public void EmailSearch() {
		System.out.println("Title of Page: " + driver.getTitle());
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Primary')]"))).click();       
        WebElement emails = driver.findElement(By.xpath("//span[@class='Dj']/span[2]"));
        String Emails = emails.getText();
        System.out.println("No.of Emails: " + Emails);
        this.getSenderSubject(3);
	}
	
	public void getSenderSubject(int number) {
		System.out.println("getSenderSubjectMethod");
		String SenderNameXpath = "//table[@id=':23']//tbody/tr[" + number + "]/td[4]/div[2]/span";
		WebElement name = driver.findElement(By.xpath(SenderNameXpath));
		String senderName = name.getText();
		System.out.println("Name of the Sender: " + senderName);
		String SenderSubjectXpath = "//table[@id=':23']//tbody/tr[" + number + "]//td[5]//span";
		WebElement Subject = driver.findElement(By.xpath(SenderSubjectXpath));
		String emailSubject = Subject.getText();
		System.out.println("Email Subject: " + emailSubject);
	}
	
	@AfterTest
	public void Logout() {
		driver.close();
	}
	
}

