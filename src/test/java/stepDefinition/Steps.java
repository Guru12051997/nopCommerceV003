package stepDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	@Before
	public void setup() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + ".//config.properties");

		prop.load(fis);

		logger = Logger.getLogger("nopCommerceV003_Cucumber");
		PropertyConfigurator.configure("log4j.properties");

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// System.getProperty("user.dir") + "//src//test//resources//chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webdriver.gecko.driver",
			// System.getProperty("user.dir") + "//src//test//resources//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		if (prop.getProperty("browser").equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
			// System.setProperty("webdriver.ie.driver",
			// System.getProperty("user.dir") +
			// "//src//test//resources//IEDriverServer.exe");
			driver = new EdgeDriver();
		}

		logger.info("****Launching URL****");
	}

	@After(order = 1)
	public void teardown(Scenario scenario) {
		if (scenario.isFailed()) {
			// takeScreenShot

			String ScreenshotName = scenario.getName().replaceAll(" ", "_");

			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", ScreenshotName);

		}
	}

	@Given("User Launch the Chrome browser")
	public void user_launch_the_chrome_browser() {

		LP = new LoginPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("****Opening URL****");

		driver.get(url);
		// driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String Email, String Password) {
		logger.info("****Providing login details****");
		LP.setUserName(Email);
		LP.setPassword(Password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		logger.info("****Login into the application ****");
		LP.ClickLogin();
		Thread.sleep(3000);
	}

	@Then("Page title Should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
		if (driver.getPageSource().contains("Login was unsuceessful.")) {
			driver.close();
			logger.info("****Login passed****");
			Assert.assertTrue(false);
		}

		else {
			logger.info("****Login Failed****");
			Assert.assertEquals(title, driver.getTitle());

		}
		Thread.sleep(3000);
	}

	@When("User Click on Logout Link")
	public void user_click_on_logout_link() throws Exception {
		logger.info("***Click on logout link****");
		LP.ClickLogOut();
		Thread.sleep(3000);
	}

	@Then("Page Title  Should bee {string}")
	public void page_title_should_bee(String title1) {
		if (driver.getPageSource().contains("Login was unsuceessful.")) {
			driver.close();
			Assert.assertTrue(false);
		}

		else {
			Assert.assertEquals(title1, driver.getTitle());

		}
	}

	@Then("Close\\\\/Quit the browser")
	public void lose_quit_the_browser() {
		logger.info("****Closing the browser****");
		driver.quit();
	}

	// ADD CustomrpageSteps

	@Then("User can view Dashboard")

	public void user_can_view_dashboard() {
		addcust = new AddcustomerPage(driver);

		Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getTitlePage());
	}

	@When("User click on customer menu")
	public void user_click_on_customer_menu() throws Exception {
		addcust.clickOnCustomerMenu();
		Thread.sleep(3000);
	}

	@When("Click on customer menu Items")
	public void click_on_customer_menu_items() throws InterruptedException {
		addcust.clickOnCustomerMenuItems();
		Thread.sleep(5000);
	}

	@When("Click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addcust.AddItems();
		Thread.sleep(3000);
	}

	@Then("User can view Add new Customer Page")
	public void user_can_view_add_new_customer_page() throws InterruptedException {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getTitlePage());

		Thread.sleep(3000);
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("****Adding new Customer****");
		logger.info("****Providing Customer details****");
		String email = randomString() + "@gmail.com";
		addcust.setEmailId(email);
		addcust.setPassWord("test123");
		addcust.SetGender("Male");
		addcust.SetmanagerOfVendor("Vendor 2");
		addcust.SetFirstName("Depak");
		addcust.SetLastname("Dutta");
		// addcust.SetDOB("12/05/1997");
		addcust.SetCompanyName("Wipro");
		addcust.SetAddminContent("Cucumber BDD Automation with pageObject Hybrid framework Approach");
		Thread.sleep(3000);

	}

	@When("Click on save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("****Saving Customer Data****");
		addcust.Buttonsave();
		Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
	}

	// Search by emailID

	@When("Enter customer Email")
	public void enter_customer_email() {
		logger.info("****Searching a new customer by using EmailID****");
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.SearchCustomersByEmail("WgvIm@gmail.com");

	}

	@When("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
		searchCustomerPage.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User Should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status = searchCustomerPage.SearchCustomersByEmail("WgvIm@gmail.com");
		Assert.assertEquals(true, status);

	}
	// Search by name

	@When("Enter Customer FirstName")
	public void enter_customer_first_name() {
		logger.info("****Searching customer by name****");
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setfirstName("Rahul");
	}

	@When("Enter Customer LastName")
	public void enter_customer_last_name() {
		searchCustomerPage.setLastName("shetty");
	}

	@Then("User Should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status1 = searchCustomerPage.SearchCustomersByName("Rahul shetty");
		Assert.assertEquals(true, status1);
	}

}
