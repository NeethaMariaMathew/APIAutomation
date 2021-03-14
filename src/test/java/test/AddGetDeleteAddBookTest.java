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

public class AddGetDeleteAddBookTest extends ReusableMethods{
    @Test
    public void addGetDelete()
    {
        RestAssured.baseURI = "http://216.10.245.166";

        AddBookResponse addBookResponse = addBook("Harry Potter","062","160","J K Rowling");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        Response getBookResponse = getBookByID(addBookResponse.getId(),200);
        GetBookResponse[] book = getBookResponse.as (GetBookResponse [].class);
        Assert.assertEquals(book[0].getAuthor(),"J K Rowling","Get Book message incorrect");

        Response deleteResponse = deleteBook(addBookResponse.getId());
        String deleteOutput = deleteResponse.asString().substring(8,36);
        Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");

        getBookResponse = getBookByID(addBookResponse.getId(),404);
         deleteOutput = getBookResponse.prettyPrint().substring(14,73);
        Assert.assertEquals(deleteOutput, "The book by requested bookid / author name does not exists!", "Get Book message incorrect");

         addBookResponse = addBook("Harry Potter","062","160","J K Rowling");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");
    }


}


