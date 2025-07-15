package dao;

import entity.BookType;
import java.util.List;

public interface BookTypeDAO {
    List<BookType> getAll();
    boolean insert(BookType type);
    boolean update(BookType type);
    boolean delete(int id);
}