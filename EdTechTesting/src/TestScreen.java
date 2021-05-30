import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScreen {

	static String Username;
	static String InstituteId;
	static String Password;
	static WebElement elemUserName;
	static WebElement elemInstituteId;
	static WebElement elemPassword;
	static WebElement login, tabClick, buttonClick;
	public static WebDriver driver;


	@Test (description="User login", priority=1)
	public void TestLogin() {

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

		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[2]/span[1]"));
		tabClick.click();
		URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test?status=in-progress");

		// driver.close();

	}

	@Test (description = "Tests progress tab click navigated to the progress tests sceen", priority=2)
	public void TestProgressTabClick() {

		tabClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/button[2]/span[1]"));
		tabClick.click();

		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div/a/button"));
		buttonClick.click();

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://135.181.27.5:3000/test/564");
	}

	@Test (description = "Test next button click navigates to the next question", priority=3)
	public void TestNextButton() {

		WebElement abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
		String queNum = abuttonClick.getText();
		Assert.assertEquals(queNum, "1");
		//System.out.println("major "+a);

		for(int i=2;i<10;i++) {

			buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[1]/button[2]"));
			buttonClick.click();

			WebElement next = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
			String queNums = next.getText();
			int queSs = Integer.parseInt(queNums);
			Assert.assertEquals(queSs, i);
			//System.out.println("major "+queNum1);
		}


	}

	@Test (description = "Test previous button click navigates to the previous question",priority=4)
	public void TestPreviousButton() {

		for(int i=8;i>2;i--) {

			buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[1]/button[1]"));
			buttonClick.click();

			WebElement previous = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
			String queNums = previous.getText();
			int queSs = Integer.parseInt(queNums);
			Assert.assertEquals(queSs, i);
			//System.out.println("major "+queNum1);
		}
	}

	//Test all the radio buttons are clicked
	@Test (description = "Test all radio button clicked",priority=5)
	public void TestAllRadioButtonClicked() {

		//Radio button option A is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[1]/span[1]/span[1]/input"));
		buttonClick.click();

		//Test radio button option A is checked
		String str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[1]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, "true");

		//Radio button option B is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[2]/span[1]/span[1]/input"));
		buttonClick.click();

		//Test radio button option B is checked
		str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[2]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, "true");

		//Radio button option C is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[3]/span[1]/span[1]/input"));
		buttonClick.click();

		//Test radio button option C is checked
		str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[3]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, "true");

		//Radio button option D is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[4]/span[1]/span[1]/input"));
		buttonClick.click();

		//Test radio button option D is checked
		str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[4]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, "true");





	}

	//Test all the radio buttons are cleared and buttons are disabled on  clear response
	@Test (description = "Test all the radio buttons are cleared and buttons are disabled on  clear response",priority=6)
	public void TestClearResponseButtonClicked() {

		//Radio button option A is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[3]"));
		buttonClick.click();

		//Test radio button option A is cleared
		String str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[1]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, null);

		//Test radio button option B is cleared
		str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[2]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, null);

		//Test radio button option C is cleared
		str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[3]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, null);

		//Test radio button option D is cleared
		str = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[4]/span[1]/span[1]/input")).getAttribute("checked");
		Assert.assertEquals(str, null);

		//Test Save and Next button is disabled
		boolean strr = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[1]")).isEnabled();
		Assert.assertEquals(strr, false);

		//Test Save and marked for review button is disabled
		strr = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[2]")).isEnabled();
		Assert.assertEquals(strr, false);

		//Test Clear response button is disabled
		strr = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[3]")).isEnabled();
		Assert.assertEquals(strr, false);

	}

	// Test question button click navigates to correct question
	@Test (description = "Test question button click navigates to correct question", priority=7)
	public void TestQuestionButtonClicked() {

		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[10]/div/div"));
		buttonClick.click();

		WebElement abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
		String queNum = abuttonClick.getText();
		Assert.assertEquals(queNum, "10");
	}

	// Test Save and next button clicked
	@Test (description = "Test Save and next button navigates to next question", priority=8)
	public void TestSaveAndNextButtonClicked() {

		//Get question number
		WebElement abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
		String currentQueNoS = abuttonClick.getText();
		int currentQueNoI = Integer. parseInt(currentQueNoS);
		currentQueNoI++;
		String currentQueNoSS = String.valueOf(currentQueNoI);
		System.out.println("Current que no : "+currentQueNoSS);

		//Radio button option A is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[1]/span[1]/span[1]/input"));
		buttonClick.click();

		// Save and next button clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[1]"));
		buttonClick.click();

		//Get question number
		abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
		String nextQueNo = abuttonClick.getText();
		System.out.println("Next que no : "+nextQueNo);

		Assert.assertEquals(currentQueNoSS, nextQueNo);
	}		

	// Test Save and marked for review button clicked
	@Test (description = "Test Save and mark for review button navigates to next question", priority=9)
	public void TestSaveAndMarkForReviewButtonClicked() {

		//Get question number
		WebElement abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
		String currentQueNoS = abuttonClick.getText();
		int currentQueNoI = Integer. parseInt(currentQueNoS);
		currentQueNoI++;
		String currentQueNoSS = String.valueOf(currentQueNoI);
		System.out.println("Current que no : "+currentQueNoSS);

		//Radio button option A is been clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[2]/fieldset/div/label[1]/span[1]/span[1]/input"));
		buttonClick.click();

		// Save and mark for review button clicked
		buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[2]"));
		buttonClick.click();

		//Get question number
		abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
		String nextQueNo = abuttonClick.getText();
		System.out.println("Next que no : "+nextQueNo);

		Assert.assertEquals(currentQueNoSS, nextQueNo);
	}		

	// Test Marked for review and next button clicked
		@Test (description = "Test Save and mark for review button navigates to next question", priority=9)
		public void TestMarkForReviewAndNextButtonClicked() {

			//Get question number
			WebElement abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
			String currentQueNoS = abuttonClick.getText();
			int currentQueNoI = Integer. parseInt(currentQueNoS);
			currentQueNoI++;
			String currentQueNoSS = String.valueOf(currentQueNoI);
			System.out.println("Current que no : "+currentQueNoSS);

			// Mark for review and next button clicked
			buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/button[4]"));
			buttonClick.click();

			//Get question number
			abuttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div/div[1]/div"));
			String nextQueNo = abuttonClick.getText();
			System.out.println("Next que no : "+nextQueNo);

			Assert.assertEquals(currentQueNoSS, nextQueNo);
		}		
		
		// Test Submit test button clicked
		@Test (description = "Test Submit test button", priority=9)
		public void TestSubmitTestButtonClicked() throws InterruptedException {

			// Mark for review and next button clicked
			buttonClick = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/button"));
			buttonClick.click();
			
			//Submit alert pop up displayed
			boolean strr = driver.findElement(By.xpath("/html/body/div[3]/div/div")).isDisplayed();
			Assert.assertEquals(strr, true);
			
			//Pop up No button click
			buttonClick = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]"));
			buttonClick.click();
			
			//Pop up cancelled redirects to same screen
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "http://135.181.27.5:3000/test/564");
			
			//Pop up yes button click
			/*
			 * buttonClick =
			 * driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"));
			 * buttonClick.click();
			 */

		
		}		

		
}
