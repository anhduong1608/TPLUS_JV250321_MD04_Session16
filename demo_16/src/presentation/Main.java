package presentation;

import business.*;
import entity.Book;
import entity.BookType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static BookTypeBusiness btBusiness = new BookTypeBusinessImpl();
    static BookBusiness bookBusiness = new BookBusinessImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("***********BOOK MANAGEMENT***********");
            System.out.println("1. Quản lý loại sách");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    manageBookType();
                    break;
                case 2:
                    manageBook();
                    break;
                case 3:
                    System.out.println("Thoát...");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void manageBookType() {
        while (true) {
            System.out.println("***********BOOK TYPE***********");
            System.out.println("1. Danh sách loại sách");
            System.out.println("2. Thêm mới loại sách");
            System.out.println("3. Cập nhật loại sách");
            System.out.println("4. Xóa loại sách");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    btBusiness.showAll();
                    break;
                case 2:
                    BookType bt = new BookType();
                    System.out.print("Tên loại: ");
                    bt.setName(sc.nextLine());
                    System.out.print("Mô tả: ");
                    bt.setDescription(sc.nextLine());
                    bt.setStatus(true);
                    btBusiness.add(bt);
                    break;
                case 3:
                    System.out.print("ID cần sửa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    BookType updateType = new BookType();
                    updateType.setId(id);
                    System.out.print("Tên mới: ");
                    updateType.setName(sc.nextLine());
                    System.out.print("Mô tả mới: ");
                    updateType.setDescription(sc.nextLine());
                    System.out.print("Trạng thái (true/false): ");
                    updateType.setStatus(Boolean.parseBoolean(sc.nextLine()));
                    btBusiness.update(updateType);
                    break;
                case 4:
                    System.out.print("ID cần xoá: ");
                    int deleteId = Integer.parseInt(sc.nextLine());
                    btBusiness.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void manageBook() {
        while (true) {
            System.out.println("***********BOOK***********");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Thêm mới nhiều sách");
            System.out.println("4. Cập nhật sách");
            System.out.println("5. Xóa sách");
            System.out.println("6. Tìm kiếm sách");
            System.out.println("7. Thống kê số lượng sách theo tác giả");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    bookBusiness.showAll();
                    break;
                case 2:
                    Book b = inputBook();
                    bookBusiness.add(b);
                    break;
                case 3:
                    List<Book> books = new ArrayList<>();
                    System.out.print("Nhập số sách cần thêm: ");
                    int n = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < n; i++) {
                        System.out.println("Nhập sách thứ " + (i + 1));
                        books.add(inputBook());
                    }
                    bookBusiness.addMultiple(books);
                    break;
                case 4:
                    Book bookToUpdate = inputBook();
                    System.out.print("Trạng thái (true/false): ");
                    bookToUpdate.setStatus(Boolean.parseBoolean(sc.nextLine()));
                    bookBusiness.update(bookToUpdate);
                    break;
                case 5:
                    System.out.print("Nhập ID sách cần xoá: ");
                    bookBusiness.delete(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Nhập tên sách cần tìm: ");
                    bookBusiness.search(sc.nextLine());
                    break;
                case 7:
                    bookBusiness.stats();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static Book inputBook() {
        Book b = new Book();
        System.out.print("Mã sách: ");
        b.setId(sc.nextLine());
        System.out.print("Tên sách: ");
        b.setName(sc.nextLine());
        System.out.print("Tiêu đề: ");
        b.setTitle(sc.nextLine());
        System.out.print("Số trang: ");
        b.setPages(Integer.parseInt(sc.nextLine()));
        System.out.print("Tác giả: ");
        b.setAuthor(sc.nextLine());
        System.out.print("Giá: ");
        b.setPrice(Double.parseDouble(sc.nextLine()));
        btBusiness.showAll();
        System.out.print("Nhập mã loại sách: ");
        b.setTypeId(Integer.parseInt(sc.nextLine()));

        b.setStatus(true);
        return b;
    }

}
