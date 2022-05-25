package com.generic;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.util.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;


/**
 * @ScriptName : Utilities
 * @Description : This class contains utilities function
 * @Author :Swagat
 */

public class Utilities extends BaseTest {

	private Pojo objPojo;
	public static String fileName = "";
	public static String fileWithPath = "";
	private Integer countOfThreads = null;
	public static String failedMethod = "";
	private String methodName = "";
	private String strMethodName = "";
	private String method = "";
	private Platform platform;
	String strScreenshotFilePath = "";
	private String macScreenshotPath = System.getProperty("user.dir") + "/TestReport/Screenshot";
	private String windowsScreenshotPath = System.getProperty("user.dir") + "\\TestReport\\Screenshot";
	private String macReportFileLoc = macScreenshotPath + "/";
	private String winReportFileLoc = windowsScreenshotPath + "\\";
	final Logger logger = LogManager.getLogger(Utilities.class);
	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";
	public static SoftAssert softAssertions = new SoftAssert();


	public Utilities(Pojo pojo) {

		this.objPojo = pojo;
	}

	String current_TCID = "";

	public String getCurrent_TCID() {
		return current_TCID;
	}


	public void setCurrent_TCID(String current_TCID) {
		this.current_TCID = current_TCID;
	}


	/**
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *          (true/false), includeMobile - result for mobile(true/false)
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @author :Swagat
	 */

	public void logReporter(String step, boolean resultLog) {
		String strLog = step;
		try {
			if (resultLog) {

				CustomReporter.test.get().pass(strLog);
				Assert.assertTrue(resultLog);

			} else {
				logger.info("Step Description--> " + strLog);
				CustomReporter.test.get().fail(strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotBase64()).build());
				this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
				Assert.assertTrue(resultLog);
			}

		} catch (Exception e) {
			CustomReporter.test.get().info("Exception Occured in Log Reporter Method : " + e.toString());
			Assert.assertTrue(resultLog);
		}


	}

	/**
	 * @param : Step - Step description, inputValue - Input value, resultLog -
	 *          result log pass/fail (true/false), includeMobile - result for
	 *          mobile(true/false)
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @author : Swagat
	 */

	public void logReporter(String step, String inputValue, boolean resultLog) {
		String strLog = step + "|| Input Value : " + inputValue;
		try {
			if (resultLog) {
				logger.info(strLog);
				CustomReporter.test.get().pass(strLog);
				Assert.assertTrue(resultLog);

			} else {
				logger.error(strLog);
				CustomReporter.test.get().fail(strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotBase64()).build());
				this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
				Assert.assertTrue(resultLog);
			}

		} catch (Exception e) {
			CustomReporter.test.get().info("Exception Occured in Log Reporter Method : " + e.toString());
			Assert.assertTrue(resultLog);
		}

	}

	/**
	 * @param : Step - Step description, expectedValue - verification point
	 *          expected value, actualValue - verification point actual value,
	 *          resultLog - result log pass/fail (true/false), includeMobile -
	 *          result for mobile(true/false)
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @author :Swagat
	 */
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog) {
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		try {
			if (resultLog) {
				CustomReporter.test.get().pass(strLog);
				Assert.assertTrue(resultLog);

			} else {
				CustomReporter.test.get().fail(strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotBase64()).build());
				this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog);
				Assert.assertTrue(resultLog);
			}

		} catch (Exception e) {
			CustomReporter.test.get().info("Exception Occured in Log Reporter Method : " + e.toString());
			Assert.assertTrue(resultLog);
		}

	}

	/**
	 * @param :
	 * @Method : addAssertTakeScreenShot
	 * @Description :
	 * @author : Automation Tester
	 */
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
										String actualValue, boolean resultLog) throws IOException {

		if (resultLog) {
			System.out.println("Step Description--> " + strLog);
			Reporter.log("Step Description--> " + strLog);
			logger.info("Step Description--> " + strLog);
			Assert.assertTrue(true);
		} else {
			fileName = getDateInSpecifiedFormat("dd_MMM_yyyy_HH_mm_ss") + "_TCID_" + current_TCID + ".png";
			System.out.println("file path :" + getReportFileLocation(getCurrentPlatform()));
			fileWithPath = getReportFileLocation(getCurrentPlatform()) + fileName;
			System.out.println("Failed at Step ===>" + "Step Description--> " + strLog);
			logger.error("Step Description--> " + strLog);
			this.takeScreenShot(objPojo.getDriver(), fileWithPath);
			this.loadConfigProperties();
			failedMethod = this.getViewMethodNameInFailedTestScripts();
			Assert.assertTrue(false);
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


	/**
	 * Generate random string
	 *
	 * @return String random string value
	 */
	public String getRandomString(int length) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * Generate random string with numbers
	 *
	 * @return String random string value
	 */
	public String getRandomStringWithNumbers(int lenght) {
		String allowedChars = "abcdefghiklABCDEFGHIJKLMNOmnopqrstuvwxyz1234567890";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * Generate random string with numbers
	 *
	 * @return String random string value
	 */
	public String getRandomNumbers(int length) {
		String allowedChars = "1234567890";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}


	/*
	 * @Method : getDateInSpecifiedFormat
	 *
	 * @Description : This method takes parameter of your required DateFormat
	 * Type Like: dd-mm-YYYY DD.MM.YYYY and in return it will give you today's
	 * date in specified date format
	 *
	 * @param : dateFormat like : dd-MM-YYYY
	 *
	 * @author :
	 *
	 */
	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		// System.out.println("getDateInSpecifiedFormat "+dateFormat + " -
		// "+current_date);
		return current_date;
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given web driver.
	 * @author :Automation Tester .
	 */
	public String takeScreenShot(WebDriver webDriver, String fileWithPath) {
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File destFile = new File(fileWithPath);
		// Copy file at destination
		String strpath = "";
		try {
			FileUtils.moveFile(srcFile, destFile);
			strpath = destFile.getAbsolutePath();
			return strpath;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return "";
		}
	}


	public String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
			case MAC:
				reportFileLocation = macReportFileLoc;
				createReportPath(macScreenshotPath);
				System.out.println("ExtentReport Path for MAC: " + macScreenshotPath + "\n");
				break;
			case WINDOWS:
				reportFileLocation = winReportFileLoc;
				createReportPath(windowsScreenshotPath);
				System.out.println("ExtentReport Path for WINDOWS: " + windowsScreenshotPath + "\n");
				break;
			default:
				System.out.println("ExtentReport path has not been set! There is a problem!\n");
				break;
		}
		return reportFileLocation;
	}

	//Create the report path if it does not exist
	private void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);

		}
	}

	//Get current platform
	private Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux")
					|| operSys.contains("aix")) {
				platform = Platform.LINUX;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
		return platform;
	}

	public void deleteDirectory() {
		try {
			platform = getCurrentPlatform();
			strScreenshotFilePath = getReportFileLocation(platform);
			File f = new File(strScreenshotFilePath); //file to be delete
			if (f.delete()) //returns Boolean value
			{
				System.out.println(f.getName() + " deleted"); //getting and printing the file name
			} else {
				System.out.println("failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getScreenShotBase64() {
		return ((TakesScreenshot) objPojo.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *          (true/false), includeMobile - result for mobile(true/false)
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @author :Swagat
	 */

	public void logReporterwithSoftassert(String step, boolean resultLog) {
		String strLog = step;
		try {
			if (resultLog) {

				CustomReporter.test.get().pass(strLog);
				softAssertions.assertTrue(resultLog);

			} else {
				logger.info("Step Description--> " + strLog);
				CustomReporter.test.get().fail(strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotBase64()).build());

			}

		} catch (Exception e) {
			CustomReporter.test.get().info("Exception Occured in Log Reporter Method : " + e.toString());

		}

	}

	public void AssertAll(){
		softAssertions.assertAll();
	}
	/**
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *          (true/false), includeMobile - result for mobile(true/false)
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @author :Swagat
	 */

	public void logReporterInfoOnly(String step) {
		String strLog = step;
		logger.info("Step Description--> " + strLog);
		CustomReporter.test.get().info(strLog);


	}

}