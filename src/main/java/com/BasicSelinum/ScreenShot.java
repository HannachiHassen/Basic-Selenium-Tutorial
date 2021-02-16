package com.BasicSelinum;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenShot {

	public static void main(String[] args) throws Exception{
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\HASSEN\\workspace\\BasicSelinum_Tutorail_Suite\\Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com");
		
		takescreenshot (driver, "C:\\Users\\HASSEN\\workspace\\BasicSelinum_Tutorail_Suite\\Pass\\");
		driver.quit();
	}

	public static void takescreenshot(WebDriver driver, String filepath) throws Exception{
		Date d=new Date();
		
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile=new File(filepath+d.toString().replace(":", "_") +".png");
		FileUtils.copyFile(scrFile, destFile);
	}
	
	
}
