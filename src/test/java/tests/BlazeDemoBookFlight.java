package tests;

//import jdk.internal.access.JavaIOFileDescriptorAccess;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BlazeDemoFlightsPage;
import pages.BlazeDemoHomePage;
import pages.BlazeDemoPurchasePage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.TestBase;

import java.util.List;

public class BlazeDemoBookFlight extends TestBase {

    String flightFrom="San Diego";
    String flightTo="New York";

    @Test
    public void TC01(){
        double tempMax=0;
        int index=0;
        double maxPrice;
        BlazeDemoHomePage blazeDemoHomePage=new BlazeDemoHomePage();
        BlazeDemoFlightsPage blazeDemoFlightPage = new BlazeDemoFlightsPage();
        BlazeDemoPurchasePage blazeDemoPurchasePage = new BlazeDemoPurchasePage();

        driver.get(Configuration.getProperty("BlazeDemoURL"));
        BrowserUtils.selectDropdownByValue(blazeDemoHomePage.departureCity, flightFrom);
        BrowserUtils.selectDropdownByValue(blazeDemoHomePage.destinationCity, flightTo);
        blazeDemoHomePage.findFlightButton.click();
        List<WebElement> price = blazeDemoFlightPage.prices;


        for(int i=0; i<price.size(); i++){
        double tempPrice=Double.parseDouble(blazeDemoFlightPage.prices.get(i).getText()
                .replaceAll("\\$",""));

        if(tempPrice>tempMax){
            tempMax=tempPrice;
            index=i;

        }

        }
        blazeDemoFlightPage.chooseFlight.get(index).click();
        Double actualPrice = Double.parseDouble(blazeDemoPurchasePage.priceOnPurchasePage.getText()
                .substring(blazeDemoPurchasePage.priceOnPurchasePage.getText().indexOf(" ")));
        Assert.assertEquals(actualPrice,tempMax);
        System.out.println(blazeDemoPurchasePage.priceOnPurchasePage.getText());
    }


    @DataProvider(name = "ticketPurchase")
    public static Object[][] testData(){//two dimensional Array
        return new Object[][]{ //return two dimensional Array Data
                {"John Doe","123 Washington ave.", "New York","NY","12345","Visa","1234567890","11","2025","John Doe"},
                {"David Clark","123 Washington ave.","Austin","TX","12345","American Espress","mycreditcardnumber","11","2025","David Clark"}
        };

    }

    @Test(dataProvider = "ticketPurchase")
    public void TC02(String name, String address,String city,String state, String sipCode,String cardType,
                     String creditCardNumber,String month,String year, String nameOnCard){
        double tempMax=0;
        int index=0;
        double maxPrice;

        BlazeDemoHomePage blazeDemoHomePage=new BlazeDemoHomePage();
        BlazeDemoFlightsPage blazeDemoFlightPage = new BlazeDemoFlightsPage();
        BlazeDemoPurchasePage blazeDemoPurchasePage = new BlazeDemoPurchasePage();

        driver.get(Configuration.getProperty("BlazeDemoURL"));
        BrowserUtils.selectDropdownByValue(blazeDemoHomePage.departureCity, flightFrom);
        BrowserUtils.selectDropdownByValue(blazeDemoHomePage.destinationCity, flightTo);
        blazeDemoHomePage.findFlightButton.click();
        List<WebElement> price = blazeDemoFlightPage.prices;


        for(int i=0; i<price.size(); i++){
            double tempPrice=Double.parseDouble(blazeDemoFlightPage.prices.get(i).getText()
                    .replaceAll("\\$",""));

            if(tempPrice>tempMax){
                tempMax=tempPrice;
                index=i;

            }

        }
        blazeDemoFlightPage.chooseFlight.get(index).click();



        System.out.println(blazeDemoPurchasePage.priceOnPurchasePage.getText());
    }
}
