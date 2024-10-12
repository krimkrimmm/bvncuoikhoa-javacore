// 2.Librarian (Người quản lý thư viện):
//- Đăng ký tài khoản/Đăng nhập: Người quản lý mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
//- Quản lý sách: Thêm mới, chỉnh sửa hoặc xóa sách.
//- Quản lý mượn/trả sách: Xem danh sách mượn/trả sách, cập nhật trạng thái các giao dịch mượn/trả sách.
//- Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.

package service;
import constant.Regex;
import constant.Status;
import entities.Book;
import util.FileUtil;
import java.util.*;
import entities.User;
public class LibrarianService {
    public List<Book> books; // Khởi tạo danh sách books
    private static final String BOOK_DATA_FILE = "books.jso";
    private final FileUtil<Book> fileUtil = new FileUtil<>(); // Lưu toàn bộ danh sách sách
    private final Scanner scanner = new Scanner(System.in); // Giữ Scanner mở
    private List<Book> cart = new ArrayList<>(); // Giỏ sách của khách hàng
    private List<Book> borrowedBooks = new ArrayList<>(); // Danh sách sách đã mượn
    private List<Book> returnBooks = new ArrayList<>();//danh sach sach da tra ;

    public LibrarianService() {
        this.books = new ArrayList<>(); // Khởi tạo danh sách trống
        loadBooks(); // Tải sách từ file khi khởi tạo
    }

    //quản lí sach
    public void loadBooks() {
        List<Book> loadedBooks = fileUtil.readDataFromFile(BOOK_DATA_FILE, Book[].class);
        if (loadedBooks != null) {
            books = loadedBooks;
        } else {
            books = new ArrayList<>(); // Nếu không có sách, khởi tạo danh sách trống
        }
    }

    // Trả về danh sách sách
    public void getBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books available.");
        } else {
            System.out.printf("%-20s %-20s %-15s %-10s %-10s%n", "Title", "Author", "ISBN", "Quantity", "Price");
            System.out.println("----------------------------------------------------------");
            for (Book book : books) {
                System.out.printf("%-20s %-20s %-15s %-10d %-10.2f%n",
                        book.getTitle(), book.getAuthor(), book.getIsbn(), book.getQuantity(), book.getPrice());
            }
        }
    }

    // Thêm sách mới vào danh sách
    public Book addBook() {
        // Lấy thông tin sách từ người dùng
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        int quantity = 0;
        while (true) {
            System.out.print("Enter quantity: ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                break; // Thoát vòng lặp nếu nhập hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a number.");
            }
        }

        double price = 0.0;
        while (true) {
            System.out.print("Enter price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                break; // Thoát vòng lặp nếu nhập hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Please enter a valid number.");
            }
        }

        Book book = new Book(title, author, isbn, quantity, price);
        books.add(book);
        saveBookData(); // Lưu danh sách sau khi thêm sách mới
        System.out.println("Book added successfully: " + book);
        return book;
    }

    // Xóa sách theo ISBN
    public boolean deleteBook() {
        System.out.print("Enter ISBN of the book to delete: ");
        String isbn = scanner.nextLine();

        // Tìm sách theo ISBN và xóa
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                saveBookData(); // Lưu dữ liệu sau khi xóa sách
                System.out.println("Book removed successfully: " + book);
                return true; // Xóa thành công
            }
        }
        System.out.println("No book found with ISBN: " + isbn);
        return false;
    }

    // Sửa thông tin sách theo ISBN
    public boolean updateBook() {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine();

        // Tìm sách theo ISBN
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                // Lấy thông tin mới từ người dùng
                System.out.print("Enter new title (leave empty to keep current): ");
                String newTitle = scanner.nextLine();
                if (!newTitle.isEmpty()) {
                    book.setTitle(newTitle);
                }

                System.out.print("Enter new author (leave empty to keep current): ");
                String newAuthor = scanner.nextLine();
                if (!newAuthor.isEmpty()) {
                    book.setAuthor(newAuthor);
                }

                int newQuantity = book.getQuantity(); // Giữ nguyên nếu không nhập mới
                System.out.print("Enter new quantity (leave empty to keep current): ");
                String quantityInput = scanner.nextLine();
                if (!quantityInput.isEmpty()) {
                    try {
                        newQuantity = Integer.parseInt(quantityInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Keeping current quantity: " + book.getQuantity());
                    }
                }
                book.setQuantity(newQuantity);

                double newPrice = book.getPrice(); // Giữ nguyên nếu không nhập mới
                System.out.print("Enter new price (leave empty to keep current): ");
                String priceInput = scanner.nextLine();
                if (!priceInput.isEmpty()) {
                    try {
                        newPrice = Double.parseDouble(priceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price. Keeping current price: " + book.getPrice());
                    }
                }
                book.setPrice(newPrice);

                saveBookData(); // Lưu dữ liệu sau khi sửa sách
                System.out.println("Book updated successfully: " + book);
                return true; // Sửa thành công
            }
        }
        System.out.println("No book found with ISBN: " + isbn);
        return false;
    }

    // Xem danh sách mượn
    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books have been borrowed.");
        } else {
            System.out.printf("%-20s %-20s %-15s%n", "Title", "Author", "ISBN");
            System.out.println("----------------------------------------------------------");
            for (Book book : borrowedBooks) {
                System.out.printf("%-20s %-20s %-15s%n",
                        book.getTitle(), book.getAuthor(), book.getIsbn());
            }
        }
    }
    //xem danh sách trả sach
    public void viewReturnedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books have been returned.");
        } else {
            System.out.printf("%-20s %-20s %-15s%n", "Title", "Author", "ISBN");
            System.out.println("----------------------------------------------------------");
            for (Book book : returnBooks) {
                System.out.printf("%-20s %-20s %-15s%n",
                        book.getTitle(), book.getAuthor(), book.getIsbn());
            }
        }
    }
    // Quản lý mượn sách
    public void borrowBook() {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();

        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.getQuantity() > 0) {
                borrowedBooks.add(book); // Thêm sách vào danh sách mượn
                book.setQuantity(book.getQuantity() - 1); // Giảm số lượng sách
                saveBookData(); // Lưu dữ liệu sách
                System.out.println("Book borrowed successfully: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not available for borrowing.");
    }

    // Quản lý trả sách
    public void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        Iterator<Book> iterator = borrowedBooks.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                iterator.remove(); // Xóa khỏi danh sách mượn
                book.setQuantity(book.getQuantity() + 1); // Tăng số lượng sách
                saveBookData(); // Lưu dữ liệu sách
                System.out.println("Book returned successfully: " + book.getTitle());
                return;
            }
        }
        System.out.println("This book was not borrowed.");
    }
    // Lưu danh sách sách vào file

    public void saveBookData() {
        fileUtil.writeDataToFile(books, BOOK_DATA_FILE); // Lưu toàn bộ danh sách books
    }

    // Đăng xuất
    public void logout() {
        System.out.println("You have been logged out.");
    }

}
