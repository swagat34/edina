package com.pageFactory.LoginPage;

import com.generic.GeneralUtil;
import com.generic.GoogleAPI;
import com.generic.OTPMethods;
import com.generic.Pojo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class LoginPage {
    private Pojo objPojo;
    private String strReturnValue = "";
    private boolean blnReturnStatus = false;
    private Integer intReturnValue = null;
    private WebElement returnWebElement = null;

    public LoginPage(Pojo pojo) {
        this.objPojo = pojo;
        PageFactory.initElements(objPojo.getDriver(), this);
    }

// ~~~ Main Locators ~~~ //

    //Main Page
    @FindBy(id = "searchBtn")
    protected WebElement searchButton;

    // ~~~ Login Locators ~~~ //

    //Login
    @FindBy(xpath = "//div[@class='account-link login-link flyout-closed']//a[@id='signinAjax']")
    protected WebElement signinPageButton;

    @FindBy(xpath = "(//i[@class='user'])[2]")
    protected WebElement signinPageButton2;

    @FindBy(id = "signInName")
    protected WebElement emailField;

    @FindBy(id = "password")
    protected WebElement passwordField;

    @FindBy(id = "rememberMe")
    protected WebElement rememberMeCheckbox;

    @FindBy(id = "next")
    protected WebElement signinButton;

    @FindBy(xpath = "//div[@class='error pageLevel']//p")
    protected WebElement loginErrorMessage;

    @FindBy(xpath = "//div[@class='nameHeader']//div[@class='nameText']")
    protected List<WebElement> loginNameList;

    //Logout
    @FindBy(xpath = "//span[@class='nameInitals']")
    protected WebElement userAccountDropDown;

    @FindBy(xpath = "//a[contains(text(), 'Sign out')]")
    protected WebElement signoutButton;

    // ~~~ Create Account Locators ~~~ //

    //Create Account
    @FindBy(xpath = "//a[contains(text(), 'Sign up now')]")
    protected WebElement signupButton;

    @FindBy(id = "email")
    protected WebElement newEmailAddressField;

    @FindBy(id = "emailVerificationControl_but_send_code")
    protected WebElement sendVerificationButton;

    @FindBy(id = "VerificationCode")
    protected WebElement verificationCodeField;

    @FindBy(id = "emailVerificationControl_but_verify_code")
    protected WebElement verifyCodeButton;

    @FindBy(xpath = "//div[contains(text(), 'The code has been verified. You can now continue.')]")
    protected WebElement successfulVerificationLabel;

    @FindBy(id = "givenName")
    protected WebElement newFirstNameField;

    @FindBy(id = "surName")
    protected WebElement newLastNameField;

    @FindBy(id = "newPassword")
    protected WebElement newPasswordField;

    @FindBy(id = "reenterPassword")
    protected WebElement confirmNewPasswordField;

    @FindBy(id = "mfaConsentChoice_AgreeToMfaYes")
    protected WebElement multiFactorAuthenticationCheckbox;

    @FindBy(id = "continue")
    protected WebElement createAccountButton;

    //
    @FindBy(id = "number")
    protected WebElement newPhoneNumberField;

    @FindBy(id = "sendCode")
    protected WebElement sendPhoneNumberCodeButton;

    @FindBy(id = "verificationCode")
    protected WebElement verifyPhoneNumberCodeField;

    @FindBy(id = "verifyCode")
    protected WebElement verifyPhoneNumberCodeButton;


    @FindBy(xpath = "//input[@type='email']")
    protected WebElement enterMicrosoftEmail;


    @FindBy(xpath = "//button[@id='MicrosoftAccountExchange']")
    protected WebElement signInSignUpUsingMicrosoft;


    @FindBy(xpath = "//span[normalize-space(text())='Next']")
    protected WebElement next;

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement MicrosoftNext_Button;

    @FindBy(xpath = "(//input[@type='submit'])[2]")
    protected WebElement Microsoftyes_Button;

    @FindBy(xpath = "//a[contains(text(),'Email code to')]")
    protected WebElement EmailCodeTo_Link;

    @FindBy(xpath = "//a[contains(text(),'Use your password instead')]")
    protected WebElement UsePassword_Link;


    @FindBy(xpath = "//input[@id='idTxtBx_OTC_Password']")
    protected WebElement inp_microsoftSendCode;

    @FindBy(xpath = "//input[@name='passwd']")
    protected WebElement inp_microsoftPassword;

    @FindBy(xpath = "//input[@aria-label='Enter your password']")
    protected WebElement enterPassword;

    @FindBy(xpath = "//button[@aria-label='Sign Up']")
    protected WebElement signUp_button;


    /**
     * Click On Login Button Present in Login Page By Swagat
     */
    public void clickSearchButtonAndSignInButton() {
        objPojo.getObjWrapperFunctions().waitFor(12);
        objPojo.getObjUtilities().logReporter("Click On Search Button ",
                objPojo.getObjWrapperFunctions().click(searchButton));
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Click On Sign In Button ",
                objPojo.getObjWrapperFunctions().click(signinPageButton));


    }


    /**
     * Entering UserName in Login Page By Swagat
     */
    public void setSignInPageUserName(String UserName) {
        objPojo.getObjUtilities().logReporter("Enter User Name in Login Page : ", UserName,
                objPojo.getObjWrapperFunctions().setText(emailField, UserName));

    }

    /**
     * Entering Password in Login Page By Swagat
     */
    public void setSigninPagePassword(String Password) {
        objPojo.getObjUtilities().logReporter("Enter Password in Login Page : ", Password,
                objPojo.getObjWrapperFunctions().setText(passwordField, Password));
    }

    public void clickLoginPageSignInbutton() {

        objPojo.getObjUtilities().logReporter("Click on Sign In Button present in Sign In Page",
                objPojo.getObjWrapperFunctions().click(signinButton));
        objPojo.getObjWrapperFunctions().waitFor(12);


    }


    public void verifyUserSuccessfullyLoggedIn(String userName) {
        String loginName = "";
        //Retrieve the full name of the logged in user & place it into a single string variable
        for (int i = 0; i < loginNameList.size(); i++) {
            loginName += loginNameList.get(i).getText() + " ";
        }

        //Remove the last char of the full name (which will be an unnecessary space)
        if (loginName.length() != 0) {
            loginName = loginName.substring(0, loginName.length() - 1);
        }
        objPojo.getObjUtilities().logReporter("Verify User is successfully logged In",
                loginName.contains(userName));

    }


    public void logoutFromTheApplication() {
        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Sign Out button",
                objPojo.getObjWrapperFunctions().clickUsingActions(signoutButton));
    }

    public void navigateToMilestonepage() {
        objPojo.getObjWrapperFunctions().setURL(objPojo.getServerAndPort() + "milestones");
        objPojo.getObjWrapperFunctions().waitFor(30);
        objPojo.getObjWrapperFunctions().waitPageLoaded();
    }

    public void createAccount(Map<String, String> data) {
        this.clickSearchButtonAndSignInButton();
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjWrapperFunctions().waitPageLoaded();
        objPojo.getObjUtilities().logReporter("Click on Sign Up Now",
                objPojo.getObjWrapperFunctions().click(signupButton));
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Enter New Email Address Field in Sign Up Page : ", data.get("User ID"),
                objPojo.getObjWrapperFunctions().setText(newEmailAddressField, data.get("User ID")));
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Click on Send Verification Button",
                objPojo.getObjWrapperFunctions().click(sendVerificationButton));
        objPojo.getObjWrapperFunctions().waitFor(10);
        objPojo.getObjUtilities().logReporter("Enter Verification Code in Sign Up Page : ", GoogleAPI.getEmailBody("notifications"),
                objPojo.getObjWrapperFunctions().setText(verificationCodeField, GoogleAPI.getEmailBody("notifications")));
        objPojo.getObjUtilities().logReporter("Click on Code Verification Button",
                objPojo.getObjWrapperFunctions().click(verifyCodeButton));
        objPojo.getObjWrapperFunctions().waitFor(10);
        objPojo.getObjUtilities().logReporter("The verification process for the new email Passed or Failed",
                objPojo.getObjWrapperFunctions().checkElementDisplayed(successfulVerificationLabel));
        objPojo.getObjUtilities().logReporter("Enter New Email First Name Field in Sign Up Page : ", data.get("First Name"),
                objPojo.getObjWrapperFunctions().setText(newFirstNameField, data.get("First Name")));
        objPojo.getObjUtilities().logReporter("Enter New Email Last Name Field in Sign Up Page : ", data.get("Last Name"),
                objPojo.getObjWrapperFunctions().setText(newLastNameField, data.get("Last Name")));
        objPojo.getObjUtilities().logReporter("Enter New Email Password Field in Sign Up Page : ", data.get("Password"),
                objPojo.getObjWrapperFunctions().setText(newPasswordField, data.get("Password")));
        objPojo.getObjUtilities().logReporter("Enter New Email Confirm Password Field in Sign Up Page : ", data.get("Password"),
                objPojo.getObjWrapperFunctions().setText(confirmNewPasswordField, data.get("Password")));

        objPojo.getObjWrapperFunctions().waitFor(10);
        if (data.get("Multi-Factor Authentication").equalsIgnoreCase("y") || data.get("Multi-Factor Authentication").equalsIgnoreCase("yes")) {
            objPojo.getObjUtilities().logReporter("Click on Multi Factor Authentication Checkbox",
                    objPojo.getObjWrapperFunctions().click(multiFactorAuthenticationCheckbox));
            objPojo.getObjUtilities().logReporter("Click on create Account Button ",
                    objPojo.getObjWrapperFunctions().click(createAccountButton));
            objPojo.getObjUtilities().logReporter("Enter New Phone Number Field in Sign Up Page : ", data.get("Phone Number"),
                    objPojo.getObjWrapperFunctions().setText(newPhoneNumberField, data.get("Phone Number")));
            objPojo.getObjUtilities().logReporter("Click on send Phone Number Code Button ",
                    objPojo.getObjWrapperFunctions().click(sendPhoneNumberCodeButton));
            objPojo.getObjWrapperFunctions().waitFor(8);
            objPojo.getObjUtilities().logReporter("Click on verify Phone Number Code Field ",
                    objPojo.getObjWrapperFunctions().click(verifyPhoneNumberCodeField));
            objPojo.getObjUtilities().logReporter("Enter New Phone Number Field in Sign Up Page : ", OTPMethods.outputOTPNumber(),
                    objPojo.getObjWrapperFunctions().setText(verifyPhoneNumberCodeField, OTPMethods.outputOTPNumber()));
            objPojo.getObjUtilities().logReporter("Click on verify Phone Number Code Button ",
                    objPojo.getObjWrapperFunctions().click(verifyPhoneNumberCodeButton));

        } else {
            objPojo.getObjUtilities().logReporter("Click on create Account Button ",
                    objPojo.getObjWrapperFunctions().click(createAccountButton));

        }

    }


    public void createAccountUsingMicrosoftAccount(Map<String, String> data) {

        this.clickSearchButtonAndSignInButton();
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjWrapperFunctions().waitPageLoaded();
        objPojo.getObjUtilities().logReporter("Click on Sign Up Now",
                objPojo.getObjWrapperFunctions().click(signInSignUpUsingMicrosoft));
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Click on Enter Microsoft Email Field ",
                objPojo.getObjWrapperFunctions().click(enterMicrosoftEmail));
        if (objPojo.getDriver().findElements(By.xpath("//input[@type='email']")).size() > 0) {
            objPojo.getObjUtilities().logReporter("Enter New Email Address Field in Sign Up Page : ", data.get("User ID"),
                    objPojo.getObjWrapperFunctions().setText(enterMicrosoftEmail, data.get("User ID")));
            objPojo.getObjWrapperFunctions().waitFor(2);
            objPojo.getObjUtilities().logReporter("Click on  Microsoft Next Button ",
                    objPojo.getObjWrapperFunctions().click(MicrosoftNext_Button));

            objPojo.getObjWrapperFunctions().waitFor(4);
            if (objPojo.getDriver().findElements(By.xpath("//a[contains(text(),'Use your password instead')]")).size() > 0) {
                objPojo.getObjUtilities().logReporter("Click on Use Password Link ",
                        objPojo.getObjWrapperFunctions().click(UsePassword_Link));

            }
        }
        objPojo.getObjWrapperFunctions().waitFor(12);


        objPojo.getObjUtilities().logReporter("Click on  Microsoft Password Field ",
                objPojo.getObjWrapperFunctions().click(inp_microsoftPassword));

        objPojo.getObjUtilities().logReporter("Enter New Email Address Field in Sign Up Page : ", data.get("Password"),
                objPojo.getObjWrapperFunctions().setText(inp_microsoftPassword, data.get("Password")));
        objPojo.getObjUtilities().logReporter("Click on  Microsoft Next Button ",
                objPojo.getObjWrapperFunctions().click(MicrosoftNext_Button));
        if (objPojo.getDriver().findElements(By.xpath("//input[@type='submit']")).size() > 0) {
            objPojo.getObjUtilities().logReporter("Click on  Microsoft Next Button ",
                    objPojo.getObjWrapperFunctions().click(MicrosoftNext_Button));
        }
        if (objPojo.getDriver().findElements(By.xpath("(//input[@type='submit'])[2]")).size() > 0) {
            objPojo.getObjUtilities().logReporter("Click on  Microsoft Yes Button ",
                    objPojo.getObjWrapperFunctions().click(Microsoftyes_Button));
        }

        objPojo.getObjWrapperFunctions().waitFor(12);
        StringBuffer str = new StringBuffer("Test@artofqa.com");
        String newemail = str.insert(4, GeneralUtil.generatingRandomNumericString()).toString();
        System.out.println(newemail);
        objPojo.getObjUtilities().logReporter("Clear new EMail Address Field ",
                objPojo.getObjWrapperFunctions().clearText(newEmailAddressField));
        objPojo.getObjUtilities().logReporter("Enter new EMail Address Field ", newemail,
                objPojo.getObjWrapperFunctions().setText(newEmailAddressField, newemail));
        objPojo.getObjUtilities().logReporter("CLick Send Verification Code Button",
                objPojo.getObjWrapperFunctions().click(sendVerificationButton));
        objPojo.getObjWrapperFunctions().waitFor(12);
        objPojo.getObjUtilities().logReporter("Enter Verification Code in Sign Up Page : ", GoogleAPI.getEmailBody("notifications"),
                objPojo.getObjWrapperFunctions().setText(verificationCodeField, GoogleAPI.getEmailBody("notifications")));
        objPojo.getObjWrapperFunctions().waitFor(8);
        objPojo.getObjUtilities().logReporter("CLick  Verification Code Button",
                objPojo.getObjWrapperFunctions().click(verifyCodeButton));
        objPojo.getObjWrapperFunctions().waitFor(8);
        objPojo.getObjUtilities().logReporter("The verification process for the new email Passed or Failed",
                objPojo.getObjWrapperFunctions().checkElementDisplayed(successfulVerificationLabel));
        objPojo.getObjUtilities().logReporter("CLick  Sign Up Button",
                objPojo.getObjWrapperFunctions().click(signUp_button));
        objPojo.getObjWrapperFunctions().waitFor(8);
        objPojo.getObjUtilities().logReporter("Clear new Email Address Field ",
                objPojo.getObjWrapperFunctions().clearText(newFirstNameField));
        objPojo.getObjUtilities().logReporter("Enter New Email First Name Field in Sign Up Page : ", data.get("First Name"),
                objPojo.getObjWrapperFunctions().setText(newFirstNameField, data.get("First Name")));
        objPojo.getObjUtilities().logReporter("Clear new Email Last Name Field ",
                objPojo.getObjWrapperFunctions().clearText(newLastNameField));
        objPojo.getObjUtilities().logReporter("Enter New Email Last Name Field in Sign Up Page : ", data.get("Last Name"),
                objPojo.getObjWrapperFunctions().setText(newLastNameField, data.get("Last Name")));
        objPojo.getObjUtilities().logReporter("CLick  Sign Up Button",
                objPojo.getObjWrapperFunctions().click(signUp_button));
        objPojo.getObjWrapperFunctions().waitFor(12);
    }


}
