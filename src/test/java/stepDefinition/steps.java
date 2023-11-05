package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;


public class steps {
    public static WebDriver driver;
    String url = "https://www.catawiki.com/en/";
    SoftAssert softassert =  new SoftAssert();
    ChromeOptions options = new ChromeOptions();

    @Before
    public void WriteFile() throws FileNotFoundException {
        File file = new File("target/sample.txt");
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);
    }

    @Given("Open the Browser")
    public void Open_the_Browser() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            options.addArguments("--start-maximized");
    }

    @And("Enter the given URL")
    public void Enter_the_given_URL() {
        driver.get(url);
        driver.findElement(By.id("cookie_bar_agree_button")).click();
    }

    @When("User Search for the keyword {string}")
    public void user_search_for_the_keyword(String keyword) {
        driver.findElement(By.xpath("(//div[@class='c-text-field__wrapper']//following-sibling::div/input)[1]")).sendKeys(keyword);
        driver.findElement(By.xpath("//div[@class='c-text-field__wrapper']/div")).click();
        System.out.println("User searches for '" +keyword+ "' in the search Box");
    }

    @When("search results page is opened for the auctions {string}")
    public void searchResultsPageIsOpenedForTheAuctionsKeyword(String keyword) {
        String actualTitle = driver.getTitle();
        softassert.assertTrue(actualTitle.contains(keyword));
        softassert.assertAll();
        System.out.println("Search Result Page is Opened with the Page Title: " +actualTitle);
    }

    @When("User selects the second lot from the list {string}")
    public void userSelectsTheSecondLotFromTheList(String keyword) {
        driver.findElement(By.xpath("//div[@class='u-m-t-l u-m-t-lg-xl']/div/child::div[2]/div/a")).click();
        System.out.println("User selected the second Item from the given list of '"+keyword+ "' Lots printed on the screen");
    }

    @Then("Validate the selected Lot page is opened")
    public void validateTheSelectedLotPageIsOpened() {
        System.out.println("Lots Page is Opened with the Title: "+driver.getTitle());
    }

    @And("Print the values of Lot's name, favourites counter and current bid")
    public void printTheValuesOfLotSNameFavouritesCounterAndCurrentBid() throws FileNotFoundException {
        String lotName= driver.findElement(By.xpath("//div[@class='u-wrapper be-lot-details__main-content-wrapper be-lot-details__main-content-wrapper--with-gallery-preview']/div/h1")).getText();
        String favCounter = driver.findElement(By.xpath("(//div[@class='u-wrapper be-lot-details__main-content-wrapper be-lot-details__main-content-wrapper--with-gallery-preview']/div/child::div//following-sibling::div/button)[1]")).getText();
        String current_bid = driver.findElement(By.xpath("(//div[@class='be-lot-core-bidding-panel']//descendant::div[2]//following-sibling::div)[1]")).getText();
        System.out.println("The Lot Name: "+lotName);
        System.out.println("The Value of the Favourites Counter: "+favCounter);
        System.out.println("The Value of the Current Bid: "+current_bid);
        }

    @After
    public void tearDown(){
        driver.quit();
    }
}




