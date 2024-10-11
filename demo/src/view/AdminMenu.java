// 3.Admin (Quản trị viên):
//- Đăng nhập hệ thống với quyền Admin.
// - Quản lý người dùng:
//+ Xem danh sách người dùng: Xem danh sách tất cả người dùng và người quản lý trong hệ thống.
//+ Tạo account quản lý: Cấp quyền người quản lý cho tài khoản người dùng mới.
//+ Khóa/Mở khóa tài khoản: Khóa tài khoản người dùng hoặc người quản lý vi phạm quy định, mở khóa tài khoản nếu cần.
//- Thống kê và báo cáo: Thống kê số lượng sách mượn/trả, báo cáo người dùng.
//- Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống
package view;
import entities.*;
import service.AccountService;
import service.AdminService;
import util.InputUtil;
import java.util.InputMismatchException;

import java.util.Scanner;
public class AdminMenu {
    private final AdminService adminService;
    private final MainMenu mainMenu;
    public AdminMenu(AdminService adminService, MainMenu mainMenu) {
        this.adminService = adminService;
        this.mainMenu = mainMenu;
    }
    public void menu() {
        while (true)
        {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("1. Quản lý người dùng");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Quản lý mượn sách");
            System.out.println("4. Quản lý trả sách");
            System.out.println("5. Thống kê, báo cáo doanh thu");
            System.out.println("6. Đăng xuất");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 6 vui lòng nhập lại: ", 1, 6);

            switch (choice) {
                case 1:
                    //
                    userManagementMenu();
                    break;
                case 2:
                    //
                    bookMenu();
                    break;
                case 3:
                    //
                    borrowbookMenu();
                    break;
                case 4:
                    //
                    returnbookMenu();
                    break;
                case 5:
                    //
                    Statisticreport();
                    break;
                case 6:
                    mainMenu.mainmenu(AccountService.user);
                    return;
            }
        }
    }

    private void showtimeMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ GIỜ BÁN SÁCH------------------");
            System.out.println("1. Thêm sách:");
            System.out.println("2. Câp nhật, sửa dổi thông tin sách:");
            System.out.println("3. Xóa sách: ");
            System.out.println("4. In danh sách sách");
            System.out.println("5. Thoát: ");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 5, vui lòng nhập lại: ", 1, 4);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    return;

            }
        }
    }


    private void bookMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ DANH SÁCH SÁCH ------------------");
            System.out.println("1. Tìm kiếm sách theo tên");
            System.out.println("2. Tìm kiếm sách theo danh mục sách");
            System.out.println("3. Quản lý danh mục sách");
            System.out.println("4. Thêm mới sách");
            System.out.println("5. Cập nhật thông tin sách");
            System.out.println("6. Danh sách các sách đang bán");
            System.out.println("7. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 7, vui lòng nhập lại: ", 1, 7);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    showCategoryManagementMenu();
                    break;
                case 4:
                    break;
                case 5:

                    break;
                case 6:
                    break;
                case 7:
                    return;
            }
        }
    }

    private void borrowbookMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ DANH SÁCH SÁCH ------------------");
            System.out.println("1. Tìm kiếm sách theo tên");
            System.out.println("2. Tìm kiếm sách theo danh mục sách");
            System.out.println("3. Quản lý danh mục sách");
            System.out.println("4. Thêm mới sách");
            System.out.println("5. Cập nhật thông tin sách");
            System.out.println("6. Danh sách các sách đang bán");
            System.out.println("7. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 7, vui lòng nhập lại: ", 1, 7);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    showCategoryManagementMenu();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
            }
        }
    }

    private void Statisticreport() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ DANH SÁCH SÁCH ------------------");
            System.out.println("1. Tìm kiếm sách theo tên");
            System.out.println("2. Tìm kiếm sách theo danh mục sách");
            System.out.println("3. Quản lý danh mục sách");
            System.out.println("4. Thêm mới sách");
            System.out.println("5. Cập nhật thông tin sách");
            System.out.println("6. Danh sách các sách đang bán");
            System.out.println("7. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 7, vui lòng nhập lại: ", 1, 7);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    showCategoryManagementMenu();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
            }
        }
    }

    private void userManagementMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ DANH SÁCH NGƯỜI DÙNG ------------------");
            System.out.println("1. Tìm kiếm người dùng theo email");
            System.out.println("2. Tạo mới tài khoản người dùng");
            System.out.println("3. Cập nhật thông tin người dùng");
            System.out.println("4. Quản lý hoạt động người dùng");
            System.out.println("5. Lịch sử đặt sách của người dùng");
            System.out.println("6. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 6, vui lòng nhập lại: ", 1, 6);
            switch (choice) {
                case 1:
                    System.out.println("Mời bạn nhập Email của User muốn tìm: ");
                    String email = new Scanner(System.in).nextLine();
                    adminService.findUserByMail(email);
                    break;
                case 2:
                    adminService.createUserCommonInfo();
                    break;
                case 3:
                    int idUserUpdate;
                    while (true) {
                        try {
                            System.out.println("Mời bạn nhập ID của User muốn update ");
                            idUserUpdate = new Scanner(System.in).nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Giá trị bạn vừa nhập không phải là một số nguyên. Vui lòng nhập lại.");
                            continue;
                        }
                        break;
                    }
                    adminService.updateUserInformation(idUserUpdate);
                    break;
                case 4:
                    statusUserManagementMenu();

                    break;
                case 5:
                    adminService.transactionHistory();
                    break;
                case 6:
                    return;

            }
        }
    }

    private void statusUserManagementMenu() {
        User user;
        int idUserLock;
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ TRẠNG THÁI NGƯỜI DÙNG ------------------");
            System.out.println("1. Khóa trạng thái hoạt động của người dùng");
            System.out.println("2. Mở khóa trạng thái hoạt động của người dùng");
            System.out.println("Xem danh sách người dùng: Xem danh sách tất cả người dùng và người quản lý trong hệ thống.");
            //+ Tạo account quản lý: Cấp quyền người quản lý cho tài khoản người dùng mới.

            System.out.println("3. Thoát");
            int functionChoice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    " Chức năng là số dương từ 1 tới 3, vui lòng nhập lai: ",
                    1, 3);
            switch (functionChoice) {
                case 1:
                    while (true) {
                        try {
                            System.out.println("Mời bạn nhập ID của User muốn khóa ");
                            idUserLock = new Scanner(System.in).nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Giá trị bạn vừa nhập không phải là một số nguyên. Vui lòng nhập lại.");
                            continue;
                        }
                        user = adminService.findUserById(idUserLock);
                        if (user == null) {
                            System.out.print("Thông tin không chính xác , vui lòng nhập lại : ");
                            continue;
                        }
                        break;
                    }
                    adminService.lockedUserById(idUserLock);
                    break;
                case 2:
                    while (true) {
                        try {
                            System.out.println("Mời bạn nhập ID của User muốn khóa ");
                            idUserLock = new Scanner(System.in).nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Giá trị bạn vừa nhập không phải là một số nguyên. Vui lòng nhập lại.");
                            continue;
                        }
                        user = adminService.findUserById(idUserLock);
                        if (user == null) {
                            System.out.print("Thông tin không chính xác , vui lòng nhập lại : ");
                            continue;
                        }
                        break;
                    }
                    adminService.unlockedUserById(idUserLock);
                    break;
                case 3:
                    return;
            }
        }
    }

    public void showCategoryManagementMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ THỂ LOẠI SÁCH ------------------");
            System.out.println("1. Thêm thể loại sách mới");
            System.out.println("2. Sửa thông tin thể loại sách:");
            System.out.println("3. Xóa thể loại sách:");
            System.out.println("4. Xem các thể loại sách đang có:  ");
            System.out.println("5. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    " Chức năng là số dương từ 1 tới 5, vui lòng nhập lai: ",
                    1, 5);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
            }
        }
    }
}





