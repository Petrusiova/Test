import io.qameta.allure.Description;
import org.junit.Test;

import java.util.List;

public class YandexSearchTest extends YandexSearchSteps {

    @Test
    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() {
        String filePath = "C:\\Users\\Olia\\Desktop\\parsing.xml";
        String maxPrice = searchElements(filePath, "Price").get(0).trim();
        List<String> producers = searchElements(filePath, "Name");
        List<String> minValues = searchMinValue(filePath, "Name", producers.get(0), "Min");
        List<String> maxValues = searchMinValue(filePath, "Name", producers.get(0), "Max");
//        for (int i = 0; i < maxValues.size(); i++) {
//            if (Long.valueOf(maxValues.get(i)) > Long.valueOf(maxPrice)) {
//                maxValues.set(i, maxPrice);
//            }
//        }


        // ��� ������� ��������� //div[34]/div[2]/div/div[1]/div/div[69]
        openPageRedirectAndCheck("http://yandex.ru", "������ ������");
        changeCityAndCategory("���", "�����-���������", "������������ �������");
        changeSection("��������", producers.get(0), minValues.get(0), maxValues.get(0));
        String s = "";
    }
}
