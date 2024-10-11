// 1.Customer (khách hang):
// - Đăng ký tài khoản/Đăng nhập(Log in): Người dùng mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
// - Xem danh sách sách: Xem, hiển thị các sách có sẵn, tìm kiếm sách theo từ khóa hoặc bộ lọc (tác giả, thể loại,…).
// - Xem chi tiết sách: Xem thông tin chi tiết của sách: tên sách, tác giả, mã sách, nsx, số lượng sách, giá sách, trạng thái sách: trc va sau khi cho mượn.
//- Thêm sách vào giỏ sách: Lựa chọn số lượng và thêm sách vào giỏ sách để mượn.
// - Xem giỏ sách: Xem danh sách các sách đã thêm vào giỏ sách, thay đổi số lượng hoặc xóa sách khỏi giỏ sách.
// - Mượn/trả sách: Xác nhận mượn/trả sách và nhập thông tin giao nhận.
// - Xem lịch sử mượn/trả sách: Xem danh sách các sách đã mượn/trả, bao gồm thông tin chi tiết về từng sách và trạng thái mượn
// - Cập nhật tài khoản cá nhân: Thay đổi thông tin cá nhân như tên, địa chỉ email, mật khẩu.
// - Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.

package view;

import service.*;
import util.InputUtil;

public class CustomerMenu {
    private final LibrarianService librarianService;
    private final CustomerService customerService;
    private final MainMenu mainMenu;  // Thay đổi để không khởi tạo MainMenu

    public CustomerMenu(CustomerService customerService, MainMenu mainMenu) {
        this.customerService = customerService;
        this.librarianService = librarianService;
        this.mainMenu = mainMenu;  // Nhận MainMenu từ tham số
    }

    public void menu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("1. Xem danh sách sách");
            System.out.println("2. Xem chi tiết sách");
            System.out.println("3. Thêm sách vào giỏ sách");
            System.out.println("4. Xem giỏ sách");
            System.out.println("5. Mượn sách");
            System.out.println("6. Trả sách");
            System.out.println("7. Xem lịch sử mượn sách");
            System.out.println("8. Xem lịch sử trả sách");
            System.out.println("9. Cập nhật tài khoản cá nhân");
            System.out.println("10. Đăng xuất");


            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 10 vui lòng nhập lại: ", 1, 10);

            switch (choice) {
                case 1:
                    // Xem danh sách sách
                    librarianService.getBooks();
                    break;
                case 2:
                    // Xem chi tiết sách
                    customerService.viewBookDetails();
                    break;
                case 3:
                    // Thêm sách vào giỏ sách
                    customerService.addBookToCart();
                    break;
                case 4:
                    // Xem giỏ sách
                    customerService.viewCart();
                    break;
                case 5:
                    // Mượn sách
                    customerService.borrowBooks();
                    break;
                case 6:
                    //trả sách
                    customerService.returnBooks();
                case 7:
                    // Xem lịch sử mượn sách
                    customerService.viewBorrowedBooks();
                    break;
                case 8:
                    //Xem lịch sử trả sách
                    customerService.viewReturnBooks();

                case 9:
                    // Cập nhật tài khoản cá nhân
                    break;
                case 10:
                    //Đăng xuất
                    mainMenu.mainmenu(AccountService.user);
                    return;
            }
        }
    }
}