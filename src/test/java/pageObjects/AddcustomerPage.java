package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {

	public WebDriver Idriver;

	public AddcustomerPage(WebDriver rdriver) {
		Idriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	By CustomerMenu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By CustomerMenuItem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");

	By AddNewButton = By.xpath("//a[@class='btn btn-primary']");

	By Email = By.xpath("//input[@id='Email']");
	By Password = By.xpath("//input[@id='Password']");
	By FirstName = By.xpath("//input[@id='FirstName']");
	By LastName = By.xpath("//input[@id='LastName']");
	By MaleGender = By.xpath("//input[@id='Gender_Male']");
	By FemaleGender = By.id("//input[@id='Gender_Female']");
	By DateOfBirth = By.xpath("//section[@class='content']");
	By CompantName = By.id("Company");

	By CustomerRole = By.xpath(
			"//div[@class='k-widget k-multiselect k-multiselect-clearable k-state-focused']//input[@role='listbox']");

	By Administrator = By.xpath("//li[contains(text(),'Administrators']");
	By Registered = By.xpath("//li[contains(text(),'Registered']");
	By Guests = By.xpath("//li[contains(text(),'Guests']");
	By Vendors = By.xpath("//li[contains(text(),'Vendors']");

	By MgrOfVendor = By.xpath("//*[@id='VendorId']");

	By AdminComment = By.xpath(" //textarea[@id='AdminComment']");
	By ButtonSave = By.xpath("//button[@name='save']");

	// Action Methods

	public String getTitlePage() {
		return Idriver.getTitle();
	}

	public void clickOnCustomerMenu() {
		Idriver.findElement(CustomerMenu).click();
	}

	public void clickOnCustomerMenuItems() {
		Idriver.findElement(CustomerMenuItem).click();
	}

	public void AddItems() {
		Idriver.findElement(AddNewButton).click();
	}

	public void SetFirstName(String name) {
		Idriver.findElement(FirstName).sendKeys(name);
	}

	public void SetDOB(String DOB) {
		Idriver.findElement(DateOfBirth).sendKeys(DOB);
	}

	public void SetCompanyName(String Cname) {
		Idriver.findElement(CompantName).sendKeys(Cname);
	}

	public void SetAddminContent(String Cntnt) {
		Idriver.findElement(AdminComment).sendKeys(Cntnt);
	}

	public void Buttonsave() {
		Idriver.findElement(ButtonSave).click();
	}

	public void SetLastname(String Lname) {
		Idriver.findElement(LastName).sendKeys(Lname);
	}

	public void setEmailId(String email) {
		Idriver.findElement(Email).sendKeys(email);
	}

	public void setPassWord(String password) {
		Idriver.findElement(Password).sendKeys(password);
	}

	public void SetmanagerOfVendor(String value) {
		Select se = new Select(Idriver.findElement(MgrOfVendor));
		se.selectByVisibleText(value);
	}

	public void SetGender(String gender) {
		if (gender.equals("Male")) {
			Idriver.findElement(MaleGender).click();
		}
		if (gender.equals("Female")) {
			Idriver.findElement(FemaleGender).click();
		} else {
			Idriver.findElement(MaleGender).click();/// default
		}
	}

}
