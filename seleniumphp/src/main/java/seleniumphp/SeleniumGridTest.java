package seleniumphp;
import java.net.URL;
import org.testng.*;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 public class SeleniumGridTest {

	WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
		  WebDriverManager.chromedriver().setup();
//		  driver = new ChromeDriver(); //Run local
//		  driver .manage().window().maximize();
		  DesiredCapabilities cap = new DesiredCapabilities();
		  cap.setCapability("browserName", "chrome"); 
		  try { 
			  driver = new RemoteWebDriver(new URL("http://3.21.127.27:4444/wd/hub"), cap); 
			  } 
		  		catch (MalformedURLException e) {
					e.printStackTrace(); 
		  		}
		 }
		
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			driver .manage().window().maximize();
			//Thread.sleep(2000);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "firefox");
			try {
				driver = new RemoteWebDriver(new URL("http://3.21.127.27:4444/wd/hub"), cap);
			} 
				catch (MalformedURLException e) {
					e.printStackTrace();
				}
		}
			
//			else if (browser.equalsIgnoreCase("edge")) {
//				
//				//driver = new EdgeDriver();
//				WebDriverManager.edgedriver().setup();
//				
//				DesiredCapabilities cap = new DesiredCapabilities();
//				cap.setCapability("browserName", "edge");
//				try {
//					driver = new RemoteWebDriver(new URL("http://18.191.158.1:4444/wd/hub"), cap);
//				} 
//					catch (MalformedURLException e) {
//						e.printStackTrace();
//					}
//			}
				
//				else if (browser.equalsIgnoreCase("opera")) {
//					
//					//driver = new OperaDriver();
//					WebDriverManager.operadriver().setup();
//					DesiredCapabilities cap = new DesiredCapabilities();
//					cap.setCapability("browserName", "opera");
//					try {
//						driver = new RemoteWebDriver(new URL("http://18.191.158.1:4444/wd/hub"), cap);
//					} 
//						catch (MalformedURLException e) {
//							e.printStackTrace();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String baseUrl = "http://3.21.127.27:8096";//UAT Server
		//String baseUrl = "http://3.137.205.43:8012"; //Master Server
		
		//String baseUrl = "https://www.google.com//";
		driver.get(baseUrl); 
						}
//				}
		
//		}
	
	
	@Test
	public void webappTitleTest() {
		 	   	
     	String expectedTitle = "Home | Simple PHP Website";
		//String expectedTitle = "Google";
     	String actualTitle = "";
     	actualTitle = driver.getTitle(); // get the actual value of the title
     	//driver .findElement(By.id ("About Us")).click(); //click a link in application
     	
     /* compare the actual title of the page with the expected one and print the result as "Passed" or "Failed" */
    		 	if (actualTitle.contentEquals(expectedTitle)){
    		 	System.out.print("The web page title is "); 
    	        System.out.println(expectedTitle); 
    	        System.out.println("Test Passed!");
    		} else {
         	System.out.println("Test Failed");
    		}
     }
    
    	@AfterMethod
    		 	public void teardown() {
    		 		driver.quit();
    		 	}
    
     	}



   