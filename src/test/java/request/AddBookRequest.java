package request;
import java.util.Random;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookRequest {
    private String name;
    private String isbn;
    private String aisle;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn =isbn;

       //this.isbn = isbn + Integer.toString(new Random().nextInt(100));
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle( String aisle) {
        this.aisle = aisle;
        //this.aisle = aisle + Integer.toString(new Random().nextInt(100));;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
