package utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.authorizationGenerationRequestDTO;


public class authTokenGeneration {
    configFileReading config=new configFileReading();
    randomNumberGenerator randnum=new randomNumberGenerator();
    authorizationGenerationRequestDTO authtokenGen=new authorizationGenerationRequestDTO();

    Response res;
    RequestSpecification resq;


    public String AUTH_TOKEN_URL="/api-clients/";

    public String authToken() throws JsonProcessingException {
        RestAssured.baseURI="https://simple-books-api.glitch.me";
        //Auth Request Body building using Pojo
        String clientEmail="Postman"+randnum.randomEmailGenerator()+"@example.com";
        String clientName="valentin"+randnum.randomNameGenerator(); // whenever an integr concats with string the result will be string

        System.out.println(clientEmail);

        System.out.println(clientName);

        authtokenGen.setClientEmail(clientEmail);
        authtokenGen.setClientName(clientName);

        //authtokenGen contains clientEmail and clientName

ObjectMapper obj=new ObjectMapper();
String authGenJsonObj = obj.writerWithDefaultPrettyPrinter().writeValueAsString(authtokenGen);  // it is possible by converting java object to json objetc and to jason string

        RequestSpecification resq=RestAssured.given();
                     res=resq.headers("Content-Type","application/json")
                            .body(authGenJsonObj)
                            .post(AUTH_TOKEN_URL);
                    res.prettyPrint();

//how to get accesstoken from response
                    String accessToken=res.getBody().jsonPath().get("accessToken");

                    return accessToken;

    }


}
