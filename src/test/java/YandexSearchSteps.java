import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.YandexMarketPage;

public class YandexSearchSteps extends BasePage {

    @Step("��������� ��������� �� �������� �������")
    public void checkStartPage(YandexMarketPage page, ChromeDriver chromeDriver) {
        Assert.assertFalse("Chromdriver �� ��� ���������������", page.getDriverPath().isEmpty());
        Assert.assertEquals("��������� �������� �� ������������� ����������",
                "������", chromeDriver.getTitle());
        Assert.assertTrue("������� �� ��������� �������� ������� �� ��� ��������",
                chromeDriver.getCurrentUrl().startsWith("https://yandex.ru"));
    }

    @Step("��������� ��������� �� �������� ������.������")
    public void checkMarketPage(YandexMarketPage page, ChromeDriver chromeDriver) {
        Assert.assertTrue("�� ��� ���������� ����� �� ����� '������ ������'",
                chromeDriver.getTitle().startsWith("������ ������"));

    }
}
