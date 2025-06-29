package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void VerifyLogin()
	{
		logger.info("Starting of TC002_LoginTest");
		try
		{
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetPage = map.msgMyAccountDisplay();
		
		Assert.assertTrue(targetPage);
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("End of TC002_LoginTest");
		

	}

}
