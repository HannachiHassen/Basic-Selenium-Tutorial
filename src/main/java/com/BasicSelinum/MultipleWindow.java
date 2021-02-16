package com.BasicSelinum;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultipleWindow {
	public static void main(String[] args) throws InterruptedException {
		String driverPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", driverPath + "\\Drivers\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();
		driver.get("https://demoqa.com/browser-windows");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement clickElement = driver.findElement(By.id("windowButton"));
		for (int i = 0; i < 3; i++) {
			clickElement.click();
			Thread.sleep(3000);
		}

		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window's handle-> " + parentWindowHandle);

		String lastWindowHandle = "";

		// Handle Windows
		Set<String> allWindowsHandles = driver.getWindowHandles();

		for (String handle : allWindowsHandles) {
			System.out.println("Window handle -> " + handle);

			System.out.println("Switching to window - > " + handle);
			System.out.println("Navigating to google.com");
			driver.switchTo().window(handle); // Switch to the desired window first and then execute commands using driver
			driver.get("https://www.google.com");
			lastWindowHandle = handle;
		}
		// Switch to the parent window
		driver.switchTo().window(parentWindowHandle);
		// close the parent window
		driver.close();
		// at this point there is no focused window, we have to explicitly switch back to some window.
		driver.switchTo().window(lastWindowHandle);
		driver.get("https://toolsqa.com");
	}
}
