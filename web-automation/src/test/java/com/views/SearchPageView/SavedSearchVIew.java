package com.views.SearchPageView;

import com.generic.Pojo;
import com.pageFactory.SavedSearchPage.SavedSearchPage;
import com.pageFactory.LoginPage.LoginPage;


import java.util.Map;

public class SavedSearchVIew {

    String testData = "";
    String testData1 = "";
    private Pojo objPojo;
    private LoginPage objLoginPage;
    private SavedSearchPage objSavedSearchPage;



    public SavedSearchVIew(Pojo objPojo) {
        this.objPojo = objPojo;
        objLoginPage = new LoginPage(objPojo);
        objSavedSearchPage = new SavedSearchPage(objPojo);


    }


    public void deleteSavedSearches() {
        objSavedSearchPage.deleteSavedSearches();
    }

    public void performSearch(Map<String, String> data) {
        objSavedSearchPage.performSearch(data);
    }

    public void verifySavedSearchInfo(Map<String, String> data) {
        objSavedSearchPage.verifySavedSearchInfo(data);
    }

    public void saveFirstThreeProperties() {
        objSavedSearchPage.saveFirstThreeProperties();

    }

    public void verifySavedProperties() {
        objSavedSearchPage.verifySavedProperties();
    }

    public void deleteExistingFavouriteSearch() {
        objSavedSearchPage.deleteExistingFavouriteSearch();
    }

    public void verifySaleHistoryAndTaxSummaryIsDisplayed(String propertyState, String propertyNameValue) {
        objSavedSearchPage.verifySaleHistoryAndTaxSummaryIsDisplayed(propertyState,propertyNameValue);
    }
}
