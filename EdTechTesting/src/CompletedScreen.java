import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompletedScreen {
	
	static String Username;
	static String InstituteId;
	static String Password;
	static WebElement elemUserName;
	static WebElement elemInstituteId;
	static WebElement elemPassword;
	static WebElement login, tabClick, buttonClick;
	public static WebDriver driver;
	
	@Test (description="User login", priority=1)
	public void TestLogin() throws InterruptedException{

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
		
		Thread.sleep(5000);

		String title = driver.getTitle();
		String expectedtitle = "EddTech";

		Assert.assertEquals(title, expectedtitle);

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=not-started");

		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[3]"));
		tabClick.click();
		URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=completed");

		// driver.close();

	}
	
	//Test result button navigation
	@Test (description = "Tests see results button click navigated to the result sceen", priority=2)
	public void TestSeeResultButtonClick() {

		//Click on completed tab
		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[3]"));
		tabClick.click();

		//Click on see results button
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div/a/button/span[1]"));
		buttonClick.click();

		//Check user has navigated to result screen
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/result/759/332876");
		
		//Click on title to navigate to home screen
		buttonClick = driver.findElement(By.xpath("//*[@id=\"header\"]/nav/div[1]/a/h1"));
		buttonClick.click();
		
		//Check user navigated to home screen
		URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test");
	}
	
	//Test result button navigation
		@Test (description = "Tests see results button click navigated to the result sceen", priority=3)
		public void TestQuePapaerButtonClick() {

			//Click on completed tab
			tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[3]"));
			tabClick.click();

			//Click on see paper button
			buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div/button/span[1]"));
			buttonClick.click();

			//Check user has navigated to question papaer screen
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "http://135.181.27.5:3000/question-paper/759");
			
			//Click on title to navigate to home screen
			buttonClick = driver.findElement(By.xpath("//*[@id=\"header\"]/nav/div[1]/a/h1"));
			buttonClick.click();
			
			//Check user navigated to home screen
			URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "http://135.181.27.5:3000/test");
		}

}
