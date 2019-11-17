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
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page.choseSpb();
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        checkSpbPage(chromeDriver);

    }

    @After
    public void closeBrowser() {
        chromeDriver.close();
        chromeDriver.quit();
    }


}
