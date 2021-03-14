package response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookResponseAuthor {
    @JsonProperty("book_name")
    private String name;
    private String isbn;
    private String aisle;

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
        this.isbn = isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    public void getDetails()
    {
        System.out.println(name);
        System.out.println(isbn);
        System.out.println(aisle);
    }
}
