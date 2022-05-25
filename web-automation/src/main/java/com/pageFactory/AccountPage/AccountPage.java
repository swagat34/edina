package com.pageFactory.AccountPage;

import com.generic.Pojo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AccountPage {
    private Pojo objPojo;
    private String strReturnValue = "";
    private boolean blnReturnStatus = false;
    private Integer intReturnValue = null;
    private WebElement returnWebElement = null;
    public static ArrayList<String> propertyCost = new ArrayList<>();
    public static ArrayList<String> propertyMLS =  new ArrayList<>();
    public static ArrayList<String>  fullAddress = new ArrayList<>();

    public AccountPage(Pojo pojo) {
        this.objPojo = pojo;
        PageFactory.initElements(objPojo.getDriver(),this);
    }

    //Logout Locators
    @FindBy(id="searchBtn")
    protected WebElement searchButton;

    @FindBy(xpath="//a[contains(text(), 'Find a home')]")
    protected WebElement findAHomeButton;

    @FindBy(xpath="//span[@class='nameInitals']")
    protected WebElement userAccountDropDown;

    @FindBy(xpath="//a[contains(text(), 'Settings')]")
    protected WebElement accountSettingsButton;

    @FindBy(xpath="//a[contains(text(), 'Favorite properties')]")
    protected WebElement favoritePropertiesButton;

    @FindBy(xpath="//a[contains(text(), 'Saved searches')]")
    protected WebElement savedSearchesButton;

    //Update Account Info
    @FindBy(id="firstName")
    protected WebElement accountFirstNameField;

    @FindBy(id="lastName")
    protected WebElement accountLastNameField;

    @FindBy(xpath="//div[@class='updateProName show']")
    protected WebElement updatedNameLabel;

    @FindBy(id="updateProfileName")
    protected WebElement updateAccountNameButton;

    //Phones
    @FindBy(id="phone")
//	protected WebElement accountPhoneField;
    protected List<WebElement> accountPhoneField;

    @FindBy(xpath="//button[contains(text(), 'Update phone number')]")
    protected WebElement updateAccountPhoneButton;

    @FindBy(xpath="//a[@class='settingNameLink']")
    protected WebElement updateAccountPhoneNumberButton;

    @FindBy(xpath="//button[@class='accountButton']")
    protected WebElement signinViaEmailButton;

    @FindBy(id="signInName")
    protected WebElement signinEmailAddressField;

    @FindBy(id="password")
    protected WebElement signinPasswordField;

    @FindBy(id="continue")
    protected WebElement signinConfirmButton;

    @FindBy(id="number")
    protected WebElement enterPhonNumberField;

    @FindBy(id="sendCode")
    protected WebElement sendOTPCodeButton;

    @FindBy(id="verificationCode")
    protected WebElement verifyOTPCodeField;

    @FindBy(id="verifyCode")
    protected WebElement verifyOTPCodeButton;

    @FindBy(id="mfaUnknownDeviceConsentChoice_AgreeToMfaUnknownDeviceYes")
    protected WebElement doNotVerifyMyDeviceCheckbox;

    //email
    @FindBy(id="emailAddress")
    protected WebElement accountEmailField;



    public void updatePhoneNumber(String s) {
        objPojo.getObjWrapperFunctions().waitFor(12);
        objPojo.getObjUtilities().logReporter("Click on Find a Home Button",
                objPojo.getObjWrapperFunctions().click(findAHomeButton));
        objPojo.getObjWrapperFunctions().waitFor(4);

        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Sign Out button",
                objPojo.getObjWrapperFunctions().clickUsingActions(accountSettingsButton));
        objPojo.getObjWrapperFunctions().waitFor(12);


        if (accountPhoneField.get(1).getAttribute("value").equalsIgnoreCase("")) {
            System.out.println(accountPhoneField.get(1).getAttribute("value"));
            accountPhoneField.get(1).sendKeys(s);
        }
        objPojo.getObjWrapperFunctions().waitFor(4);
        objPojo.getObjUtilities().logReporter("Click on Update Account Phone Button",
                objPojo.getObjWrapperFunctions().click(updateAccountPhoneButton));
        objPojo.getObjWrapperFunctions().waitFor(4);
    }

    public void updatePhoneNumberPart2(String username, String password, String phoneNumber) {

        objPojo.getObjUtilities().logReporter("Updating phone number, Click On Account Phone Number Button ",
                objPojo.getObjWrapperFunctions().click(updateAccountPhoneNumberButton));
        objPojo.getObjWrapperFunctions().waitFor(4);
        objPojo.getObjUtilities().logReporter( "Click On Sign In Via Email Button ",
                objPojo.getObjWrapperFunctions().click(signinViaEmailButton));

        objPojo.getObjWrapperFunctions().waitFor(2);

        objPojo.getObjUtilities().logReporter( "Set Sign In Email Address Field ",
                objPojo.getObjWrapperFunctions().setText(signinEmailAddressField,username));
        objPojo.getObjUtilities().logReporter( "Set Sign In Email Password Field ",
                objPojo.getObjWrapperFunctions().setText(signinPasswordField,password));

        objPojo.getObjUtilities().logReporter( "Click On Sign In Confirm Button ",
                objPojo.getObjWrapperFunctions().click(signinConfirmButton));
        objPojo.getObjWrapperFunctions().waitFor(2);
        objPojo.getObjUtilities().logReporter( "Set Phone Number Field ",
                objPojo.getObjWrapperFunctions().setText(enterPhonNumberField,phoneNumber));
        objPojo.getObjUtilities().logReporter( "Click On send OTP Code Button ",
                objPojo.getObjWrapperFunctions().click(sendOTPCodeButton));

    }

    public void confirmOTPCode(String otpCode) {

        objPojo.getObjUtilities().logReporter( "Set Phone Number OTP Code Field ",
                objPojo.getObjWrapperFunctions().setText(verifyOTPCodeField,otpCode));

        objPojo.getObjUtilities().logReporter( "Click On OTP Code Button ",
                objPojo.getObjWrapperFunctions().click(verifyOTPCodeButton));
    }


    public void updateName(Map<String, String> data) {

        objPojo.getObjWrapperFunctions().waitFor(4);
        objPojo.getObjUtilities().logReporter("Click on Find a Home Button",
                objPojo.getObjWrapperFunctions().click(findAHomeButton));
        objPojo.getObjWrapperFunctions().waitFor(4);

        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Sign Out button",
                objPojo.getObjWrapperFunctions().clickUsingActions(accountSettingsButton));
        objPojo.getObjWrapperFunctions().waitFor(12);
        objPojo.getObjUtilities().logReporter("Clear Account First Name Field",
                objPojo.getObjWrapperFunctions().clearText(accountFirstNameField));
        objPojo.getObjUtilities().logReporter("Set Account First Name Field",
                objPojo.getObjWrapperFunctions().setText(accountFirstNameField , data.get("First Name")));

        objPojo.getObjUtilities().logReporter("Clear Account Last Name Field",
                objPojo.getObjWrapperFunctions().clearText(accountLastNameField));
        objPojo.getObjUtilities().logReporter("Set Account Last Name Field",
                objPojo.getObjWrapperFunctions().setText(accountLastNameField , data.get("Last Name")));
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Click on Update Account Name Button",
                objPojo.getObjWrapperFunctions().click(updateAccountNameButton));
        objPojo.getObjWrapperFunctions().waitFor(5);
    }


    public void verifySavedInfoViaRefresh(Map<String, String> data) {

        objPojo.getObjUtilities().logReporter("The 'Successfully Updated Name' label appeared",
                objPojo.getObjWrapperFunctions().checkElementDisplayed(updatedNameLabel));
        objPojo.getObjWrapperFunctions().pageRefresh();
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Verifying First Name",
                objPojo.getObjWrapperFunctions().getAttribute(accountFirstNameField,"value").equalsIgnoreCase(data.get("First Name")));
        objPojo.getObjUtilities().logReporter("Verifying Last Name",
                objPojo.getObjWrapperFunctions().getAttribute(accountLastNameField,"value").equalsIgnoreCase(data.get("Last Name")));
        objPojo.getObjWrapperFunctions().waitFor(10);
    }

    public void verifySavedInfo(Map<String, String> data) {

        objPojo.getObjWrapperFunctions().waitFor(4);
        objPojo.getObjUtilities().logReporter("Click on Find a Home Button",
                objPojo.getObjWrapperFunctions().click(findAHomeButton));
        objPojo.getObjWrapperFunctions().waitFor(4);

        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Sign Out button",
                objPojo.getObjWrapperFunctions().clickUsingActions(accountSettingsButton));
        objPojo.getObjWrapperFunctions().waitFor(12);

        objPojo.getObjUtilities().logReporter("Verifying First Name",
                objPojo.getObjWrapperFunctions().getAttribute(accountFirstNameField,"value").equalsIgnoreCase(data.get("First Name")));
        objPojo.getObjUtilities().logReporter("Verifying Last Name",
                objPojo.getObjWrapperFunctions().getAttribute(accountLastNameField,"value").equalsIgnoreCase(data.get("Last Name")));
        objPojo.getObjWrapperFunctions().waitFor(10);
    }
}
