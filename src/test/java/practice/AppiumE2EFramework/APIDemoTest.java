package practice.AppiumE2EFramework;

import org.testng.annotations.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.Homepage;

public class APIDemoTest extends Base {

	@Test(dataProvider = "inputData", dataProviderClass = TestData.class)
	public void apiDemo(String data) throws IOException, InterruptedException {
		service=startServer();
		// Inheriting AppiumBase class and calling the "capabilities" method to reuse anywhere.
		AndroidDriver<AndroidElement> driver = capabilities("apiDemo");
		Homepage hp = new Homepage(driver);
		// xpath in identifying an element in the emulator.
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		hp.preference.click();
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		
	    //Id in locating an element in the emulator.
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();// Another way - ("(//android.widget.RelativeLayout)[2]")
		driver.findElementById("android:id/edit").sendKeys(data);
		driver.findElementById("android:id/button1").click(); // Another way - findElementsByClassName("android.widget.Button").get(1).click
		service.stop();
	}

}
