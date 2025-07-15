package dao;

import entity.BookType;
import util.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDAOImpl implements BookTypeDAO {

    @Override
    public List<BookType> getAll() {
        List<BookType> list = new ArrayList<>();
        String sql = "{CALL get_all_book_types()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                BookType bt = new BookType();
                bt.setId(rs.getInt("Type_id"));
                bt.setName(rs.getString("Type_name"));
                bt.setDescription(rs.getString("Type_description"));
                bt.setStatus(rs.getBoolean("Type_status"));
                list.add(bt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(BookType type) {
        String sql = "{CALL insert_book_type(?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, type.getName());
            cs.setString(2, type.getDescription());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(BookType type) {
        String sql = "{CALL update_book_type(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, type.getId());
            cs.setString(2, type.getName());
            cs.setString(3, type.getDescription());
            cs.setBoolean(4, type.isStatus());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "{CALL delete_book_type(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}