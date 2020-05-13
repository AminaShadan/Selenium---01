package Store;


import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class GooglePlus {
	
	WebDriver driver;
	
	 @Test
	 public void GooglePluswindow() throws IOException, InterruptedException{
   
		 //Login
		 
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("email")).sendKeys("amina.shadan@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Pa55word");
		driver.findElement(By.id("SubmitLogin")).click();
		Thread.sleep(2000);
		if(driver.getPageSource().contains("Welcome to your account. Here you can manage all of your personal information and orders"))
		{
			System.out.println("Login Successfull");
					
		}
		else
		{
			System.out.println("Login Failed");
			
		}
		
		//search
		driver.findElement(By.id("search_query_top")).sendKeys("printed dresses");
		driver.findElement(By.name("submit_search")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).isDisplayed());
		{
			System.out.println("Search Page");
		}
		
		//SRP page - Click on product	
		
		
		WebElement elementToHover=driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/h5/a"));;
		WebElement elementToClick =driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/div[2]/a[2]"));
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(elementToClick).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 20);
   	
   	//Click Google+
	
		
		driver.findElement(By.xpath("//*[@id=\'center_column\']/div/div/div[3]/p[7]/button[3]")).click();
		Thread.sleep(2000);
		 Set <String> winids=driver.getWindowHandles();
	     System.out.println("Number of Open windows :"+ winids.size());
	     
	     winids=driver.getWindowHandles();
	     //the above step is going to launch another window
	     System.out.println("Number of Open windows :"+ winids.size());
	     //Iterator is used to navigate to each of the window ids in getWindowHandles
	     Iterator<String> itr =winids.iterator();
	     //itr.hasNext()
	     String mainWIndowID =itr.next().toString();
	     String subWIndowID =itr.next().toString();
	     driver.switchTo().window(subWIndowID);
	     driver.manage().window().maximize(); //maximize
	     Thread.sleep(3000);
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'identifierId\']")));
	     driver.findElement(By.name("identifier")).sendKeys("amina.shadan");
	     //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'identifierId\']")));
	     WebElement  email = driver.findElement(By.xpath("//*[@id=\'identifierId\']"));
	     String emailid = email.getAttribute("value");
	     
	     System.out.println("Email id entered:"+emailid);
	     Thread.sleep(2000);
	     driver.findElement(By.id("identifierNext")).click();
	     Thread.sleep(4000);
	     if(driver.getPageSource().contains("Google"))
	     {
	    	 System.out.println("User landed on Google plus page");
	     }
	     else
	     {
	    	 System.out.println("User did not land on Google plus page"); 
	     }
	     driver.close(); //subwindow close
	     driver.switchTo().window(mainWIndowID);
	     if(driver.getTitle().contains("My Store"));
	     System.out.println("User dback on main page"); 
	     Thread.sleep(2000);
	  }
	
	 @BeforeMethod
	 public void LaunchBrowser() throws IOException{
		  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	   	  driver = new ChromeDriver(); //launching the browser
	   		
		  driver.get("http://automationpractice.com/index.php");
		 	
		 	driver.manage().window().maximize(); //maximize 	
		 	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	 @AfterMethod
	 public void CloseBrowser(){

			if(driver!=null){
				driver.close(); //the current active browser
				driver.quit(); ///closes all open browsers opened via yout script
			}


	 }




}
