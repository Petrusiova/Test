import io.qameta.allure.Description;
import org.junit.Test;
import pages.YandexPage;

public class YandexSearchTest extends YandexSearchSteps {

    @Test
//    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() {
        YandexPage page = new YandexPage();
        page.goToYandex();
        new YandexPage().setSearch("������ ������");
        validateLoadPage();
        changeRegion("���");
    }


}
