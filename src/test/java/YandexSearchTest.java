import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.YandexMarketPage;

import java.util.concurrent.TimeUnit;

public class YandexSearchTest extends YandexSearchSteps {

    ChromeDriver chromeDriver = getChromeDriver();
    YandexMarketPage page = PageFactory.initElements(chromeDriver, YandexMarketPage.class);


    @Test
    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() {
        checkStartPage(page, chromeDriver);
        page.setSearch("������ ������");
        checkSearchMarketPage(chromeDriver);
        page.redirectToMarket();
        checkMarketPage(chromeDriver);
        page.anotherCity();
        checkAnotherCity(chromeDriver);
        page.changeCity("���");
        try {
            Thread.sleep(5000);
            page.choseSpb();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkSpbPage(chromeDriver);

    }

    @After
    public void closeBrowser() {
        chromeDriver.close();
        chromeDriver.quit();
    }


}
