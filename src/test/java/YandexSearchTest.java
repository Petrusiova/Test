import io.qameta.allure.Description;
import org.junit.Test;

public class YandexSearchTest extends YandexSearchSteps {

    @Test
    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() {
        openPageRedirectAndCheck("http://yandex.ru", "������ ������");
        changeCityAndCategory("���", "�����-���������", "������������ �������");
        changeSection("��������");
        String s = "";
    }
}
