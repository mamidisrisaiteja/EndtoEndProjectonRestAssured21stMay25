package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configFileReading {
    public String configFileReading() throws Exception {
        String filePath="C:\\Users\\mamid\\eclipse-workspace\\RestAssured08Mar2023\\src\\test\\java\\config\\config.properties";

        Properties prop=new Properties();// we need to access load method in Properties class
        //load mehtod accecpts input stream as input
        String basePath;
        FileInputStream fip=new FileInputStream(filePath);// it reads the file in the path speceified in the parametes
        prop.load(fip); // loaded fip object into mehtod in Properties class , load is a mehtod in properties class, to access that mehtod we created object of properties class

        System.out.println("the base path is"+prop.getProperty("baseURI"));
        return basePath=prop.getProperty("baseURI");

    }

}