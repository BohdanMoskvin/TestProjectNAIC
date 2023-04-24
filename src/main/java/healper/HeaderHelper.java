package healper;

import basePages.AbstractBasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CartPage;

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

    private WebElement cartBtnIcon() {
        return waitClickableElementByXpath(CART_BTN_ICON);
    }

    private WebElement goToCart() {
        return waitClickableElementByXpath(GO_TO_CART);
    }

    public HeaderHelper clickCartBtnIcon() {
        log.info("==================== Click Icon Cart =======================");
        cartBtnIcon().click();
        scrollIntoView(goToCart());
        return this;
    }

    public CartPage clickGoToCart() {
        log.info("=================== Click Button Go To Cart ==========================");
        goToCart().click();
        return new CartPage(driver);
    }
}
