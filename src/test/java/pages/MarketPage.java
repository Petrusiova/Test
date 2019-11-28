package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketPage extends BasePage {

    @FindBy(xpath = "//div[2]/div[2]/span")
    private WebElement answerNo;

    @FindBy(xpath = "//div[1]/span/input")
    private WebElement region;

    @FindBy(xpath = "//*[contains(text(),'�����-���������')]")//!�����������
    private WebElement spb;

    @FindBy(xpath = "//*[contains(text(),'���������� � ����� ��������')]")
    private WebElement continueBtn;

    public void anotherCity() {
        Assert.assertTrue("�� ������� ������ ������ ������� ������", answerNo.isDisplayed());
        answerNo.click();
    }

    @Step("����� ������ �� {0}")
    public void changeCity(String city){
        Assert.assertTrue("�� ������� ���� ��� ����� �������", region.isDisplayed());
        region.clear();
        region.sendKeys(city);
    }


    public void choseSpb(){//��� ����� ���������� � changeCity
        try {//������� ������������ xpath ��� ������
            spb.click();
        } catch (NoSuchElementException e) {//������ ����� �������� ������
            region.click();
            Assert.assertTrue("�� ������� ����������� ���� ������ �������", spb.isDisplayed());
            spb.click();
        }
        region.sendKeys(Keys.ENTER);
    }


    @Step("��������� ��������� �� �������� ������.������") //////
    public void checkSearchMarketPage() {
        Assert.assertTrue("�� ��������� ������� �� ��������� ������� ������ �������",
                getChromeDriver().getCurrentUrl().startsWith("https://market.yandex.ru"));
    }
}
