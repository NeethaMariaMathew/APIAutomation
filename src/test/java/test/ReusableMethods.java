package test;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import request.AddBookRequest;
import response.AddBookResponse;
import response.GetBookResponse;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    public AddBookResponse addBook(String name, String isbn, String aisle, String author)
    {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName(name);
        addBookRequest.setIsbn(isbn);
        addBookRequest.setAisle(aisle);
        addBookRequest.setAuthor(author);
        Response responseAddBook = given().header("Content-Type","application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(200).extract().response();
        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
        return  addBookResponse;
    }
    public Response getBookByID(String id,int statuscode)
    {
        Response getBookResponse = given().queryParam("ID",id)
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(statuscode).extract().response();
        return getBookResponse;
    }
    public Response deleteBook(String id)
    {
        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID",id);

        Response deleteResponse = given().header("Content-Type","application/json")
                .body(requestParamsDelete.toJSONString())
                .when().post("/Library/DeleteBook.php").then()
                .statusCode(200).extract().response();
        return  deleteResponse;
    }

    public Response getBookByAuthor(String author,int statuscode)
    {
        Response getBookResponse = given().queryParam("AuthorName",author)
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(statuscode).extract().response();
        return getBookResponse;
    }
}
