package PageObjectsClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.ReusableCode;

public class ProductCart extends ReusableCode{
	
 WebDriver driver;
 
 @FindBy(css = ".mb-3")
 List<WebElement> ProductsPage;
 @FindBy(css = "[routerlink*='cart']")
 WebElement CartButton;
 By VisibleProductsPage = By.cssSelector(".mb-3");
 By AddToCartButton =  By.cssSelector(".card-body button:last-of-type");
	public ProductCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}

	public Checkoutpage AddToCart(String ProductName) {
		super.ByWait(VisibleProductsPage);
		List<WebElement> products = ProductsPage;
		WebElement prod = getProductNameFromUI(products,ProductName);
		prod.findElement(AddToCartButton).click();
		ByWait(By.cssSelector("#toast-container"));
		WebElement ProductSuccessfulMsg = driver.findElement(By.cssSelector(".ng-animating"));
		InvisibleWait(ProductSuccessfulMsg);
		return new Checkoutpage(driver,ProductName);
	}
	

	public WebElement getProductNameFromUI(List<WebElement> products,String productName) {
		WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	public void gotoCartSection() {
    	CartButton.click();
    }

}
