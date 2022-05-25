package com.views.AccountPageView;

import com.generic.Pojo;
import com.pageFactory.AccountPage.AccountPage;
import com.pageFactory.LoginPage.LoginPage;
import com.pageFactory.SavedSearchPage.SavedSearchPage;

import java.util.Map;

public class AccountPageView {

    String testData = "";
    String testData1 = "";
    private Pojo objPojo;
    private LoginPage objLoginPage;
    private AccountPage objAccountPage;


    public AccountPageView(Pojo objPojo) {
        this.objPojo = objPojo;
        objLoginPage = new LoginPage(objPojo);
        objAccountPage = new AccountPage(objPojo);


    }


    public void updatePhoneNumber(String s) {
        objAccountPage.updatePhoneNumber(s);

    }

    public void updatePhoneNumberPart2(String s, String s1, String s2) {
        objAccountPage.updatePhoneNumberPart2(s,s1,s2);
    }

    public void confirmOTPCode(String otpNumber) {
        objAccountPage.confirmOTPCode(otpNumber);
    }

    public void updateName(Map<String, String> data) {
        objAccountPage.updateName(data);
    }

    public void verifySavedInfoViaRefresh(Map<String, String> data) {
        objAccountPage.verifySavedInfoViaRefresh(data);
    }

    public void verifySavedInfo(Map<String, String> data) {
        objAccountPage.verifySavedInfo(data);
    }
}
