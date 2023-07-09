package lib;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLink {

	private static WebDriver driver = null;
	
	public static void main(String[] args) {
		myBrokenLinks();
	}

	public static void myBrokenLinks() {

		String homePage = "https://www.google.com";
		String url = "";
		HttpURLConnection huc = null;
		int responseCode = 200;
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(homePage);

		/// Storing the links in a list and traversing through the links
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// This line will print the number of links and the count of links.
		System.out.println("Total number of links on the Page are " + links.size());

		Iterator<WebElement> it = links.iterator();

		// checking the links fetched.
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			System.out.println("URL of the Link is : " + url);
			if (url == null || url.isEmpty()) {
				System.out.println("Empty URL or an Unconfigured URL");
				continue;
			}
			if (!url.startsWith(homePage)) {
				System.out.println("This URL is from another domain.");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.setConnectTimeout(5000);
				huc.connect();
				responseCode = huc.getResponseCode();
				if (responseCode >= 400) {
					System.out.println(url + " HTTP STATUS - " + huc.getResponseMessage() + " is broken link");
				} else {
					System.out.println("HTTP STATUS - " + huc.getResponseMessage() + " ** Valid link ** ");
				}
			} catch (MalformedURLException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		driver.quit();
	}
}
