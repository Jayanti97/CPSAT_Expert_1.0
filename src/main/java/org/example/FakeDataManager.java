package org.example;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import static org.apache.pdfbox.cos.COSName.W;

public class FakeDataManager {

    public static String inputData;
    Map<String, String> commonData;



    public String getData(String dataField) {

        switch (dataField) {
            case "firstName":
                inputData = new Faker().name().firstName();
                break;
            case "middleName":
            case "lastName":
                inputData = new Faker().name().lastName();
                break;
            case "city":
                inputData = new Faker().address().city();
                break;
            case "state":
                inputData = new Faker().address().state();
                break;
            case "buildingNumber":
                inputData = new Faker().address().buildingNumber();
                break;
            case "country":
                inputData = new Faker().address().country();
                break;
            case "streetName":
                inputData = new Faker().address().streetName();
                break;
            case "zipCode":
                inputData = new Faker().address().zipCode();
                break;
            case "fullAddress":
                inputData = new Faker().address().fullAddress();
                break;
            case "country_code":
                inputData = new Faker().address().countryCode();
                break;
            case "comissaryNumber":
                inputData = String.valueOf(new Faker().number().randomNumber());
                break;
            case "email":
            case "Email":
                inputData = emailGen();
                break;


        }
        return inputData;
    }

    @Test
    public void test() {
        System.out.println(new FakeDataManager().getData("streetName"));
        System.out.println(new FakeDataManager().getData("city"));
        System.out.println(new FakeDataManager().getData("state"));
        System.out.println(new FakeDataManager().getData("zipCode").substring(0, 5));
        System.out.println(new FakeDataManager().getData("countryCode"));
        System.out.println(new FakeDataManager().getData("fullAddress"));
    }
    public String emailGen() {

        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        Random randomGenerator = new Random();
        String first = String.valueOf((alphabets.charAt(randomGenerator.nextInt(alphabets.length()))));
        String second = String.valueOf((alphabets.charAt(randomGenerator.nextInt(alphabets.length()))));
        return new Faker().name().firstName() + getData("firstName").charAt(0) + (getData("middleName").charAt(1)) + first +
                second + new Random().nextInt(9) + new Random().nextInt(9) + "@kc6d3dey.mailosaur.net";

    }



}


