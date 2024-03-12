package SimpleShopping.ShoppingCart;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbstractComponents.BaseTest;
import PageObjectsClasses.Checkoutpage;
import PageObjectsClasses.ConfirmationPage;
import PageObjectsClasses.LandingPage;
import PageObjectsClasses.OrderHistory;
import PageObjectsClasses.PlaceOrder;
import PageObjectsClasses.ProductCart;

public class OrderProduct extends BaseTest{


	@Test(dataProvider="getData",groups="Purchase")
    public void submitOrder(HashMap<String,String> input) throws Exception {
    	//LandingPage landingPage = LaunchSimpleShoppingApp();
		//as LaunchSimpleShoppingApp() consider as beforeTest so landingPage obj will initialize
    	ProductCart productCart = landingPage.Login(input.get("email"),input.get("password"));
    	Checkoutpage checkout = productCart.AddToCart(input.get("productName"));
    	productCart.gotoCartSection();
    	PlaceOrder orderTheProduct = checkout.ValidateTheProduct();
    	ConfirmationPage ConfirmPage =orderTheProduct.PlaceTheOrder();
    	ConfirmPage.GetConfirmationPage();
    }
	@Test (dependsOnMethods = {"submitOrder"},dataProvider="getData", groups={"Purchase"})
	public void OrderHistory(HashMap<String,String> map) {
		landingPage.Login(map.get("email"),map.get("password"));
		OrderHistory orders = new OrderHistory(driver,map.get("productName"));
		orders.GotoOrders();
		Boolean status = orders.ValidateTheOrderedProduct(false);
		System.out.println(status);
	}
	@DataProvider
	public Object[][] getData() throws Exception {
		List<HashMap<String,String>> JsonTestData =getJsonData();
		return new Object[][] {{JsonTestData.get(0)},{JsonTestData.get(1)}};
		//Using HashMap
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "roja.reddy@gmail.com");
		map.put("password", "Hydrogen@01");
		map.put("productName", "ZARA COAT 3");
		HashMap<String,String> map1 = new HashMap<String,String>(); 
		map1.put("email", "rajesh.manne@gmail.com");
		map1.put("password", "Rajesh@0425");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object[][]{{map},{map1}};*/
		//Object[][] data= new Object[][]{{map},{map1}};
		//return data;
		
		//Using object
		//return new Object[][] {{"roja.reddy@gmail.com","Hydrogen@01","ZARA COAT 3"},{"rajesh.manne@gmail.com","Rajesh@0425","ADIDAS ORIGINAL"}};
	}
	
	
}
