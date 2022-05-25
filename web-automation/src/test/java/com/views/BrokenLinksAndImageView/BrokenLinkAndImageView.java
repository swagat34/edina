package com.views.BrokenLinksAndImageView;

import com.generic.Pojo;
import com.pageFactory.BrokenLinksAndImagePage.BrokenLinkAndImagePage;
import com.pageFactory.LoginPage.LoginPage;

public class BrokenLinkAndImageView {


    String testData ="";
    private Pojo objPojo;
    private BrokenLinkAndImagePage objBrokenLinkAndImagePage;


    public BrokenLinkAndImageView(Pojo objPojo) {
        this.objPojo = objPojo;
        objBrokenLinkAndImagePage = new BrokenLinkAndImagePage(objPojo);

    }


    public void verifyTopLinksHaveValidLinks() {
        objBrokenLinkAndImagePage.verifyTopLinksHaveValidLinks();
    }

    public void verifyTopLinksHaveValidImages() {
        objBrokenLinkAndImagePage.verifyTopLinksHaveValidImages();
    }

    public void verifyAllBrokenLinks() {
        objBrokenLinkAndImagePage.verifyAllBrokenLinks();
    }

    public void verifyAllBrokenImages() {
        objBrokenLinkAndImagePage.verifyAllBrokenImages();
    }
}
