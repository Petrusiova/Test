import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class YandexSearchTest extends YandexSearchSteps {

    @Test
    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() {
        openYandexAndCheck();
        checkMarket();
    }

    @After
    public void closeBrowser() {
        ChromeDriver  chromeDriver = getChromeDriver();
        if (chromeDriver != null) {
            chromeDriver.close();
            chromeDriver.quit();
        }
    }
}
