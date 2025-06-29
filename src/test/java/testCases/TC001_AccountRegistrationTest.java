package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
@Test(groups={"Regression","Master"})
public void verify_Account_Registration() throws InterruptedException
{
	logger.info("Starting of TC001_AccountRegistrationTest");
	
	try
	{
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	logger.info("Clicked on MyAccount link");
	hp.clickRegister();
	logger.info("Clicked on Register link");
	
	AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
	
	logger.info("Providing customer details");
	regpage.setFirstName(randomString().toUpperCase()); //Random generated first name
	regpage.setLastName(randomString().toUpperCase()); //Random generated last name
	regpage.setEmail(randomString()+"@gmail.com"); //Random generated email
	regpage.setTelephone(randomNumber());   //Random generated number
	
	//Random generated alphanumeric password
    String	password= randomAlphanumeric();
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
	regpage.clickPrivacyPolicy();
	logger.info("Clicked on Privacy Policy checkbox");
	regpage.clickContinue();
	logger.info("Clicked in Continue");
	
	logger.info("Validation of confirmation message..");
	String confirmationMessage=regpage.getConfirmationMsg();
	if(confirmationMessage.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		logger.error("Test Failed");
		logger.debug("Debug logs..");
		Assert.assertTrue(false);
	}
	
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	
	logger.info("End of TC001_AccountRegistrationTest");
}

}
