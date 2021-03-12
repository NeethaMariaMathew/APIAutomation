package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.AddBookRequest;
import response.AddBookResponse;

import static io.restassured.RestAssured.given;

public class AddBookPositiveTest {
    @Test
    public void addBookPostPositive() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Book Name");
        addBookRequest.setIsbn("389");
        addBookRequest.setAisle("546");
        addBookRequest.setAuthor("Some Name");

        Response responseAddBook = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(200).extract().response();

        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
        System.out.println(addBookResponse.getId());
        Assert.assertEquals(addBookResponse.getMsg(), "successfully added", "Add Book message incorrect");

    }
}
