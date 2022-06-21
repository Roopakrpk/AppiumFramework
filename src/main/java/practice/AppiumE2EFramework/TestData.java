package practice.AppiumE2EFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="inputData")
	public Object[][] getDataForEdit() {
		// 2 sets of data: "Hello", "!@#$%^"
		
		Object[][] obj = new Object[][] 
				{	
			{"Hello"}, {"!@#$%^"}
		};
		return obj;
	}

}
