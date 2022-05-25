package com.views.LoginPageView;

import com.generic.Pojo;
import com.pageFactory.LoginPage.LoginPage;

import java.util.Map;

public class LoginPageView {

    String testData ="";
    private Pojo objPojo;
    private LoginPage objLoginPage;


    public LoginPageView(Pojo objPojo) {
        this.objPojo = objPojo;
        objLoginPage = new LoginPage(objPojo);

    }

    /*
     Author : Swagat Mohapatra
     Module :
     Excel Parameter Key : UserName , Password
     Description : Use below function to Login to the Application
    */
    public void loginToApplication(String userName , String password) {
        objLoginPage.clickSearchButtonAndSignInButton();
        objLoginPage.setSignInPageUserName(userName);
        objLoginPage.setSigninPagePassword(password);
        objLoginPage.clickLoginPageSignInbutton();

        }


    public void verifyUserSuccessfullyLoggedIn(String userName) {
        objLoginPage.verifyUserSuccessfullyLoggedIn(userName);
    }

    public void logoutFromTheApplication() {
        objLoginPage.logoutFromTheApplication();
    }

    public void navigateToMilestonepage() {
        objLoginPage.navigateToMilestonepage();
    }

    public void createAccountAndSignUp(Map<String, String> data) {
        if(data.get("Account Type").trim().equalsIgnoreCase("Local Account")){
            objLoginPage.createAccount(data);
        }else if(data.get("Account Type").trim().equalsIgnoreCase("Microsoft Account")){
            objLoginPage.createAccountUsingMicrosoftAccount(data);
        }
    }
}
