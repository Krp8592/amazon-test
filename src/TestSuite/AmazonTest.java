package TestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.Utility;

import java.util.List;

public class AmazonTest extends Utility {

    String baseUrl = "https://www.amazon.co.uk/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");  // Disable browser notification

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    public void verifyUserShouldNavigateToProductSelected() throws InterruptedException {

        clickOnElement(By.xpath("//a[@id = 'sp-cc-rejectall-link']"));

        Thread.sleep(1000);
        //Type "Dell Laptop" in the search box and press enter or click on search button.
        sendTextToElement(By.xpath("//input[@id='twotabsearchtextbox']"), "Dell Laptop");

        //CLick on search button
        clickOnElement(By.xpath("//input[@id='nav-search-submit-button']"));

        //Click on the checkbox brand Dell on the left side
        clickOnElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[3]"));

        /**
         * Verify that the 30( May-be different products are displayed on the page)
         */
        List<WebElement> expectedProductsDisplayed = driver.findElements(By.xpath("//div[@class='a-section" +
                " a-spacing-none puis-padding-right-small s-title-instructions-style']"));
        int expectedNumberOFProductsDisplayed = Integer.parseInt(String.valueOf(expectedProductsDisplayed.size()));

        List<WebElement> actualProductsDisplayed = driver.findElements(By.xpath("//div[@class='a-section" +
                " a-spacing-none puis-padding-right-small s-title-instructions-style']"));
        int actualNumberOFProductsDisplayed = Integer.parseInt(String.valueOf(actualProductsDisplayed.size()));

        verifyText("Expected number of Products are not displayed", String.valueOf(expectedNumberOFProductsDisplayed),
                String.valueOf(actualNumberOFProductsDisplayed));

        //Print all Names in console
        for (int i = 1; i <= expectedNumberOFProductsDisplayed; i++) {
            String productsNames = element(By.xpath("(//div[@class='a-section a-spacing-none " +
                    "puis-padding-right-small s-title-instructions-style'])[" + i + " ]")).getText();
            System.out.println(productsNames);
        }

        //Click on the product name 'Dell Latitude 5300 Laptop core i5'
        clickOnElement(By.xpath("//span[normalize-space()='Dell Inspiron 3520 15.6\" FHD 120Hz Laptop, Intel Core i7-1255U, " +
                "16GB RAM, 512GB SSD, Windows 11 Home (Platinum Silver)']"));

        /**
         * Verify the Product Name
         */
        String expectedProductName = "Dell Inspiron 3520 15.6\" FHD 120Hz Laptop, " +
                "Intel Core i7-1255U, 16GB RAM, 512GB SSD, Windows 11 Home (Platinum Silver)";
        String actualProductName = getTextFromElement(By.xpath("//span[@id='productTitle']"));

        verifyText("Expected product title is not matching", expectedProductName, actualProductName);
    }


}
