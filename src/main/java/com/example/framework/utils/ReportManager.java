package com.example.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.nio.file.*;

public final class ReportManager {
    private static ExtentReports extent;
    private ReportManager(){}

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            try {
                Path out = Paths.get("target", "extent", "ExtentReport.html");
                Files.createDirectories(out.getParent());

                ExtentHtmlReporter html = new ExtentHtmlReporter(out.toString());
                html.config().setReportName("QA Framework");
                html.config().setDocumentTitle("Automation Report");

                extent = new ExtentReports();
                extent.attachReporter(html);
            } catch (Exception e) {
                throw new RuntimeException("Failed to init ExtentReports", e);
            }
        }
        return extent;
    }
}
