package com.coforge.ddttest;


import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.coforge.utility.Helper;

import au.com.bytecode.opencsv.CSVReader;

public class DDTTest {
	WebDriver driver;
	String baseUrl = "https://only-testing-blog.blogspot.com/2014/05/form.html";
	
	@BeforeTest
	public void setUp() {
		driver = Helper.startBrowser("Chrome");
		driver.navigate().to(baseUrl);
		driver.manage().window().maximize();
	}
	
	@Test
	public void BrowserTest() throws Exception {
		String csvPath = "C:\\Users\\hp\\eclipse-workspace\\SeleniumWebDriver-DataDrivenTesting\\CSVFileReading\\csvfile.csv";
		CSVReader reader = new CSVReader(new FileReader(csvPath));
		String[] csvCell;
		while((csvCell = reader.readNext()) != null) {
			
			String data1 = csvCell[0];
			String data2 = csvCell[1];
			String data3 = csvCell[2];
			String data4 = csvCell[3];
			String data5 = csvCell[4];
			
			driver.findElement(By.name("FirstName")).sendKeys(data1);
			driver.findElement(By.name("LastName")).sendKeys(data2);
			driver.findElement(By.name("EmailID")).sendKeys(data3);
			driver.findElement(By.name("MobNo")).sendKeys(data4);
			driver.findElement(By.name("Company")).sendKeys(data5);
			
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
			driver.switchTo().alert().accept();
		}
		reader.close();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}