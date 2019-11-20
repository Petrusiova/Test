package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.ArrayList;

public class YandexPage extends BasePage {

    @FindBy(id = "text")
    private WebElement resultStats;

    @FindBy(className = "search2__button")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@class='iUh30']") ////��� �� ���??
    private WebElement pointButton;


    @Step("��������� �� �������� www.yandex.ru")
    public void goToYandex(){
        getChromeDriver().get("http://www.yandex.ru");
        checkStartPage();
    }

    @Step("����� �� ������� \"{0}\"")
    public void setSearch(String market) {
        resultStats.clear();
        resultStats.sendKeys(market);
        searchButton.click();
        redirectToMarket();//��������� market �� ���������� setSearch
        //�������� ������� �� ������� ���������

        Link newFind = new Link(getChromeDriver().findElement(By.xpath("...."+market+"")));
        newFind.click();
    }

    //�������� @Step + �������� �� ������� ���������
    private void redirectToMarket() {
        pointButton.click();
        ArrayList<String> tabs2 = new ArrayList<String>(getChromeDriver().getWindowHandles());
        getChromeDriver().switchTo().window(tabs2.get(0));
        getChromeDriver().close();
        getChromeDriver().switchTo().window(tabs2.get(1));
    }


    @Step("��������� ��������� �� �������� �������")
    private void checkStartPage() {
        Assert.assertEquals("��������� �������� �� ������������� ����������",
                "������", getChromeDriver().getTitle());
        Assert.assertTrue("������� �� ��������� �������� ������� �� ��� ��������",
                getChromeDriver().getCurrentUrl().startsWith("https://yandex.ru"));
        Assert.assertTrue("�� ������� ������ ��� ������",
                getChromeDriver().findElementById("text").isDisplayed());//������ ��� ������ - ������� ��� ��������� �������
        Assert.assertTrue("�� ������� ������ '�����'",
                getChromeDriver().findElementByClassName("search2__button").isDisplayed());//������� ��� ������� ������ �����
    }


}