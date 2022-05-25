package com.pageFactory.SavedSearchPage;

import com.generic.ExcelDataProvider;
import com.generic.Pojo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class SavedSearchPage {
    private Pojo objPojo;
    private String strReturnValue = "";
    private boolean blnReturnStatus = false;
    private Integer intReturnValue = null;
    private WebElement returnWebElement = null;
    public static ArrayList<String> propertyCost = new ArrayList<>();
    public static ArrayList<String> propertyMLS =  new ArrayList<>();
    public static ArrayList<String>  fullAddress = new ArrayList<>();

    public SavedSearchPage(Pojo pojo) {
        this.objPojo = pojo;
        PageFactory.initElements(objPojo.getDriver(),this);
    }



    //Main Page
    @FindBy(xpath="//a[contains(text(), 'Find a home')]")
    protected WebElement searchButton;

    //Search Page Fields
    @FindBy(id="multiSuggester")
    protected WebElement searchCityField;

    @FindBy(xpath="//div[@class='select-dropdown']")
    protected List<WebElement> searchCriteriaDropDowns;
    //Status Drop-Down Options
    @FindBy(id="sts_sale_main")
    protected WebElement forSaleOption;

    @FindBy(id="sts_con_main")
    protected WebElement activeContingentOption;

    @FindBy(id="sts_pend_main")
    protected WebElement pendingOption;

    @FindBy(id="sts_sold_main")
    protected WebElement soldOption;

    @FindBy(id="sts_soon_main")
    protected WebElement comingSoonOption;

    //Bedroom Count Drop-Down Options
    @FindBy(xpath="//p[contains(text(), '2+ beds')]")
    protected WebElement twoBedroomOption;


    //Bathroom Count Drop-Down Options
    @FindBy(xpath="//p[contains(text(), '2+ baths')]")
    protected WebElement twoBathroomOption;

    @FindBy(xpath="//div[@class='select-dropdown price-range-input']")
    protected WebElement priceRangeDropDown;
    //Status Drop-Down Options
    @FindBy(id="minPrice_main")
    protected WebElement minPriceOption;

    @FindBy(id="maxPrice_main")
    protected WebElement maxPriceOption;

    @FindBy(xpath="//a[contains(text(), 'Save search')]")
    protected WebElement saveSearchButton;

    //Save Search Pop-up
    @FindBy(id="searchName")
    protected WebElement saveSearchNameField;

    @FindBy(xpath="//button[contains(text(), 'Save')]")
    protected WebElement confirmSaveSearchButton;

    //Save property
    @FindBy(xpath="//span[@ng-if='!item.document.auctionYn']")
    protected List<WebElement> propertyCostList;

    @FindBy(xpath="//p[@class='mls-number']//span")
    protected List<WebElement> propertyMLSList;

    @FindBy(xpath="//span[@itemprop='streetAddress']")
    protected List<WebElement> propertyStreetAddressList;

    @FindBy(xpath="//span[@itemprop='addressLocality']")
    protected List<WebElement> propertyCityList;

    @FindBy(xpath="//span[@itemprop='addressRegion']")
    protected List<WebElement> propertyStateList;

    @FindBy(xpath="//span[@itemprop='postalCode']")
    protected List<WebElement> propertyZipCodeList;

    @FindBy(xpath="//a[@class='saved-property']")
    protected List<WebElement> savePropertyList;

    //Saved Info
    @FindBy(xpath="//div[@ng-repeat='search in savedSearch.savedSearches']//h4")
    protected List<WebElement> savedSearchList;

    @FindBy(xpath="//a[contains(text(), 'Delete')]")
    protected List<WebElement> savedSearchesDeleteButton;

    @FindBy(xpath="//a[@class='saved-property']")
    protected List<WebElement> savedPropertiesFavouriteButton;

    @FindBy(xpath="//button[@ng-click='unfavoriteProperty()']")
    protected WebElement unfavouritePopupButton;

    @FindBy(xpath="//span[@ng-if='!item.document.auctionYn']")
    protected List<WebElement> savedPropertyCostList;

    @FindBy(xpath="//p[@class='mls-number']//span")
    protected List<WebElement> savedPropertyMLSList;

    @FindBy(xpath="//span[@class='address-line']")
    protected List<WebElement> savedPropertyAddressList;

    @FindBy(xpath="//span[@itemprop='addressLocality']")
    protected List<WebElement> savedPropertyCityList;

    @FindBy(xpath="//span[@itemprop='addressRegion']")
    protected List<WebElement> savedPropertyStateList;

    @FindBy(xpath="//span[@itemprop='postalCode']")
    protected List<WebElement> savedPropertyZipCodeList;

    @FindBy(xpath="//a[@class='address-link']")
    protected List<WebElement> fullAddressList;

    //Account Drop-Down
    @FindBy(xpath="//span[@class='nameInitals']")
    protected WebElement userAccountDropDown;

    //	@FindBy(xpath="//a[contains(text(), 'Favorite properties')]")
    @FindBy(xpath="//a[@href='/account/savedlistinglist']")
    protected WebElement favoritePropertiesButton;

    @FindBy(xpath="//a[contains(text(), 'Saved searches')]")
    protected WebElement savedSearchesButton;

    @FindBy(xpath="//h2[contains(text(),'Sales History & Tax Summary')]/parent::a")
    protected WebElement salesHistoryAndTaxSummaryPlusIcon;

    //Search Page Fields
    @FindBy(xpath="//button[@id='inlineSearchBtn']")
    protected WebElement search_button;

    @FindBy(id="taxSummary")
    protected WebElement taxSummaryTable;

    @FindBy(xpath="//thead//th[text()='Tax Year']")
    protected WebElement taxSummaryTableHeader_TaxYear;
    @FindBy(xpath="//thead//th[text()='Estimated Market Value']")
    protected WebElement taxSummaryTableHeader_EMA;
    @FindBy(xpath="//thead//th[text()='Taxable Market Value']")
    protected WebElement taxSummaryTableHeader_TaxableMarketValue;
    @FindBy(xpath="//thead//th[text()='Total Tax']")
    protected WebElement taxSummaryTableHeader_TotalTax;
    @FindBy(xpath="//table[@id='taxSummary']//tbody")
    protected WebElement taxSummaryTable_BodyValue;


    public void deleteSavedSearches() {
        objPojo.getObjUtilities().logReporter("Click on Search Button",
                objPojo.getObjWrapperFunctions().click(searchButton));
        objPojo.getObjWrapperFunctions().waitFor(3);
        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Saved Search button",
                objPojo.getObjWrapperFunctions().clickUsingActions(savedSearchesButton));
        objPojo.getObjWrapperFunctions().waitFor(8);
        List<WebElement> ele = objPojo.getDriver().findElements(By.xpath("//a[contains(text(), 'Delete')]"));
        int countsize = ele.size();
        while(countsize>0){
            WebElement ele1=   objPojo.getDriver().findElement(By.xpath("(//a[contains(text(), 'Delete')])["+countsize +"]"));
            objPojo.getObjUtilities().logReporter("Click on Delete button",
                    objPojo.getObjWrapperFunctions().click(ele1));
            objPojo.getObjWrapperFunctions().waitFor(3);
            objPojo.getObjUtilities().logReporter("Click on Pop up yes button",
                    objPojo.getObjWrapperFunctions().click(unfavouritePopupButton));
            objPojo.getObjWrapperFunctions().waitFor(3);
            countsize--;
        }

    }


    public void performSearch(Map<String, String> data) {
        objPojo.getObjWrapperFunctions().waitFor(7);
        objPojo.getObjWrapperFunctions().waitPageLoaded();
        objPojo.getObjWrapperFunctions().scrollToViewUsingActions(searchButton);
        objPojo.getObjUtilities().logReporter("Click on Find A Home Button",
                objPojo.getObjWrapperFunctions().click(searchButton));
        objPojo.getObjWrapperFunctions().waitFor(3);
        objPojo.getObjUtilities().logReporter("Set City Name",data.get("Search City Criteria"),
                objPojo.getObjWrapperFunctions().setText(searchCityField,data.get("Search City Criteria")));
        objPojo.getObjWrapperFunctions().waitFor(2);
        objPojo.getObjUtilities().logReporter("Click on Search Criteria Dropdown",
                objPojo.getObjWrapperFunctions().click(searchCriteriaDropDowns.get(0)));
        objPojo.getObjWrapperFunctions().waitFor(5);
        objPojo.getObjUtilities().logReporter("Click on For Sale Checkbox in Status",
                objPojo.getObjWrapperFunctions().selectCheckBox(forSaleOption,true));
        objPojo.getObjUtilities().logReporter("Uncheck Active Contigent in Status",
                objPojo.getObjWrapperFunctions().selectCheckBox(activeContingentOption,false));
        objPojo.getObjUtilities().logReporter("Uncheck PendingOption in Status",
                objPojo.getObjWrapperFunctions().selectCheckBox(pendingOption,false));
        objPojo.getObjUtilities().logReporter("Uncheck sold option in status ",
                objPojo.getObjWrapperFunctions().selectCheckBox(soldOption,false));
        objPojo.getObjUtilities().logReporter("Uncheck coming soon option in status ",
                objPojo.getObjWrapperFunctions().selectCheckBox(comingSoonOption,false));
        objPojo.getObjUtilities().logReporter("Click on Price Range Dropdown",
                objPojo.getObjWrapperFunctions().click(priceRangeDropDown));
        //Remove any existing info
        for (int i = 0; i < 9; i++) {
            objPojo.getObjWrapperFunctions().sendKeyBoardKeys(minPriceOption,"clear");
        }

        objPojo.getObjUtilities().logReporter("Set Minimun Price : ",data.get("Search Minimum Price"),
                objPojo.getObjWrapperFunctions().setText(minPriceOption,data.get("Search Minimum Price")));

        objPojo.getObjWrapperFunctions().waitFor(2);

        //Remove any existing info
        for (int i = 0; i < 9; i++) {
            objPojo.getObjWrapperFunctions().sendKeyBoardKeys(maxPriceOption,"clear");
        }
        objPojo.getObjUtilities().logReporter("Set maximum Price : ",data.get("Search Maximum Price"),
                objPojo.getObjWrapperFunctions().setText(maxPriceOption,data.get("Search Maximum Price")));

        objPojo.getObjWrapperFunctions().waitFor(2);

        objPojo.getObjUtilities().logReporter("Select Amount of Bedroom Dropdown",
                objPojo.getObjWrapperFunctions().click(searchCriteriaDropDowns.get(1)));

        WebElement ele1=   objPojo.getDriver().findElement(By.xpath("//p[contains(text(), '" + data.get("Number of Bedrooms") + "+ beds')]"));
        objPojo.getObjUtilities().logReporter("Click on No of Bedroom ",
                objPojo.getObjWrapperFunctions().click(ele1));

        // Select no of Bathroom
        objPojo.getObjWrapperFunctions().waitFor(2);

        objPojo.getObjUtilities().logReporter("Select Amount of Bathroom Dropdown",
                objPojo.getObjWrapperFunctions().click(searchCriteriaDropDowns.get(2)));

        WebElement ele2=   objPojo.getDriver().findElement(By.xpath("//p[contains(text(), '" + data.get("Number of Bathrooms") + "+ baths')]"));
        objPojo.getObjUtilities().logReporter("Click on No of Bathrooms ",
                objPojo.getObjWrapperFunctions().click(ele2));

        objPojo.getObjWrapperFunctions().waitFor(4);

        objPojo.getObjUtilities().logReporter("Click on Save Search Button ",
                objPojo.getObjWrapperFunctions().click(saveSearchButton));

        objPojo.getObjWrapperFunctions().waitFor(2);

        objPojo.getObjUtilities().logReporter("Set maximum Price : ",data.get("Saved Search Name"),
                objPojo.getObjWrapperFunctions().setText(saveSearchNameField,data.get("Saved Search Name")));

        objPojo.getObjWrapperFunctions().waitFor(2);

        objPojo.getObjUtilities().logReporter("Click on Confirm Save Search Button ",
                objPojo.getObjWrapperFunctions().click(confirmSaveSearchButton));

    }

    public void verifySavedSearchInfo(Map<String, String> data) {
        objPojo.getObjUtilities().logReporter("Click on Search Button",
                objPojo.getObjWrapperFunctions().clickWebElementUsingJavaScript(searchButton));
        objPojo.getObjWrapperFunctions().waitFor(3);
        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Saved Search button",
                objPojo.getObjWrapperFunctions().clickUsingActions(savedSearchesButton));
        objPojo.getObjWrapperFunctions().waitFor(4);
        objPojo.getObjUtilities().logReporter("Verify on Saved Search has been saved properly ",
                objPojo.getObjWrapperFunctions().getText(savedSearchList.get(0),"text").equalsIgnoreCase(data.get("Saved Search Name")));

    }

    public void saveFirstThreeProperties() {
        objPojo.getObjWrapperFunctions().waitFor(8);

            try {
                for (int i = 1; i < 4; i++) {
                    //Save the desired Property
                    objPojo.getObjWrapperFunctions().scrollToViewDown(savePropertyList.get(i));
                    objPojo.getObjUtilities().logReporter("Click on property Favourite icon ",
                            objPojo.getObjWrapperFunctions().click(savePropertyList.get(i)));
                    objPojo.getObjUtilities().logReporter("Address Details : ",
                            propertyStreetAddressList.get(i).getText() + " " + propertyCityList.get(i).getText() + ", " + propertyStateList.get(i).getText() + ", " + propertyZipCodeList.get(i).getText(),true);
                 propertyCost.add(propertyCostList.get(i).getText().trim());
                 propertyMLS.add(propertyMLSList.get(i).getText().trim());
                 fullAddress.add(propertyStreetAddressList.get(i).getText() + " " + propertyCityList.get(i).getText() + ", " + propertyStateList.get(i).getText() + " " + propertyZipCodeList.get(i).getText());
                    System.out.println(propertyCostList.get(i).getText());
                    System.out.println(propertyMLSList.get(i).getText());
                    System.out.println(propertyStreetAddressList.get(i).getText() + " " + propertyCityList.get(i).getText() + ", " + propertyStateList.get(i).getText() + ", " + propertyZipCodeList.get(i).getText());
                }
            } catch (Exception e) {
                System.out.println(e);
            }

    }

    public void verifySavedProperties() {
        objPojo.getObjUtilities().logReporter("Click on Find a Home link",
                objPojo.getObjWrapperFunctions().click(searchButton));
        objPojo.getObjWrapperFunctions().waitFor(3);
        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Favourite Property button",
                objPojo.getObjWrapperFunctions().clickUsingActions(favoritePropertiesButton));
        objPojo.getObjWrapperFunctions().setURL(objPojo.getServerAndPort()+"account/savedlistinglist");
        objPojo.getObjWrapperFunctions().waitFor(12);
        List<String> ExpectedCost= new ArrayList<>();
        List<String> ExpectedMLS= new ArrayList<>();
        List<String> ExpectedAddress= new ArrayList<>();
        objPojo.getObjWrapperFunctions().pageRefresh();
        objPojo.getObjWrapperFunctions().waitFor(12);

        List<WebElement> ele = objPojo.getDriver().findElements(By.xpath("//a[@class='saved-property']"));
        int countsize = ele.size();
        System.out.println(countsize);
        while(countsize>0){

            WebElement ele1 = objPojo.getDriver().findElement(By.xpath("(//a[@class='address-link'])["+countsize+"]"));
            objPojo.getObjWrapperFunctions().scrollToViewUsingActions(ele1);
            String abc= (objPojo.getObjWrapperFunctions().getText(ele1,"text")).replace("\n", " ");
            ExpectedAddress.add(abc);

            WebElement ele2 = objPojo.getDriver().findElement(By.xpath("(//p[@class='h4-headline']//span)["+countsize+"]"));
            objPojo.getObjWrapperFunctions().scrollToViewUsingActions(ele2);
            String qw= objPojo.getObjWrapperFunctions().getText(ele2,"text");
            ExpectedCost.add(qw);

            WebElement ele3 = objPojo.getDriver().findElement(By.xpath("(//p[@class='mls-number']//span)["+countsize+"]"));
            objPojo.getObjWrapperFunctions().scrollToViewUsingActions(ele3);
            String w = objPojo.getObjWrapperFunctions().getText(ele3,"text");
            ExpectedMLS.add(w);
            objPojo.getObjWrapperFunctions().waitFor(5);
            countsize--;
        }
        Collections.sort(fullAddress);
        Collections.sort(propertyCost);
        Collections.sort(propertyMLS);
        Collections.sort(ExpectedCost);
        Collections.sort(ExpectedMLS);
        Collections.sort(ExpectedAddress);
        System.out.println(propertyCost.containsAll(ExpectedCost));
        System.out.println(propertyMLS.containsAll(ExpectedMLS));
        System.out.println(fullAddress.containsAll(ExpectedAddress));
        for(String s : ExpectedAddress ){
            objPojo.getObjUtilities().logReporter("Properties Listed In Favourite Properties Screen : ", s, true);
        }

        objPojo.getObjUtilities().logReporter("Verified All Favourite Property Cost", propertyCost.containsAll(ExpectedCost));
        objPojo.getObjUtilities().logReporter("Verified All Favourite Property MLS", propertyMLS.containsAll(ExpectedMLS));
        objPojo.getObjUtilities().logReporter("Verified All Favourite Property Full Address ", fullAddress.containsAll(ExpectedAddress));


    }

    public void deleteExistingFavouriteSearch() {
        objPojo.getObjUtilities().logReporter("Click on Search Button",
                objPojo.getObjWrapperFunctions().click(searchButton));
        objPojo.getObjWrapperFunctions().waitFor(7);
        objPojo.getObjWrapperFunctions().mouseHover(userAccountDropDown);
        objPojo.getObjUtilities().logReporter("Click on Favourite Properties button",
                objPojo.getObjWrapperFunctions().clickUsingActions(favoritePropertiesButton));
        objPojo.getObjWrapperFunctions().waitFor(15);
        objPojo.getObjWrapperFunctions().pageRefresh();
        objPojo.getObjWrapperFunctions().waitFor(15);
        List<WebElement> ele = objPojo.getDriver().findElements(By.xpath("//a[@class='saved-property']"));
        int countsize = ele.size();
        System.out.println(countsize);
        while(countsize>0){
            WebElement ele1=   objPojo.getDriver().findElement(By.xpath("(//a[@class='saved-property'])["+countsize +"]"));
            objPojo.getObjUtilities().logReporter("Click on Delete button",
                    objPojo.getObjWrapperFunctions().click(ele1));
            objPojo.getObjWrapperFunctions().waitFor(7);
            if(objPojo.getObjWrapperFunctions().checkElementDisplayed(unfavouritePopupButton)) {
                objPojo.getObjUtilities().logReporter("Click on Pop up yes button",
                        objPojo.getObjWrapperFunctions().click(unfavouritePopupButton));
            }
            objPojo.getObjWrapperFunctions().waitFor(5);
            countsize--;
        }

    }






    public void verifySaleHistoryAndTaxSummaryIsDisplayed(String propState , String propValue) {
         try{
             objPojo.getObjUtilities().logReporter("Click on Find A Home Link ",
                     objPojo.getObjWrapperFunctions().click(searchButton));
           objPojo.getObjWrapperFunctions().waitFor(6);
             objPojo.getObjUtilities().logReporter("Set Search City Input Field ", propState,
                     objPojo.getObjWrapperFunctions().setText(searchCityField , propState));
             objPojo.getObjWrapperFunctions().waitFor(3);
             objPojo.getObjUtilities().logReporter("Click on City Suggestion Dropdown ",
                     objPojo.getObjWrapperFunctions().click(objPojo.getDriver().findElement(By.xpath("//a[contains(@title,'"+propState+"')]"))));
             objPojo.getObjUtilities().logReporter("Click on Search button ",
                     objPojo.getObjWrapperFunctions().click(search_button));
             objPojo.getObjWrapperFunctions().waitFor(12);

            ((JavascriptExecutor)objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(true);", objPojo.getDriver().findElement(By.xpath("//span[contains(text(),'"+propValue+"')]")));
             objPojo.getObjWrapperFunctions().waitFor(6);

             objPojo.getDriver().findElement(By.xpath("//span[contains(text(),'"+propValue+"')]")).click();
             objPojo.getObjWrapperFunctions().waitFor(3);
             objPojo.getObjUtilities().logReporterInfoOnly( "Scrolled to the Sales History And Tax Summary");

            ((JavascriptExecutor)objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(true);", salesHistoryAndTaxSummaryPlusIcon);
             objPojo.getObjWrapperFunctions().waitFor(3);
            JavascriptExecutor executor = (JavascriptExecutor) objPojo.getDriver();
            executor.executeScript("arguments[0].click();", salesHistoryAndTaxSummaryPlusIcon);

             objPojo.getObjWrapperFunctions().waitFor(5);

             objPojo.getObjUtilities().logReporter("verify Tax Summary Table is Displayed",
                     objPojo.getObjWrapperFunctions().checkElementDisplayed(taxSummaryTable));
             objPojo.getObjUtilities().logReporter("verify Tax Summary Table - Tax year  is Displayed",
                     objPojo.getObjWrapperFunctions().checkElementDisplayed(taxSummaryTableHeader_TaxYear));
             objPojo.getObjUtilities().logReporter("verify Tax Summary Table - EMA is Displayed",
                     objPojo.getObjWrapperFunctions().checkElementDisplayed(taxSummaryTableHeader_EMA));
             objPojo.getObjUtilities().logReporter("verify Tax Summary Table - Taxable Market Value is Displayed",
                     objPojo.getObjWrapperFunctions().checkElementDisplayed(taxSummaryTableHeader_TaxableMarketValue));
             objPojo.getObjUtilities().logReporter("verify Tax Summary Table -Total Tax is Displayed",
                     objPojo.getObjWrapperFunctions().checkElementDisplayed(taxSummaryTableHeader_TotalTax));
             objPojo.getObjUtilities().logReporter("verify Tax Summary Table - Body Value is Displayed",
                     objPojo.getObjWrapperFunctions().checkElementDisplayed(taxSummaryTable_BodyValue));
            ((JavascriptExecutor)objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(true);", userAccountDropDown);


        }catch (Exception e){e.printStackTrace();}

    }


}
