package test;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.AddBookRequest;
import response.AddBookResponse;
import response.GetBookResponse;

import static io.restassured.RestAssured.given;

public class AddGetDeleteAddBookTest {
    @Test
    public void addGetDelete()
    {
        RestAssured.baseURI = "http://216.10.245.166";

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Harry Potter");
        addBookRequest.setIsbn("768");
        addBookRequest.setAisle("456");
        addBookRequest.setAuthor("J K Rowling");
        Response responseAddBook = given().header("Content-Type","application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(200).extract().response();
        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        Response getBookResponse = given().queryParam("ID",addBookResponse.getId())
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(200).extract().response();

        GetBookResponse[] book = getBookResponse.as (GetBookResponse [].class);
        Assert.assertEquals(book[0].getAuthor(),addBookRequest.getAuthor(),"Get Book message incorrect");
       JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID",addBookResponse.getId());

        Response deleteResponse = given().header("Content-Type","application/json")
                .body(requestParamsDelete.toJSONString())
                .when().post("/Library/DeleteBook.php").then()
                .statusCode(200).extract().response();
        String deleteOutput = deleteResponse.asString().substring(8,36);
        Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");

         getBookResponse = given().queryParam("ID",addBookResponse.getId())
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(404).extract().response();

         deleteOutput = getBookResponse.prettyPrint().substring(14,73);

        Assert.assertEquals(deleteOutput, "The book by requested bookid / author name does not exists!", "Get Book message incorrect");

        addBookRequest.setName("Harry Potter");
        addBookRequest.setIsbn("768");
        addBookRequest.setAisle("456");
        addBookRequest.setAuthor("J K Rowling");
         responseAddBook = given().header("Content-Type","application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(200).extract().response();
         addBookResponse = responseAddBook.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");
    }
}


