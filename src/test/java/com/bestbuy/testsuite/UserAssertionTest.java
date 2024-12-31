package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given().log().all()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("public/v2/users")
                .then().statusCode(200);

    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("totaL", hasSize(20));
    }

    //            2. Verify if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
    @Test
    public void test002() {
        response.body("[2].name", equalTo("Kashyapi Patel"));
    }

    //            3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
    @Test
    public void test003() {
        response.body("[3].name", equalTo("Bhadran Pandey Ret."));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test004() {
        response.body("name", hasItems("Fr. Param Sethi", "Manisha Talwar", "Udai Desai"));
    }

    //5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
    @Test
    public void test005() {
        response.body("[1].email", equalTo("dutta_acharyanandana@mitchell.example"));
    }

    //            6. Verify the status is “Active” of user name is “Amaresh Rana”
    @Test
    public void test006() {
        response.body("[8].status", equalTo("active"));
    }

    //            7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
    @Test
    public void test007() {
        response.body("[9].gender", equalTo("male"));

    }

}
