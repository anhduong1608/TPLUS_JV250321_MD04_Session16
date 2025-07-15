package business;

import entity.BookType;

public interface BookTypeBusiness {
    void showAll();
    void add(BookType type);
    void update(BookType type);
    void delete(int id);
}