package org.example;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReportingManager {

    public static ExtentReports extent;
    public static String reportDestination;



    public static void startReport() throws IOException {
        extent = new ExtentReports();
        reportDestination=System.getProperty("user.dir")+"test-output/Execution Report/"+"Automation Test Report.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportDestination);
        spark.config().setReportName("Automation Test Report");
        spark.config().setDocumentTitle("//WU");
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
        new TestUtils().log().info("start report completed");


    }

    public static  String getBase() throws IOException {

//        if (new PropertyManager().getProps().getProperty("AppPlatform").equals("MakeMyTrip"))
            return ((TakesScreenshot) new DriverManager().getWebDriver()).getScreenshotAs(OutputType.BASE64);
//        else
//            return ((TakesScreenshot) new DriverManager().getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    public static ExtentTest extentTest;
    public static void report(String info, Status status)
    {
        try {

            String screenshotPath = takeScreenshotAtEndOfTest();
            switch (status)
            {
                case PASS:
                    extentTest.pass(info, MediaEntityBuilder.createScreenCaptureFromPath(getBase()).build());
                    break;
                case FAIL:
                    extentTest.fail(info , MediaEntityBuilder.createScreenCaptureFromPath(getBase()).build());
//                    extentTest.fail(info + extentTest.addScreenCaptureFromPath(screenshotPath));
                    break;
                case INFO:
                    extentTest.info(info);
                    break;
                case SKIP:
                    extentTest.skip(info, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase()).build());
                    break;
                case WARNING:
                    extentTest.warning(info, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase()).build());
                    break;

            }
        }
        catch (Exception ec)
        {

        }


    }

    public static  void flushReport()
    {
        extent.flush();
        new TestUtils().log().info("flush completed");
        File htmlFile=new File(reportDestination);

//        try {
//            Desktop.getDesktop().browse(htmlFile.toURI());
//        } catch (IOException e) {
//
//            throw new RuntimeException(e);
//        }
//
    }
    public static String takeScreenshotAtEndOfTest() throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot)new DriverManager().getWebDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"test-output/Execution Report/"+
                "Automation Test Report.html"+ "/screenshots/" +  dateName
                + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        return destination;
    }


}
