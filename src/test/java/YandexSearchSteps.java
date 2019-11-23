import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.MarketPage;
import pages.YandexPage;

public class YandexSearchSteps extends BasePage {

    ChromeDriver chromeDriver = getChromeDriver();

    @Step("��������� �������� �������")
    public void openYandexAndCheck(){
        YandexPage page = new YandexPage();
        checkStartPage(page, chromeDriver);
        page.setSearch("������ ������");
        page.redirectToMarket();
    }

    @Step("��������� �������� ������.�������")
    public void checkMarket(){
        MarketPage market = new MarketPage();
        checkSearchMarketPage(chromeDriver);
        market.anotherCity();
        market.changeCity("���");
        try {
            Thread.sleep(5000);
            market.choseSpb();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step("��������� ��������� �� �������� �������") //////
    public void checkStartPage(YandexPage page, ChromeDriver chromeDriver) {
        Assert.assertEquals("��������� �������� �� ������������� ����������",
                "������", chromeDriver.getTitle());
        String currentUrl = chromeDriver.getCurrentUrl();
        Assert.assertTrue("������� �� ��������� �������� ������� �� ��� ��������",
                currentUrl.startsWith("https://yandex.ru") || currentUrl.startsWith("https://www.yandex.ru"));
    }

    @Step("��������� ��������� �� �������� ������.������") //////
    public void checkSearchMarketPage(ChromeDriver chromeDriver) {
        Assert.assertTrue("�� ��������� ������� �� ��������� ������� ������ �������",
                chromeDriver.getCurrentUrl().startsWith("https://market.yandex.ru"));
    }
}
