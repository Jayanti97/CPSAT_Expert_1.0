package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TestUtils {
    public static long WAIT = 40;

    public static String defaultUserEmail;
    public static String defaultPwd;

    public static AtomicInteger wait = new AtomicInteger();
    public static final long less_WAIT = 15;
    public static String last3Digits = "";
    public static JSONObject destinationPath;


    public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringMap = new HashMap<String, String>();
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(file);

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();

        //Get all elements
        NodeList nList = document.getElementsByTagName("string");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                // Store each element key value in map
                stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
            }
        }
        return stringMap;
    }

    public String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public synchronized Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }


    public static String readJson(String platform, String key) throws ParseException, IOException {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/devices.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        obj = path.get(platform);
        path = (JSONObject) obj;
        return path.get(key).toString();

    }


    public static String RandomLast3Digits() {

        for (int i = 0; i < 3; i++)
            last3Digits = last3Digits + new Random().nextInt(9);
        if (last3Digits.length() > 3) {
            last3Digits = last3Digits.substring(0, 3);
        }
        return last3Digits;
    }

    @Test
    public void chek() throws IOException {
        new TestUtils().log().info("10-07-1994".substring(6));
    }


    public JSONObject readDestination(String DestinationCountry) throws ParseException, IOException {


        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/destinationinfo.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        obj = path.get(DestinationCountry);
        destinationPath = (JSONObject) obj;

        return destinationPath;

    }


    public void getDestinationFldValue(String key) throws ParseException, IOException {


        System.out.println(destinationPath.get(key).toString());
    }


    @Test
    public void cheek() throws ParseException, IOException {
        new TestUtils().log().info(readMasterTestData("@TransactionOnly"));
    }

    public JSONObject readMasterTestData(String flowName) throws ParseException, IOException {


        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/testDataMaster.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        obj = path.get(flowName);
        JSONObject flowCreds = (JSONObject) obj;

        return flowCreds;

    }


    private static ThreadLocal<String> UserEmail = new ThreadLocal<>();
    private static ThreadLocal<String> UserPassword = new ThreadLocal<>();


    public String getUserEmail() {
        return UserEmail.get();
    }


    public String getUserPwd() {
        System.out.println("User pwd - " + UserPassword.get() + "|");
        return UserPassword.get();

    }

    public void getUserCreds(String featureName, String appName) throws ParseException, IOException {
        String UserEmail = "";
        String UserPassword = "";
        String UserCreds = readMasterTestData(featureName).get(appName).toString();
        int length = UserCreds.length();
        int a = UserCreds.indexOf("_") + 1;
        UserPassword = UserCreds.substring(a, length);
        UserEmail = UserCreds.replace("_" + UserPassword, "");
        this.UserEmail.set(UserEmail);
        this.UserPassword.set(UserPassword);

    }

    public String getUserCredsParallel(String featureTag, String countryFirstLetter) throws ParseException, IOException {
        String UserCreds = "";
        return UserCreds = readMasterTestData(featureTag).get("User_" + countryFirstLetter).toString();

    }


    public JSONObject jsonDataReader(String fileName, String jsonObject) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/" + fileName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        obj = path.get(jsonObject);
        JSONObject creds = (JSONObject) obj;

        return creds;

    }

    public JSONObject jsonDataReaderFile(String fileName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/" + fileName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        new TestUtils().log().info(path.get("amount").toString());

        return path;

    }

    public static String randomUserEmail;
    public static String randomUserPassword;

    public void getRandomUser(String country) throws IOException, ParseException {


        String randomUserCreds = jsonDataReader("randomTestDataPicker", country).get("User_" + (new Random().nextInt(3) + 1)).toString();
        int length = randomUserCreds.length();
        int a = randomUserCreds.indexOf("_") + 1;
        randomUserPassword = randomUserCreds.substring(a, length);
        randomUserEmail = randomUserCreds.replace("_" + UserPassword, "");

    }


    public String readDebitCardDeliverFee(String countryISO3, String deliveryType) throws ParseException, IOException {


        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/debitCardDeliveryFee.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        obj = path.get(countryISO3);
        JSONObject deliverTypes = (JSONObject) obj;

        return deliverTypes.get(deliveryType).toString();

    }

    public JSONObject readDirectToBankDate(String countryName) throws ParseException, IOException {


        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/directToBankTestData.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not located in src/main/resources/");
        }
        Object obj = jsonParser.parse(reader);
        JSONObject path = (JSONObject) obj;
        obj = path.get(countryName);
        JSONObject flowCreds = (JSONObject) obj;

        return flowCreds;

    }
//    public JSONObject readPageLocators(String screenName, String elementName) throws ParseException, IOException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//
//            switch (PropertyManager.App) {
//                case "USW":
//                    reader = new FileReader("src/test/java/com/wu/USWPageLocators/" + screenName + ".json");
//                    break;
//                case "CorrectionsPay":
//                    reader = new FileReader("src/test/java/com/wu/CorrectionsPayPageLocators/" + screenName + ".json");
//                    break;
//                case "WUPlus":
//                    reader = new FileReader("src/test/java/com/wu/WUPlusPageLocators/" + screenName + ".json");
//                    break;
//                case "Brazil":
//                    reader = new FileReader("src/test/java/com/wu/BrazilPageLocators/" + screenName + ".json");
//                    break;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/test/java/com/wu/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(elementName);
//        JSONObject locators = (JSONObject) obj;
//
//        return locators;
//
//    }
//


//    public JSONObject readTextValuesBasedOnTag(String screenName, String tagName) throws ParseException, IOException {
//
//        return (JSONObject) readPageLocators(screenName, "Text References")
//                .get(tagName);
//
//
//    }


//    public void checkMe()   {
//
//        // create an empty source file
//        File sourceFile = File.createTempFile("Hello", ".java");
//        sourceFile.deleteOnExit();
//
//        // generate the source code, using the source filename as the class name
//        String classname = sourceFile.getName().split(\\.)[0];
//        String sourceCode = "public class " + classname + "{ public void hello() { System.out.print(\"Hello world\");}}";
//
//        // write the source code into the source file
//        FileWriter writer = new FileWriter(sourceFile);
//        writer.write(sourceCode);
//        writer.close();
//
//        // compile the source file
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//        File parentDirectory = sourceFile.getParentFile();
//        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
//        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
//        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
//        fileManager.close();
//
//        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{parentDirectory.toURI().toURL()});
//        Class<?> helloClass = classLoader.loadClass(classname);
//
//        // call a method on the loaded class
//        Method helloMethod = helloClass.getDeclaredMethod("hello");
////        helloMethod.invoke(helloClass.newInstance());
//
//
//    }

//    public List<String> loadExpText(String tagName) throws ParseException, IOException {
//
//        int size = readTextValuesBasedOnTag("", tagName).size();
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < size; i++) {
//            list.add(readTextValuesBasedOnTag("", tagName).get("expCont" + "" + (i + 1)).toString());
//        }
//        return list;
//    }


//    public String getXpath(String screenName, String elementName) throws ParseException, IOException {
//        new TestUtils().log().info("Screen Name: "+screenName+" Element Name: "+elementName);
//        System.out.println(readPageLocators(screenName, elementName));
//        return readPageLocators(screenName, elementName).get("xpath").toString();
//
//    }


    //    @Test
//    public void check() throws ParseException, IOException {
//        PropertyManager.App = "USW";
//        new TestUtils().log().info(readPageLocators("Enter your card's CVV", "CVV"));
//        new TestUtils().log().info(decideLocator("Enter your card's CVV", "CVV"));
//    }
//
//
    public By decideLocator(String screenName, String element) throws ParseException, IOException {
        JSONObject jsonObjectHelper = readPageLocators(screenName, element);
        HashMap<String, String> locatorType = new HashMap<>(jsonObjectHelper);
        Object obj;

        if (!(locatorType.keySet().size() > 1)) {
            obj = locatorType.keySet().toArray()[0];
        } else {
            if (new PropertyManager().getProps().getProperty("AppPlatform").equals("Mobile")) {
                obj = locatorType.keySet().toArray()[0];
            } else {
                new TestUtils().log().info("im in else block " + locatorType.keySet().toArray()[1]);
                obj = locatorType.keySet().toArray()[1];


            }
        }
//
//
//        StringBuilder definedLocator = new StringBuilder(locatorType.get(obj));
//        By ele = null;
//        new TestUtils().log().info("locators - " + locatorType);
//        switch (obj.toString()) {
//
//            case "xpath":
//            case "xpath_web":
//                ele = By.xpath(locatorType.get(obj));
//                break;
//            case "css":
//            case "css_web":
//                ele = By.cssSelector(locatorType.get(obj));
//                break;
//            case "id":
//            case "id_web":
//                ele = By.id(locatorType.get(obj));
//                break;
//            case "className":
//            case "className_web":
//                ele = By.className(locatorType.get(obj));
//                break;
//            case "name":
//            case "name_web":
//                ele = By.name(locatorType.get(obj));
//                break;
//
//        }
//
//
//        return ele;
//
//    }
//
//
//    public static LinkedList<String> billers;

//    public void readBillers() throws ParseException, IOException {
//
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader("src/main/resources/testDataBillPay.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get("Billers");
//        JSONObject locators = (JSONObject) obj;
//        billers = new LinkedList<>();
//        for (Object s : locators.values()) {
//            billers.add(s.toString());
//        }
//
//
//    }


//    public Map<String, String> readReferences(String screenName) throws ParseException, IOException {
//
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            PropertyManager.App = "USW";
//            switch (PropertyManager.App) {
//                case "USW":
//                    new TestUtils().log().info(screenName + "Memory");
//
//                    reader = new FileReader("src/test/java/sessionMemory/" + screenName + " Memory.json");
//                    break;
//                case "CorrectionsPay":
//                    reader = new FileReader("src/test/java/com/wu/sessionMemory3/" + screenName + " Memory.json");
//                    break;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/test/java/com/wu/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        new TestUtils().log().info(path);
//        path.toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, String> referenceStorage = objectMapper.readValue(obj.toString(), Map.class);
//        new TestUtils().log().info(referenceStorage);
//        return referenceStorage;
//
//    }

//    @Test
//    public void hj() throws ParseException, IOException {
//
//        readReferenceValue("Who are you paying", "button");
//    }

//    public String readReferenceValue(String screenName, String reference) throws ParseException, IOException {
//
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            PropertyManager.App = "USW";
//            switch (PropertyManager.App) {
//                case "USW":
//                    new TestUtils().log().info(screenName + "Memory");
//
//                    reader = new FileReader("src/test/java/sessionMemory/" + screenName + " Memory.json");
//                    break;
//                case "CorrectionsPay":
//                    reader = new FileReader("src/test/java/com/wu/sessionMemory3/" + screenName + " Memory.json");
//                    break;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/test/java/com/wu/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(reference);
//
//        return obj.toString();
//    }

//    public JSONObject readBillerTestData(String dataType) throws ParseException, IOException {
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader("src/main/resources/testDataBillPay.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(dataType);
//        return (JSONObject) obj;
//
//    }

//    @Test
//    public void check1() throws ParseException, IOException {
//
//        JSONObject receiversObj = new TestUtils().readReceiversForMoneyTransfer("Receivers", "Standard Bank Transfer");
//    }


//    public JSONObject readReceiversForMoneyTransfer(String dataType, String deliveryMethod) throws ParseException, IOException {
//
//
//
//
//        String deliveryMethodValue = deliveryMethod.replaceAll("\\s", "");
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//
//        try {
//            log().info("src/main/resources/testDataMoneyTransfer_" + deliveryMethodValue +".json");;
//            reader = new FileReader("src/main/resources/testDataMoneyTransfer_" + deliveryMethodValue + ".json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(dataType);
//        return (JSONObject) obj;
//
//    }
//    @Test
//    public void gh()
//    {
//        String last2Digits="2024";
//        last2Digits=last2Digits.substring(2);
//        new TestUtils().log().info(last2Digits);
//    }
//
//    public JSONObject readReceiversForFundsIn(String dataType, String deliveryMethod) throws ParseException, IOException {
//
//        String deliveryMethodValue = deliveryMethod.replaceAll(\\s, "");
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader("src/main/resources/testFundsInMultiCurrency_" + deliveryMethodValue + ".json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(dataType);
//        return (JSONObject) obj;
//
//    }
//
//    public JSONObject readReceiversForFundsInPrimary(String dataType, String deliveryMethod) throws ParseException, IOException {
//
//        String deliveryMethodValue = deliveryMethod.replaceAll(\\s, "");
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader("src/main/resources/testFundsInPrimaryAccount_" + deliveryMethodValue + ".json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(dataType);
//        return (JSONObject) obj;
//
//    }
//
//    public JSONObject readOnboardingTestData(String dataType) throws ParseException, IOException {
//
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader("src/main/resources/testDataOnboarding.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(dataType);
//        return (JSONObject) obj;
//
//    }
//
//    @Test
//    public void text() throws ParseException, IOException {
//        JSONObject receiversObj = new TestUtils().readReceiversForMoneyTransfer("United States", "WU Wallet");
//        String phoneNumber = receiversObj.get("Phone Number").toString();
//        new TestUtils().log().info("WU Phone Number:"+phoneNumber);
//    }
//
//
//
//    public static int i;
//    public LinkedHashMap<String, LinkedHashMap<String,String>> getPaymentData(String dataFor) throws IOException, ParseException {
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//
//        try {
//            reader = new FileReader("src/main/resources/testData_PaymentMethods.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//
//        Object obj = jsonParser.parse(reader);
//        LinkedHashMap<String, LinkedHashMap<String,String>> dataUnit = null;
//        Object document = Configuration.defaultConfiguration().jsonProvider().parse(obj.toString());
//
//        switch (dataFor) {
//            case "Bank Transfer":
//                dataUnit = JsonPath.read(document, "$.BankAccounts");
//                break;
//            case "Credit Card":
//            case "Debit Card":
//            case "Debit/Credit Card":
//                dataUnit = JsonPath.read(document, "$.Cards");
//                break;
//        }
//        i=new Random().nextInt(dataUnit.size());
//        return dataUnit;
//    }
//
//    public String getPaymentDataCard(String cardSubType, String flowType) throws IOException, ParseException {
//        Object obj = jsonReader("testData_paymentMethods");
//        String dataFor = cardSubType+"_"+flowType;
//        int index = new Random().nextInt(JsonPath.read(obj,"$.Cards."+dataFor+".length()"));
//        dataFor = dataFor.concat(".card"+index);
//
//        return JsonPath.read( obj,"$.Cards."+dataFor);
//    }
//
//
//
//    public Object jsonReader(String dataFor) throws IOException, ParseException {
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//
//        try {
//            reader = new FileReader("src/main/resources/"+dataFor+".json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//
//        Object obj = jsonParser.parse(reader);
//        String dataUnit = null;
//        Object document = Configuration.defaultConfiguration().jsonProvider().parse(obj.toString());
//
//        return document;
//    }
//
//    @Test
//    public void testCheck() throws IOException, ParseException {
//        Object obj = jsonReader("testDataNonWalletOnboarding");
////        String value1 = "USA";
////        String value = "amount";
////        String value2= "test";
////        String data1 = "Test Data - USA.amount.test";
////        String data = JsonPath.read(obj, "$."+value1+"."+value+"."+value2);
////        log().info(data);
////
////
////                String temdata = "$.".concat(data1.substring(11));
////                log().info("check="+JsonPath.read(obj, temdata));
//
//        Object obj1 = jsonReader("testData_PaymentMethods");
//        log().info(obj1);
//        int c= JsonPath.read(obj1,"$.Cards.Visa_3DS.length()");
//        log().info("Size"+c);
//        String  data= getPaymentDataCard("Visa", "3DS");
//        //new Random().nextInt(new TestUtils().getPaymentDataCard("Cards.Visa_3DS"));
//
//        log().info(data);
//
//
//
////        JSONObject file = jsonDataReaderFile("testDataOnboarding");
////        log().info(file.get("USA.amount").toString());
//    }
//
//
//    public JSONObject readFacilitiesForSendMoney(String dataType) throws IOException, ParseException {
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader("src/main/resources/testDataCPSendMoneyCashMethod.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not located in src/main/resources/");
//        }
//        Object obj = jsonParser.parse(reader);
//        JSONObject path = (JSONObject) obj;
//        obj = path.get(dataType);
//        return (JSONObject) obj;
//    }
//
//    public boolean jsonKeyExists (JSONObject jsonObject, String key){
//        return jsonObject.containsKey(key);
//    }


//    @Test
//    public void test() throws ParseException, IOException {
//        int selectCardIndex = new Random().nextInt(new TestUtils().readBillerTestData("3DS_CreditCards").size());
//        System.out.println(selectCardIndex);
//        String cardNumber = new TestUtils().readBillerTestData("3DS_CreditCards").get("card" + selectCardIndex).toString();
//        System.out.println(cardNumber);
//    }
//
//
//    public String  getTestUserLabel(String tag)
//    {
//        return tag.split("-")[2];
//    }
        return null;
    }

    private JSONObject readPageLocators(String screenName, String element) {
        return null;
    }
}




