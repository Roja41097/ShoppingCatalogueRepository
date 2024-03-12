package PageObjectsClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableCode;

public class PlaceOrder extends ReusableCode {
	WebDriver driver;
	String Country = "india";
	@FindBy(css = "[placeholder='Select Country']")
	 WebElement SelectCountry;
    
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	 WebElement SelectIndia;

	/*@FindBy(css = ".action__submit")
	 WebElement PlaceOrderButton;*/
	@FindBy(xpath = "//div[@class='actions']/a")
	 WebElement PlaceOrderButton;


	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    public ConfirmationPage PlaceTheOrder() {
    	SelectShippingCountry();
    	
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", PlaceOrderButton);
    	return new ConfirmationPage(driver);
    }
    public void SelectShippingCountry() {
    	Actions a = new Actions(driver);
    	a.sendKeys(SelectCountry, Country).build().perform();
    	ByWait(By.cssSelector(".ta-results"));
    	SelectIndia.click();
    }
}
