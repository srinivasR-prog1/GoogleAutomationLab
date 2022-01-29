package com.docker.pageObjects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) throws MalformedURLException {
		
	//	String host = "localhost";
		String host = "selenium-hub";

		DesiredCapabilities dc = new DesiredCapabilities();

		if (browserName.equalsIgnoreCase("chrome")) {

			dc.setBrowserName(BrowserType.CHROME);

		} else if (browserName.equalsIgnoreCase("firefox")) {

			dc.setBrowserName(BrowserType.FIREFOX);

		}
		
		/*if(browserName.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			
		}*/
		
		String completeURL = "http://" + host + ":4444/wd/hub";
		driver = new RemoteWebDriver(new URL(completeURL), dc);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
