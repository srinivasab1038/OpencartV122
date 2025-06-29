package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//Valid Data -> Login Success -> Test Pass -> Logout
//Valid Data -> Login Failed -> Test Fail

//Invalid Data -> Login Success -> Test Fail ->Logout
//Invalid Data -> Login Failed -> Test Pass

public class TC003_LoginTestDDT extends BaseClass{

@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven") //getting data provider from different class
public void Verify_LoginDDT(String username, String pwd, String res)
{
	logger.info("Starting of TC003_LoginTestDDT");
	HomePage hp= new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmailAddress(username);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	MyAccountPage map= new MyAccountPage(driver);
	boolean targetPage = map.msgMyAccountDisplay();
	if(res.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			map.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	if(res.equalsIgnoreCase("Invalid"))
	{
		if(targetPage==true)
		{
			map.clickLogout();
			Assert.assertTrue(false);	
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}
	
	logger.info("End of TC003_LoginTestDDT");
	
}

}
