package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkbxPrivaryPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephoneNum)
	{
		txtTelephone.sendKeys(telephoneNum);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String Password)
	{
		txtConfirmPassword.sendKeys(Password);
	}
	
	public void clickPrivacyPolicy()
	{
		chkbxPrivaryPolicy.click();
	}
	
	public void clickContinue()
	{
		//sol1
		btnContinue.click();
		
		//sol2
		//btncontinue.submit();
		
		//sol3
		//Actions action = new Actions(driver);
		//action.moveToElement(btnContinue).click().perform();
		
		//sol4
		//JavaScriptExecutore js =(JavaScriptExecutor) driver;
		//js.executeScript("arguments[0].click();",btnContinue);
		
		//Sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol6
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMsg()
	{
		   try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.visibilityOf(msgConfirmation));
		        return msgConfirmation.getText();
		    } catch(Exception e) {
		        return e.getMessage();
		    }
		}
}
