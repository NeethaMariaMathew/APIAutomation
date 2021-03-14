package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.AddBookRequest;
import response.AddBookResponse;
import response.GetBookResponseAuthor;

public class AddGetDeleteBookByAuthorName extends ReusableMethods{
    @Test
    public void addGetDeletePrintByAuthor()
    {
        RestAssured.baseURI = "http://216.10.245.166";

        AddBookResponse addBookResponse = addBook("Nimmy","037","150","Mathew Sebastian");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        addBookResponse = addBook("Nisha","034","158","Mathew Sebastian");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        addBookResponse = addBook("Neetha","030","164","Mathew Sebastian");
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        System.out.println("Added 3 books with author as Mathew Sebastian");

        Response getBookResponse = getBookByAuthor("Mathew Sebastian",200);
        GetBookResponseAuthor[] book = getBookResponse.as (GetBookResponseAuthor [].class);
        Assert.assertEquals(book[0].getName(),"Nimmy","Get Book message incorrect");
        Assert.assertEquals(book[1].getName(),"Nisha","Get Book message incorrect");
        Assert.assertEquals(book[2].getName(),"Neetha","Get Book message incorrect");
        System.out.println("Number of books returned for author Mathew Sebastian is = "+ book.length);
        for(int i=0;i<book.length;i++)
            System.out.println("Book details : book" + (i+1) + " " +book[i].getName());


         getBookResponse = getBookByAuthor("Mathew",200);
         book = getBookResponse.as (GetBookResponseAuthor [].class);
        System.out.println("Number of books returned for author Mathew is = "+ book.length);
        for(int i=0;i<book.length;i++)
            System.out.println("Book details : book" + (i+1) + " " +book[i].getName());


        String id = book[0].getIsbn().concat(book[0].getAisle());
        Response deleteResponse = deleteBook(id);
        String deleteOutput = deleteResponse.asString().substring(8,36);
        Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");
        System.out.println("Book with id "+ id + " successfully deleted");


        getBookResponse = getBookByAuthor("Mathew",200);
        book = getBookResponse.as (GetBookResponseAuthor [].class);
        System.out.println("Number of books returned for author Mathew is = "+ book.length);
        for(int i=0;i<book.length;i++)
            System.out.println("Book details : book" + (i+1) + " " +book[i].getName());



        getBookResponse = getBookByAuthor("Mathew Sebastian",200);
        book = getBookResponse.as (GetBookResponseAuthor [].class);
        Assert.assertEquals(book[0].getName(),"Nimmy","Get Book message incorrect");
        Assert.assertEquals(book[1].getName(),"Nisha","Get Book message incorrect");
        Assert.assertEquals(book[2].getName(),"Neetha","Get Book message incorrect");
        System.out.println("Number of books returned for author Mathew Sebastian is = "+ book.length);
        for(int i=0;i<book.length;i++)
            System.out.println("Book details : book" + (i+1) + " " +book[i].getName());

    }
}
