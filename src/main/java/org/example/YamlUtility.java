package org.example;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class YamlUtility {


    public void ModifyReportConfigs() throws IOException {
        new PropertyManager().getProps();
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(new File("src/test/resources/pdf-config.yaml"));
        Map<String, Object> data = yaml.load(inputStream);
        System.out.println(data.get("summaryConfig"));
        Map<String, Object> data1 = (Map<String, Object>) data.get("summaryConfig");
        data1.put("title", PropertyManager.App + " Automation Test Report");
        data.put("summaryConfig", data1);
        PrintWriter writer = new PrintWriter(new File("src/test/resources/pdf-config.yaml"));
        yaml.dump(data, writer);
    }

    public Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
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

    private Map<String, Object> data;

    public void dataRetriever(String yamlFilePath) {
        try (InputStream inputStream = new FileInputStream(new File(yamlFilePath));) {
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getCountryData(String countryName, String deliveryMethod) {
        if (data.containsKey("countries")) {
            List<Map<String, Object>> countries = (List<Map<String, Object>>) data.get("countries");
            for (Map<String, Object> country : countries) {
                if (country.containsKey("name") && country.get("name").equals(countryName)) {
                    if (country.containsKey("delivery_methods")) {
                        List<Map<String, Object>> deliveryMethods = (List<Map<String, Object>>) country.get("delivery_methods");
                        for (Map<String, Object> method : deliveryMethods) {
                            if (method.containsKey("method") && method.get("method").equals(deliveryMethod)) {
                                return (Map<String, Object>) method.get("data");
                            }
                        }
                    }

                }
            }
        }
        return null; // Country or delivery method not found
    }


    @Test
    public void chk() {
        dataRetriever("src/main/resources/Receivers.yaml");
        new TestUtils().log().info(getCountryData("china", "cash").get("first name"));
    }

}