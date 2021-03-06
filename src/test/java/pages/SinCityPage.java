package pages;

import static base.TestBase.BASE_URL;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;

public class SinCityPage {

    private static final String PAGE_URL = "sincity.php";
    private WebDriver driver;
    @FindBy(name = "title")
    private WebElement titleInput;

    @FindBy(name = "author")
    private WebElement authorInput;

    @FindBy(name = "message")
    private WebElement messageInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement confessButton;

    @FindBy(css = "div.sinsListArea")
    private WebElement sinListSection;

    @FindBy(css = "ul.list-of-sins")
    private WebElement listOfSins;

    public SinCityPage() {
        driver = WebDriverSingleton.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    public void fillSinInformation(Sin sin) {
        fillSinTitle(sin.getTitle());
        fillSinAuthor(sin.getAuthor());
        fillSinMessage(sin.getMessage());
    }

    public void fillSinTitle(String title) {
        titleInput.sendKeys(title);
    }

    public void fillSinAuthor(String author) {
        authorInput.sendKeys(author);
    }

    public void fillSinMessage(String message) {
        messageInput.sendKeys(message);
    }

    public void markTag(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='" + tag.getXpathValue() + "']")).click();
        }
    }

    public void confessSin() {
        confessButton.click();
    }

    public void checkSinStatus(Sin sin) {
        //1.chcem najst element hriechu
        WebElement actualSin = getSinFromListElement(sin);
        //2.chcem z neho vytiahnut text
        String actualText = actualSin.findElement(By.cssSelector("p")).getText().trim();
        //3.porovnat ocakavane a skutocne
        if (sin.isForgiven()) {
            Assert.assertEquals("forgiven", actualText);
        } else {
            Assert.assertEquals("pending", actualText);
        }
    }

    private WebElement getSinFromListElement(Sin sin) {
        return listOfSins.findElement(By.xpath("./li[contains(text(),'" + sin.getTitle() + "')]"));
    }

    public void checkSinVisibleInTheList(String sinTitle) {
        Assert.assertTrue(listOfSins.findElements(
                By.xpath("./li[contains(text(),'" + sinTitle + "')]")).size() > 0);
    }

    public void openDetail(Sin sin) {
        getSinFromListElement(sin).click();
    }

    public void checkDetail(Sin sin) {
        //1.najst element detailu
        WebElement sinDetail = driver.findElement(By.cssSelector("div.detail"));
        //2.pockat na hriech
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElement(
                        sinDetail.findElement(By.cssSelector("p")),
                        sin.getMessage()
                        )
                );
        //3.overit message, author, title
        String actualTitle = sinDetail.findElement(By.cssSelector("h4")).getText();
        Assert.assertTrue(actualTitle.contains(sin.getTitle()));
        Assert.assertTrue(actualTitle.contains(sin.getAuthor()));
    }

}
