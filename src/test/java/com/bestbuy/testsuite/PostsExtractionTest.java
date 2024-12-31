package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given().log().all()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("public/v2/posts")
                .then().statusCode(200);

    }

//1. Extract the title
@Test
public void test001() {
    List<String> title = response.extract().path("title");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("all titles are " + title);
    System.out.println("------------------End of Test---------------------------");
}
//2. Extract the total number of record
@Test
public void test002() {
    List<String> records = response.extract().path("data");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("total records are " + records.size());
    System.out.println("------------------End of Test---------------------------");
}


//3. Extract the body of 15th record
@Test
public void test003() {
    String records = response.extract().path("body[14]");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("total records are " + records);
    System.out.println("------------------End of Test---------------------------");
}

//4. Extract the user_id of all the records
@Test
public void test004() {
    List <Integer> recrods = response.extract().path("user_id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All user_id are: " + recrods);
    System.out.println("------------------End of Test---------------------------");
}


//5. Extract the title of all the records
@Test
public void test005() {
    List <String> titles = response.extract().path("title");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All tiles are: " + titles);
    System.out.println("------------------End of Test---------------------------");
}
//6. Extract the title of all records whose user_id = 5914200
@Test
public void test006() {
    List <String> record = response.extract().path("findAll{it.user_id == 7609179}.title");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Tile of user_id 184663 is: " + record);
    System.out.println("------------------End of Test---------------------------");
}

//7. Extract the body of all records whose id = 93957
@Test
public void test007() {
    List <String> record = response.extract().path("findAll{it.id == 184660}.body");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("body of id 184661 is: " + record);
    System.out.println("------------------End of Test---------------------------");
}

}
