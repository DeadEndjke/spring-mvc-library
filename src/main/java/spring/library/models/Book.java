package spring.library.models;

public class Book {
    private int id;
    private int personId;
    private String name;
    private String author;
    private int bookYear;

    public Book(int id, int personId, String author, int bookYear, String name) {
        this.id = id;
        this.personId = personId;
        this.author = author;
        this.bookYear = bookYear;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}
