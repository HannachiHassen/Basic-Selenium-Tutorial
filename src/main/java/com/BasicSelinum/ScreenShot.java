package com.BasicSelinum;

import java.io.File;
import java.text.SimpleDateFormat;
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
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destFile=new File(filepath+timeStamp.toString().replace(":", "_") +".png");
		FileUtils.copyFile(source, destFile);
		
		System.out.println("**********************************");
		System.out.println("ScreenShot stored at: " + filepath);
		System.out.println("**********************************");
	}
}
