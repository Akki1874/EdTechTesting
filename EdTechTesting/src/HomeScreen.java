import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeScreen {

	static String Username;
	static String InstituteId;
	static String Password;
	static WebElement elemUserName;
	static WebElement elemInstituteId;
	static WebElement elemPassword;
	static WebElement login, tabClick, buttonClick;
	public static WebDriver driver;

	/*
	 * public static void main(String[] args) {
	 * 
	 * HomeScreen data = new HomeScreen(); //data.setData("9999999999", "1",
	 * "1234"); driver = data.login(); System.out.println(driver);
	 * 
	 * }
	 * 
	 */
	
	@Test (description="User login", priority=1)
	public void UserLogin() {

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

	//Test click on not started tab navigates to not started screen tests
	@Test (description = "Tests not started tab", priority=2)
	public void TestNotStartedTab() {

		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[1]/span[1]"));
		tabClick.click();
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=not-started");

	}

	//Test click on progress tab navigates to progress screen tests
	@Test (description = "Tests progress tab", priority=3)
	public void TestProgressTab() {

		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[2]/span[1]"));
		tabClick.click();
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=in-progress");
		
	}

	//Test click on completed tab navigates to completed screen tests
	@Test (description = "Tests completed tab", priority=4)
	public void TestCompletedTab() {

		// Click on completed tab
		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[3]/span[1]"));
		tabClick.click();
		
		// Check user is on the completed tab screen
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=completed");

	}
	
	//Test navigation to profile screen from home screen
		@Test (description = "Tests profile screen navigation", priority=5)
		public void TestProfileScreenNav() {

			// Click on profile icon
			buttonClick = driver.findElement(By.xpath("//*[@id=\"profile-icon\"]"));
			buttonClick.click();
			
			// Click on profile icon
			buttonClick = driver.findElement(By.xpath("//*[@id=\"header\"]/nav/div[2]/div/div/div/a"));
			buttonClick.click();
			
			// Test user is on the profile screen
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "http://135.181.27.5:3000/student-profile");
			
			// Click on title and navigate to home screen
			buttonClick = driver.findElement(By.xpath("//*[@id=\"header\"]/nav/div[1]/a/h1"));
			buttonClick.click();
			
			// Test user is on the home screen
			URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "http://135.181.27.5:3000/test");
			
		}
	
}
