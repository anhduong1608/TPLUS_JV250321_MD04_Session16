package business;

import dao.BookTypeDAO;
import dao.BookTypeDAOImpl;
import entity.BookType;
import java.util.List;

public class BookTypeBusinessImpl implements BookTypeBusiness {
    private BookTypeDAO dao = new BookTypeDAOImpl();

    @Override
    public void showAll() {
        List<BookType> list = dao.getAll();
        for (BookType bt : list) {
            System.out.println(bt.getId() + " | " + bt.getName() + " | " + bt.getDescription() + " | " + (bt.isStatus() ? "Hoạt động" : "Ẩn"));
        }
    }

    @Override
    public void add(BookType type) {
        if (dao.insert(type)) {
            System.out.println("Thêm loại sách thành công.");
        } else {
            System.out.println("Thêm thất bại.");
        }
    }

    @Override
    public void update(BookType type) {
        if (dao.update(type)) {
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("Cập nhật thất bại.");
        }
    }

    @Override
    public void delete(int id) {
        if (dao.delete(id)) {
            System.out.println("Xoá thành công.");
        } else {
            System.out.println("Xoá thất bại.");
        }
    }
}