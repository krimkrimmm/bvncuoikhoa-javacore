package entities;


public class Book {
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private double price;
    private boolean details;

    public Book(String title, String author, String isbn, int quantity, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    public boolean getDetails() {
        return details;

    }

    public void setDetails(boolean details) {
        this.details = details;
    }

    public boolean isAvailable() {
        return false;
    }
}
