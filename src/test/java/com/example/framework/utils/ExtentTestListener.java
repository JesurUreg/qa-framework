package com.example.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExtentTestListener implements ITestListener, ISuiteListener {
    private static final ConcurrentMap<Long, ExtentTest> TESTS = new ConcurrentHashMap<>();
    private ExtentReports extent;

    private static ExtentTest getTest() { return TESTS.get(Thread.currentThread().getId()); }
    private static void setTest(ExtentTest t) { TESTS.put(Thread.currentThread().getId(), t); }
    private static void removeTest() { TESTS.remove(Thread.currentThread().getId()); }

    @Override public void onStart(ISuite suite) { extent = ReportManager.getReporter(); }
    @Override public void onFinish(ISuite suite) { if (extent != null) extent.flush(); }

    @Override public void onTestStart(ITestResult result) {
        setTest(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override public void onTestSuccess(ITestResult result) {
        ExtentTest t = getTest();
        if (t != null) t.pass("Passed");
        removeTest();
    }

    @Override public void onTestFailure(ITestResult result) {
        ExtentTest t = getTest();
        String msg = result.getThrowable() == null ? "Failed" : result.getThrowable().toString();
        if (t != null) {
            t.fail(msg);
            try {
                String b64 = ScreenshotUtil.takeBase64(DriverFactory.getDriver());
                if (b64 != null && !b64.isEmpty()) {
                    // v4 API:
                    t.addScreenCaptureFromBase64String(b64, "failure.png");
                }
            } catch (Throwable ignored) {}
        }
        removeTest();
    }

    @Override public void onTestSkipped(ITestResult result) {
        ExtentTest t = getTest();
        if (t != null) t.skip("Skipped");
        removeTest();
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
