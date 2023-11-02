package org.example;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GlobalParams {
    private static ThreadLocal<String> platformName = new ThreadLocal<String>();
    private static ThreadLocal<String> udid = new ThreadLocal<String>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<String>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<String>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<String>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<String>();
    private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<String>();
    private static ThreadLocal<String> browserName = new ThreadLocal<String>();


    public void setPlatformName(String platformName1) {
        platformName.set(platformName1);
    }
    public void setBrowserName(String browser) {
        browserName.set(browser);
    }
    public String getBrowserName(){
        return browserName.get();
    }



    public synchronized String getPlatformName() {
        return platformName.get();
    }

    public String getUDID() {
        return udid.get();
    }

    public void setUDID(String udid2) {
        udid.set(udid2);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }

    public String getSystemPort() {
        return systemPort.get();
    }

    public void setSystemPort(String systemPort2) {
        systemPort.set(systemPort2);
    }

    public String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort2) {
        chromeDriverPort.set(chromeDriverPort2);
    }

    public String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort2) {
        wdaLocalPort.set(wdaLocalPort2);
    }

    public String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort2) {
        webkitDebugProxyPort.set(webkitDebugProxyPort2);
    }

    public synchronized void initializeGlobalParams() throws IOException, ParseException {
        GlobalParams params = new GlobalParams();
        //
        String platform = new PropertyManager().getProps().getProperty("platform");
        switch (platform) {
            case "Android":
                params.setPlatformName(System.getProperty("platformName", "Android"));
                params.setUDID(System.getProperty("udid", TestUtils.readJson(platform,"udid")));
                params.setDeviceName(System.getProperty("deviceName", TestUtils.readJson(platform,"deviceName")));
                break;
            case "iOS":
                params.setPlatformName(System.getProperty("platformName", "iOS"));
                params.setUDID(System.getProperty("udid", TestUtils.readJson(platform,"udid")));
                params.setDeviceName(System.getProperty("deviceName", TestUtils.readJson(platform,"deviceName")));
                break;

            case "BrowserStack-Android":
                params.setPlatformName(System.getProperty("platformName", "BrowserStack-Android"));
                break;
            case "BrowserStack-iOS":
                params.setPlatformName(System.getProperty("platformName", "BrowserStack-iOS"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name!");
        }
    }
    public synchronized void initializeGlobalParamsBrowser()  {

        GlobalParams params = new GlobalParams();
        params.setPlatformName(System.getProperty("platformName","Browser"));
        params.setBrowserName(System.getProperty("browserName", "chrome"));



    }


}

