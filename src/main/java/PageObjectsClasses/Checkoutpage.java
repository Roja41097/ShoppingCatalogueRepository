package PageObjectsClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.ReusableCode;

public class Checkoutpage extends ReusableCode {
	
	WebDriver driver;
	String ProdName;
	@FindBy(css = ".cartSection h3")
	List<WebElement> ProductsToCheckout;
	@FindBy(css = ".totalRow button")
	WebElement CheckoutButton;
	public Checkoutpage(WebDriver driver,String ProdName) {
		super(driver);
		this.driver = driver;
		this.ProdName = ProdName;
		PageFactory.initElements(driver, this);
	}

	public PlaceOrder ValidateTheProduct() {
		List <WebElement> cartProducts = getListOfProductInCart();	
		Boolean match = 	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProdName));
		Assert.assertTrue(match);
		CheckoutButton.click();
		return new PlaceOrder(driver);
	}
    public List<WebElement> getListOfProductInCart() {
	  return ProductsToCheckout;
    }
}
