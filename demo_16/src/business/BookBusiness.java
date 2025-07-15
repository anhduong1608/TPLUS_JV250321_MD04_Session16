package business;

import entity.Book;
import java.util.List;

public interface BookBusiness {
    void showAll();
    void add(Book b);
    void addMultiple(List<Book> books);
    void update(Book b);
    void delete(String id);
    void search(String name);
    void stats();
}