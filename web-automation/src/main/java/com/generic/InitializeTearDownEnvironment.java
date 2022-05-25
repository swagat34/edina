
package com.generic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class InitializeTearDownEnvironment {
    private WebDriver webDriver;
    private Platform platform;

    public WebDriver initializeWebEnvironment(Properties objConfig, String b) {
        try {
            String browser="";
            try{
                if(System.getProperty("browser").equalsIgnoreCase("")||System.getProperty("browser")==null){
                    browser=b;
                    BaseTest.activeBrowser=browser;
                }else{
                    browser = System.getProperty("browser");
                    BaseTest.activeBrowser=browser;
                }
            }catch (Exception e){
                browser=b;
                BaseTest.activeBrowser=browser;
            }


            switch (browser) {
                case "edge": {
                    // File path= new File("msedgedriver.exe");
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions options = new EdgeOptions();
                    this.webDriver = new EdgeDriver(options);

                    // System.setProperty("webdriver.edge.driver",path.getAbsolutePath());
                    //webDriver = new EdgeDriver();
                    JavascriptExecutor js = (JavascriptExecutor)webDriver;
                    String useragent = (String)js.executeScript("return navigator.userAgent;");
                    System.out.println("user agent is "+useragent);
                    if(useragent.contains(CheckBrowserVersion(webDriver))){
                        BaseTest.activeBrowserVersion = CheckBrowserVersion(webDriver);
                    }

                    break;
                }
                case "firefox": {
                    WebDriverManager.firefoxdriver().clearDriverCache().setup();
                    this.webDriver = new FirefoxDriver();
                    JavascriptExecutor js = (JavascriptExecutor)webDriver;
                    String useragent = (String)js.executeScript("return navigator.userAgent;");
                    System.out.println("user agent is "+useragent);
                    if(useragent.contains(CheckBrowserVersion(webDriver))){
                        BaseTest.activeBrowserVersion = CheckBrowserVersion(webDriver);
                    }
                    break;
                }
                case "chrome": {
                    //setup the chromedriver using WebDriverManager
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    this.webDriver = new ChromeDriver();
                    JavascriptExecutor js = (JavascriptExecutor)webDriver;
                    String useragent = (String)js.executeScript("return navigator.userAgent;");
                    System.out.println("user agent is "+useragent);
                    if(useragent.contains(CheckBrowserVersion(webDriver))){
                        BaseTest.activeBrowserVersion = CheckBrowserVersion(webDriver);
                    }
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    this.webDriver = new ChromeDriver();
                    JavascriptExecutor js = (JavascriptExecutor)webDriver;
                    String useragent = (String)js.executeScript("return navigator.userAgent;");
                    System.out.println("user agent is "+useragent);
                    if(useragent.contains(CheckBrowserVersion(webDriver))){
                        BaseTest.activeBrowserVersion = CheckBrowserVersion(webDriver);
                    }
                }
            }
            this.webDriver.manage().window().maximize();
            this.webDriver.manage().timeouts().implicitlyWait((long)Integer.parseInt(objConfig.getProperty("driver.implicitlyWait").trim()), TimeUnit.SECONDS);
            this.webDriver.manage().timeouts().pageLoadTimeout((long)Integer.parseInt(objConfig.getProperty("driver.pageLoadTimeout").trim()), TimeUnit.SECONDS);
            return this.webDriver;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    //Get current platform
    private Platform getCurrentPlatform () {
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

    protected void killBrowserAndDriver() {
        if (getCurrentPlatform().equals("WINDOWS")) {
            String browser = System.getProperty("web.browser").trim();
            String browserProcess = "";
            String driverProcess = "";
            if (!browser.equals("") && browser.equalsIgnoreCase("IE")) {
                browserProcess = "iexplore";
                driverProcess = "IEDriverServer.exe";
            } else if (!browser.equals("") && browser.equalsIgnoreCase("Chrome")) {
                browserProcess = browser;
                //   driverProcess = "chromedriver.ex";
            }
            try {
                Process procDriver = Runtime.getRuntime().exec("taskkill /F /T /IM " + driverProcess);
                Process procIE = Runtime.getRuntime().exec("taskkill /F /T /IM " + browserProcess + ".exe");
                procDriver.waitFor();
                procIE.waitFor();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public String CheckBrowserVersion(WebDriver webDriver){
        //Get Browser name and version.
        Capabilities caps = ((RemoteWebDriver) webDriver).getCapabilities();
        String browserVersion = caps.getVersion();
        return browserVersion;
    }


}
