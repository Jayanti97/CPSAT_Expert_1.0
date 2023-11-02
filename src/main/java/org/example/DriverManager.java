package org.example;
import java.io.File;

import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.SystemUtils.getUserDir;
import static org.example.CapabilitiesManager.caps;


public class DriverManager {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public static String platForm;

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public synchronized RemoteWebDriver getDriver() {
        return driver.get();
    }

    public synchronized WebDriver getWebDriver() {
        return webDriver.get();
    }

    public synchronized void setDriver(RemoteWebDriver driver1) {
        driver.set(driver1);
    }

    public synchronized void setWebDriver(WebDriver driver1) {
        webDriver.set(driver1);
    }




//    public void initializeDriver() throws Exception {
//
//        RemoteWebDriver driver = null;
//        GlobalParams params = new GlobalParams();
//        PropertyManager props = new PropertyManager();
//        URL url = new URL("http://127.0.0.1:4723/" + "wd/hub");
//        URL browserStackiOSURL = new URL("http://hub-cloud.browserstack.com/wd/hub");
//        readBS();
//        userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) browserStackYamlMap.get("userName");
//        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) browserStackYamlMap.get("accessKey");
//        URL browserStackURL=new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName , accessKey));
//
//
//        if (driver == null) {
//            try {
//                utils.log().info("initializing Appium driver");
//                switch (params.getPlatformName()) {
//                    case "Android":
//                        driver = new AndroidDriver(url, new CapabilitiesManager().getoptions());
//                        break;
//                    case "iOS":
//                        //driver = new IOSDriver(url, new CapabilitiesManager().getoptions());
//                        break;
//                    case "BrowserStack-Android":
//                        driver = new AndroidDriver(browserStackURL, new CapabilitiesManager().getoptions());
//                        // driver = new AndroidDriver(browserStackURL, new CapabilitiesManager().getoptions());
//                        break;
//                    case "BrowserStack-iOS":
//                        //driver = new IOSDriver(browserStackiOSURL, new CapabilitiesManager().getoptions());
//                        break;
//
//
//
//                }
//                if (driver == null) {
//                    throw new Exception("driver is null. ABORT!!!");
//                }
//                utils.log().info("Driver is initialized");
//                this.driver.set(driver);
//            } catch (IOException e) {
//                e.printStackTrace();
//                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
//                throw e;
//            }
//        }
//
//    }

    public synchronized void initializeDriver(String UDID, String platformVersion, String systemPort) throws Exception {
        RemoteWebDriver driver = null;


        GlobalParams params = new GlobalParams();
        PropertyManager props = new PropertyManager();

        URL url = new URL("http://127.0.0.1:4723/" + "wd/hub");

        if (driver == null) {
            try {
                utils.log().info("initializing Appium driver");
                switch (params.getPlatformName()) {
                    case "Android":
                        driver = new AndroidDriver(url, new CapabilitiesManager().getCaps(UDID, platformVersion, systemPort));
                        break;
                    case "iOS":
                        // driver = new IOSDriver(url, new CapabilitiesManager().getoptions(UDID, platformVersion, systemPort));
                        break;

                }
                if (driver == null) {
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }

    public void initializeBrowserDriver(String browserName, String url) {

        WebDriver webDriver = null;
        switch (browserName) {
            case "Chrome": {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                webDriver = new ChromeDriver();
                utils.log().info("Chrome driver initialized");
                break;
            }
            case "Firefox": {
                webDriver = new FirefoxDriver();
                utils.log().info("Firefox driver initialized");
                break;
            }

        }
        if (webDriver == null) {
            utils.log().info("Invalid Browser - only allowed values are [Chrome,Firefox]");
        }

        this.webDriver.set(webDriver);
        new DriverManager().getWebDriver().manage().window().maximize();
        new DriverManager().getWebDriver().get(url);


    }

    public static void updateImplicitWait(int seconds)
    {
        new DriverManager().getWebDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }


    public synchronized void initializeCloudDriver(String DeviceName, String OSVersion, String platformDiff) throws Exception {
        RemoteWebDriver driver = null;

        GlobalParams params = new GlobalParams();
        PropertyManager props = new PropertyManager();

        URL url = new URL("http://127.0.0.1:4723/" + "wd/hub");
        URL browserStackURL = new URL("http://hub.browserstack.com/wd/hub");
        URL browserStackiOSURL = new URL("http://hub-cloud.browserstack.com/wd/hub");


        try {

            // utils.log().info("initializing Appium driver");
            //  switch(params.getPlatformName()){


            driver = new AndroidDriver(browserStackURL, new CapabilitiesManager().getCloudCaps(DeviceName, OSVersion, platformDiff));
            utils.log().info(platformDiff + " - this got traced");


            //  }
            if (driver == null) {
                throw new Exception("driver is null. ABORT!!!");
            }
            utils.log().info("Driver is initialized");
            this.driver.set(driver);
        } catch (IOException e) {
            e.printStackTrace();
            utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
            throw e;

        }

    }
    public static Map<String, Object> browserStackYamlMap;
    public AndroidDriver driver1;
    public String userName;
    public String accessKey;
//    public UiAutomator2Options options;
    public void  readBS() {
        File file = new File(getUserDir() + "/browserstack.yml");
        this.browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
    }

    public void setUp() throws Exception {
//        options = new UiAutomator2Options();
        userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) browserStackYamlMap.get("userName");
        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) browserStackYamlMap.get("accessKey");
//        options.setCapability("appium:app", "bs://sample.app");
//        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
//        options.setCapability("appium:platformVersion", "12.0");

        driver1 = new AndroidDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName , accessKey)), caps);
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try {
            InputStream inputStream = Files.newInputStream(yamlFile.toPath());
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            map.putAll(config);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }
}
