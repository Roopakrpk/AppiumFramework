package practice.AppiumE2EFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class Base {
	public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService service;
	public  AppiumDriverLocalService  startServer() {
		boolean flag = checkIfServerIsRunning(4723);
		
		if (!flag) {
		service = AppiumDriverLocalService.buildDefaultService();
	    service.start();
		}
		return service;
	}
	
	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}
		catch(IOException e) {
			// If control comes here, then it means that the port is in use.
			isServerRunning = true;
		}
		finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	// /home/roopak/Eclipse-Latest/AppiumE2EFramework/src/main/java/resources/startEmulator.
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/java/resources/startEmulator");
		Thread.sleep(6000);
	}

	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {
	
		//System.getProperty("user.dir") Will dynamically generate the path until project name level(AppiumE2EFramework)
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/practice/AppiumE2EFramework/global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String apkName = (String) prop.get(appName);
		File appDir = new File("src");
		File app = new File(appDir, apkName);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
		//String device = (String) prop.get("device"); // This is for invoking emulator through global properties file.
		String device = System.getProperty("deviceName"); // This is for invoking emulator through Maven command in terminal.
		if (device.contains("emulator")) {
			startEmulator();
		}
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");// For running tests in real android device.
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability("newCommandTimeout", 14);
		//cap.setCapability("chromedriverExecutable","/home/roopak/Downloads/drivers/chromedriver");
		
		// UIAutomator helps to automate Android apps; UIAutomator2 is the latest stable version.
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		// AndroidDriver class connects with DesiredCapabilities class to invoke mobile operations.
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void getScreenshot(String tc) throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/"+tc+".png"));
	}
}
