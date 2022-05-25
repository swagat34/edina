package com.pageFactory.BrokenLinksAndImagePage;

import com.generic.Pojo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrokenLinkAndImagePage {
    private Pojo objPojo;
    private String strReturnValue = "";
    private boolean blnReturnStatus = false;
    private Integer intReturnValue = null;
    private WebElement returnWebElement = null;

    public BrokenLinkAndImagePage(Pojo pojo) {
        this.objPojo = pojo;
        PageFactory.initElements(objPojo.getDriver(),this);
    }

    @FindBy(xpath="//a[text()='Realtor careers' and contains(@class,'nav-link')]")
    protected WebElement RealtorCareerLink;
    @FindBy(xpath="//a[text()='Contact' and contains(@class,'nav-link')]")
    protected WebElement ContactLink;
    @FindBy(xpath="//a[text()='Services' and @class='nav-link']")
    protected WebElement ServicesLink;
    @FindBy(xpath="//a[text()='Insights' and @class='nav-link']")
    protected WebElement InsightsLink;
    @FindBy(xpath="//a[text()='Sell' and @class='nav-link']")
    protected WebElement SellLink;
    @FindBy(xpath="//a[text()='Mortgage' and @class='nav-link']")
    protected WebElement MortagageLink;
    @FindBy(xpath="//a[text()='Find a Realtor']")
    protected WebElement FindaRealtorLink;
    @FindBy(xpath="//a[text()='Find a home']")
    protected WebElement FindaHomelink;

    @FindBy(xpath="//a[contains(text(), 'MyAtlas Milestones')]")
    protected WebElement milestonePage_navlink;

        public void verifyAllBrokenLinks() {
            String url = "";
            HttpURLConnection huc = null;
            int respCode = 200;

            List<WebElement> links = objPojo.getDriver().findElements(By.tagName("a"));
            System.out.println(links.size());
            Iterator<WebElement> it = links.iterator();

            while (it.hasNext()) {

                url = it.next().getAttribute("href");

                System.out.println(url);

                if (url == null || url.isEmpty()) {
                    System.out.println("URL is either not configured for anchor tag or it is empty");
                    continue;
                }

                try {
                    huc = (HttpURLConnection)(new URL(url).openConnection());

                    huc.setRequestMethod("HEAD");

                    huc.connect();

                    respCode = huc.getResponseCode();

                    if(respCode >= 400){

                       objPojo.getObjUtilities().logReporterwithSoftassert(url +" is a broken Link ",false);
                        System.out.println(url+" is a broken link");

                    }
                    else{
                        objPojo.getObjUtilities().logReporterwithSoftassert(url +" is a valid Link ",true);
                        System.out.println(url+" is a valid link");
                    }

                } catch (MalformedURLException e) {
                    System.out.println(e.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        public void verifyTopLinksHaveValidLinks() {
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on Realtor Career Page");
            objPojo.getObjUtilities().logReporter("Click on Realtor Career Link",
                    objPojo.getObjWrapperFunctions().click(RealtorCareerLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly("Verifying Broken Links on Find a Home Page");
            objPojo.getObjUtilities().logReporter("Click on Find a Home Link",
                    objPojo.getObjWrapperFunctions().click(FindaHomelink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on Find a Realtor Page");
            objPojo.getObjUtilities().logReporter("Click on Find a Realtor Link",
                    objPojo.getObjWrapperFunctions().click(FindaRealtorLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on Mortgage Page");
            objPojo.getObjUtilities().logReporter("Click on Mortgage Link",
                    objPojo.getObjWrapperFunctions().click(MortagageLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on SellLink Page");
            objPojo.getObjUtilities().logReporter("Click on Sell Link",
                    objPojo.getObjWrapperFunctions().click(SellLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on Insights Link Page");
            objPojo.getObjUtilities().logReporter("Click on Insights Link",
                    objPojo.getObjWrapperFunctions().click(InsightsLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on Services Link Page");
            objPojo.getObjUtilities().logReporter("Click on Services Link",
                    objPojo.getObjWrapperFunctions().click(ServicesLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Links on Contact Link Page");

            objPojo.getObjUtilities().logReporter("Click on Contact Link",
                    objPojo.getObjWrapperFunctions().click(ContactLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenLinks();
            objPojo.getDriver().navigate().back();
            objPojo.getObjUtilities().AssertAll();
        }

        public void verifyTopLinksHaveValidImages() {
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on Realtor Career Page");
            objPojo.getObjUtilities().logReporter("Click on Realtor Career Link",
                    objPojo.getObjWrapperFunctions().click(RealtorCareerLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on Find a Home Page");

            objPojo.getObjUtilities().logReporter("Click on Find a Home Link",
                    objPojo.getObjWrapperFunctions().click(FindaHomelink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on Find a Realtor Page");
            objPojo.getObjUtilities().logReporter("Click on Find a Realtor Link",
                    objPojo.getObjWrapperFunctions().click(FindaRealtorLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on Mortgage Page");
            objPojo.getObjUtilities().logReporter("Click on Mortgage Link",
                    objPojo.getObjWrapperFunctions().click(MortagageLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on SellLink Page");
            objPojo.getObjUtilities().logReporter("Click on Sell Link",
                    objPojo.getObjWrapperFunctions().click(SellLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);;
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on Insights Link Page");
            objPojo.getObjUtilities().logReporter("Click on Insights Link",
                    objPojo.getObjWrapperFunctions().click(InsightsLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Image on Services Link Page");
            objPojo.getObjUtilities().logReporter("Click on Services Link",
                    objPojo.getObjWrapperFunctions().click(ServicesLink));

            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
            objPojo.getObjWrapperFunctions().waitFor(6);
            objPojo.getObjUtilities().logReporterInfoOnly(" Verifying Broken Images on Contact Link Page");
            objPojo.getObjUtilities().logReporter("Click on Contact Link",
                    objPojo.getObjWrapperFunctions().click(ContactLink));
            objPojo.getObjWrapperFunctions().waitFor(6);
            this.verifyAllBrokenImages();
            objPojo.getDriver().navigate().back();
        }

        public void verifyAllBrokenImages() {
            URL url = null;
            HttpURLConnection huc = null;
            List<WebElement> images = objPojo.getDriver().findElements(By.tagName("img"));
            System.out.println(images.size());
            for (WebElement image: images){
                String imgsrc = image.getAttribute("src");
                try {
                    url = new URL(imgsrc);
                    URLConnection urlConnection= url.openConnection();
                    HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
                    httpURLConnection.setConnectTimeout(7000);
                    httpURLConnection.connect();

                    if(httpURLConnection.getResponseCode()==200){
                        objPojo.getObjUtilities().logReporterwithSoftassert(imgsrc +" >> " + httpURLConnection.getResponseMessage(),true);
                    }else{
                        objPojo.getObjUtilities().logReporterwithSoftassert(imgsrc +" >> " + httpURLConnection.getResponseMessage(),false);
                    }
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        public void verifyAllBrokenLinksinLinksTag() {
            String url = "";
            HttpURLConnection huc = null;
            int respCode = 200;

            List<WebElement> links = objPojo.getDriver().findElements(By.tagName("link"));
            System.out.println(links.size());
            Iterator<WebElement> it = links.iterator();

            while (it.hasNext()) {

                url = it.next().getAttribute("href");

                System.out.println(url);

                if (url == null || url.isEmpty()) {
                    System.out.println("URL is either not configured for anchor tag or it is empty");
                    continue;
                }

                try {
                    huc = (HttpURLConnection)(new URL(url).openConnection());

                    huc.setRequestMethod("HEAD");

                    huc.connect();

                    respCode = huc.getResponseCode();

                    if(respCode >= 400){
                        objPojo.getObjUtilities().logReporterwithSoftassert(url +" is a broken Link ",false);
                        System.out.println(url+" is a broken link");

                    }
                    else{
                        objPojo.getObjUtilities().logReporterwithSoftassert(url +" is a valid Link ",true);
                        System.out.println(url+" is a valid link");
                    }

                } catch (MalformedURLException e) {
                    System.out.println(e.toString());
                } catch (Exception e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        public void verifyAllBrokenImagesShadowDom() {
            JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
            WebElement ele1 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('#headingOne > div > div.advisorImg > img')");
            WebElement ele2 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('.transAdvisors > div:nth-child(2) > div > div > div > img')");
            WebElement ele3 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(4) > div > div.transItemLevel > div > div:nth-child(1) > div > div > div > img')");
            WebElement ele4 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(4) > div > div.transItemLevel > div > div:nth-child(2) > div > div > div > img')");
            WebElement ele5 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(5) > div > div.transItemLevel > div > div:nth-child(1) > div > div > div > img')");
            WebElement ele6 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(5) > div > div.transItemLevel > div > div:nth-child(2) > div > div > div > img')");
            WebElement ele7 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(5) > div > div.transItemLevel > div > div:nth-child(3) > div > div > div > img')");
            WebElement ele8 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(5) > div > div.transItemLevel > div > div:nth-child(4) > div > div > div > img')");
            WebElement ele9 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('div > div:nth-child(5) > div > div.transItemLevel > div > div:nth-child(5) > div > div > div > img')");


            ArrayList l= new ArrayList();
            l.add(ele1);
            l.add(ele2);
            l.add(ele3);
            l.add(ele4);
            l.add(ele5);
            l.add(ele6);
            l.add(ele7);
            l.add(ele8);
            l.add(ele9);

            List<WebElement> images = l;
            URL url = null;
            HttpURLConnection huc = null;

            for (WebElement image: images){
                String imgsrc = image.getAttribute("src");
                try {
                    url = new URL(imgsrc);
                    URLConnection urlConnection= url.openConnection();
                    HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
                    httpURLConnection.setConnectTimeout(7000);
                    httpURLConnection.connect();

                    if(httpURLConnection.getResponseCode()==200){
                        objPojo.getObjUtilities().logReporterwithSoftassert(imgsrc +" >> " + httpURLConnection.getResponseMessage(),true);
                    }else{
                        objPojo.getObjUtilities().logReporterwithSoftassert(imgsrc +" >> " + httpURLConnection.getResponseMessage(),false);
                    }
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        public void clickOnPropertyinsideShadowDom(){
            objPojo.getObjWrapperFunctions().waitFor(6);
            JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
            WebElement ele = (WebElement) js.executeScript("return document.querySelector('web-widget-milestones').shadowRoot.querySelector('div.transIcon')");
            System.out.println(ele.isDisplayed());
            js.executeScript("arguments[0].click()",ele);
            if(ele.isDisplayed()){
                objPojo.getObjUtilities().logReporter("Verified Properties is Displayed" ,true);

            }else{
                objPojo.getObjUtilities().logReporter("Verified Properties is not Displayed" ,false);

            }
            System.out.println("end");

            WebElement ele1 = (WebElement) js.executeScript("return document.querySelector('web-widget-milestones').shadowRoot.querySelector('div.transMenuInfo > span')");
            System.out.println("getattribute : "+ele1.getAttribute("innerHTML"));
            if(!ele1.getAttribute("innerHTML").equalsIgnoreCase("")){
                objPojo.getObjUtilities().logReporter("Verified Properties Name : " + ele1.getAttribute("innerHTML") ,true);

            }else{
                objPojo.getObjUtilities().logReporter("Verified Properties is not Displayed" ,false);

            }

            WebElement ele2 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('#wrapper > div > div.transMenu > div > div > div:nth-child(4) > div > div.transItemLevel > label:nth-child(2) > div.transMenutext > div > span')");
            System.out.println("getattribute : "+ele2.getAttribute("innerHTML"));
            if(!ele2.getAttribute("innerHTML").equalsIgnoreCase("")){
                objPojo.getObjUtilities().logReporter("Verified Second Level Properties Name : " + ele2.getAttribute("innerHTML")  ,true);
            }else{
                objPojo.getObjUtilities().logReporter("Verified Second Level Properties is not Displayed" ,false);
            }

            WebElement ele3 = (WebElement) js.executeScript("return document.querySelector('#webwidgetmilestones').shadowRoot.querySelector('#wrapper > div > div.transMenu > div > div > div:nth-child(5) > div > div.transItemLevel > label:nth-child(2) > div.transMenutext > div > span')");
            System.out.println("getattribute : "+ele3.getAttribute("innerHTML"));
            if(!ele3.getAttribute("innerHTML").equalsIgnoreCase("")){
                objPojo.getObjUtilities().logReporter("Verified Third Level Properties Name : " + ele3.getAttribute("innerHTML")  ,true);

            }else{
                objPojo.getObjUtilities().logReporter("Verified Third Level Properties is not Displayed" ,false);
            }

        }

    }

