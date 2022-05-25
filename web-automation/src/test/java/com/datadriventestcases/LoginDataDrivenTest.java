package com.datadriventestcases;

import com.generic.BaseTest;
import com.generic.ExcelDataProvider;
import com.views.LoginPageView.LoginPageView;
import com.views.commonViews.AllCommonView;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.util.Map;


public class LoginDataDrivenTest extends BaseTest {

	private LoginPageView objLoginPageView;
	private AllCommonView objAllCommonView;

	// Initialize Views
	public void initializeViewsAndPages() {
		objLoginPageView = new LoginPageView(this);
		objAllCommonView = new AllCommonView(this);


	}

	@BeforeClass(groups = { "P1", "P2", "P3" })
	@Parameters({"EdinaRealityDataTable"})
	public void initializeEnvironment(String excelName) {
		this.initializeWebEnvironmentForDataDriven(excelName);
		excelMethods.setSheetName("SignIn");
		this.initializeViewsAndPages();
		objAllCommonView.setResourceName("Swagat Mohapatra");

	}

	@BeforeMethod(groups = { "P1", "P2", "P3" })
	public void refreshWholePage() {
		objAllCommonView.refreshWholePageF5();

	}


	@Test(priority = 1, groups = { "P1" },dataProvider="inputs", dataProviderClass= ExcelDataProvider.class)
	public void SignInLocalTest(Map<String,String> data)  {
		if (data.get("Active").equalsIgnoreCase("y") || data.get("Active").equalsIgnoreCase("yes")){
			getObjUtilities().logReporterInfoOnly(data.get("Report Title"));
			objLoginPageView.loginToApplication(data.get("User ID"), data.get("Password"));
		    objLoginPageView.verifyUserSuccessfullyLoggedIn(data.get("Expected Login Name"));
		    objLoginPageView.logoutFromTheApplication();
		}else{
			throw new SkipException(data.get("Report Title"));
		}


	}



	@AfterClass(alwaysRun = true, groups = { "P1", "P2", "P3" })
	public void tearDownEnvironment() {
		tearDownWebEnvironment();
		objLoginPageView =null;
		objAllCommonView = null;
	}

}