package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import utils.ReqFileReader;
import utils.authTokenGeneration;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class BookStepDef {
    RequestSpecification resq;

    authTokenGeneration authtokenGen = new authTokenGeneration();

    @Before
    public void printLogBefore(){
        System.out.println("Scenario execution started");
    }

    @Given("Simple Books API is available")
    public void simple_books_api_is_available() {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String path) {
        //path=/books
        resq = RestAssured.given();
        Response response = resq.get(path);
        response.prettyPrint();
        Serenity.setSessionVariable("response").to(response);

    }

    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Response response = Serenity.sessionVariableCalled("response");
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        //To get a specific field from an object

        //  String actualType=res.jsonPath().getString("[3].type");
        // System.out.println(resps);

        //   Assert.assertEquals(actualType,"fiction");


    }

    //how to get a field dynamically from an object without hardcoding?
    //the question is here i have bookid 4 i want the type must be fiction

    @Then("The response for the field {string} should contain value {string} for bookId {int}")
    public void the_response_for_the_field_should_contain_value_for_book_id(String field, String value, Integer id) {
        Response Getallbooks = Serenity.sessionVariableCalled("response");

        //To get all the books we use getList()
        List<Map<String, Object>> books = Getallbooks.jsonPath().getList("");
        //Map defines for each object the data is in key value pairs
        //why we used Object here because for id the value is int and for name the value is string
        //so we have different data type for the value so for that we have used

        // Iterate over each book and check the required properties
        for (Map<String, Object> book : books) {
            if (book.get("id").equals(id)) {

                Assert.assertEquals("Type should be fiction", value, book.get(field));
                break;
            }
        }


    }


//post request

    @Given("I have an auth token")
    public void i_have_an_auth_token() throws JsonProcessingException {
        String AuthToken = authtokenGen.authToken();
        Serenity.setSessionVariable("AuthGlobal").to(AuthToken);


    }

    @When("To submit a new order using post API")
    public void to_submit_a_new_order_using_post_api() throws IOException, ParseException {
        String Token = Serenity.sessionVariableCalled("AuthGlobal");
        RestAssured.baseURI = "https://simple-books-api.glitch.me";
        // to read a json file, we use json parse
        //using json parser , we are converting data in jason file to java object
        //In Javascript, the standard way to do this is by using the method JSON.parse()

        //  JSONParser jsonparser=new JSONParser();


        // Object obj = jsonparser.parse(new FileReader("C:\\Users\\mamid\\OneDrive\\Desktop\\SaiTeja\\JDBL\\MicroServicesProjects\\microservices-3.4.1\\saitejav2.0\\section2\\SimpleBooksApiCopy2\\src\\test\\java\\Payload\\SubmitOrder.json"));

        // the payload defined in the file is a json object and .parse method is converting to java object
        // you are assigin to the java pbject to reference obj of type Object

        //   String strobj=obj.toString();
        String strobj = ReqFileReader.readFile("C:\\\\Users\\\\mamid\\\\OneDrive\\\\Desktop\\\\SaiTeja\\\\JDBL\\\\MicroServicesProjects\\\\microservices-3.4.1\\\\saitejav2.0\\\\section2\\\\SimpleBooksApiCopy2\\\\src\\\\test\\\\java\\\\Payload\\\\SubmitOrder.json");

        //now we have to searilize the payload before hitting any request
        //so we are converting to string

        resq = RestAssured.given();
        Response response = resq.headers("Authorization", Token)
                .headers("Content-Type", "application/json")
                .body(strobj)
                .post("/orders");

        response.prettyPrint();

        Serenity.setSessionVariable("response").to(response);


    }

    @Then("The reponse should be {int}")
    public void the_reponse_should_be(Integer int1) {
        Response response = Serenity.sessionVariableCalled("response");
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 201);


    }

    @Then("verify the field {string} should have value {string}")
    public void responseValidation(String field, String value) {
        Response response = Serenity.sessionVariableCalled("response");
        String actualValue = response.getBody().jsonPath().getString(field); //created
        //String actualValue(will have true)=response.getBody().jsonPath().getString("created");
        Assert.assertEquals(actualValue, value);
        //in  Examples we are passing the test data based on the requiremnt
        //so the value is also from the requirement document

    }


    //patch API


    @When("I update an order with orderId {string}")
    public void i_update_an_order_with_order_id_gq_k_ocx_bbf_jk_yqn8b_p_kiq(String orderid) throws IOException, ParseException, IOException, ParseException {
        String token = Serenity.sessionVariableCalled("AuthGlobal");

//        JSONParser jsonparser=new JSONParser();
//
//
//        Object obj = jsonparser.parse(new FileReader("C:\\Users\\mamid\\OneDrive\\Desktop\\SaiTeja\\JDBL\\MicroServicesProjects\\microservices-3.4.1\\saitejav2.0\\section2\\SimpleBooksApiCopy2\\src\\test\\java\\Payload\\SubmitOrder.json"));
//
//        // the payload defined in the file is a json object and .parse method is converting to java object
//        // you are assigin to the java pbject to reference obj of type Object
//
//        String strobj=obj.toString();

      String strobj= ReqFileReader.readFile("C:\\Users\\mamid\\OneDrive\\Desktop\\SaiTeja\\JDBL\\MicroServicesProjects\\microservices-3.4.1\\saitejav2.0\\section2\\SimpleBooksApiCopy2\\src\\test\\java\\Payload\\PayloadForUpdatetheData.json");




        //now we have to searilize the payload before hitting any request
        //so we are converting to string
        String orderId=orderid;
        RestAssured.baseURI = "https://simple-books-api.glitch.me";

        resq = RestAssured.given();
        Response postResponse = resq.header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(strobj)
                .patch("/orders/gqKOcxBBFJkYQN8bP-kiq");

        postResponse.prettyPrint();
        Serenity.setSessionVariable("response").to(postResponse);


    }
}
