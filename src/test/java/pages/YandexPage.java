package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class YandexPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(id = "text")
    private WebElement resultStats;

    @FindBy(className = "search2__button")
    private WebElement searchButton;

    @FindBy(linkText = "������")
    private WebElement pointButton;

    public YandexPage() {
        PageFactory.initElements(getChromeDriver(), this);
        this.webDriver = getChromeDriver();
        webDriver.get("http://yandex.ru");
    }

    public YandexPage setSearch(String market) {
        Assert.assertTrue("�� ������� ������ ��� ������ ������� ��� ������", resultStats.isDisplayed());
        resultStats.clear();
        resultStats.sendKeys(market);
        searchButton.click();
        return this;
    }

    public void redirectToMarket() {
        Assert.assertTrue("����� ��������� ������ ��� �������� ������ �������", pointButton.isDisplayed());
        pointButton.click();
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        Assert.assertEquals("�������� ������ ������� �� ��������� � ����� ����", 2, tabs.size());
        webDriver.switchTo().window(tabs.get(0));
        webDriver.close();
        webDriver.switchTo().window(tabs.get(1));
        Assert.assertEquals("�������� ���������� �������� �� ���������",
                1, webDriver.getWindowHandles().size());
    }
}
