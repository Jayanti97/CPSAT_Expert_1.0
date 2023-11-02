package org.example;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static  String Baseurl;
    public static String Login;
    public static String Secret;

    public static void setup()

    {

        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream("./Config/credentials.properties");
            prop = new Properties();
            try {
                prop.load(fis);
            } catch (IOException e)
            {

                e.printStackTrace();
            }
        } catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Baseurl=prop.getProperty("baseurl");
        Login=prop.getProperty("login");
        Secret=prop.getProperty("secret");


    }
    @Test
    public void check()
    {
        System.out.println("V@z45678912345678".length());
    }


}
