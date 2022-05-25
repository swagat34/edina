package com.datadriventestcases;

import com.generic.BaseTest;
import com.generic.ExcelDataProvider;
import com.views.BrokenLinksAndImageView.BrokenLinkAndImageView;
import com.views.LoginPageView.LoginPageView;
import com.views.SearchPageView.SavedSearchVIew;
import com.views.commonViews.AllCommonView;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.util.Map;


public class BrokenLinksTest extends BaseTest {

    private LoginPageView objLoginPageView;
    private AllCommonView objAllCommonView;
    private BrokenLinkAndImageView objBrokenLinkAndImageView;

    // Initialize Views
    public void initializeViewsAndPages() {
        objLoginPageView = new LoginPageView(this);
        objAllCommonView = new AllCommonView(this);
        objBrokenLinkAndImageView = new BrokenLinkAndImageView(this);
    }

    @BeforeClass(groups = { "P1", "P2", "P3" })
    @Parameters({"EdinaRealityDataTable"})
    public void initializeEnvironment(String excelName) {
        this.initializeWebEnvironmentForDataDriven(excelName);
        excelMethods.setSheetName("VerifyBrokenLinks");
        this.initializeViewsAndPages();
        objAllCommonView.setResourceName("Swagat Mohapatra");

    }

    @BeforeMethod(groups = { "P1", "P2", "P3" })
    public void refreshWholePage() {
        objAllCommonView.refreshWholePageF5();

    }


    @Test(priority = 1, groups = { "P1" },dataProvider="inputs", dataProviderClass= ExcelDataProvider.class)
    public void verifyBrokenLinksTest(Map<String,String> data)  {

        if (data.get("Active").equalsIgnoreCase("y") || data.get("Active").equalsIgnoreCase("yes")) {
            getObjUtilities().logReporterInfoOnly(data.get("Report Title"));
            objLoginPageView.loginToApplication(getObjConfig().getProperty("userName"), getObjConfig().getProperty("password"));
            objBrokenLinkAndImageView.verifyTopLinksHaveValidLinks();
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
        objBrokenLinkAndImageView = null;
    }

}



