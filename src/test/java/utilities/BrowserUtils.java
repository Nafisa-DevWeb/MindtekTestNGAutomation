package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BrowserUtils {

    /*
    This method will accept webelement of dropdown
    and String value of dropdown. And then it will select provided value in dropdown.
    Ex.:
    .selectByValue(element,"1");
     */

    public static void selectDropdownByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
