import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.YandexMarketPage;

import java.util.concurrent.TimeUnit;

public class YandexSearchSteps extends BasePage {

    @Step("��������� ��������� �� �������� �������")
    public void checkStartPage(YandexMarketPage page, ChromeDriver chromeDriver) {
        Assert.assertFalse("Chromdriver �� ��� ���������������", page.getDriverPath().isEmpty());
        Assert.assertEquals("��������� �������� �� ������������� ����������",
                "������", chromeDriver.getTitle());
        Assert.assertTrue("������� �� ��������� �������� ������� �� ��� ��������",
                chromeDriver.getCurrentUrl().startsWith("https://yandex.ru"));
        Assert.assertTrue("�� ������� ������ ��� ������",
                chromeDriver.findElementById("text").isDisplayed());
        Assert.assertTrue("�� ������� ������ '�����'",
                chromeDriver.findElementByClassName("search2__button").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkSearchMarketPage(ChromeDriver chromeDriver) {
        Assert.assertTrue("�� ��� ���������� ����� �� ����� '������ ������'",
                chromeDriver.getTitle().startsWith("������ ������"));
        Assert.assertTrue("� ��������� ������ �� ������ ������ ������",
                chromeDriver.findElementByLinkText("������").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkMarketPage(ChromeDriver chromeDriver) {
        Assert.assertTrue("�� ��������� ������� �� ��������� ������� ������ �������",
                chromeDriver.getCurrentUrl().startsWith("https://market.yandex.ru"));
        Assert.assertTrue("�� ������� ����������� ���� � �������� � �������",
                chromeDriver.findElementByXPath("//*[@class='n-region-notification__header']").isDisplayed());
        Assert.assertTrue("�� ������� ������ ������ ������� �������",
                chromeDriver.findElementByXPath("//*[contains(text(), '���, ������')]").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkAnotherCity(ChromeDriver chromeDriver) {
        Assert.assertTrue("�� ������� ����������� ���� ������ ������� �������",
                chromeDriver.findElementByXPath("//*[@class='header2-region-popup']").isDisplayed());
        Assert.assertTrue("�� ������� ���� ����� �������",
                chromeDriver.findElementByXPath("//div[1]/span/input").isDisplayed());
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkSpbPage(ChromeDriver chromeDriver) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("��� ������ ������� �����-��������� �� ��������� ���������� �������",
                chromeDriver.findElementsByXPath("//*[contains(text(),'�����-���������')]").size() > 0);
    }
}
