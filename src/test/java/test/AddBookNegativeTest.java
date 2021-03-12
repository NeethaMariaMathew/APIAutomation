package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.AddBookRequest;
import response.AddBookResponse;

import static io.restassured.RestAssured.given;

public class AddBookNegativeTest {
    @Test
    public void addBookPostNegative() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Book New");
        addBookRequest.setIsbn("657");
        addBookRequest.setAisle("1000");
        addBookRequest.setAuthor("New Name");

        Response responseAddBook = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(200).extract().response();

        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
        System.out.println(addBookResponse.getId());
        Assert.assertEquals(addBookResponse.getMsg(), "successfully added", "Add Book message incorrect");

        Response response = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(404).extract().response();
        String output =  response.asString().substring(8,69);
        Assert.assertEquals(output, "Add Book operation failed, looks like the book already exists", "Adding existing Book message incorrect");

    }

}
