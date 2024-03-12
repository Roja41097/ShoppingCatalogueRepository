package AbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjectsClasses.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws Exception {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\ShoppingCart\\Data\\GlobalVariables.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions cp = new ChromeOptions();
			cp.addArguments("remote-allow-origins=*");
			 driver = new ChromeDriver(cp);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\SeleniumProjects\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			// edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public String getScreenshot(String filepath,WebDriver driver) throws Exception {
		File SSfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SSfile, new File(filepath+".png"));
		return filepath+".png";
	}
	public ExtentReports ExtentReportNG() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//index.html");
		reporter.config().setReportName("ErrorValidationReport");
		reporter.config().setDocumentTitle("ShoppingCart TestResults");
		ExtentReports extents = new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester","Roja Reddy");
		return extents;
	}
	public List<HashMap<String, String>> getJsonData() throws Exception {
		//Converting Json to String
		String JsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\ShoppingCart\\Data\\TestData.json"),StandardCharsets.UTF_8);
		//Converting String to HashMap
		ObjectMapper mapper = new ObjectMapper();//used to read data to or from POJO file     
		List<HashMap<String,String>> data = mapper.readValue(JsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}  
	  
    @BeforeMethod(alwaysRun = true)
    public LandingPage LaunchSimpleShoppingApp() throws Exception {
		driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
	}
    @AfterMethod(alwaysRun = true)
    public void Close() {
    	driver.close();
    }
    
}
