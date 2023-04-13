package tests;


import listener.Retry;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ItemPage;

public class ItemTest extends TestInit {
    private ItemPage itemPage;

    @BeforeMethod
    private void beforeMethod() {
        itemPage = new ItemPage(driver);
    }

    @DataProvider
    private Object[][] dataProvider() {
        return new Object[][]{
                {"/bosch-brand/filtr-masljanyj-0-986-452-044-1923945/", "Фільтр масляний 0986452044", "209"},
                {"/champion-brand/filtr-masljanyj-c124-606-373459/", "Фільтр масляний C124606", "81"},
                {"/mann-filter-brand/filtr-masljanyj-w-914-2-23340494/", "Фільтр масляний W9142", "335"},
                {"/wix-brand/filtr-masljanyj-wl7168-11658768/", "Фільтр масляний WL7168", "162"},
                {"/fram-brand/filtr-masljanyj-ph2857a-11900067/", "Фільтр масляний PH2857A", "180"}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void checkProductName(String url, String expectedName, String ignoredPrice) {
        String actualName = itemPage
                .open(url)
                .getProductName();


        Assert.assertEquals(actualName, expectedName);
        Assert.assertTrue(itemPage.productName().isDisplayed());
    }

    @Test(dataProvider = "dataProvider")
    public void checkProductPrice(String url, String ignoredName, String expectedPrice) {
        String actualPrice = itemPage
                .open(url)
                .getProductPrice();

        Assert.assertEquals(actualPrice, expectedPrice);
        Assert.assertTrue(itemPage.productPrice().isDisplayed());
    }
}

