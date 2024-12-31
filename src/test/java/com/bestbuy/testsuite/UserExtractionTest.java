package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class UserExtractionTest extends TestBase {

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
//1. Extract the All Ids
@Test
public void test001() {
    List<?> allIds = response.extract().path("id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All Ids are : " + allIds);
    System.out.println("------------------End of Test---------------------------");
}

//2. Extract the all Names
@Test
public void test002() {
    List <String> name = response.extract().path("name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("all names are " + name);
    System.out.println("------------------End of Test---------------------------");
}


//3. Extract the name of 5th object
@Test
public void test003() {
    String name = response.extract().path("[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Name is " + name);
    System.out.println("------------------End of Test---------------------------");
}

//4. Extract the names of all object whose status = inactive
@Test
public void test004() {
    List <?> status = response.extract().path("findAll{it.status =='inactive'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Names of all inactive people are:  " + status);
    System.out.println("------------------End of Test---------------------------");
}

//5. Extract the gender of all the object whose status = active
@Test
public void test005() {
    List <?> activeGender = response.extract().path("findAll{it.status=='active'}.gender");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Gender of all Active people are:  " + activeGender);
    System.out.println("------------------End of Test---------------------------");
}
//6. Print the names of the object whose gender = female
@Test
public void test006() {
    List <?> female = response.extract().path("findAll{it.gender=='female'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" Name of all females are: " + female);
    System.out.println("------------------End of Test---------------------------");
}
//7. Get all the emails of the object where status = inactive
@Test
public void test007() {
    List <?> email = response.extract().path("findAll{it.status=='inactive'}.email");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("email of all inactive people are:  " + email);
    System.out.println("------------------End of Test---------------------------");
}

//8. Get the ids of the object where gender = male
@Test
public void test008() {
    List <?> male = response.extract().path("findAll{it.gender=='female'}.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" Ids of all males are: " + male);
    System.out.println("------------------End of Test---------------------------");
}

//9. Get all the status
@Test
public void test009() {
    List<?> allstatus = response.extract().path("status");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All status are : " + allstatus);
    System.out.println("------------------End of Test---------------------------");
}


//10. Get email of the object where name = Lal Dwivedi
@Test
public void test0010() {
   String email = response.extract().path("findAll{it.name.startsWith == 'Kash'}.email");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Names of all inactive people are:  " + email);
    System.out.println("------------------End of Test---------------------------");
}

//11. Get gender of id = 5914189

    @Test
    public void test0011() {
        String gender = response.extract().path("[2].gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is:  " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

}
