import io.qameta.allure.Step;
import org.junit.Assert;
import pages.BasePage;
import pages.CompTechPage;
import pages.MarketPage;
import pages.YandexPage;
import util.XmlDOMParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class YandexSearchSteps extends BasePage {


    @Step("��������� �������� �������")
    public void openPageRedirectAndCheck(String goTo, String search) {
        YandexPage page = new YandexPage();
        goTo(goTo);
        page.setSearch(search);
        page.redirectToMarket();
    }

    @Step("��������� ����� ������ �� ������ ���� ������ {0} � ��������� {2}")
    public void changeCityAndCategory(String firstLetters, String fullName, String category) {
        MarketPage page = new MarketPage();
        page.anotherCity();
        page.changeCity(firstLetters, fullName);
        getChromeDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        page.changeSection(category);
    }

    @Step("���������� ����� ������: {0}")
    public void changeSection(String section) {
        CompTechPage compTechPage = new CompTechPage();
        compTechPage.changeCategory(section);
        compTechPage.changeShowedCount();
    }

    @Step("���������� ����� �������������")
    public  void changeManufacturer(String name, String min, String max, String manufacturer){
        CompTechPage compTechPage = new CompTechPage();
        compTechPage.changeProducer(name);
        compTechPage.changeLowestPrice(min);
        compTechPage.changeHighestPrice(max);
        compTechPage.chooseThirdElement();
        ArrayList<String> tabs = new ArrayList<>(getChromeDriver().getWindowHandles());
        getChromeDriver().switchTo().window(tabs.get(1));
        compTechPage.validateManufacturer(manufacturer);
    }

    @Step("���������� ����� ���������� ���������")
    public void changeShops(List<String> excludedVendors, String rating, String value){
        CompTechPage compTechPage = new CompTechPage();
        compTechPage.changeShops(excludedVendors);
        compTechPage.changeRating(rating);
        compTechPage.sortBy(value);
    }

    @Step ("������� ���� � xml-�����")
    public String searchPriceValue(String filePath, String tagName, String tagValue, String childTagName){
        String element = new XmlDOMParser(filePath).getStringByNeighbourValue(tagName, tagValue, childTagName);
        Assert.assertFalse("������ �������� ����� " + tagName + " ������", element.isEmpty());
        return element;
    }

    @Step("������� ������������� � xml-�����")
    public List<String> searchElements(String filePath, String tagName){
        List<String> elements = new XmlDOMParser(filePath).getListElementsByTagName(tagName);
        Assert.assertFalse("������ �������� ����� " + tagName + " ������", elements.isEmpty());
        return elements;
    }

    public String getNodeAttributes(String filePath, String tagName, int nodeIndex, String attrName){
        Assert.assertNotNull("���������� ��������� �������� �������� � ������ null", attrName);
        String attribute = new XmlDOMParser(filePath).getNodeAttributes(tagName, nodeIndex).getNamedItem(attrName).getNodeValue();
        Assert.assertNotNull("�� ������ ������� � ������ " + attrName, attribute);
        return attribute;
    }

    @Step("������� �������������� ���������� ��������")
    public Map<String, String> getCharacteristics(String...chars){
        return new CompTechPage().getChars(chars);
    }

    @Step("������� ������� ��������������")
    public void unselectManufacturer(String name){
        ArrayList<String> tabs = new ArrayList<>(getChromeDriver().getWindowHandles());
        getChromeDriver().switchTo().window(tabs.get(1));
        getChromeDriver().close();
        getChromeDriver().switchTo().window(tabs.get(0));
        CompTechPage compTechPage = new CompTechPage();
        compTechPage.cancelProducer(name);
    }

    @Step("������ �������� � ������ �������������� ��� �������������: {0}")
    public Map<String, String> check(String filePath, String manufacturer, String maxPrice) {
        String min = searchPriceValue(filePath, "Name", manufacturer, "Min");
        String max = searchPriceValue(filePath, "Name", manufacturer, "Max");
        if (Long.valueOf(max) > Long.valueOf(maxPrice)) {
            max = maxPrice;
        }

        changeManufacturer(manufacturer, min, max, manufacturer);
        String path = makeScreenShot();
        Map<String, String> characteristics = getCharacteristics("�����", "���");
        characteristics.put("�������������", manufacturer);
        characteristics.put("��� �����", path);
        unselectManufacturer(manufacturer);
        return characteristics;
    }
}
