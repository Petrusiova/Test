import io.qameta.allure.Description;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YandexSearchTest extends YandexSearchSteps {

    @Test
    @Description("���� ��� �������� �������� �� �����-������������� ������ ������")
    public void open() throws IOException {
        String filePath = "parsing.xml";
        Map<String, String> ourMap = new HashMap<>();
        ourMap.put("�����", "0");
        ourMap.put("���", "0");

        String rating = getNodeAttributes(filePath, "Excluded_vendors", 0, "rating");
        String maxPrice = searchElements(filePath, "Price").get(0).trim();
        List<String> excludedVendors = searchElements(filePath, "Vendor");
        List<String> manufacturers = searchElements(filePath, "Name");

        openPageRedirectAndCheck("http://yandex.ru", "������ ������");
        changeCityAndCategory("���", "�����-���������", "������������ �������");
        changeSection("��������");
        changeShops(excludedVendors, rating, "�� ����");

        for (String producer : manufacturers) {
            Map<String, String> newMap = check(filePath, producer, maxPrice);
            double ourScreen = Double.parseDouble(ourMap.get("�����").replaceAll("\\s.+", ""));
            double newScreen = Double.parseDouble(newMap.get("�����").replaceAll("\\s.+", ""));

            if (ourScreen < newScreen) {
                ourMap = newMap;
            } else if (ourScreen == newScreen) {
                if (Double.parseDouble(ourMap.get("���").replaceAll("\\s.+", "")) >
                        Double.parseDouble(newMap.get("���").replaceAll("\\s.+", ""))) {
                    ourMap = newMap;
                }
            }
        }
        attachScreenShot(ourMap.get("��� �����"));
    }
}
