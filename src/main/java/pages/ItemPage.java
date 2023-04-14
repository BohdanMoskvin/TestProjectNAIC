package pages;

import basePages.AbstractBasePage;
import healper.HeaderHelper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j
public class ItemPage extends AbstractBasePage {

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    private final static String PRODUCT_NAME = "//div[@id='page-title']/h1";
    private final static String PRODUCT_PRICE = "(//div[@class='ProductPriceBlockstyle__ProductPriceValue-sc-1pwmnni-6 DstHZ'])[2]";
    private final static String PAY_BTN = "(//div[@data-testid='ProductPrice']//button[@aria-label='Купити'])[2]";
    private final static String CLOSE_SMS_ALERT = "(//span[@role='button'])[1]";

    public ItemPage open(String endPoint) {
        log.info("======================Open Item Page================================");
        openUrl(ENV + endPoint);
        return this;
    }

    public WebElement productName() {
        return waitVisibleOfElement(PRODUCT_NAME);
    }

    public WebElement productPrice() {
        return waitVisibleOfElement(PRODUCT_PRICE);
    }

    public WebElement payBtn() {
        return waitClickableElementByXpath(PAY_BTN);
    }

    public WebElement smsAlert() {
        return waitClickableElementByXpath(CLOSE_SMS_ALERT);
    }

    public ItemPage clickPayBtn() {
        log.info("=====================Click Pay Button===========================");
        payBtn().click();
        new HeaderHelper(driver).waitForCartItemsCountChainged(3);
        return this;
    }

    public ItemPage closeSmsAlert() {
        log.info("=====================Close SMS ALERT===========================");
        if (isElementPresent(By.xpath(CLOSE_SMS_ALERT)))
            smsAlert().click();
        return this;
    }


    public String getProductName() {
        String textContent = productName().getAttribute("textContent");
        int index = textContent.indexOf("Бренд");
        return textContent.substring(0, index - 1);
    }

    public String getProductPrice() {
        return productPrice().getAttribute("textContent");
    }

    public List<String> getUrlProduct() {
        List<String> urls = new ArrayList<>();
        urls.add("/bosch-brand/filtr-masljanyj-0-986-452-044-1923945/");
        urls.add("/champion-brand/filtr-masljanyj-c124-606-373459/");
        urls.add("/mann-filter-brand/filtr-masljanyj-w-914-2-23340494/");
        urls.add("/wix-brand/filtr-masljanyj-wl7168-11658768/");
        urls.add("/fram-brand/filtr-masljanyj-ph2857a-11900067/");
        return urls;
    }
}
