package listener;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.TestInit;

import java.io.File;


@Log4j
public class ListenerTest implements ITestListener {

    @Override
    public void onFinish(ITestContext result) {
        log.info("  The name of the testcase  is On Finish :-" + result.getName() + "             ");
        log.info("=========================================");
    }


    @Override
    public void onStart(ITestContext result) {
        log.info("The name of the testcase on Start" + result.getName() + "            ");
        log.info("===============================================================");
    }


    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((TestInit) result.getInstance()).getDriver();
        screenShot(driver);
        log.info("The name of the testcase failed is :" + result.getName());
        log.info("===============================================================");

    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult result) {
        WebDriver driver = ((TestInit) result.getInstance()).getDriver();
        screenShot(driver);
        log.info("The name of the testcase Skipped is :" + result.getName());
        log.info("===============================================================");
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getName() + " test case started");
        log.info("===============================================================");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The name of the testcase passed is :" + result.getName());
    }

    @SneakyThrows
    public void screenShot(WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(String.format("target/screenshot/screenshot%s.png", System.currentTimeMillis())));
    }
}