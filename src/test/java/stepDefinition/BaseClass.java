package stepDefinition;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage LP;
	public AddcustomerPage addcust;
	public SearchCustomerPage searchCustomerPage;
	public static Logger logger;
	public Properties prop;

//craete random String

	public static String randomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(10);
		return generatedString1;
	}
}
