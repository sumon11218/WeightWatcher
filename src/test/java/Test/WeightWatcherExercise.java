package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;
import java.util.Scanner;

public class WeightWatcherExercise {

    @Test
    public static void test() throws InterruptedException {

        //setting the path for executable chromedriver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //defining chromeoptions to set arguments
        ChromeOptions options = new ChromeOptions();
        //maximizing the chrome browser and disabling any extensions
        options.addArguments("--start-maximized", "--disable-extensions");
        //setting chromedriver as driver to perform user actions
        WebDriver driver = new ChromeDriver(options);
        //defining explicit wait for dynamic wait handling
        WebDriverWait wait = new WebDriverWait(driver, 7);
        //using soft assert with testNG for any verification
        SoftAssert softAssert = new SoftAssert();
        //defining javascriptExecutor to peform scrolling actions
        JavascriptExecutor executor = (JavascriptExecutor)driver;

        //navigate to https://www.weightwatchers.com/us/
        driver.navigate().to("https://www.weightwatchers.com/us");
        //Verify loaded page title matches “Weight Loss Program, Recipes & Help | Weight Watchers”
        softAssert.assertEquals(driver.getTitle(),"Weight Loss Program, Recipes & Help | Weight Watchers");
        //click on 'Find a meeting'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'find-a-meeting')]"))).click();
        //Verify loaded page title contains “Get Schedules & Times Near You”
        softAssert.assertEquals(driver.getTitle(),"Get Schedules & Times Near You");
        //type in zipcode on meeting search field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='meetingSearch']"))).sendKeys("10011");
        //click on search button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='input-group-btn']"))).click();
        //storing first search location title in a variable
        String searchLocation = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='location__name']"))).get(0).getText();

        //storing first search result into a string varible to print
        String searchResult = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='location__top']"))).get(0).getText();
        //printing the search result
        System.out.println("Search Title & distance is " + searchResult);

        //click on the first search location link
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='location__name']"))).get(0).click();
        //store the location name into a string variable
        String locationName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='location__name']"))).get(0).getText();
        //verify location name matches with the location name displayed on this page
        softAssert.assertEquals(searchLocation,locationName);

        //storing hours of operation title into a WebElement variable
        WebElement element = driver.findElement(By.xpath("//*[@class='meeting-hours meeting-detail-bottom-section']"));
        //scrolling into hours of operation section
        executor.executeScript("arguments[0].scrollIntoView(true);", element);

       //storing current day hours of opp into variable
        String hoursOfOp = driver.findElement(By.xpath("//*[contains(@class,'hours-list--currentday')]")).getText();
        //printing the hours off operations for current day
        System.out.println("Current day hours of operation - " + hoursOfOp);


        //quitting the driver
        driver.quit();



    }// end of webdriver test


}
