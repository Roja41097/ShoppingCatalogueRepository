package PageObjectsClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.BaseTest;

public class LandingPage extends BaseTest {
	WebDriver driver;
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit; 
	
	@FindBy(css = "div[class*='flyInOut']")
	WebElement LoginErrMsg;
	
	public LandingPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	public void goTo() throws Exception {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public ProductCart Login(String name,String password) {
		userEmail.sendKeys(name);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCart productCart = new ProductCart(driver);
		return productCart;
	}
	
    public String getLoginErrMsg() {
    	return LoginErrMsg.getText();
    }
    
}
