package database;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
public class Database {
    private static List<Book> bookList = new ArrayList<>();

    public static List<Book> getBooks() {
        return bookList;
    }

    public static void addBook(Book book) {
        bookList.add(book);
    }
    public static void removeBook(Book book) {
        bookList.remove(book);
    }
}

