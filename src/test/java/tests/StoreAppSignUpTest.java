package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StoreAppHomePage;
import pages.StoreAppRegisterPage;
import pages.StoreAppSignInPage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.DataUtils;
import utilities.TestBase;

public class StoreAppSignUpTest extends TestBase {



    @DataProvider(name = "signUpTestData")
    public static Object[][] testData(){//two dimensional Array
        return new Object[][]{ //return two dimensional Array Data
                {"John","Doe","1234567","1","1","2000","123 Washington ave.","Chicago","13","12345","1234567890"},
                {"John","Mr. Doe","sdgdfg","1","1","2020","33 Dee Rd.","New York","32","12355","12345555555"}
        };

    }

    @Test(dataProvider = "signUpTestData")
    public void signUpTest(String firstName,String lastName,String password,String day, String month, String year,
                           String address, String city, String state, String postcode, String mobileNumber){

        StoreAppHomePage storeAppHomePage=new StoreAppHomePage();
        StoreAppSignInPage storeAppSignInPage=new StoreAppSignInPage();
        StoreAppRegisterPage storeAppRegisterPage = new StoreAppRegisterPage();

        driver.get(Configuration.getProperty("StoreURL"));
        storeAppHomePage.signInButton.click();

        storeAppSignInPage.emailInputBox.sendKeys(DataUtils.getRandomEmail());//provide here DataUtils class for random emails
        storeAppSignInPage.createAccountButton.click();

        storeAppRegisterPage.firstName.sendKeys(firstName);
        storeAppRegisterPage.lastName.sendKeys(lastName);
        storeAppRegisterPage.password.sendKeys(password);

        //DropDown:
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.days,day);
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.months,month);
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.years,year);
        storeAppRegisterPage.address1.sendKeys(address);
        storeAppRegisterPage.cities.sendKeys(city);
        BrowserUtils.selectDropdownByValue(storeAppRegisterPage.states,state);
        storeAppRegisterPage.postcode.sendKeys(postcode);
        storeAppRegisterPage.mobile.sendKeys(mobileNumber);
        storeAppRegisterPage.registerButton.click();
        String expectedTitle="My account - My Store";

        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Actual title "+actualTitle+
                " didn't match with expected title"+expectedTitle);


    }


}
