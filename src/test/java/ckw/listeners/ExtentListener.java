package ckw.listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ckw.utils.ScreenshotUtil;

public class ExtentListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                ExtentManager.getExtentReport()
                        .createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        String screenshot =
                ScreenshotUtil.capture(result.getMethod().getMethodName());

        try {

            test.get().addScreenCaptureFromPath(screenshot);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.getExtentReport().flush();
    }
}