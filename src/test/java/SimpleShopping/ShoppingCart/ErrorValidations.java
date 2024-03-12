package SimpleShopping.ShoppingCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import AbstractComponents.BaseTest;
import PageObjectsClasses.Checkoutpage;
import PageObjectsClasses.PlaceOrder;
import PageObjectsClasses.ProductCart;

public class ErrorValidations extends BaseTest {
	
	@Test(groups= {"ErrorvalidationsTest"})
	 public void LoginErrorValidation() {
		 landingPage.Login("roja.reddy@gmail.com","Hydroggen@01");//incorrect password
		 Assert.assertEquals("Incorrect email or password.", landingPage.getLoginErrMsg());
	 }
	@Test(groups= {"ErrorvalidationsTest"})
	public void ProductErrorValidation() {
		  ProductCart productCart = landingPage.Login("rajesh.manne@gmail.com","Rajesh@0425");
            Checkoutpage checkout = productCart.AddToCart("ZARA COAT 3");
	    	productCart.gotoCartSection();
	    	checkout.ValidateTheProduct();
	    
	 }

}
