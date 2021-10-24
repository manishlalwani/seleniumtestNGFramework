package com.makemytrip.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.makemytrip.basetest.basetest;

import io.qameta.allure.Description;

public class homepagetestcases extends basetest{




	@Description("Homepagetitle test...")
	@Test(enabled=true,priority=0)
	public void verifyhomePageTitleTest() {
		AssertJUnit.assertEquals(hp.getHomePageTitle(),"MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
	}

	@Description("Verify Image Logo....")
	@Test(enabled=true,priority=0)
	public void verifyhompePageLogoTest() {
		AssertJUnit.assertEquals(hp.getImageIconDetails(),"MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
	}
	
	@Description("Compare all header links")
	@Test(enabled=true,priority=1)
	public void verifyAllLinksText() {
		String[] arr = {"Flights","Hotels","Homestays","Holiday Packages","Trains","Buses","Cabs","Visa","Charter Flights","Activities"};
		List<String> actualListText = hp.getprintAllLinks();
		List<String> expectedList = new ArrayList<String>();
		expectedList = Arrays.asList(arr);
		AssertJUnit.assertTrue(expectedList.equals(actualListText));
		
	}
	
	@Test(enabled=true,priority=2)
	public void verifyTripSelection() {
		hp.enterFromCity("Mumbai");
		hp.enterToCity("Delhi");
		hp.selectCurrentDateFromDatePicker();
		hp.clickSearch();
	}






}
