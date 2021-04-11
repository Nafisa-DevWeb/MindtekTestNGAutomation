package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BlazeDemoFlightsPage;
import pages.BlazeDemoHomePage;
import utilities.Configuration;
import utilities.TestBase;

import java.util.List;

public class BlazeDemoPriceTest extends TestBase {
    BlazeDemoHomePage blazeDemoHomepage=new BlazeDemoHomePage();
    BlazeDemoFlightsPage blazeDemoFlightsPage=new BlazeDemoFlightsPage();

    //This test will verify that all flight prices are bellow $1000

    @Test
    public void priceTest(){
        driver.get(Configuration.getProperty("BlazeDemoURL"));
     //   driver.findElement(By.xpath("//input[@type='submit']")).click();
        blazeDemoHomepage.findFlightButton.click();
        List<WebElement> prices = blazeDemoFlightsPage.prices;

        for(WebElement price:prices){
            //We need to get the price
            String strPrice = price.getText().substring(1);//should return $472.56->472.56
            //String 472.56 -> Double
            double doublePrice = Double.parseDouble(strPrice);
            //Double 472.56
            Assert.assertTrue(doublePrice<1000);//472.56<1000 = true,432.98<1000=true and all 5 price less then 1000
            System.out.println(doublePrice);
        }


    }

    /*
    Test 1:
    Verify that test result having only two "Virgin America" flights
    https://blazedemo.com/reserve.php

    Test 2:
    Verify that flight text having city names like (Flights from San Diego to New York:)
    https://blazedemo.com/reserve.php
     */
}
