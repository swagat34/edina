package com.generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Hashtable;
import java.util.Properties;


public class Pojo
{
  private WebDriver webDriver;
  private WebDriverWait webDriverWait;
  private Properties objConfig;
  private Hashtable<String, String> dataPoolHashTable;
  private Utilities objUtilities;
  private WrapperFunctions objWrapperFunctions;

  public Pojo() {}

  private String runningScript = "";
  private String baseURL = "";
  private String serverAndPort = "";
  private String version = "";
  private String server = "";
  private int afterClickwait = 0;
  private String resourceName = "Undefined";
  private String customException = "";
  private String groups = "";
  private String description = "";
  public String browserName = "";
  private String methodName = "";

  public void setBrowserName(String browserName) {
    this.browserName = browserName;
  }

  public String getBrowserName() {
    return browserName;
  }


  public void setDescription(String description) {
    this.description = description; }

  public String getDescription()
  {
    return description;
  }

  public String getGroupName() {
    return groups;
  }

  public void setGroupName(String groups) {
    this.groups = groups;
  }

  public String getCustomException() {
    return customException;
  }

  public void setCustomException(String customException) {
    this.customException = customException;
  }

  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  private int scriptTimeoutWait = 0;

  public int getScriptTimeoutWait() {
    return scriptTimeoutWait;
  }

  public void setScriptTimeoutWait(int scriptTimeoutWait) {
    this.scriptTimeoutWait = scriptTimeoutWait;
  }

  public Integer getAfterClickwait() {
    return Integer.valueOf(afterClickwait);
  }

  public void setAfterClickwait(Integer afterClickwait) {
    this.afterClickwait = afterClickwait.intValue();
  }

  public void setDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebDriver getDriver() {
    return webDriver;
  }

  public void setWebDriverWait(WebDriverWait webDriverWait) {
    this.webDriverWait = webDriverWait;
  }

  public WebDriverWait getWebDriverWait() {
    return webDriverWait;
  }

  public void setObjConfig(Properties objConfig) {
    this.objConfig = objConfig;
  }

  public Properties getObjConfig() {
    return objConfig;
  }

  public void setDataPoolHashTable(Hashtable<String, String> dataPoolHashTable) {
    this.dataPoolHashTable = dataPoolHashTable;
  }

  public Hashtable<String, String> getDataPoolHashTable() {
    return dataPoolHashTable;
  }

  public void setRunningScriptName(String scriptName) {
    runningScript = scriptName;
  }

  public Utilities getObjUtilities() {
    return objUtilities;
  }

  public void setObjUtilities(Utilities objUtilities) {
    this.objUtilities = objUtilities;
  }

  public WrapperFunctions getObjWrapperFunctions() {
    return objWrapperFunctions;
  }

  public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions) {
    this.objWrapperFunctions = objWrapperFunctions;
  }

  public void setServerAndPort(String serverAndPort) {
    this.serverAndPort = serverAndPort;
  }
  public String getServerAndPort() {
    return serverAndPort;
  }


  public void setBaseURL(String baseURL) {
    this.baseURL = baseURL;
  }

  public String getBaseURL() {
    return baseURL;
  }

  public String getServer() {
    return server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName; }

  public String getMethodName()
  {
    return methodName;
  }
}

