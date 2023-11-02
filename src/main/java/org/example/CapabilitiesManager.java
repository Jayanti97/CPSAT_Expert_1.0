package org.example;




import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;



public class CapabilitiesManager {
    public static String nameOfScen;
    TestUtils utils = new TestUtils();

    public static DesiredCapabilities caps;
    public DesiredCapabilities getCaps() throws IOException, ParseException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        try{
            utils.log().info("getting capabilities");
            caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

            switch(params.getPlatformName()){
                case "Android":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    caps.setCapability("platformVersion","12");
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("AndroidAutomationName"));
                    break;
                case "iOS":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                    caps.setCapability("bundleId",props.getProperty("iOSBundleId"));
                    caps.setCapability("startIWDP",true);
                    //  caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW,true);
                    break;
                case "BrowserStack-Android":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                    caps.setCapability("browserstack.user", TestUtils.readJson(params.getPlatformName(),"browserstack.user"));
                    caps.setCapability("browserstack.key",  TestUtils.readJson(params.getPlatformName(),"browserstack.key"));
                    caps.setCapability("app",  TestUtils.readJson(params.getPlatformName(),"app"));
                    caps.setCapability("device",  TestUtils.readJson(params.getPlatformName(),"device"));
                    caps.setCapability("os_version",  TestUtils.readJson(params.getPlatformName(),"os_version"));
                    caps.setCapability("project",  TestUtils.readJson(params.getPlatformName(),"project"));
                    caps.setCapability("build",  TestUtils.readJson(params.getPlatformName(),"build"));
                    caps.setCapability("name",  nameOfScen);
                    caps.setCapability("browserstack.enablePasscode","true");
                    caps.setCapability("browserstack.enableBiometric", "true");


                    break;
                case "BrowserStack-iOS":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                    caps.setCapability("browserstack.user", TestUtils.readJson(params.getPlatformName(),"browserstack.user"));
                    caps.setCapability("browserstack.key",  TestUtils.readJson(params.getPlatformName(),"browserstack.key"));
                    caps.setCapability("app",  TestUtils.readJson(params.getPlatformName(),"app"));
                    caps.setCapability("device",  TestUtils.readJson(params.getPlatformName(),"device"));
                    caps.setCapability("os_version",  TestUtils.readJson(params.getPlatformName(),"os_version"));
                    caps.setCapability("project",  TestUtils.readJson(params.getPlatformName(),"project"));
                    caps.setCapability("build",  TestUtils.readJson(params.getPlatformName(),"build"));
                    caps.setCapability("name",  nameOfScen);
                    break;


            }
            return caps;
        } catch(Exception e){
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }

    public synchronized DesiredCapabilities getCaps(String UDID,String platformVersion,String systemPort) throws IOException, ParseException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        try {
            utils.log().info("getting capabilities");
            caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.UDID, UDID);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

            switch (params.getPlatformName()) {
                case "Android":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    caps.setCapability("platformVersion", platformVersion);
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("AndroidAutomationName"));
                    // caps.setCapability("chromedriverExecutable","/Users/79443498/Downloads/chromedriver");
                    // caps.setCapability("browserName", "chrome");
                    // caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,systemPort);
                    //  caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Flutter");
                    break;
                case "iOS":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    caps.setCapability("startIWDP", true);
                    //  caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW,true);
                    break;

            }
            return caps;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }

    public synchronized DesiredCapabilities getCloudCaps(String DeviceName, String OSVersion,String platformDiff) throws IOException, ParseException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        try{
            utils.log().info("getting capabilities");
            caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

            switch(platformDiff){
                case "BrowserStack-Android":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                    caps.setCapability("browserstack.user", TestUtils.readJson(platformDiff,"browserstack.user"));
                    caps.setCapability("browserstack.key",  TestUtils.readJson(platformDiff,"browserstack.key"));
                    caps.setCapability("app",  TestUtils.readJson(platformDiff,"app"));
                    caps.setCapability("browserstack.enablePasscode","true");
                    caps.setCapability("device",  DeviceName);
                    caps.setCapability("os_version",  OSVersion);
                    caps.setCapability("project",  TestUtils.readJson(platformDiff,"project"));
                    caps.setCapability("build",  TestUtils.readJson(platformDiff,"build"));
                    caps.setCapability("name",  nameOfScen);
                    break;
                case "BrowserStack-Android-1":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                    caps.setCapability("browserstack.user", TestUtils.readJson(platformDiff,"browserstack.user"));
                    caps.setCapability("browserstack.key",  TestUtils.readJson(platformDiff,"browserstack.key"));
                    caps.setCapability("app",  TestUtils.readJson(platformDiff,"app"));
                    caps.setCapability("browserstack.enablePasscode","true");
                    caps.setCapability("device",  DeviceName);
                    caps.setCapability("os_version",  OSVersion);
                    caps.setCapability("project",  "WUITPL");
                    caps.setCapability("build",  "IT-PL build");
                    caps.setCapability("name",  nameOfScen);
                    break;
                case "BrowserStack-iOS":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                    caps.setCapability("browserstack.user", TestUtils.readJson(params.getPlatformName(),"browserstack.user"));
                    caps.setCapability("browserstack.key",  TestUtils.readJson(params.getPlatformName(),"browserstack.key"));
                    caps.setCapability("app",  TestUtils.readJson(params.getPlatformName(),"app"));
                    caps.setCapability("device",  TestUtils.readJson(params.getPlatformName(),"device"));
                    caps.setCapability("os_version",  TestUtils.readJson(params.getPlatformName(),"os_version"));
                    caps.setCapability("project",  TestUtils.readJson(params.getPlatformName(),"project"));
                    caps.setCapability("build",  TestUtils.readJson(params.getPlatformName(),"build"));
                    caps.setCapability("name",  nameOfScen);
                    break;


            }
            return caps;
        } catch(Exception e){
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }



}
