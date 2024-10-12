// 1.Customer (khách hang):
// - Đăng ký tài khoản/Đăng nhập(Log in): Người dùng mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
// - Xem danh sách sách: Xem, hiển thị các sách có sẵn, tìm kiếm sách theo từ khóa hoặc bộ lọc (tác giả, thể loại,…).
// - Xem chi tiết sách: Xem thông tin chi tiết của sách: tên sách, tác giả, mã sách, nsx, số lượng sách, giá sách, trạng thái sách: trc va sau khi cho mượn.
// - Thêm sách vào giỏ sách: Lựa chọn số lượng và thêm sách vào giỏ sách để mượn.
// - Xem giỏ sách: Xem danh sách các sách đã thêm vào giỏ sách, thay đổi số lượng hoặc xóa sách khỏi giỏ sách.

// - Mượn/trả sách: Xác nhận mượn/trả sách và nhập thông tin giao nhận.
// - Xem lịch sử mượn/trả sách: Xem danh sách các sách đã mượn/trả, bao gồm thông tin chi tiết về từng sách và trạng thái mượn
// - Cập nhật tài khoản cá nhân: Thay đổi thông tin cá nhân như tên, địa chỉ email, mật khẩu.
// - Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.
package service;
import entities.Book;
import entities.User;

import util.FileUtil;

import java.util.*;
public class CustomerService {
    private List<User> users;
    private List<Book> books;
    private List<String> lockedCustomer = new ArrayList<>();
    private static final HashSet<String> lockUserByEmails = new HashSet<>();
    private static final String BOOK_DATA_FILE = "books.json";

    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";
    private final FileUtil<User> fileUtil = new FileUtil<>();
    private static int AUTO_ID;
    private static final int MAX_LOGIN_TIMES = 5;
    private List<Book> cart = new ArrayList<>(); // Giỏ sách của khách hàng
    private List<Book> borrowedBooks = new ArrayList<>(); // Danh sách sách đã mượn
    private List<Book> returnBooks = new ArrayList<>();//danh sach sach da tra ;
    private int quantity;

    //Xem danh sách sách
    public CustomerService() {
        this.books = books;
    }

    // Chức năng xem danh sách sách
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("Không có sách nào trong thư viện.");
            return;
        }
        System.out.println("Danh sách sách có sẵn:");
        for (Book book : books) {
            System.out.println(book.getDetails());
        }
    }

    // Tìm kiếm sách theo từ khóa
    public void searchBooks(String keyword) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("Không tìm thấy sách nào phù hợp với từ khóa: " + keyword);
        } else {
            System.out.println("Kết quả tìm kiếm cho từ khóa: " + keyword);
            for (Book book : foundBooks) {
                System.out.println(book.getDetails());
            }
        }
    }

    //Xem chi tiết sách
    // Chức năng xem chi tiết sách
    public void viewBookDetails() {
        String ibsn = "d";
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(ibsn)) {
                System.out.println("Chi tiết sách:");
                System.out.println(book.getDetails());
                return;
            }
        }
        System.out.println("Không tìm thấy sách với mã: " + ibsn);
    }


    // Thêm sách vào giỏ sách
    public void addBookToCart() {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(book.getIsbn()) && book.isAvailable() && book.getQuantity() >= quantity) {
                for (int i = 0; i < quantity; i++) {
                    cart.add(book);
                }
                System.out.println("Đã thêm " + quantity + " cuốn sách '" + book.getTitle() + "' vào giỏ.");
                return;
            }
        }
        System.out.println("Không thể thêm sách. Vui lòng kiểm tra mã sách hoặc số lượng.");
    }

    // Xem giỏ sách
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Giỏ sách của bạn hiện đang trống.");
            return;
        }
        System.out.println("Danh sách sách trong giỏ:");
        for (Book book : cart) {
            System.out.println(book.getDetails());
        }
    }

    // Mượn sách
    public void borrowBooks() {
        if (cart.isEmpty()) {
            System.out.println("Giỏ sách của bạn hiện đang trống. Không có sách để mượn.");
            return;
        }

        for (Book book : cart) {
            // Giảm số lượng sách trong kho
            book.setQuantity(book.getQuantity() - 1);
            borrowedBooks.add(book); // Thêm vào danh sách đã mượn
        }
        cart.clear(); // Xóa giỏ sách sau khi mượn
        System.out.println("Đã mượn thành công các sách trong giỏ.");
    }

    // Trả sách
    public void returnBooks() {
        for (Book book : borrowedBooks) {
            if (book.getIsbn().equalsIgnoreCase(book.getIsbn())) {
                borrowedBooks.remove(book);
                book.setQuantity(book.getQuantity() + 1); // Tăng số lượng sách trong kho
                System.out.println("Đã trả sách '" + book.getTitle() + "' thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy sách với mã: " + books + " trong danh sách đã mượn.");
    }

    // Xem lịch sử mượn sách
    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Bạn chưa mượn sách nào.");
            return;
        }
        System.out.println("Danh sách sách đã mượn:");
        for (Book book : borrowedBooks) {
            System.out.println(book.getDetails());
        }
    }

    //Xem lịch sử trả sách
    public void viewReturnBooks() {

        if (returnBooks.isEmpty()) {
            System.out.println("Bạn chưa trả sách nào.");
            return;
        }
        System.out.println("Danh sách sách đã trả:");
        for (Book book : returnBooks) {
            System.out.println(book.getDetails());

        }
    }
}









