package business;

import dao.BookDAO;
import dao.BookDAOImpl;
import entity.Book;
import java.util.List;

public class BookBusinessImpl implements BookBusiness {
    private BookDAO dao = new BookDAOImpl();

    @Override
    public void showAll() {
        List<Book> list = dao.getAll();
        for (Book b : list) {
            System.out.println(b.getId() + " | " + b.getName() + " | " + b.getTitle() + " | " +
                    b.getPages() + " | " + b.getAuthor() + " | " + b.getPrice() + " | " + b.getTypeId() +
                    " | " + (b.isStatus() ? "Hoạt động" : "Ẩn"));
        }
    }

    @Override
    public void add(Book b) {
        if (dao.insert(b)) {
            System.out.println("Thêm sách thành công.");
        } else {
            System.out.println("Thêm sách thất bại.");
        }
    }

    @Override
    public void addMultiple(List<Book> books) {
        for (Book b : books) add(b);
    }

    @Override
    public void update(Book b) {
        if (dao.update(b)) {
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("Cập nhật thất bại.");
        }
    }

    @Override
    public void delete(String id) {
        if (dao.delete(id)) {
            System.out.println("Xoá thành công.");
        } else {
            System.out.println("Xoá thất bại.");
        }
    }

    @Override
    public void search(String name) {
        List<Book> results = dao.searchByName(name);
        for (Book b : results) {
            System.out.println(b.getId() + " | " + b.getName() + " | " + b.getAuthor());
        }
    }

    @Override
    public void stats() {
        dao.countBooksByAuthor();
    }
}