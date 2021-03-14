package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.AddBookRequest;
import response.AddBookResponse;
import response.GetBookResponse;
import response.GetBookResponseAuthor;

public class GetBookByAuthorName extends ReusableMethods{
    @Test
    public void addGetPrintByAuthorName()
    {
        RestAssured.baseURI = "http://216.10.245.166";

        AddBookResponse addBookResponse = addBook("Nimmy","037","150","Mathew Sebastian");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

         addBookResponse = addBook("Nisha","034","158","Mathew Sebastian");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

         addBookResponse = addBook("Neetha","030","164","Mathew Sebastian");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        AddBookRequest addBookRequest = new AddBookRequest();
        Response getBookByAuthor = getBookByAuthor("Mathew Sebastian", 200);

        Response getBookResponse = getBookByAuthor("Mathew Sebastian",200);
        GetBookResponseAuthor [] book = getBookResponse.as (GetBookResponseAuthor [].class);
        Assert.assertEquals(book[0].getName(),"Nimmy","Get Book message incorrect");
        Assert.assertEquals(book[1].getName(),"Nisha","Get Book message incorrect");
        Assert.assertEquals(book[2].getName(),"Neetha","Get Book message incorrect");

        System.out.println("Number of books returned = "+ book.length);
        for(int i=0;i<book.length;i++)
            System.out.println("Book details : book" + (i+1) + " " +book[i].getName());

    }
}
