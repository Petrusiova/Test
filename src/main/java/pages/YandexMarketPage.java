package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexMarketPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[2]/div[2]/span")
    private WebElement answerNo;

    @FindBy(xpath = "//div[1]/span/input")
    private WebElement region;

    @FindBy(xpath = "//*[contains(text(),'�����-���������')]")
    private WebElement spb;

    @FindBy(xpath = "//*[contains(text(),'���������� � ����� ��������')]")
    private WebElement continueBtn;


    //�������� ��������� + ��������
    public void anotherCity() {
        answerNo.click();
    }

    public void changeCity(String city){
        region.clear();
        region.sendKeys(city);
    }

    public void choseSpb(){
        try {
            spb.click();
        } catch (NoSuchElementException e) {
            region.click();
            spb.click();
        }
        region.sendKeys(Keys.ENTER);
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkSearchMarketPage() {
        Assert.assertTrue("�� ��� ���������� ����� �� ����� '������ ������'",
                getChromeDriver().getTitle().startsWith("������ ������"));
        Assert.assertTrue("� ��������� ������ �� ������ ������ ������",
                getChromeDriver().findElementByLinkText("������").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkMarketPage() {
        Assert.assertTrue("�� ��������� ������� �� ��������� ������� ������ �������",
                getChromeDriver().getCurrentUrl().startsWith("https://market.yandex.ru"));
        Assert.assertTrue("�� ������� ����������� ���� � �������� � �������",
                getChromeDriver().findElementByXPath("//*[@class='n-region-notification__header']").isDisplayed());
        Assert.assertTrue("�� ������� ������ ������ ������� �������",
                getChromeDriver().findElementByXPath("//*[contains(text(), '���, ������')]").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkAnotherCity() {
        Assert.assertTrue("�� ������� ����������� ���� ������ ������� �������",
                getChromeDriver().findElementByXPath("//*[@class='header2-region-popup']").isDisplayed());
        Assert.assertTrue("�� ������� ���� ����� �������",
                getChromeDriver().findElementByXPath("//div[1]/span/input").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkSpbPage() {
        Assert.assertTrue("��� ������ ������� �����-��������� �� ��������� ���������� �������",
                getChromeDriver().findElementsByXPath("//*[contains(text(),'�����-���������')]").size() > 0);
    }

}
