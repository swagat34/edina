package com.generic;

import org.testng.IRetryAnalyzer;
import org.testng.ITest;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RetryAnalyzer implements IRetryAnalyzer
{

  int retryLimit = 0;
  int counter = 0;

  public RetryAnalyzer() {}

  public boolean retry(ITestResult result)
  {
    CustomReporter objCustomReporter = new CustomReporter();
    Properties objProperties = new Properties();
    try {
      objProperties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    retryLimit = Integer.parseInt(objProperties.getProperty("retry"));
    if (counter < retryLimit)
    {
      if(getResultStatusName(result.getStatus()).equalsIgnoreCase("FAILURE") && result.getStatus()==2){
        CustomReporter.retryFailureDeleteFromExtentReport(result);
        counter += 1;
        System.out.println("Remove Failed TC from report to avoid duplicate result");
        return true;
      }
    }
    return false;
  }

  public String getResultStatusName(int status) {
    String resultName = null;
    if(status==1)
      resultName = "SUCCESS";
    if(status==2)
      resultName = "FAILURE";
    if(status==3)
      resultName = "SKIP";
    return resultName;
  }

}
