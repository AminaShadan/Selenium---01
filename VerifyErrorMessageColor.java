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
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.utils.PropertyReader;



public class VerifyErrorMessageColor {
	
	
	public WebDriver driver;
	public String email = null;
	public String password = null;
	

	String ProjectPath = System.getProperty("user.dir"); 
	String filepath = ProjectPath + "/src/testdata";
	String filename = "invalid_login.xls";
	String sheetname ="invalid_login";
	
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
		public void Verifycolor() throws InterruptedException
		{

		
			driver.findElement(By.linkText("Sign in")).click();
			Thread.sleep(3000); 
			
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

			System.out.println("--------Color & Background verification----------------------");
			String backgroundColor = driver.findElement(By.xpath("//*[@id='center_column']/div[1]")).getCssValue("background-color");
			String ErrorTextColor = driver.findElement(By.cssSelector("#center_column > div.alert.alert-danger > ol > li")).getCssValue("color");
	    
			System.out.println("Background color: " + backgroundColor);
			System.out.println("Text color " + ErrorTextColor);
			String[] hexValue = backgroundColor.replace("rgba(", "").replace(")", "").split(",");
	    
			hexValue[0] = hexValue[0].trim();
			int hexValue1 = Integer.parseInt(hexValue[0]);                   

	    	hexValue[1] = hexValue[1].trim();
	    	int hexValue2 = Integer.parseInt(hexValue[1]);                   

	    	hexValue[2] = hexValue[2].trim();
	    	int hexValue3 = Integer.parseInt(hexValue[2]);                   
	    
	    	String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
	    	System.out.println("Actual Color:"+ actualColor);
	    	String Testcolor = Color.fromString(backgroundColor).asHex();
	    	System.out.println("Background color - RGB to Hex color: "+Testcolor);
	    	Assert.assertEquals( Testcolor, "#f3515c", "Backgroud color matches");
	    	
	    	
	    	String[] hexcolor = ErrorTextColor.replace("rgba(", "").replace(")", "").split(",");
	    	hexcolor[0] = hexcolor[0].trim();
	    	int Text_hexValue1 = Integer.parseInt(hexcolor[0]);                   

	    	hexcolor[1] = hexcolor[1].trim();
	    	int Text_hexValue2 = Integer.parseInt(hexcolor[1]);                   

	    	hexcolor[2] = hexcolor[2].trim();
	    	int Text_hexValue3 = Integer.parseInt(hexcolor[2]);                   
	    	
	    	String Text_actualColor = String.format("#%02x%02x%02x", Text_hexValue1, Text_hexValue2, Text_hexValue3);
	    	System.out.println("Actual Color:"+ Text_actualColor);
	    	String Testcolor_one = Color.fromString(ErrorTextColor).asHex();
	    	System.out.println("Text Color - RGB to Hex color: "+Testcolor_one);
	    	Assert.assertEquals(Testcolor_one, "#ffffff", "Text color for error message is as expected");
		} 
}
