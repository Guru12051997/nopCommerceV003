package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Utilities.ExplicitWaitHelper;

public class SearchCustomerPage {
	public WebDriver Idriver;
	ExplicitWaitHelper explicitWaitHelper;

	public SearchCustomerPage(WebDriver rdriver) {
		Idriver = rdriver;
		PageFactory.initElements(rdriver, this);
		explicitWaitHelper = new ExplicitWaitHelper(Idriver);
	}

	/*
	 * By Email = By.xpath("//input[@id='SearchEmail']"); By FirstName =
	 * By.xpath("//input[@id='SearchFirstName']"); By LastName =
	 * By.xpath("//input[@id='SearchLastName']");
	 * 
	 * public void SearchEmailID(String EM) {
	 * Idriver.findElement(Email).sendKeys(EM); }
	 * 
	 * public void SearchFirstName(String FN) {
	 * Idriver.findElement(FirstName).sendKeys(FN); }
	 * 
	 * public void SearchLastName(String LN) {
	 * Idriver.findElement(LastName).sendKeys(LN); }
	 */

	// Another approach to find element locator
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;

	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement Drpdobmonth;

	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement Drpdobday;

	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement Companyname;

	@FindBy(how = How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement CustomerRole;

	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement ItemAdministrators;

	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement ItemRegistered;

	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement ItemGuests;

	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement ItemVendors;

	@FindBy(how = How.XPATH, using = "//button[@id='search-customers']")
	@CacheLookup
	WebElement Searchbtn;

	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement Tablesearchresults;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")

	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")

	List<WebElement> tableColumn;

	// Action methods

	public void setEmail(String email) {
		explicitWaitHelper.waitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setfirstName(String fname) {
		explicitWaitHelper.waitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		explicitWaitHelper.waitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}

	public void clickSearch() {
		Searchbtn.click();
		explicitWaitHelper.waitForElement(Searchbtn, 30);
	}

	public int getRows() {
		return tableRows.size();
	}

	public int getColumns() {
		return tableRows.size();
	}

	public Boolean SearchCustomersByEmail(String email) {
		Boolean flag = false;

		for (int i = 1; i <= getRows(); i++) {
			String emailID = table
					.findElement(By.xpath("//table[@id='customers-grid']//" + "tbody/tr[" + i + "]/td[2]")).getText();
			System.out.println(emailID);

			if (emailID.equals(email)) {
				flag = true;
			}

		}

		return flag;

	}

	public Boolean SearchCustomersByName(String Name) {
		Boolean flag = false;

		for (int i = 1; i <= getRows(); i++) {

			String names = table.findElement(By.xpath("//table[@id='customers-grid']//" + "tbody/tr[" + i + "]/td[3]"))
					.getText();
			System.out.println(names);
			String name1[] = names.split(" ");
			if (name1[0].equals("Guaa") && name1[1].equals("Da")) {
				flag = true;
			}

		}
		return flag;

	}
}
