package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;

public class FilterTest extends BaseTest {


    @BeforeMethod
    public void setUpHomePage() {

        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void tourMonthAndCountryTest() throws Exception {

        WebElement input = homePage.getWhereToInputAnchor();
        //WebElement input = homePage.getWhereToInputCSS();
        input.clear();
        input.sendKeys("Romania");

        Thread.sleep(1000);
        String enteredText = input.getAttribute("value");
        System.out.println(enteredText);

        boolean isCountryContained = false;
        if (enteredText.contains("Romania")){
            isCountryContained = true;
        }
       Assert.assertTrue(isCountryContained);

        input.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        List<WebElement> listDropDown = driver.findElements(By.xpath("//ul[@class='autocomplete-list']/li[@class='autocomplete-item']"));
        String firstElementText = listDropDown.get(0).getText();

        boolean firstElementContainsCountry = false;
        if (firstElementText.contains("Romania")) {
            firstElementContainsCountry = true;
        }
        System.out.println(firstElementText);
        Assert.assertTrue(firstElementContainsCountry,
                "Verify that the Country was entered in the input field");

        listDropDown.get(0).click();

        List<WebElement> months = driver.findElements(By.xpath("//div[@class='months']/div"));
        months.get(11).click();

        WebElement webElement = driver.findElement(By.linkText("Search"));
        webElement.click();

        Thread.sleep(1000);

        WebElement webElement1 = driver.findElement(By.xpath("//div[@class='c']/div[@class='stat']/h2"));

        String tripsFound = webElement1.getText();
        System.out.println(tripsFound);
        boolean containsCountry = false;
        if (tripsFound.contains("Romania")) {
            containsCountry = true;
        }

        Assert.assertTrue(containsCountry);
    }
}
