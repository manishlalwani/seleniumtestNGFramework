package com.makemytrip.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.makemytrip.basetest.basetest;

import io.qameta.allure.Description;

public class homepagetestcases extends basetest{




	@Description("Homepagetitle test...")
	@Test(enabled=false)
	public void verifyhomePageTitleTest() {
		Assert.assertEquals(hp.getHomePageTitle(),"MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
	}

	@Description("Verify Image Logo....")
	@Test(enabled=false)
	public void verifyhompePageLogoTest() {
		Assert.assertEquals(hp.getImageIconDetails(),"MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
	}
	
	@Description("Compare all header links")
	@Test(enabled=false)
	public void verifyAllLinksText() {
		String[] arr = {"Flights","Hotels","Homestays","Holiday Packages","Trains","Buses","Cabs","Visa","Charter Flights","Activities"};
		List<String> actualListText = hp.getprintAllLinks();
		List<String> expectedList = new ArrayList<String>();
		expectedList = Arrays.asList(arr);
		Assert.assertTrue(expectedList.equals(actualListText));
		
	}
	
	@Test
	public void verifyTripSelection() {
		hp.enterFromCity("Mumbai");
		hp.enterToCity("Delhi");
		hp.selectCurrentDateFromDatePicker();
		hp.clickSearch();
	}






}
