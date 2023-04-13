package pages;

import basePages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends AbstractBasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        openUrl(ENV + "cart/");
        return this;
    }

    private final static String PRICE_PRODUCT = "//div[@class='Cartstyle__CartTotal-sc-16lu03h-16 cFdIEQ']";
    private final static String PRICE_TOTAL = "//div[@class='CartFooterstyle__CartFooterTotal-sc-10qax3-2 rTHDe']";

    public List<WebElement> prices() {
        return waitPresenceOfAllElementsLocated(PRICE_PRODUCT);
    }
    public int getPrice() {
        return prices()
                .stream()
                .map(x -> Integer.parseInt(x.getText().split(" ")[0]))
                .mapToInt(Integer::intValue).sum();
    }

    public WebElement totalPrice(){
        return waitVisibleOfElement(PRICE_TOTAL);
    }
    public int getTotalPrice(){
        return Integer.parseInt(totalPrice().getText().split(" ")[1]);
    }



}
