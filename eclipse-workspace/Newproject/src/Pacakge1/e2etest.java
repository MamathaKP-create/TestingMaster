package Pacakge1;


import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;

import net.bytebuddy.jar.asm.TypeReference;
public class e2etest extends Datatesting {

	@Test(dataProvider="getdata")
	public void e2eflow(HashMap<String,String> input) throws InterruptedException
	{
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		String productname="ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys(input.get("email"));
		driver.findElement(By.id("userPassword")).sendKeys(input.get("password"));
		driver.findElement(By.id("login")).click();
		List<WebElement> products= driver.findElements(By.xpath("//div/h5/b"));
		WebElement prod=products.stream().filter(product->product.findElement(By.xpath("//div/h5/b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> selectedproducts= driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		
		Boolean value=selectedproducts.stream().anyMatch(selectedproduct->selectedproduct.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(value);
		WebElement button=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Checkout']")));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",button );
		Thread.sleep(3000);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
		driver.findElement(By.xpath("//div/section/button[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		String confmesg= driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(confmesg.equalsIgnoreCase("Thankyou for the order."));
}
	@DataProvider()
	public Object[][] getdata() throws IOException
	{
		
		List<HashMap<String,String>> data= getjsondatatoMap();
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}

