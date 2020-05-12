package Store;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderConfirmation {
		
		WebDriver driver;
		
		@Test
		public void searchitem() throws InterruptedException {
			
			
			 //Login
			 
			driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.id("email")).sendKeys("amina.shadan@gmail.com");
			driver.findElement(By.id("passwd")).sendKeys("Pa55word"); 
			driver.findElement(By.id("SubmitLogin")).click();
			Thread.sleep(1000);
			if(driver.getPageSource().contains("Welcome to your account. Here you can manage all of your personal information and orders"))
			{
				System.out.println("Login Successfull");
						
			}
			else
			{
				System.out.println("Login Failed");
				
			}
			
			//search for an item
			WebElement products = driver.findElement(By.xpath("//*[@id=\'search_query_top\']"));
			products.clear(); 
			products.sendKeys("printed summer dress");
			driver.findElement(By.name("submit_search")).click();
			
			//SRP - Click on Add to cart
			//Item 1 - add to cart
			
			WebElement elementToHover=driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/h5/a"));;
			WebElement elementToClick =driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li/div/div[2]/div[2]/a[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(elementToHover).click(elementToClick).build().perform();
			new WebDriverWait(driver, 50);
			if(driver.getPageSource().contains("Product successfully added to your shopping cart"))
			{
				
				System.out.println("Item 1 Successfull added to bag");
				
			}
			else
			{
				System.out.println("Item not added to bag");
				
			}
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/span/span")).click();
			Thread.sleep(1000);
			
			
			new WebDriverWait(driver, 50);
			driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
			new WebDriverWait(driver, 30);
			WebElement elementToHover_1=driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/h5/a"));;
			WebElement elementToClick_2 =driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li/div/div[2]/div[2]/a[1]"));
			new Actions(driver);
			action.moveToElement(elementToHover_1).click(elementToClick_2).build().perform();
			new WebDriverWait(driver, 30);
			if(driver.getPageSource().contains("Product successfully added to your shopping cart"))
			{
			
				System.out.println("Item 2 Successfull added to bag");
				
			}
			else
			{
				System.out.println("Item not added to bag");
				
			}
			
		
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/span/span")).click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 50);
			
			//Item 3 Add to cart
			driver.findElement(By.xpath("//*[@id=\'block_top_menu\']/ul/li[1]/a")).click();
			new WebDriverWait(driver, 30);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\'subcategories\']/ul/li[1]/h5/a")).click();
			Thread.sleep(3000);
			new WebDriverWait(driver, 30);
			driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[2]/div/div[1]/div/a[1]/img"));
			new WebDriverWait(driver, 30);
			WebElement Item_Hover_2=driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/h5/a"));;
			WebElement Item_Click_3 =driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li/div/div[2]/div[2]/a[1]"));
			new Actions(driver);
			action.moveToElement(Item_Hover_2).click(Item_Click_3).build().perform();
			new WebDriverWait(driver, 30);
			if(driver.getPageSource().contains("Product successfully added to your shopping cart"))
			{
				
				System.out.println("Item 3 Successfull added to bag");
				
			}
			else
			{
				System.out.println("Item not added to bag");
				
			}
			
			Thread.sleep(2000);
			//Clicked on Mini shopping cart on top
			driver.findElement(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/a")).click();
			System.out.println("Clicked on Cart Icon");
			Thread.sleep(1000);
			
			//Shopping cart page
			if(driver.getPageSource().contains("SHOPPING-CART SUMMARY"))
			{
				System.out.println("Shopping cart page - Proceed to checkout");
			}
			else
			{System.out.println("Failed -- Shopping Cart Page");}
				
			driver.findElement(By.xpath("//*[@id=\'center_column\']/p[2]/a[1]")).click();
			new WebDriverWait(driver, 50);
			System.out.println("Clicked on checkout button in shopping cart page");
			Thread.sleep(1000);
			
			
			//Address Page
			if(driver.getPageSource().contains("ADDRESSES"))
			{
				System.out.println("Shipping & Billing Adresses section");
			}
			else
			{System.out.println("Failed - Shipping & Billing Page");}
			
			Thread.sleep(1000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			new WebDriverWait(driver, 30);
			driver.findElement(By.cssSelector("#center_column > form > p > button > span")).click();
			Thread.sleep(2000);
			
			//Shipping page
			WebElement checkbox = driver.findElement(By.xpath("//input[@value='1']"));
			checkbox.click();
			System.out.println("Clicked on checkbox");
			Thread.sleep(1000);
			WebDriverWait wait11 = new WebDriverWait(driver, 30);
			wait11.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='form']/p/button/span"))).click();
			
			System.out.println("Clicked on proceed to checkbox button");
			new WebDriverWait(driver, 50);
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@id=\'HOOK_PAYMENT\']/div[2]/div/p/a")).click();
			new WebDriverWait(driver, 50);
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@id=\'cart_navigation\']/button")).click();
			new WebDriverWait(driver, 50);
			Thread.sleep(1000);

			if(driver.getPageSource().contains("Your order on My Store is complete"))
			{
				System.out.println("Order placed successfully");
				
			}
			else
			{
				System.out.println("Failed - Unable to place order");
			}
			
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

