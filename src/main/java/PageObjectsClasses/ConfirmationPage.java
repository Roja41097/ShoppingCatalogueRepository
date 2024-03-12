package PageObjectsClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ConfirmationPage {
    WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
    public void GetConfirmationPage() {
    	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
    	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    	
    }
}
