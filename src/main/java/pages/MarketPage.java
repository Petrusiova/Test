package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class MarketPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(),'���')]")
    private WebElement answerNo;

    @FindBy(xpath = "//*[@title=\"������\"]/../..")
    private WebElement regionChange;

    @FindBy(xpath = "//input[@placeholder=\"������� ������ ������\"]")
    private WebElement region;

    @FindBy(xpath = "//*[contains(text(),'���������� � ����� ��������')]")
    private WebElement continueBtn;

    @FindBy(xpath = "//button[@data-reactroot]")
    private WebElement allCategories;

    public void anotherCity() {
        waitFor(10);
        regionChange.click();
    }

    @Step("����� ������ �� {0}")
    public void changeCity(String city, String fullName) {
        try{
            region.isDisplayed();
        }
        catch (NoSuchElementException e){
            regionChange.click();
        }
        Assert.assertTrue("�� ������� ���� ��� ����� �������", region.isDisplayed());
        region.clear();
        region.sendKeys(city);
        region.click();
        String cityXPath = "//*[contains(text(),'" + fullName + "')]";
        checkElementOnPage(By.xpath(cityXPath));
        WebElement spb = getChromeDriver().findElementByXPath(cityXPath);
        Assert.assertTrue("�� ������� ����������� ���� ������ �������", spb.isDisplayed());
        spb.click();
        region.sendKeys(Keys.ENTER);
    }

    @Step("�������� ��������� ��: {0}")
    public void changeSection(String category) {
        checkElementOnPage(allCategories);
        clickElement(allCategories);
        WebElement categoryElement = getChromeDriver().findElementByXPath("//*[contains(text(), '" + category + "')]");
        checkElementOnPage(categoryElement);
        clickElement(categoryElement);
    }
}
