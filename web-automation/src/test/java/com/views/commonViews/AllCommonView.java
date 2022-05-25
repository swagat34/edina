package com.views.commonViews;

import com.generic.Pojo;

import java.lang.reflect.Method;


/**
 * @Author : Swagat Mohapatra
 * @Created on : 24-12-2021
 * @Updated By :
 * @Updated on :
 * @Description : This view will contain most of common functionalities.
 */

public class AllCommonView {

	String testData = "";
	String testData1 = "";
	String testData2 = "";
	String testData3 = "";
	private String strReturnValue = "";
	private String strUniqueKey = "";

	private Pojo objPojo;

	public AllCommonView(Pojo objPojo) {

		this.objPojo = objPojo;
		strUniqueKey = objPojo.getObjUtilities().getRandomStringWithNumbers(3);

	}


	public void refreshWholePageF5() {
		objPojo.getObjWrapperFunctions().pageRefresh();
		objPojo.getObjWrapperFunctions().waitFor(5);
	}

	public void setUrl(String url) {
		objPojo.getObjWrapperFunctions().pageRefresh();
		objPojo.getObjWrapperFunctions().setURL(url);
		objPojo.getObjWrapperFunctions().waitFor(2);
	}


	public void clickOnWindowAlertOkButton() {
		objPojo.getObjWrapperFunctions().waitFor(2);
		if (objPojo.getObjWrapperFunctions().ifAlertIsPresent()) {
			objPojo.getObjWrapperFunctions().alertBoxAccept();
		}
		objPojo.getObjWrapperFunctions().waitFor(1);
	}


	public void setResourceName(String resName) {
		objPojo.getObjWrapperFunctions().setResouceName(resName);
	}


	public void commonGoBackward() {
		objPojo.getDriver().navigate().back();
	}

	public void setGroupPriority(Method priority) {
		objPojo.getObjWrapperFunctions().setGroupPriority(priority);
	}


	public void setGroupPriority(Method priority, Method description) {

		objPojo.getObjWrapperFunctions().setGroupPriority(priority);
		objPojo.getObjWrapperFunctions().setDescription(description);
	}


	public void commonSwitchToDefaultContent() {
		objPojo.getObjWrapperFunctions().switchToDefaultContent();
	}


}
