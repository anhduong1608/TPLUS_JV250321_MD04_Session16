package dao;

import entity.Book;
import java.util.List;

public interface BookDAO {
    List<Book> getAll();
    boolean insert(Book book);
    boolean update(Book book);
    boolean delete(String id);
    List<Book> searchByName(String name);
    void countBooksByAuthor();
}
