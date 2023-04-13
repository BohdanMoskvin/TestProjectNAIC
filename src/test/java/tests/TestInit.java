package tests;

import healper.DriverHelper;
import listener.ListenerTest;
import listener.RetryListener;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.SuiteConfig;

@Log4j
@Listeners(ListenerTest.class)
public abstract class TestInit {

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        SuiteConfig siteConfig = new SuiteConfig();
        DriverHelper.setHeadless(siteConfig.getProperty("headless"));
    }

    public WebDriver getDriver() {
        return driver;
    }

        @BeforeMethod
        public void setUpDriver() {
            driver = DriverHelper.getDriver();
            log.info("---------- Finished set up driver --------------");
        }

        @AfterMethod
        public void quitDriver() {
            driver.quit();
            log.info("----------- Driver quit -----------");
        }
    }

