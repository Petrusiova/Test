import io.qameta.allure.Step;
import pages.BasePage;
import pages.MarketPage;
import pages.YandexPage;

public class YandexSearchSteps extends BasePage {


    @Step("��������� �������� �������")
    public void openYandexAndCheck() {
        YandexPage page = new YandexPage();
        goTo("http://yandex.ru");
        page.checkStartPage();
        page.setSearch("������ ������");
        page.redirectToMarket();
    }

    @Step("����� ������ �� ������ ���� ������ {0}")
    public void changeCityBy(String firstLetters) {
        MarketPage page = new MarketPage();
        page.checkSearchMarketPage();
        page.anotherCity();
        page.changeCity(firstLetters);
        try {
            Thread.sleep(5000);
            page.choseSpb();
        } catch (InterruptedException e) {//�������� �� �����
            e.printStackTrace();
        }
    }


}
