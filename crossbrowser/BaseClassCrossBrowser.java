package week6.crossbrowser;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class BaseClassCrossBrowser {
	public  RemoteWebDriver driver;
	
	public String excelFile;
	@Parameters({"browser","url","uname","pwd"})
	@BeforeMethod
	
	public void preCondition(String browser,String url,String uname,String pwd)
	{
		switch(browser) {
		case "chrome":
		{
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(option);
			break;
		}
		case "Edge":
		{
			driver=new EdgeDriver();
			break;
		}
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
	}
	@AfterMethod
	public void postCondition() {
		driver.close();
	}
	@DataProvider(name="fetchData")

	public String[][] getdata() throws IOException {
		
		String[][] inputData =ReadInput.inputData(excelFile);
		return inputData;

	}
}
