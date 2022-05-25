package com.datadriventestcases;

import com.generic.BaseTest;
import com.generic.ExcelDataProvider;
import com.generic.OTPMethods;
import com.views.AccountPageView.AccountPageView;
import com.views.LoginPageView.LoginPageView;
import com.views.commonViews.AllCommonView;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.util.Map;

public class MySettings extends BaseTest {

    private LoginPageView objLoginPageView;
    private AllCommonView objAllCommonView;
    private AccountPageView objAccountPageView;

    // Initialize Views
    public void initializeViewsAndPages() {
        objLoginPageView = new LoginPageView(this);
        objAllCommonView = new AllCommonView(this);
        objAccountPageView = new AccountPageView(this);
    }

    @BeforeClass(groups = { "P1", "P2", "P3" })
    @Parameters({"EdinaRealityDataTable"})
    public void initializeEnvironment(String excelName) {
        this.initializeWebEnvironmentForDataDriven(excelName);
        excelMethods.setSheetName("My Settings");
        this.initializeViewsAndPages();
        objAllCommonView.setResourceName("Swagat Mohapatra");

    }

    @BeforeMethod(groups = { "P1", "P2", "P3" })
    public void refreshWholePage() {
        objAllCommonView.refreshWholePageF5();

    }
    @AfterMethod(groups = { "P1", "P2", "P3" })
    public void recoveryScenario(ITestResult result) {
        if(result.getStatus() != ITestResult.SUCCESS){
            objLoginPageView.logoutFromTheApplication();
        }

    }

    @Test(priority = 1, groups = { "P1" },dataProvider="inputs", dataProviderClass= ExcelDataProvider.class)
    public void MySettingsTest(Map<String,String> data)  {

        if (data.get("Active").equalsIgnoreCase("y") || data.get("Active").equalsIgnoreCase("yes")) {
            getObjUtilities().logReporterInfoOnly(data.get("Report Title"));
            objLoginPageView.loginToApplication(getObjConfig().getProperty("userName"), getObjConfig().getProperty("password"));
            objAccountPageView.updatePhoneNumber("3332221111");
            objAccountPageView.updatePhoneNumberPart2("Cynthia.Astralis@gmail.com", "Akatsukimember1!", "9378844148");
            //Retrieve the OTP value
            String OTPNumber = OTPMethods.outputOTPNumber();
            objAccountPageView.confirmOTPCode(OTPNumber);
            objAccountPageView.updateName(data);
            objAccountPageView.verifySavedInfoViaRefresh(data);
            objAccountPageView.verifySavedInfo(data);
            objLoginPageView.logoutFromTheApplication();

        } else {
            throw new SkipException(data.get("Report Title"));
        }

    }



    @AfterClass(alwaysRun = true, groups = { "P1", "P2", "P3" })
    public void tearDownEnvironment() {
        tearDownWebEnvironment();
        objLoginPageView =null;
        objAllCommonView = null;
        objAccountPageView = null;
    }

}
