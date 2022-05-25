package com.generic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;


public class CustomReporter
  implements ITestListener
{
  //Extent Report Declarations
  public static ExtentReports extent = ExtentManager.createInstance();
  public static ExtentTest extentTest;
  public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


  public CustomReporter() {}
  
  public void onTestStart(ITestResult result) {
    System.out.println((result.getMethod().getMethodName() + " started!"));
    extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
    test.set(extentTest);
  }

  
  public void onTestSuccess(ITestResult result)
  {
    System.out.println((result.getMethod().getMethodName() + " passed!"));
    test.get().pass("Test Case : " + result.getMethod().getMethodName() + "- passed!");

  }


  public void onTestFailure(ITestResult result)
  {
    System.out.println((result.getMethod().getMethodName() + " failed!"));
    Pojo objPojo = (Pojo) result.getInstance();
    test.get().fail(objPojo.getCustomException());
    test.get().fail("Test Case : " + result.getMethod().getMethodName() + "- failed!");


  }

  

  public void onTestSkipped(ITestResult result)
  {
    System.out.println((result.getMethod().getMethodName() + " skipped!"));
    test.get().skip(result.getThrowable());
    extent.removeTest(extentTest);
  }
  
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));

  }
  
  public void onStart(ITestContext context) {
    System.out.println("Extent Reports Test Suite started!");

  }

  public void onFinish(ITestContext context) {
    System.out.println("Extent Report  Test Suite is ending!");
    extent.flush();
  }

  public static void retryFailureDeleteFromExtentReport(ITestResult result){
    System.out.println((result.getMethod().getMethodName() + " removed !"));
    extent.removeTest(extentTest);

    System.out.println("Removing multiple failure TC id in extent report");
  }

}
