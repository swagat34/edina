package com.datadriventestcases;

import com.generic.BaseTest;
import com.generic.ExcelDataProvider;
import com.views.LoginPageView.LoginPageView;
import com.views.SearchPageView.SavedSearchVIew;
import com.views.commonViews.AllCommonView;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.util.Map;


public class SavedPropertiesTest extends BaseTest {

    private LoginPageView objLoginPageView;
    private AllCommonView objAllCommonView;
    private SavedSearchVIew objSavedSearchVIew;

    // Initialize Views
    public void initializeViewsAndPages() {
        objLoginPageView = new LoginPageView(this);
        objAllCommonView = new AllCommonView(this);
        objSavedSearchVIew = new SavedSearchVIew(this);
    }

    @BeforeClass(groups = { "P1", "P2", "P3" })
    @Parameters({"EdinaRealityDataTable"})
    public void initializeEnvironment(String excelName) {
        this.initializeWebEnvironmentForDataDriven(excelName);
        excelMethods.setSheetName("Saved Properties");
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
    public void savedPropertiesTest(Map<String,String> data)  {

            if (data.get("Active").equalsIgnoreCase("y") || data.get("Active").equalsIgnoreCase("yes")) {
                getObjUtilities().logReporterInfoOnly(data.get("Report Title"));
                objLoginPageView.loginToApplication(getObjConfig().getProperty("userName"), getObjConfig().getProperty("password"));
                objSavedSearchVIew.deleteExistingFavouriteSearch();
                objSavedSearchVIew.performSearch(data);
                objSavedSearchVIew.saveFirstThreeProperties();
                objSavedSearchVIew.verifySavedProperties();
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
        objSavedSearchVIew = null;
    }

}