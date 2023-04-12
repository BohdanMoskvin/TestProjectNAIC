package tests;

import healper.HeaderHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ItemPage;
import pages.CartPage;

public class TestCart extends TestInit {
    private ItemPage itemPage;
    private CartPage cartPage;
    private HeaderHelper headerHelper;

    @BeforeMethod
    private void beforeMethod() {
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);
        headerHelper = new HeaderHelper(driver);
    }

    @Test
    public void checkCart() {
        for (String url : itemPage.getUrlProduct()) {
            itemPage
                    .open(url)
                    .clickPayBtn();
        }
        headerHelper
                .clickCartBtnIcon()
                .clickGoToCart();
        int price = cartPage
                .getPrice();

        Assert.assertEquals(price, cartPage.getTotalPrice());
    }
}
