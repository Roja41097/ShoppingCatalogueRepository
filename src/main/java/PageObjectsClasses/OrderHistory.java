package PageObjectsClasses;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrderHistory {
	WebDriver driver;
	String submitorder;
	public OrderHistory(WebDriver driver,String submitorder) {
		this.driver = driver;
		this.submitorder = submitorder;
		PageFactory.initElements(driver, this);
	}
   @FindBy(css="button[routerlink*='myorders']")
   WebElement OrdersButton;
   //@FindBy(css = ".ng-star-inserted td:nth-child(3)")
   //WebElement orderedProduct;
   @FindBy(css=".ng-star-inserted")
   List<WebElement> Orderslist;   
   public void GotoOrders() {
	   OrdersButton.click();
   }
   
   public boolean ValidateTheOrderedProduct(Boolean getstatus) {
	   Boolean status = false;
	   for(int i=1;i<Orderslist.size();i++) {
		String OrderedProduct = driver.findElement(By.cssSelector(".ng-star-inserted tr:nth-child("+i+") td:nth-child(3)")).getText();
	    if(OrderedProduct.equalsIgnoreCase(submitorder)) {
	    	return status = true;
	        
	    }
	   }
	   //String OrderedProduct = orderedProduct.getText(); 
	  // Assert.assertTrue(submitorder.equalsIgnoreCase(OrderedProduct));
	return status;
   }
	
}
