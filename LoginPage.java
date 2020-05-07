package Store;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.utils.PropertyReader;

public class LoginPage {

	static WebDriver driver;
	public String email;
	public String password;
	

	String ProjectPath = System.getProperty("user.dir"); 
	String filepath = ProjectPath + "/src/testdata";
	String filename = "UserInformation_login.xls";
	String sheetname ="login_details";
	
	@BeforeMethod
	  public void LaunchBrowser() throws IOException {
		  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	   	  driver = new ChromeDriver(); //launching the browser
	   		
		  //driver.get("http://automationpractice.com/index.php");
		 	driver.get(PropertyReader.ReadProperty("aapurl"));
		 	driver.manage().window().maximize(); 	
	}
	
	@AfterMethod
	  public void CloseBrowser(){

			if(driver!=null){
				driver.close(); //the current active browser
				driver.quit(); ///closes all open browsers opened via yout script
			}
	}
	
	
	@Test
	public void inputlogin() throws InterruptedException{
		
		
	 		
			driver.findElement(By.linkText("Sign in")).click();
	 		Thread.sleep(2000);
	 		try {
	 			FileInputStream inputStream = new FileInputStream(filepath +"/" + filename);
	 			
	            // Create workbook instance referencing the file created above
	 			HSSFWorkbook workbook = new HSSFWorkbook (inputStream);

	            // Get your first or desired sheet from the workbook
	 			Sheet sheet = workbook.getSheetAt(0);

	            HSSFRow row = (HSSFRow) sheet.getRow(1);
	            HSSFCell cell1 = row.getCell(0);
	            HSSFCell cell2 = row.getCell(1);
	            

	            email = cell1.toString();
	            password = cell2.toString();
	            
	            //inputStream.close();
	        } 
	 		catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	 		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys(email);
	 		Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\'passwd\']")).sendKeys(password);
			Thread.sleep(2000);
			driver.findElement(By.id("SubmitLogin")).click();
			Thread.sleep(3000);
			System.out.println("------------------------------"); 

			if(driver.getPageSource().contains("Welcome to your account. Here you can manage all of your personal information and orders"))
			{
				System.out.println("Username:"+email);
				System.out.println("Password:"+password);
				System.out.println("------------------------------");

				System.out.println("User Login Successfull");
						
			}
			else
			{
				System.out.println("Use Login Failed");
			}
			System.out.println("------------------------------");
	 	
			if(driver.getPageSource().contains("Sign out"))
			{
				System.out.println("--Sign off link appears on the header--");
			}
			
			
	    }
	

}
