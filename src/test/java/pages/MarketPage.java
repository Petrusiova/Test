package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketPage extends BasePage {

    @FindBy(xpath = "//div[2]/div[2]/span")
    private WebElement answerNo;

    @FindBy(xpath = "//div[1]/span/input")
    private WebElement region;

    @FindBy(xpath = "//*[contains(text(),'�����-���������')]")
    private WebElement spb;

    @FindBy(xpath = "//*[contains(text(),'���������� � ����� ��������')]")
    private WebElement continueBtn;

    public MarketPage() {
        PageFactory.initElements(getChromeDriver(), this);
    }

    public void anotherCity() {
        Assert.assertTrue("�� ������� ������ ������ ������� ������", answerNo.isDisplayed());
        answerNo.click();
    }

    public void changeCity(String city){
        Assert.assertTrue("�� ������� ���� ��� ����� �������", answerNo.isDisplayed());
        region.clear();
        region.sendKeys(city);
    }

    public void choseSpb(){
        try {
            spb.click();
        } catch (NoSuchElementException e) {
            region.click();
            Assert.assertTrue("�� ������� ����������� ���� ������ �������", spb.isDisplayed());
            spb.click();
        }
        region.sendKeys(Keys.ENTER);
    }
}
