package com.generic;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BaseTest
        extends Pojo {
    private WebDriver webDriver;
    protected Properties objConfig;
    private WebDriverWait webDriverWait;
    private Utilities objUtilities;
    private WrapperFunctions objWrapperFunctions;
    private String callingClassName = "";
    private String serverAndPort = "";
    private String baseURL = "";
    protected ExcelDataProvider excelMethods = new ExcelDataProvider();
    public static String activeBrowser = "";
    public static String activeBrowserVersion = "";
    public static String activeWebexchangeVersion = "";
    private InitializeTearDownEnvironment objInitializeTearDownEnvironment;
    Hashtable<String, Hashtable<String, String>> testDataTable = new Hashtable();
    Hashtable<String, String> testDataForTest = new Hashtable();
    public static List<Method> l = new ArrayList<>();
    public static List<String> strlist ;

    public void loadBaseURL() {
        try {
            this.webDriver.manage().deleteAllCookies();
            this.webDriver.get(this.getBaseURL());
            getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(this.objConfig.getProperty("pageLoadWait")), TimeUnit.SECONDS);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void tearDownWebEnvironment() {
        this.webDriver.manage().deleteAllCookies();
        this.webDriver.quit();
        objInitializeTearDownEnvironment.killBrowserAndDriver();
        this.objUtilities = null;
        this.objWrapperFunctions.waitFor(2);
        this.objWrapperFunctions = null;
    }

//
//    public void loadTestData(String TCIDRowNumber) {
//        this.testDataForTest = this.testDataTable.get(TCIDRowNumber);
//        this.setDataPoolHashTable(this.testDataForTest);
//        this.objUtilities = new Utilities((Pojo)this);
//        this.setObjUtilities(this.objUtilities);
//        this.objUtilities.setCurrent_TCID(TCIDRowNumber);
//        Logger logger = getObjUtilities().logger;
//        logger.info((Object)("TC ID " + TCIDRowNumber + " Started at " + this.objUtilities.getDateInSpecifiedFormat("dd-MMM-yyyy-HH-mm-ss")+ " >>> Execution Status >>> "+getObjUtilities().dpString("Execution")));
//        logger.info((Object)("testDataForTest------->" + this.testDataForTest));
//        if(!getObjUtilities().dpString("Execution").equalsIgnoreCase("Y")){
//            throw new SkipException(TCIDRowNumber);
//        }
//
//    }



    public void loadConfigProperties() {
        try {
            this.objConfig = new Properties();
            this.objConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
            this.setObjConfig(this.objConfig);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public void initializeWebEnvironmentForDataDriven(String testDataFilePath) {
        this.loadConfigProperties();
        this.setBrowserName( this.objConfig.getProperty("browserExecution").trim());
        this.loadDataFileProviderForDataDriven(testDataFilePath);
        this.callingClassName = this.getClass().getName();
        this.setRunningScriptName(this.callingClassName);
        this.objInitializeTearDownEnvironment = new InitializeTearDownEnvironment();
        this.webDriver = this.objInitializeTearDownEnvironment.initializeWebEnvironment(this.objConfig,getBrowserName());
        this.setDriver(this.webDriver);
        this.setAfterClickwait(Integer.parseInt(this.objConfig.getProperty("AfterClickWait")));
        this.setScriptTimeoutWait(Integer.parseInt(this.objConfig.getProperty("ScriptTimeoutWait")));
        this.webDriverWait = new WebDriverWait(this.webDriver, (long)Integer.parseInt(this.objConfig.getProperty("driver.WebDriverWait").trim()));
        this.setWebDriverWait(this.webDriverWait);
        this.objUtilities = new Utilities((Pojo)this);
        this.setObjUtilities(this.objUtilities);
        this.objWrapperFunctions = new WrapperFunctions((Pojo)this);
        this.setObjWrapperFunctions(this.objWrapperFunctions);
        this.serverAndPort = this.objConfig.getProperty("web.protocol") + "://" + this.objConfig.getProperty("web.domain") + this.objConfig.getProperty("web.port");
        this.baseURL = this.serverAndPort + this.objConfig.getProperty("web.path");
        this.setServerAndPort(this.serverAndPort);
        this.setBaseURL(this.baseURL);
        this.loadBaseURL();
    }

    public void loadDataFileProviderForDataDriven(String testDataFilePath) {
        if (!testDataFilePath.equals("")) {
            this.loadConfigProperties();
            testDataFilePath = System.getProperty("user.dir") + "/src/test/resources/DataDrivenTestData/" + testDataFilePath;
            excelMethods.setDataTablePath(testDataFilePath);
        }
    }

    @BeforeSuite
    public void setBrowserNameForExecution(){
        loadConfigProperties();
        setBrowserName( this.objConfig.getProperty("browserExecution").trim());
         }





}

