package org.example;


import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static Properties props = new Properties();
    public static String App;
    TestUtils utils = new TestUtils();



    public Properties getProps() throws IOException {
        InputStream is = null;
        String propsFileName = "";
        switch(getProps("configmaster.properties").getProperty("App")) {
            case "MakeMyTrip":
                propsFileName="config.properties";
                App="Web";
                break;
        }

        if(props.isEmpty()){
            try{
                utils.log().info("loading config properties");
                is = getClass().getClassLoader().getResourceAsStream(propsFileName);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Failed to load config properties. ABORT!!" + e.toString());
                throw e;
            } finally {
                if(is != null){
                    is.close();
                }
            }
        }

        return props;
    }


    public Properties getProps(String filename) throws IOException {
        InputStream is = null;
        Properties props1=new Properties();
        String propsFileName = filename;

        if(props1.isEmpty()){
            try{
                utils.log().info("loading properties");
                is = getClass().getClassLoader().getResourceAsStream(propsFileName);
                props1.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Failed to load config properties. ABORT!!" + e.toString());
                throw e;
            } finally {
                if(is != null){
                    is.close();
                }
            }
        }
        return props1;
    }



    public String F005(String countryName) throws IOException {
        countryName=countryName.replace(" ","");
        return  getProps("countrymobileCodes.properties").getProperty(countryName);

    }


    public String multiCurrencyAccount(String WUCurrency) throws IOException {
        return  getProps("Multicurrency.properties").getProperty(WUCurrency);
    }




    public String getCountryMobileNum(String countryName) throws IOException {
        String originalMobileNum=  getProps("countrymobileNumbers.properties").getProperty(countryName).replace(" ","").replace("-","");
        originalMobileNum=originalMobileNum.replace(originalMobileNum.substring(originalMobileNum.length()-3,originalMobileNum.length()),"");
        TestUtils.RandomLast3Digits();
        return originalMobileNum+TestUtils.last3Digits;

    }
    @Test
    public void unitTest() throws IOException {

        System.out.println(getCountryMobileNum("VirginIslandsBritish"));


    }

}

