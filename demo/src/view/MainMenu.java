package view;
import enums.Role;
import entities.*;
import service.AccountService;
import service.AdminService;
import service.CustomerService;

import service.LibrarianService;
import util.InputUtil;
public class MainMenu {
    private final CustomerService customerService = new CustomerService();
    private final AccountService accountService = new AccountService();
    private final AdminService adminService = new AdminService();
    private final LibrarianService librarianService = new LibrarianService();
    private final AdminMenu adminMenu ;
    private final CustomerMenu customerMenu ;  // Chỉ khai báo CustomerMenu
    private final LibrarianMenu librarianMenu ;

    public MainMenu() {
        this.customerMenu = new CustomerMenu(customerService,this);
        this.adminMenu = new AdminMenu(adminService,this);
        this.librarianMenu = new LibrarianMenu(librarianService,this);
    }

    public void menu() {
        while (true) {
            System.out.println("==================================================================");
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VỆN--------");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Thoát");

            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 3, vui lòng nhập lại: ", 1, 3);

            switch (choice) {
                case 1:
                    User loggedInCustomer = accountService.login();
                    if (loggedInCustomer == null) {
                        System.out.println("Login failed.");
                    } else {
                        mainmenu(loggedInCustomer);
                    }
                    break;
                case 2:
                    User registeredUser = accountService.register();
                    if (registeredUser == null) {
                        System.out.println("Dừng đăng ký tài khoản!!!");
                        break;
                    }
                    System.out.println("Đăng ký tài khoản thành công, vui lòng đăng nhập để tiếp tục sử dụng phần mềm.");
                    break;
                case 3:
                    return;
            }
        }
    }
    // Xử lý Trang chủ
    public void mainmenu(User user) {
        System.out.println("==================================================================");
        System.out.println("1.Trang chủ");
        System.out.println("2.Trang sách");
        System.out.println("3.Giỏ sách");
        System.out.println("4. Chức năng cơ bản");

        if (user.getRole().equals(Role.LIBRARIAN) || user.getRole().equals(Role.ADMIN)) {
            System.out.println("5. Chức năng librarian");
        } else {
            System.out.println("5. Chức năng này đang khóa");
        }

        if (user.getRole().equals(Role.ADMIN)) {
            System.out.println("6. Chức năng admin");
        } else {
            System.out.println("6. Chức năng này đang khóa");
        }

        System.out.println("7. Thoát");
        int choice = InputUtil.chooseOption("Xin mời chọn chức năng", "Vui lòng nhập lại: ", 1, 7);

        switch (choice) {
            case 1:
                // Xử lý Trang chủ
                mainmenu(AccountService.user);
                break;
            case 2:
                // Xử lý Trang sách
                managerbook();
                break;
            case 3:
                // Xử lý Giỏ sách
                bookcart();
                break;
            case 4:
                customerMenu.menu();
                break;
            case 5:
                if (!(user.getRole().equals(Role.LIBRARIAN) || user.getRole().equals(Role.ADMIN))) {
                    System.out.println("Bạn phải là librarian hoặc admin");
                    mainmenu(AccountService.user);
                    break;
                }
                librarianMenu.menu();
                break;
            case 6:
                if (!user.getRole().equals(Role.ADMIN)) {
                    System.out.println("Bạn phải là admin");
                    mainmenu(AccountService.user);
                    break;
                }
                adminMenu.menu();
                break;
            case 7:
                return;
        }
    }

    public void initializeData() {
        accountService.setUsers();
        accountService.createDefaultAdminUser();
        accountService.findCurrentAutoId();
    }
    // Xử lý Trang sách
    public void managerbook(){
        System.out.println("====================");
        System.out.println("");
    }
    // Xử lý Giỏ sách
    public void bookcart(){
        System.out.println("=====================");
        System.out.println("");
    }
}