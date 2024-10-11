// 2.Librarian (Người quản lý thư viện):
//- Đăng ký tài khoản/Đăng nhập: Người quản lý mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
//- Quản lý sách: Thêm mới, chỉnh sửa hoặc xóa sách.
//- Quản lý mượn/trả sách: Xem danh sách mượn/trả sách, cập nhật trạng thái các giao dịch mượn/trả sách.
//- Quản lý người dùng: Xem thông tin người dùng và lịch sử mượn sách.
//- Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.

package service;
import constant.Regex;
import constant.Status;
import entities.Book;
import util.FileUtil;
import java.util.*;

public class LibrarianService {
    public List<Book> books; // Khởi tạo danh sách books
    private static final String BOOK_DATA_FILE = "books.jso";
    private final FileUtil<Book> fileUtil = new FileUtil<>(); // Lưu toàn bộ danh sách sách
    private final Scanner scanner = new Scanner(System.in); // Giữ Scanner mở
    private List<>

    public LibrarianService() {
        this.books = new ArrayList<>(); // Khởi tạo danh sách trống
        loadBooks(); // Tải sách từ file khi khởi tạo
    }

    private void loadBooks() {
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

    // Lưu danh sách sách vào file

    public void saveBookData() {
        fileUtil.writeDataToFile(books, BOOK_DATA_FILE); // Lưu toàn bộ danh sách books
    }

    //Xem danh sách mượn sách
    public void viewBorrowedBooks() {
        // Assuming you have a way to track borrowed books
        System.out.println("Borrowed Books:");
        // Here you'd retrieve and print the list of borrowed books from your data structure
        // For example, you could have a list of borrowed transactions
        // Just as an illustration:
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books are currently borrowed.");
        } else {
            System.out.printf("%-20s %-20s %-15s%n", "Title", "Borrower", "Borrow Date");
            System.out.println("------------------------------------------------");
            for (BorrowedBookRecord record : borrowedBooks) {
                System.out.printf("%-20s %-20s %-15s%n",
                        record.getBookTitle(), record.getBorrowerName(), record.getBorrowDate());
            }
        }
    }

    //Xem danh sách trả sách
    public void viewReturnedBooks() {
        // Similar to the borrowed books method
        System.out.println("Returned Books:");
        if (returnedBooks.isEmpty()) {
            System.out.println("No books have been returned.");
        } else {
            System.out.printf("%-20s %-20s %-15s%n", "Title", "Borrower", "Return Date");
            System.out.println("------------------------------------------------");
            for (ReturnedBookRecord record : returnedBooks) {
                System.out.printf("%-20s %-20s %-15s%n",
                        record.getBookTitle(), record.getBorrowerName(), record.getReturnDate());
            }
        }
    }

    //cập nhật trạng thái các giao dịch mượn sách
    public boolean updateBorrowTransactionStatus() {
        System.out.print("Enter ISBN of the borrowed book to update: ");
        String isbn = scanner.nextLine();

        // Find the transaction by ISBN
        for (BorrowedBookRecord record : borrowedBooks) {
            if (record.getBookIsbn().equals(isbn)) {
                System.out.print("Enter new status (e.g., Returned): ");
                String newStatus = scanner.nextLine();
                record.setStatus(newStatus);
                System.out.println("Transaction status updated successfully.");
                return true;
            }
        }
        System.out.println("No borrowed book found with ISBN: " + isbn);
        return false;
    }


    //cập nhật trạng thái các giao dịch trả sách
    public boolean updateReturnTransactionStatus() {
        System.out.print("Enter ISBN of the returned book to update: ");
        String isbn = scanner.nextLine();

        // Find the return transaction by ISBN
        for (ReturnedBookRecord record : returnedBooks) {
            if (record.getBookIsbn().equals(isbn)) {
                System.out.print("Enter new status (e.g., Available): ");
                String newStatus = scanner.nextLine();
                record.setStatus(newStatus);
                System.out.println("Return transaction status updated successfully.");
                return true;
            }
        }
        System.out.println("No returned book found with ISBN: " + isbn);
        return false;
    }


}