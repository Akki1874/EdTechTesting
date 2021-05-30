import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InprogressScreen {
	
	static String Username;
	static String InstituteId;
	static String Password;
	static WebElement elemUserName;
	static WebElement elemInstituteId;
	static WebElement elemPassword;
	static WebElement login, tabClick, buttonClick;
	public static WebDriver driver;
	
	
	@Test
	public void alogin() {

		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://135.181.27.5:3000/");

		elemUserName = driver.findElement(By.name("userName"));
		elemInstituteId = driver.findElement(By.name("instituteId"));
		elemPassword = driver.findElement(By.name("password"));

		// clear();

		elemUserName.sendKeys("9999999999");
		elemInstituteId.sendKeys("1");
		elemPassword.sendKeys("1234");

		Username = elemUserName.getAttribute("value");
		InstituteId = elemInstituteId.getAttribute("value");
		Password = elemPassword.getAttribute("value");

		login = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
		login.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String title = driver.getTitle();
		String expectedtitle = "EddTech";

		Assert.assertEquals(title, expectedtitle);

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=not-started");

		// driver.close();

	}
	
	@Test (description = "Tests progress tab resume button click")
	public void TestResumeButtonClick() {
		
		// Navigate to progress tab
		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[2]/span[1]"));
		tabClick.click();
		
		// Click resume button
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div/a/button"));
		buttonClick.click();
		
		// Test user is on test screen
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test/564");
	}

}
