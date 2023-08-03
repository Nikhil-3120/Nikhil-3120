package testpackage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTest
{
    WebDriver driver;
    @BeforeMethod
	public void open()
	{
		System.setProperty("webdriver.chrome.driver","./automation/chromedriver.exe");
	    driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	@Test
	public void test()
	{
		driver.get("https://www.amazon.in");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Samsung galaxy S23 ultra phantom black");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Samsung Galaxy S23 Ultra 5G 256GB Phantom Black']")));
		WebElement ele = driver.findElement(By.xpath("//span[.='Samsung Galaxy S23 Ultra 5G 256GB Phantom Black']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String price = driver.findElement(By.xpath("//span[@id='productTitle']/../../../..//span[@class='a-price-whole']")).getText();
		System.out.println( "The price of Samsung S23 Ultra in amazon is --->  "  +  price);
		Reporter.log("The price of Samsung S23 Ultra in amazon is --->  "  +  price);
		//String str1=price.getText().toString();
		String a = price;
		price =price.replaceAll("[,]","");
		int num=Integer.parseInt(price);
		jse.executeScript("window.open()");
		ArrayList<String> tab1 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab1.get(2));
		driver.get("https://www.flipkart.com/");
		try {
           // Use implicit wait to wait for the element to appear for a maximum of 10 seconds
           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           WebElement closeButton = driver.findElement(By.xpath("//button[.='✕']"));
           closeButton.click();
       } catch (NoSuchElementException e) {
       	
       }
		//driver.findElement(By.xpath("//button[.='✕']")).click();
		 WebDriverWait wait1 = new WebDriverWait(driver,10);
		 wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		 driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Samsung galaxy s23 ultra phantom black");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Thread.sleep(2000);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//div[.='SAMSUNG Galaxy S23 Ultra 5G Smartphone (Phantom Black, 256 GB)']")).click();
		 ArrayList<String> tab2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab2.get(3));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String money = driver.findElement(By.xpath("//div[@id='price-info-icon']/ancestor::div[2]/div[1]//div[1]/div[1]")).getText();
		System.out.println("price of Samsung galaxy S23 Ultra in flipkart --> " + money);
		Reporter.log("price of Samsung galaxy S23 Ultra in flipkart --> " + money);
		//System.out.println(str);
		String f= money;
	    money=money.replaceAll("[₹,]","");
		//System.out.println(str);
		int num1 = Integer.parseInt(money);
		 if(num>num1)
		 {
			 System.out.println("price of Samsung galaxy S23 Ultra is less in flipkart  " +  f);
			 Reporter.log("price of Samsung galaxy S23 Ultra is less in flipkart  " +  f);
		 }
		 else if(num < num1)
		 {
			 System.out.println("price of Samsung galaxy S23 Ultra is less in amazon  --->  " + a);
			 Reporter.log("price of Samsung galaxy S23 Ultra is less in amazon  " +  a);
		 }
		
		 else 
		 {
			 System.out.println("price of Samsung galaxy S23 Ultra is same in both app's  -____-- " + "amazon --- " +  a  +   "  fipkart ---" + f );
			 Reporter.log("price of Samsung galaxy S23 Ultra is same in both app's  -____-- " + "amazon --- " +  a  +   "  fipkart ---" + f);
		 }
	 }
	@AfterMethod
	public void close()
	{
		driver.quit();
	}
	

}
