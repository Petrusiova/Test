import io.qameta.allure.Step;
import org.junit.Assert;
import pages.BasePage;
import pages.CompTechPage;
import pages.MarketPage;
import pages.YandexPage;
import util.DOMExample;

import java.util.List;

public class YandexSearchSteps extends BasePage {


    @Step("��������� �������� �������")
    public void openPageRedirectAndCheck(String goTo, String search) {
        YandexPage page = new YandexPage();
        goTo(goTo);
        page.setSearch(search);
        page.redirectToMarket();
    }

    @Step("Change city by first three letters {0} and category on {2}")
    public void changeCityAndCategory(String firstLetters, String fullName, String category) {
        MarketPage page = new MarketPage();
        page.anotherCity();
        page.changeCity(firstLetters, fullName);
        page.changeSection(category);
    }

    @Step("Change section on {0}")
    public void changeSection(String section, String name, String min, String max) {
        CompTechPage compTechPage = new CompTechPage();
        compTechPage.changeCategory(section);
        compTechPage.changeProducer(name);
        compTechPage.changeLowestPrice(min);
        compTechPage.changeHighestPrice(max);
        compTechPage.changeShowedCount();
    }

    @Step("Change shops")
    public void changeShops(List<String> excludedVendors){
        CompTechPage compTechPage = new CompTechPage();
        compTechPage.changeShops(excludedVendors);
    }

    @Step ("Search max price in XML")
    public List<String> searchMaxPrice(String filePath, String tagName, String tagValue, String childTagName){
        List<String> elements = new DOMExample(filePath).getListByNeighbourValue(tagName, tagValue, childTagName);
        Assert.assertFalse("������ �������� ����� " + tagName + " ������", elements.isEmpty());
        return elements;
    }

    @Step("SearchProducerInXML")
    public List<String> searchElements(String filePath, String tagName){
        List<String> elements = new DOMExample(filePath).getListElementsByTagName(tagName);
        Assert.assertFalse("������ �������� ����� " + tagName + " ������", elements.isEmpty());
        return elements;
    }

    @Step ("SearchValueInXML")
    public List<String> searchValue(String filePath, String neighbourTag, String neighbourValue, String childTag){
        List<String> elements = new DOMExample(filePath).getListByNeighbourValue(neighbourTag, neighbourValue, childTag);
        Assert.assertFalse("������ �������� ����� " + childTag + " ������", elements.isEmpty());
        return elements;
    }

}
