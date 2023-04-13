package basePages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractBasePage {
    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jse;
    protected Actions actions;
    public static final String ENV = "https://exist.ua/uk";

    protected AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        jse = (JavascriptExecutor) driver;
    }

    protected WebElement waitVisibleOfElement(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    protected WebElement waitClickableElementByXpath(String locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    protected WebElement waitPresenceOfElementLocated(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    protected WebElement waitVisibilityOfElementLocated(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    protected List<WebElement> waitPresenceOfAllElementsLocated(String locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }

    @SneakyThrows
    @Step("Open url {0}")
    protected void openUrl(String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
            Thread.sleep(2000);
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
