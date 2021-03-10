import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.AddBookRequest;
import response.AddBookResponse;
import response.GetBookResponse;

import static io.restassured.RestAssured.given;

public class AddBookTest {
    @Test
    public void addBookPost()
    {
        RestAssured.baseURI = "http://216.10.245.166";
      AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Harry Potter");
        addBookRequest.setIsbn("987");
        addBookRequest.setAisle("123");
        addBookRequest.setAuthor("J K Rowling");
        Response responseAddBook = given().header("Content-Type","application/json")
                                .body(addBookRequest)
                                .when().post("/Library/Addbook.php").then()
                                .statusCode(200).extract().response();
        //System.out.println(responseAddBook.asString());
        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
       // System.out.println(addBookResponse.getMsg());
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

      Response getBookResponse = given().queryParam("ID",addBookResponse.getId())
                                .header("Content-Type","application/json")
                                .when().get("/Library/GetBook.php").then()
                                .statusCode(200).extract().response();
       //System.out.println(getBookResponse.asString());

       GetBookResponse [] book = getBookResponse.as (GetBookResponse [].class);
       Assert.assertEquals(book[0].getAuthor(),"J K Rowling","Get Book message incorrect");

    }
}
