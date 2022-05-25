package com.generic;

import com.google.common.base.Function;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ScriptName : WrapperFunctions
 * @Description : Core wrapper function required for framework
 * @Author : Swagat
 */

public class WrapperFunctions extends LoadableComponent<WrapperFunctions> {


	private Pojo objPojo;
	private RobotClass objRobotClass;
	private boolean blnReturnStatus = false;
	private String strReturnValue = "";
	private String setException = "";
	private Integer countOfThreads = null;
	public static String failedMethod = "";
	private String methodName = "";
	private String method = "";
	private String parent = "";

	public WrapperFunctions(Pojo pojo) {

		this.objPojo = pojo;
		objRobotClass = new RobotClass();
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 * locator - By identification of element
	 */
	public void waitForElementPresence(By locator) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}



	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 * locator - By identification of element
	 */
	public void waitForPresenceOfNestedElementLocated(WebElement webElement, By sub_locator) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, sub_locator));
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 * locator - By identification of element
	 */
	public void waitForPresenceOfNestedElementsLocated(By locator, By sub_locator) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator));
	}

	/**
	 * @Description : This is wrapper method wait for element to be clickable
	 * @param :
	 * locator - By identification of element
	 */
	public void waitForElementToBeClickable(By locator) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @Description : This is wrapper method wait for element to be clickable
	 * @param :
	 * WebElement - By identification of element
	 */
	public void waitForElementToBeClickable(WebElement element) {
		try {
			objPojo.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
		}catch(Exception exception){
			commonExceptions(exception);
		}
	}


	public void waitForElementVisibility(WebElement webElement) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element
	 * located
	 * @param :
	 * locator - By identification of element
	 */
	public void waitForElementInVisibilityLocated(WebElement webElement) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.invisibilityOf(webElement));
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param :
	 * timeUnitSeconds - wait time seconds
	 */
	public boolean waitFor(int timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param :
	 * timeUnitSeconds - wait time seconds
	 */
	public boolean waitFor(String timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(Integer.parseInt(timeUnitSeconds), TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	/**
	 * @Description : This is wrapper method to check the web element is
	 * displayed on the page
	 * @param :
	 * locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean checkElementPresence(By locator) {
		try {
			this.waitForElementPresence(locator);
			return true;
		} catch (UnhandledAlertException exception) {
			this.alertBoxAccept();
			return false;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 * displayed on the page
	 * @param :
	 * locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean isElementDisplayed(By locator) {
		try {
			objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (UnhandledAlertException exception) {
			this.alertBoxAccept();
			return false;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 * displayed on the page
	 * @param :
	 * locator - By identification of element
	 * @return : - true if element present
	 */

	public boolean checkElementInVisible(WebElement webElement) {
		try {
			this.waitForElementInVisibilityLocated(webElement);
			return !webElement.isDisplayed();
		} catch (Exception exception) {
			return true;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 * displayed on the page
	 * @param :
	 * locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean waitForElementDisplayed(By locator, int timeInMilliSeconds) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(objPojo.getDriver(), timeInMilliSeconds);
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (UnhandledAlertException exception) {
			this.alertBoxAccept();
			return false;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	// Not using selenium void org.openqa.selenium.WebElement.clear() command
	// having open issue
	// with chrome hence using customized clear command
	// Please update all clear commands after issue close (Ref -
	// https://github.com/SeleniumHQ/selenium/issues/4110)

	public boolean clearText(WebElement webElement) {
		try {
			webElement.clear();
			//webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : selectRadioButton
	 * @Description : This is wrapper method select/deselect radio button
	 * @param :
	 * locator - By identification of element
	 * @param :
	 * status - select/deselect
	 * @author :Swagat
	 */
	public boolean selectRadioButton(WebElement webElement, boolean status) {
		try {
			waitForElementToBeClickable(webElement);
			if (webElement.getAttribute("type").equals("radio")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					this.click(webElement);
				return true;
			} else
				return false;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : mouseHover
	 * @Description : This is wrapper method used for Mouse Hovering to the
	 * element
	 * @param :
	 * locator - By identification of element
	 * @author :Swagat
	 */
	public boolean mouseHover(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			Actions actionBuilder = new Actions(objPojo.getDriver());
			actionBuilder.moveToElement(webElement).build().perform();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToFrameUsingIframe_Element
	 * @Description : This method will switch you to the Frame by Frame name
	 * @param :
	 * locator - The most common one. You locate your iframe like
	 * other elements, then pass it into the method
	 * @author : Swagat
	 */
	public boolean switchToFrameUsingIframeElement(WebElement webElement) {
		try {
			objPojo.getDriver().switchTo().defaultContent();
			objPojo.getDriver().switchTo().frame(webElement);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToFrameUsingNameOrId
	 * @Description : This method will switch you to the Frame by Frame name
	 * @param :
	 * frameName - Name/Id of frame you want to switch
	 * @author : Swagat
	 */
	public boolean switchToFrameUsingNameOrId(String frameName) {
		try {
			objPojo.getDriver().switchTo().defaultContent();
			objPojo.getDriver().switchTo().frame(frameName);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToFrameUsingIndex
	 * @Description : This method will switch you to the Frame by Frame name
	 * @param :
	 * index - Index on page
	 * @author : Swagat
	 */
	public boolean switchToFrameUsingIndex(int index) {
		try {
			objPojo.getDriver().switchTo().defaultContent();
			objPojo.getDriver().switchTo().frame(index);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToDefaultContent
	 * @Description : This method will switch you to the default Window
	 * @author : Swagat
	 */
	public void switchToDefaultContent() {
		try {
			objPojo.getDriver().switchTo().defaultContent();
		} catch (Exception exception) {
			commonExceptions(exception);
			return;
		}
	}

	/**
	 * @Method : getSelectedValueFromDropDown
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 * locator - By identification of element
	 * @author : Swagat
	 */
	public String getSelectedValueFromDropDown(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			Select selectDorpDown = new Select(webElement);
			String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
			return selectedDorpDownValue;
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}

	/**
	 * @Method : getAttribute
	 * @Description : This function return locator attribute
	 * @param :
	 * locator - By identification of element
	 * @author : Swagat
	 */
	public String getAttribute(WebElement webElement, String sElementAttribute) {
		try {
			waitForElementVisibility(webElement);
			return webElement.getAttribute(sElementAttribute);
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}


	/**
	 * @method : getTagName
	 * @param :
	 * locator
	 * @return : TageName for given locator
	 * @author : Swagat
	 *
	 */
	public String getTagName(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			return webElement.getTagName();
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}

	public void pageRefresh() {
		try {
			objPojo.getDriver().navigate().refresh();
		} catch (Exception exception) {
			commonExceptions(exception);

		}
	}

	/**
	 * @Method : getElementWidth
	 * @Description : This is wrapper method to get web element width
	 * @param :
	 * locator - By identification of element
	 * @return : - true if click successful
	 * @author : Swagat
	 */
	public int getElementWidth(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			waitForElementToBeClickable(webElement);

			return webElement.getSize().getWidth();
		} catch (Exception exception) {
			commonExceptions(exception);
			return 0;
		}
	}

	/**
	 * @Method : getElementHeight
	 * @Description : This is wrapper method to get web element Height
	 * @param :
	 * locator - By identification of element
	 * @return : - true if click successful
	 * @author : Swagat
	 */
	public int getElementHeight(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			waitForElementToBeClickable(webElement);
			return webElement.getSize().getHeight();
		} catch (Exception exception) {
			commonExceptions(exception);
			return 0;
		}
	}

	public List<WebElement> getWebElementList(By locator) {
		try {
			return objPojo.getDriver().findElements(locator);
		} catch (Exception exception) {
			commonExceptions(exception);
			return null;
		}
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
	}

	/**
	 * @Method : rightClick
	 * @Description : This is wrapper method to simulate mouse right click
	 * @param :
	 * locator - By identification of element
	 * @return : - true if right click action is successful
	 * @author :
	 */
	public boolean rightClick(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			waitForElementToBeClickable(webElement);
			Actions actionBuilder = new Actions(this.objPojo.getDriver());
			actionBuilder.contextClick(webElement).build().perform();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : get Table Column Header
	 * @Description : it will give table header size in whole table
	 * @param :
	 * WebElement - By identification of element (table with all
	 * headers)
	 * @author :
	 */
	public Hashtable<String, String> getTableColumnHeader(By weResultTableForThead) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForThead);
			waitForPresenceOfNestedElementsLocated(weResultTableForThead, By.xpath(".//thead/tr/th"));
			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for (WebElement weColumnHeader : weColumnsHeaders) {
				String strHeader = weColumnHeader.getText().trim();
				if (!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber++;
			}
			return dataColumnHeader;
		} catch (Exception exception) {
			commonExceptions(exception);
			return null;
		}
	}

	/**
	 * @Method : tableRowSize
	 * @Description : it will give table rows size
	 * @param :
	 * WebElement - By identification of element (table with all
	 * rows)
	 * @author :
	 */
	public int getTableRowSize(By weResultTableForTbody) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			int tableRowSize = weRows.size();
			return tableRowSize;
		} catch (Exception exception) {
			commonExceptions(exception);
			return 0;
		}
	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param :
	 * WebElement -By identification of Locator
	 * @return : - true if click successful
	 * @author :
	 */
	public boolean click(WebElement element) {
		try {
			waitPageLoaded();
			waitForElementVisibility(element);
			element.click();
			waitAfterEachClick();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : getText
	 * @Description : This is wrapper method to extract the text value of an
	 * webelement
	 * @param :
	 * webElement - By identification of Locator
	 * @param :
	 * textBy - String - "value" or "text"
	 * @return : text value of the passed locator
	 * @author : Swagat
	 */
	public String getText(WebElement webElement, String textBy) {

		String strText = "";
		try {
			switch (textBy.toLowerCase()) {
				case "value":
					strText = webElement.getAttribute("value");
					break;
				case "text":
					strText = webElement.getText();
					break;
				default:
					strText = webElement.getText();
					break;
			}
			return strText;
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}

	/**
	 * @Method : uploadFile
	 * @Description : This is wrapper method to upload file using AutoIT
	 * @param :
	 * filePath - path of file to upload
	 * @return : - true if upload is successful
	 * @author :
	 */
	// USING AUTO-IT
	public boolean uploadFile(String fileNameOnly, String thinkTime) {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllImageDoc\\"
					+ fileNameOnly;
			System.out.println("Upload the file from Path : " + System.getProperty("user.dir")
					+ "\\src\\test\\resources\\testData\\AllImageDoc\\" + fileNameOnly);
			this.waitFor(5);
			String sys_path = System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\";
			Runtime.getRuntime().exec(sys_path + "Fileupload.exe" + " " + filePath);
			this.waitFor(5);
			Runtime.getRuntime().exec(sys_path + "Fileupload.exe" + " " + filePath);
			waitFor(thinkTime);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}
	/**
	 * @Method : selectCheckBox
	 * @Description : This is wrapper method select/deselect checkbox
	 * @param :
	 * WebElement - By identification of Locator
	 * @param :
	 * status - select/deselect
	 * @author : Swagat
	 */
	public boolean selectCheckBox(WebElement webElement, boolean status) {
		try {

			if (webElement.getAttribute("type").equals("checkbox")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status)) {
					waitFor(2);
					webElement.click();
				}
				return true;
			} else
				return false;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	public void waitAfterEachClick() {
		waitFor(objPojo.getAfterClickwait());
	}


	public void alertBoxAccept() {
		try {
			Alert alert = objPojo.getDriver().switchTo().alert();
			alert.accept();
		} catch (Exception exception) {
			commonExceptions(exception);
			return;
		}
	}


	public void alertBoxReject() {
		try {
			Alert alert = objPojo.getDriver().switchTo().alert();
			alert.dismiss();
		} catch (Exception exception) {
			commonExceptions(exception);
			return;
		}
	}

	// Description : This method should be used for Drag & Drop / Re Order
	// You need to pass source locator and target locator
	public boolean dragAndDrop(String sourceElementName, By sourceLocator, By targetLocator) {
		try {
			WebElement SourcewebElement = objPojo.getDriver().findElement(sourceLocator);
			WebElement TargetwebElement = objPojo.getDriver().findElement(targetLocator);
			Actions builder = new Actions(objPojo.getDriver());
			builder.dragAndDrop(SourcewebElement, TargetwebElement).build().perform();
			waitAfterEachClick();
			System.out.println("Dragged and Dropped File successfully.");
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToNestedFrameUsingIframeElement
	 * @Description : This method will switch you to the nested frame
	 * @param :
	 * frmLocator1 - First Frame : frmLocator2 - Second Frame
	 * @author :
	 */
	public boolean switchToNestedFrameUsingIframeElement(By frmLocator1, By frmLocator2) {
		try {
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator1));
			waitForElementPresence(frmLocator2);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator2));
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : saveWindowFileUsingRobotActions
	 * @Description : This is wrapper method to save Window File
	 * @return : - true if Save is successful
	 * @author : Swagat
	 * @created on :
	 */
	public boolean saveWindowFileUsingRobotActions() {
		try {
			objRobotClass.saveWindowFileUsingRobotActions();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method :
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 * element - WebElement identification of element
	 * @param :
	 * fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author :
	 */
	public boolean setText(WebElement element, String fieldValue) {
		try {
			this.waitPageLoaded();
			waitForElementVisibility(element);
			clearText(element);
			element.sendKeys(fieldValue);
			waitAfterEachClick();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToNestedFrameUsingIframeElement
	 * @Description : This method will switch you to the nested frame
	 * @param :
	 * frmLocator1 - First Frame : frmLocator2 - Second Frame :
	 * frmLocator3 - Third Frame
	 * @author :
	 */
	public boolean switchToNestedFrameUsingIframeElement(By frmLocator1, By frmLocator2, By frmLocator3) {
		try {
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator1));
			waitForElementPresence(frmLocator2);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator2));
			waitForElementPresence(frmLocator3);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator3));
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	// Description : This method should be used for Drag & Drop / Re Order
	// You need to pass WebElement for source and target
	public boolean dragAndDrop(String sourceElementName, WebElement source, WebElement target) {
		try {
			Actions builder = new Actions(objPojo.getDriver());
			builder.dragAndDrop(source, target).build().perform();
			waitAfterEachClick();
			System.out.println("Dragged and Dropped File successfully.");
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : selectDropDownOption
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 * locator - By identification of element
	 * @param :
	 * option - drop down element (user may specify text/value/index)
	 * @param :
	 * selectType - select dorp down element by Text/Value/Index
	 * @author :
	 */
	public boolean selectDropDownOption(WebElement webElement, String option, String... selectType) {
		try {
			waitForElementVisibility(webElement);
			Select sltDropDown = new Select(webElement);
			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));
				return true;
			} else {
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
						break;
					} else
						iIndex++;
				}
				return blnOptionAvailable;
			}
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * Scroll element to view using javascript This script is used for scrolling
	 * down.
	 */
	public boolean scrollToViewDown(WebElement webElement) {
		try {
			((JavascriptExecutor) objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : uploadImageUsingInput
	 * @Description : Upload Image using Input Element (Ex:
	 * fileNameOnly=emg1.jpg)
	 * @author :
	 * Note: use below method from
	 * log reporter only
	 */
	public boolean uploadImageUsingInput(By locator, String fileNameOnly) {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllImageDoc\\"
					+ fileNameOnly;
			System.out.println("Upload the file from Path : " + filePath);
			objPojo.getDriver().findElement(locator).sendKeys(filePath);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	// Getting the text on the alert box
	public String alertGetText() {
		try {
			Alert alert = objPojo.getDriver().switchTo().alert();
			return alert.getText();
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}



	/**
	 * @Method :
	 * @Description : This is wrapper method to press function keys irrespective
	 * of a webelement or locator
	 * @param :
	 * key
	 * @return : - true if text entered successfully
	 * @author :
	 */
	public boolean sendFunctionKeys(String functionKey) {
		try {
			Actions action = new Actions(objPojo.getDriver());
			switch (functionKey.toUpperCase()) {
				case "F1":
					action.sendKeys(Keys.COMMAND + "" + Keys.F1);
					break;
				case "F2":
					action.sendKeys(Keys.COMMAND + "" + Keys.F2);
					break;

				case "F3":
					action.sendKeys(Keys.COMMAND + "" + Keys.F3);
					break;

				case "F4":
					action.sendKeys(Keys.COMMAND + "" + Keys.F4);
					break;

				case "F5":
					action.sendKeys(Keys.COMMAND + "" + Keys.F5);
					break;

				case "F6":
					action.sendKeys(Keys.COMMAND + "" + Keys.F6);
					break;

				case "F7":
					action.sendKeys(Keys.COMMAND + "" + Keys.F7);
					break;

				case "F8":
					action.sendKeys(Keys.COMMAND + "" + Keys.F8);
					break;

				case "F9":
					action.sendKeys(Keys.COMMAND + "" + Keys.F9);
					break;

				case "F10":
					action.sendKeys(Keys.COMMAND + "" + Keys.F10);
					break;

				case "F11":
					action.sendKeys(Keys.COMMAND + "" + Keys.F11);
					break;

				case "F12":
					action.sendKeys(Keys.COMMAND + "" + Keys.F12);
					break;

				default:
					return false;
			}
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : doubleClick
	 * @Description : This is wrapper method used for doubleClick on element
	 * @param :
	 * WebElement - By identification of Locator
	 * @return : - true if double click successful
	 * @author : Swagat
	 */
	public boolean doubleClick(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			waitForElementToBeClickable(webElement);
			Actions actionBuilder = new Actions(objPojo.getDriver());
			actionBuilder.doubleClick(webElement).build().perform();
			waitAfterEachClick();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	/**
	 * @Method : uploadFilefromDownloads
	 * @Description : This is wrapper method to upload file using AutoIT
	 * @param :
	 * filePath - path of file to upload
	 * @return : - true if upload is successful
	 * @author :
	 */
	// USING AUTO-IT
	public boolean uploadFilefromDownloads(String fileNameOnly, String thinkTime) {
		try {
			String filePath = System.getProperty("user.home") + "\\Downloads\\" + fileNameOnly;
			System.out.println("Upload the file from Path : " + filePath);
			this.waitFor(5);
			String sys_path = System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\";
			Runtime.getRuntime().exec(sys_path + "Fileupload.exe" + " " + filePath);
			waitFor(thinkTime);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : isAlertPresent
	 * @Description : This is wrapper method used for Alert Present or not on
	 * Page
	 * @param :
	 * WebElement - By identification of Locator
	 * @return : - true if Alert is Present
	 * @author :
	 */
	public boolean isAlertPresent() {
		try {
			objPojo.getDriver().switchTo().alert();
			System.out.println("Alert is Present.");
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	public void setResouceName(String resName) {
		objPojo.setResourceName(resName);
	}

	/**
	 * @Method : sendKeyBoardKeys
	 * @Description : This is wrapper method is used to send keyboard keys
	 * @param :
	 * locator - By identification of element
	 * @param :
	 * key - key name
	 * @return : - true if text entered successfully
	 * @author : Swagat
	 */
	public boolean sendKeyBoardKeys(WebElement webElement, String key) {
		try {
			waitForElementVisibility(webElement);
			if (key.equalsIgnoreCase("enter"))
				webElement.sendKeys(Keys.ENTER);
			if (key.equalsIgnoreCase("shift"))
				webElement.sendKeys(Keys.SHIFT);
			if (key.equalsIgnoreCase("tab"))
				webElement.sendKeys(Keys.TAB);
			if (key.equalsIgnoreCase("keydown"))
				webElement.sendKeys(Keys.DOWN);
			if (key.equalsIgnoreCase("escape"))
				webElement.sendKeys(Keys.ESCAPE);
			if (key.equalsIgnoreCase("F2"))
				webElement.sendKeys(Keys.F2);
			if (key.equalsIgnoreCase("clear"))
				webElement.sendKeys(Keys.BACK_SPACE);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : verifyDropDownOptionValues
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 * locator - By identification of element
	 * @param :
	 * option - if want to verify more then one option pass values
	 * separated by ';'
	 * @author : Swagat
	 */
	public boolean verifyDropDownOptionValues(WebElement element, String option) {
		try {
			waitForElementVisibility(element);
			Select sltDropDown = new Select(element);
			List<WebElement> options = sltDropDown.getOptions();
			boolean blnOptionAvailable = false;
			ArrayList<String> optionsList;
			if (option.contains(";"))
				optionsList = new ArrayList<String>(Arrays.asList(option.trim().split(";")));
			else {
				optionsList = new ArrayList<String>();
				optionsList.add(option);
			}
			for (WebElement weOptions : options) {
				String optionValue = weOptions.getText().trim();
				if (optionsList.contains(optionValue)) {
					blnOptionAvailable = true;
					optionsList.remove(optionValue);
					if (optionsList.isEmpty())
						break;
				}
			}
			return blnOptionAvailable;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	/**
	 * @Method : verifyTableContent
	 * @Description : it will check given data in whole table
	 * @param :
	 * locator - By identification of element (table with all rows)
	 * @param :
	 * columnHeader - String column header
	 * @param :
	 * ContentToVerify - String Content to be verify
	 * @author : Swagat
	 */
	public boolean verifyTableContents(By locator, String columnHeader, String ContentToVerify) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		boolean blnverify = false;
		try {
			waitForElementPresence(locator);
			WebElement weResultTable = objPojo.getDriver().findElement(locator);
			waitForPresenceOfNestedElementsLocated(locator, By.xpath(".//thead/tr/th"));
			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for (WebElement weColumnHeader : weColumnsHeaders) {
				String strHeader = weColumnHeader.getText().trim();
				System.out.println("strHeader------->" + strHeader);
				if (!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber++;
			}
			waitForPresenceOfNestedElementsLocated(locator, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weExceptedClm = weRow
						.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeader) + "]"));
				System.out.println("************************---------->" + weExceptedClm.getAttribute("value"));
				if (weExceptedClm.getAttribute("value").trim().contains(ContentToVerify)) {
					blnverify = true;
					return blnverify;
				}
			}
			return blnverify;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : isCheckBoxSelected
	 * @Description : This is wrapper checkbox is selected or not
	 * @param :
	 * locator - By identification of element
	 * @author : Swagat
	 */
	public boolean isCheckBoxSelected(WebElement webElement) {
		blnReturnStatus = false;
		try {
			waitForElementVisibility(webElement);
			if (webElement.getAttribute("type").equals("checkbox"))
				blnReturnStatus = webElement.isSelected();
			return blnReturnStatus;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : isCheckBoxNotSelected
	 * @Description : This is wrapper checkbox is selected or not
	 * @param :
	 * locator - By identification of element
	 * @author : Swagat
	 */
	public boolean isCheckBoxNotSelected(WebElement webElement) {
		blnReturnStatus = false;
		try {
			waitForElementVisibility(webElement);
			if (webElement.getAttribute("type").equals("checkbox"))
				blnReturnStatus = !webElement.isSelected();
			return blnReturnStatus;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : isRadioButtonelected
	 * @Description : This is wrapper radio button is selected or not
	 * @param :
	 * locator - By identification of element
	 * @author : Swagat
	 */
	public boolean isRadioButtonSelected(WebElement webElement) {
		blnReturnStatus = false;
		try {
			waitForElementVisibility(webElement);
			if (webElement.getAttribute("type").equals("checkbox"))
				blnReturnStatus = webElement.isSelected();
			return blnReturnStatus;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}



	/**
	 * @Method : checkElementDisplayed
	 * @Description : it will check checkElementDisplayed in whole table
	 * @param :
	 * WebElement - By identification of element (table with all
	 * rows) WebElement element
	 * @author : Swagat
	 */
	public boolean checkElementDisplayed(WebElement element) {
		try {
			waitForElementVisibility(element);
			boolean check = element.isDisplayed();
			return check;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : uploadFileUsingRobotActions
	 * @Description : This is wrapper method to upload file
	 * @param :
	 * filePath - path of file to upload
	 * @return : - true if upload is successful
	 * @author : Swagat
	 */
	public boolean uploadFileUsingRobotClass(String fileNameOnly, String thinkTime) {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllImageDoc\\"
					+ fileNameOnly;
			System.out.println("Upload the file from Path : " + System.getProperty("user.dir")
					+ "\\src\\test\\resources\\testData\\AllImageDoc\\" + fileNameOnly);
			objRobotClass.uploadFileUsingRobotActions(filePath);
			waitFor(thinkTime);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToNestedFrameUsingIframeElement
	 * @Description : This method will switch you to the nested frame
	 * @param :
	 * frmLocator1 - First Frame, frmLocator2 - Second Frame,
	 * frmLocator3 - Third Frame, frmLocator4 - Fourth Frame
	 * @author :
	 */
	public boolean switchToNestedFrameUsingIframeElement(By frmLocator1, By frmLocator2, By frmLocator3,
														 By frmLocator4) {
		try {
			if (!frmLocator1.equals(""))
				objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator1));
			if (!frmLocator2.equals(""))
				waitForElementPresence(frmLocator2);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator2));
			if (!frmLocator3.equals(""))
				waitForElementPresence(frmLocator3);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator3));
			if (!frmLocator4.equals(""))
				waitForElementPresence(frmLocator4);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator4));
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}



	public void setGroupName(String groupName) {
		objPojo.setGroupName(groupName);
	}


	public void setGroupPriority(Method priority) {
		Test test = priority.getAnnotation(Test.class);
		String[] groups = test.groups();
		String groupName = Arrays.toString(groups);
		if (!groupName.equals("[]")) {
			String groupPriority = groupName.substring(1, 3);
			this.setGroupName(groupPriority);
		}
	}

	/**
	 * @Method : clickWebElementUsingJavaScript()
	 * @Description : TThis is wrapper method to click a web element
	 * @param :
	 * webElement - By identification of element
	 * @return : - true if clicked successful
	 * @author : Swagat Mohapatra
	 */
	public boolean clickWebElementUsingJavaScript(WebElement webElement) {
		try {
			objPojo.getDriver().manage().timeouts().setScriptTimeout(objPojo.getScriptTimeoutWait(), TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("arguments[0].click()", webElement);
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	/**
	 * @Method : setTextUsingJavaScriptByWebElementAttribute()
	 * @Description : This is wrapper method to set value in web element
	 * @param :
	 * webElement - By identification of element fieldValue - Value
	 * to set byAttribute - Attribute value
	 * @return : - true if set successful
	 * @author : Swagat Mohapatra 06-11-2018
	 */
	public boolean setTextUsingJavaScriptByWebElementAttribute(WebElement webElement, String fieldValue,
															   String byAttribute) {
		try {
			waitPageLoaded();
			JavascriptExecutor jse = (JavascriptExecutor) objPojo.getDriver();
			this.clickWebElementUsingJavaScript(webElement);
			clearText(webElement);
			if (byAttribute.contains("Value")) {
				jse.executeScript("arguments[0].value='" + fieldValue + "';$(arguments[0]).trigger('change');",
						webElement);
			} else if (byAttribute.contains("innerText")) {
				jse.executeScript("arguments[0].innerText='" + fieldValue + "';", webElement);
			} else if (byAttribute.contains("innerHTML")) {
				jse.executeScript("arguments[0].innerHTML='" + fieldValue + "';", webElement);
			}
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	public void setDescription(Method description) {
		Test test = description.getAnnotation(Test.class);
		strReturnValue = test.description();
		if (!strReturnValue.equals("")) {
			this.setDescription(strReturnValue);
		}
	}


	public void setDescription(String description) {
		objPojo.setDescription(description);

	}


	/**
	 * @Method : actionClick
	 * @Description : This is wrapper method to simulate mouse click
	 * @param :
	 * locator - By identification of element
	 * @return : - true if click action is successful
	 * @author :
	 * @created on :
	 */
	public boolean actionClick(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			waitForElementToBeClickable(webElement);
			Actions actionBuilder = new Actions(this.objPojo.getDriver());
			actionBuilder.click(webElement).build().perform();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : getLatestDownloadedFileName
	 * @Description : This is wrapper method to get name of latest downloaded
	 * file.
	 * @return : - file name in a string otherwise "";
	 * @author :
	 */
	public String getLatestDownloadedFileName() {
		String latestFile = "";
		try {
			File directoryForDownload = new File(System.getProperty("user.home") + "/Downloads");
			File[] downloadedFiles = directoryForDownload.listFiles();
			if (downloadedFiles == null || downloadedFiles.length == 0) {
				return "";
			}
			File lastModifiedFile = downloadedFiles[0];
			for (int i = 0; i < downloadedFiles.length; i++) {
				if (lastModifiedFile.lastModified() == downloadedFiles[i].lastModified()) {
					lastModifiedFile = downloadedFiles[i];
					latestFile = lastModifiedFile.getName();
				}
			}
			return latestFile;
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}

	/**
	 * @Method : getLatestDownloadedFileName(String fileName)
	 * @Description : This is wrapper method to get name of latest downloaded
	 * file.
	 * @return : - file name in a string otherwise "";
	 * @author :
	 */
	public String getLatestDownloadedFileName(String fileName) {
		String latestFile = "";
		try {
			File directoryForDownload = new File(System.getProperty("user.home") + "/Downloads");
			File[] downloadedFiles = directoryForDownload.listFiles();

			if (downloadedFiles == null || downloadedFiles.length == 0) {
				return "";
			}
			for (int i = 0; i < downloadedFiles.length; i++) {
				if ((downloadedFiles[i]).getName().contains(fileName)) {
					latestFile = downloadedFiles[i].getName();
					blnReturnStatus = true;
				}
			}
			return latestFile;
		} catch (Exception exception) {
			commonExceptions(exception);
			return "";
		}
	}

	/**
	 * Scroll element to view using Action Class Updated By :
	 */
	public boolean scrollToViewUsingActions(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			Actions actions = new Actions(objPojo.getDriver());
			actions.moveToElement(webElement);
			actions.perform();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * Click using Actions
	 */
	public boolean clickUsingActions(WebElement webElement) {
		try {
			waitForElementVisibility(webElement);
			waitForElementToBeClickable(webElement);
			Actions actions = new Actions(objPojo.getDriver());
			actions.moveToElement(webElement);
			actions.click().perform();
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}


	// Exceptions For Return Type Void
	public void commonExceptions(Exception e) {
		try {
			throw e;
		} catch (InvalidSelectorException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (NoSuchElementException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (ElementNotVisibleException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (ElementNotSelectableException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (TimeoutException exception) {
			if (exception.getCause() != null) {
				setException = exception.getCause().toString();
				exception.getCause().printStackTrace();
			} else {
				setException = exception.toString();
				exception.printStackTrace();
			}
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			return;
		} catch (NoSuchSessionException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (StaleElementReferenceException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (NoAlertPresentException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (NoSuchFrameException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (NoSuchWindowException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (UnhandledAlertException exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			this.alertBoxAccept();
			return;
		} catch (WebDriverException exception) {
			setException = "";
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		} catch (Exception exception) {
			setException = exception.toString();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			objPojo.setCustomException(setException);
			exception.printStackTrace();
			return;
		}
	}


	public String getViewMethodNameInFailedTestScripts() {
		methodName = "";
		countOfThreads = Thread.activeCount();
		for (int i = countOfThreads + 5; i > 0; i--) {
			methodName = Thread.currentThread().getStackTrace()[i].getMethodName();
			if (methodName.startsWith("TCID")) {
				method = Thread.currentThread().getStackTrace()[i - 1].getMethodName();
				break;
			}
		}
		return method;
	}


	public boolean ifCheckElementPresence(By locator) {
		try {
			this.waitForElementPresence(locator);
			return true;
		} catch (UnhandledAlertException exception) {
			this.alertBoxAccept();
			return false;
		} catch (Exception exception) {
			return false;
		}
	}


	public boolean ifElementEnabled(WebElement webElement) {
		try {
			this.waitForElementVisibility(webElement);
			return webElement.isEnabled();
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	public boolean ifAlertIsPresent() {
		try {
			objPojo.getDriver().switchTo().alert();
			System.out.println("Alert is Present.");
			return true;
		} catch (UnhandledAlertException exception) {
			this.alertBoxAccept();
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	public void setURL(String url){
		try {
			objPojo.getDriver().get(url);
			objPojo.getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(objPojo.getObjConfig().getProperty("pageLoadWait")), TimeUnit.SECONDS);
		}catch (Exception exception){
			commonExceptions(exception);
		}
	}

	public void waitPageLoaded(){
		try {
			objPojo.getWebDriverWait().until(webDriver -> "complete".equals(((JavascriptExecutor) objPojo.getDriver())
					.executeScript("return document.readyState")));
		}catch (Exception exception){
			commonExceptions(exception);
		}
	}


	public String dbValidation(String url, String userName, String password, String query ) {
		// String query="select OTP from AccountCredential where id in (select AccountCredentialId FROM [Meta].[dbo].[Account] where mobiletelephonenumber = '"+phoneNumber+"')";
		String returnValueFromDB="";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, userName, password);
			Thread.sleep(10000);
			System.out.println("Connecting To Database");
			Statement stmt = con.createStatement();
			Thread.sleep(3000);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Thread.sleep(2000);
				returnValueFromDB = rs.getString(1);
				System.out.println("Value FETCHED From DB :::: " + returnValueFromDB); }
			con.close();

		}catch(Exception e){
			commonExceptions(e);
		}
		return returnValueFromDB;
	}

	public String getCurrentMonth()
	{
		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();
		System.out.println("Current month: "+currentMonth);
		return currentMonth.toString();
	}

	public String getCurrentYear()
	{
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();
		System.out.println("Current month: "+currentYear);
		return Integer.toString(currentYear);
	}

	/**
	 * @Method : switchToNestedFrameUsingIframeElement
	 * @Description : This method will switch you to the nested frame
	 * @param :
	 * frmLocator1 - First Frame : frmLocator2 - Second Frame
	 * @author :
	 */
	public boolean switchToNewWindow() {
		try {
			parent = objPojo.getDriver().getWindowHandle();
			Set<String> s= objPojo.getDriver().getWindowHandles();

// Now iterate using Iterator
			Iterator<String> I1= s.iterator();

			while(I1.hasNext())
			{

				String child_window=I1.next();


				if(!parent.equals(child_window))
				{
					objPojo.getDriver().switchTo().window(child_window);

					System.out.println(objPojo.getDriver().switchTo().window(child_window).getTitle());

				}

			}
			return true;
		} catch (Exception exception) {
			commonExceptions(exception);
			return false;
		}
	}

	/**
	 * @Method : switchToDefaultContent
	 * @Description : This method will switch you to the default Window
	 * @author : Swagat
	 */
	public void switchToParentWindow() {
		try {
			objPojo.getDriver().switchTo().window(parent);
		} catch (Exception exception) {
			commonExceptions(exception);
			return;
		}
	}
}
