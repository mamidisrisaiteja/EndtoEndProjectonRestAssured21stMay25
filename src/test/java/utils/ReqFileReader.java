package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReqFileReader {

    public static String readFile(String path) throws IOException, ParseException {

        JSONParser jsonparser=new JSONParser();


        Object obj = jsonparser.parse(new FileReader(path));

        // the payload defined in the file is a json object and .parse method is converting to java object
        // you are assigin to the java pbject to reference obj of type Object

        String strobj=obj.toString();
        return strobj;
    }
}
