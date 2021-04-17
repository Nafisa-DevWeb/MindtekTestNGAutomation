package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SaucedemoHomePage;
import pages.SaucedemoLoginPage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.TestBase;

import java.util.List;

public class SaucedemoAppTest extends TestBase {

    @Test(groups = {"regression","smoke"})
    public void filterTest(){
        driver.get(Configuration.getProperty("SaucedemoURL"));//open webside-url inside Confog.proper
        String expectedTitle="Swag Labs"; //check Title correct "Swag Labs"
        String actualTitle=driver.getTitle();//check Title

        SoftAssert softAssert = new SoftAssert();
    //  Assert.assertEquals(actualTitle,expectedTitle);//compaire Titles
        softAssert.assertEquals(actualTitle,expectedTitle);

//Login functionality do in different class pages package
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.username.sendKeys(Configuration.getProperty("SaucedemoUsername"));
        saucedemoLoginPage.password.sendKeys(Configuration.getProperty("SaucedemoPassword"));
        saucedemoLoginPage.loginButton.click();

        SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();
        BrowserUtils.selectDropdownByValue(saucedemoHomePage.filterDropdown,"lohi");

        List<WebElement> prices = saucedemoHomePage.prices;
        //$7.99,$9.99, $15.99, $15.99, $29.99, $49.99

        for(int i=0; i< prices.size()-1; i++){
            //                                i get 1-st price
            double price1=Double.parseDouble(prices.get(i).getText().substring(1));//"7.99"->7.99
            //                               i+1 get 2-st price
            double price2=Double.parseDouble(prices.get(i+1).getText().substring(1));//"9.99"->9.99
            softAssert.assertTrue(price1<=price2);
        }

        softAssert.assertAll();//show me fail here

    }
}
