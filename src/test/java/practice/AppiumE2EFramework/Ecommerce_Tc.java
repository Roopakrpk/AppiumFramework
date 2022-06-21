package practice.AppiumE2EFramework;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.FormPage;
public class Ecommerce_Tc extends Base {
    // To validate the total amount displayed in cart page is matching with sum of product amount selected.
	
	@Test
	public void totalValidation() throws IOException, InterruptedException{
		service=startServer();
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
		//driver.findElementById("android:id/text1").click();
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
		
		FormPage fp = new FormPage(driver);
		fp.countrySelect.click();
		
		Utilities u = new Utilities(driver);
		u.scrollToText("Bangladesh");
		
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bangladesh\"))");
	    driver.findElementByXPath("//*[@text='Bangladesh']").click();
		
		//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello User");
		fp.nameField.sendKeys("Hello User");
	    driver.hideKeyboard();
	    fp.femaleOpt.click();
		//driver.findElementByXPath("//*[@text='Female']").click();
	    fp.letShop.click();
		//driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
        
		driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(0).click();	
		driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(1).click();
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		
		int count = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
		double sum=0;
		for (int i = 0; i < count; i++) {
			String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
			double amount=getAmount(amount1);
			sum=sum+amount;
		}
		
	    System.out.println(sum+" Sum of products value");
	    String total = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
	    total = total.substring(1);
	    double totalValue = Double.parseDouble(total);
	    System.out.println(totalValue+" Total product value");
	    Assert.assertEquals(sum, totalValue);
	    service.stop();
	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("fuser -k 4723/tcp");
		Thread.sleep(3000);
	}
	
	public static double getAmount(String value) {
		value = value.substring(1);
		double amountValue = Double.parseDouble(value);
		return amountValue;
	}

}
