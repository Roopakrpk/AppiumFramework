package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Homepage {
	// All the objects belonging to one page will be defined in java class.
	// Call the driver object from test 	case to PageObject file.
	
	public Homepage(AndroidDriver<AndroidElement>driver) {
		
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//android.widget.TextView[@text='Preference']")
    public WebElement preference;
	
}
