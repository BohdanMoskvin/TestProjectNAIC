package healper;

import basePages.AbstractBasePage;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CartPage;
import pages.ItemPage;

import java.util.Objects;
@Log4j
public class HeaderHelper extends AbstractBasePage {
    public HeaderHelper(WebDriver driver) {
        super(driver);
    }

    public HeaderHelper open() {
        openUrl(ENV);
        return this;
    }

    private final static String CART_BTN_ICON = "//button[@aria-label='dropdown-cart']";
    private final static String GO_TO_CART = "//a[@aria-label='Перейти до кошика']";
    private final static String CART_COUNT = "//div[contains(@class,'HeaderUserMenuBadge')]";

    private WebElement cartBtnIcon() {
        return waitClickableElementByXpath(CART_BTN_ICON);
    }

    private WebElement goToCart() {
        return waitClickableElementByXpath(GO_TO_CART);
    }

    private WebElement getCartCount(){
        return waitVisibilityOfElementLocated(CART_COUNT);
    }

    public HeaderHelper clickCartBtnIcon() {
        log.info("==================== Click Icon Cart =======================");
        cartBtnIcon().click();
        return this;
    }

    public CartPage clickGoToCart() {
        log.info("=================== Click Button Go To Cart ==========================");
        goToCart().click();
        return new CartPage(driver);
    }

    @SneakyThrows
    public boolean waitForCartItemsCountChainged(int secondsToWait) {
        String value = getCartCount().getText();

        for (int i = 0; i < secondsToWait; i++) {
            String currentValue = getCartCount().getText();
            if (Objects.equals(currentValue, value)) {
                Thread.sleep(1000);
            } else {
                return true;
            }
        }
        return false;
    }
}
