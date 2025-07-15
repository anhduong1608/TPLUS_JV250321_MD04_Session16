package dao;

import entity.Book;
import util.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        String sql = "{CALL get_all_books()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getString("Book_id"));
                b.setName(rs.getString("Book_name"));
                b.setTitle(rs.getString("Book_title"));
                b.setPages(rs.getInt("Book_pages"));
                b.setAuthor(rs.getString("Book_author"));
                b.setPrice(rs.getDouble("Book_price"));
                b.setTypeId(rs.getInt("Type_id"));
                b.setStatus(rs.getBoolean("Book_status"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(Book b) {
        String sql = "{CALL insert_book(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, b.getId());
            cs.setString(2, b.getName());
            cs.setString(3, b.getTitle());
            cs.setInt(4, b.getPages());
            cs.setString(5, b.getAuthor());
            cs.setDouble(6, b.getPrice());
            cs.setInt(7, b.getTypeId());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Book b) {
        String sql = "{CALL update_book(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, b.getId());
            cs.setString(2, b.getName());
            cs.setString(3, b.getTitle());
            cs.setInt(4, b.getPages());
            cs.setString(5, b.getAuthor());
            cs.setDouble(6, b.getPrice());
            cs.setInt(7, b.getTypeId());
            cs.setBoolean(8, b.isStatus());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "{CALL delete_book(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, id);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> searchByName(String name) {
        List<Book> list = new ArrayList<>();
        String sql = "{CALL search_book_by_name(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getString("Book_id"));
                b.setName(rs.getString("Book_name"));
                b.setTitle(rs.getString("Book_title"));
                b.setPages(rs.getInt("Book_pages"));
                b.setAuthor(rs.getString("Book_author"));
                b.setPrice(rs.getDouble("Book_price"));
                b.setTypeId(rs.getInt("Type_id"));
                b.setStatus(rs.getBoolean("Book_status"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void countBooksByAuthor() {
        String sql = "{CALL count_books_by_author()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();
            System.out.println("Tác giả | Số lượng sách");
            while (rs.next()) {
                System.out.println(rs.getString("Book_author") + " | " + rs.getInt("total_books"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}