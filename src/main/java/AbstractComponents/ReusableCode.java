package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableCode {
	WebDriver driver;
	public ReusableCode(WebDriver driver) {
		this.driver = driver;
	}
	public WebDriverWait ByWait(By ByElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ByElement));
		return wait;
	}
	public WebDriverWait WebElementWait(WebElement waitWebElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(waitWebElement));
		return wait;
	}
	public WebDriverWait InvisibleWait(WebElement waitWebElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(waitWebElement));
		return wait;
	}
	

}
