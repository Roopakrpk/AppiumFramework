package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class FormPage {
	
public FormPage(AndroidDriver<AndroidElement>driver) {
		
		PageFactory.initElements(driver, this);
	}

    @FindBy(id="com.androidsample.generalstore:id/nameField")
    public WebElement nameField;
    
    @FindBy(xpath="//*[@text='Female']")
    public WebElement femaleOpt;
    
    @FindBy(id="android:id/text1")
    public WebElement countrySelect;
    
    @FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    public WebElement letShop;

}
