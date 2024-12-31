package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest extends TestBase {
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


    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("total", hasSize(25));
    }
    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
    @Test
    public void test002() {
        response.body("[3].title", equalTo("Suffragium spes comitatus omnis vesper centum beneficium approbo voluptas."));
    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7609179));
    }

    //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
    @Test
    public void test004() {
        response.body("id", hasItems(184662,184660,184661));
    }

    //5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    @Test
    public void test005() {
        response.body("[5].body", equalTo("Confido uberrime copia. Vestrum sui solium. Debilito ambitus volup. Solus id adhaero. Strenuus trepide vespillo. Concido thermae canis. Ars vesper patria. Trucido rerum adulatio. Tutis denego animi. Adversus sumo viduo. Voluntarius et vero. Confido vel defendo. Sed fugiat tamquam. Suggero callide depulso. Traho eaque conicio. Antea armo dens. Aedificium argentum quisquam. Apud cupiditate tredecim. Pecunia inventore nam."));
    }

}
