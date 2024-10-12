package util;
import entities.Book;

import java.util.List;
public interface DataReadable<T> {
    List<T> readDataFromFile(String fileName, Class<Book[]> clazz);
}


