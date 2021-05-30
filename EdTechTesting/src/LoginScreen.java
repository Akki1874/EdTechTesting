import java.util.ArrayList;
import java.util.concurrent.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginScreen {

	static ArrayList<String> username = new ArrayList<String>();
	static ArrayList<String> instituteid = new ArrayList<String>();
	static ArrayList<String> password = new ArrayList<String>();
	static String Username;
	static String InstituteId;
	static String Password;
	static WebElement elemUserName;
	static WebElement elemInstituteId;
	static WebElement elemPassword;
	static WebElement login;
	WebDriver driver;

	@Test
	public void login() {
		username.add("999999999900");
		username.add("9999999");
		username.add("9999999999");
		
		instituteid.add("1");
		instituteid.add("2");
		instituteid.add("1");
		
		password.add("123");
		password.add("12345");
		password.add("1234");

		System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://135.181.27.5:3000/");
		
		elemUserName = driver.findElement(By.name("userName"));
		elemInstituteId = driver.findElement(By.name("instituteId"));
		elemPassword = driver.findElement(By.name("password"));


		for(int i=0;i<username.size();i++) {
			
			clear();
			
			elemUserName.sendKeys(username.get(i));
			elemInstituteId.sendKeys("1");
			elemPassword.sendKeys("1234");

			Username = elemUserName.getAttribute("value");
			InstituteId = elemInstituteId.getAttribute("value");
			Password = elemPassword.getAttribute("value");
			
			if(!Username.isEmpty()) {
				if(Username.matches("[0-9]+")) {

					System.out.print("\nPassed : Alphanumeric Test successfull\t"+Username ); 	

				}else {

					System.out.print("\nFailed : Alphanumeric test not successfull\t"+Username ); 

				}
			}else {
				System.out.print("\nFailed : Enter Username\t"+Username ); 	
			}

			if(!InstituteId.isEmpty()) {
				System.out.print("\nPassed : InstituteId not empty\t"+InstituteId ); 	
			}else {
				System.out.print("\nFailed : Enter InstituteId\t"+InstituteId ); 	
			}

			if(!Password.isEmpty()) {
				System.out.print("\nPassed : Password not empty\t"+Password ); 	
			}else {
				System.out.print("\nFailed : Enter Password\t"+Password ); 	
			}
			
			// check contact number length
			if(Username.length()<10) {

				System.out.print("\nFailed : Contact number is less 10 digits "+Username ); 	

			}else if(Username.length()>10){

				System.out.print("\nFailed : Contact number is greater 10 digits "+Username ); 	

			}else if(Username.length()==10){

				System.out.print("Passed : Contact number is equals to 10 digits "+Username +"\n"); 	
			}

		}

		//driver.findElement(By.name("userName")).sendKeys("9999999999");
		//driver.findElement(By.name("instituteId")).sendKeys("1");
		//driver.findElement(By.name("password")).sendKeys("1234");
		//login = driver.findElement(By.name("Login"));
		//login.click();
		
		//Thread.sleep(1000);
		// driver.findElement(By.xpath("//input[@id='u_0_d_mr']")).click();
		//driver.close();

	}
	
	@Test
	public void loginButton() {
		login = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
		login.click();
		
		String title = driver.getTitle();
		String expectedtitle = "EddTech";
		
		Assert.assertEquals(title, expectedtitle);
		
		driver.close();
		
		/*
		 * if(title.equalsIgnoreCase(expectedtitle)) {
		 * System.out.print("Title Test Successfull\n"); } else {
		 * System.out.print("Test Failed "+ title +"\n"); }
		 */
	}

	private static void clear() {
		// TODO Auto-generated method stub
		elemUserName.sendKeys(Keys.CONTROL + "a");
		elemUserName.sendKeys(Keys.DELETE);
		elemInstituteId.sendKeys(Keys.CONTROL + "a");
		elemInstituteId.sendKeys(Keys.DELETE);
		elemPassword.sendKeys(Keys.CONTROL + "a");
		elemPassword.sendKeys(Keys.DELETE);
	}
}
