import io.qameta.allure.Description;
import org.junit.Test;

import java.util.List;

public class YandexSearchTest extends YandexSearchSteps {

    @Test
    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() {
        openPageRedirectAndCheck("http://yandex.ru", "������ ������");
        changeCityAndCategory("���", "�����-���������", "������������ �������");
        List<String> searchProducer = searchElements("C:\\Users\\Olia\\Desktop\\parsing.xml", "Name");
        changeSection("��������", searchProducer.get(0));
        String s = "";
    }
}
