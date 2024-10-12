// 3.Admin (Quản trị viên):
//- Đăng nhập hệ thống với quyền Admin.
//- Quản lý người dùng:
//+ Xem danh sách người dùng:Tìm kiếm người dùng theo ten(email), Xem danh sách tất cả người dùng và người quản lý trong hệ thống.
//+ Tạo account quản lý: Cấp quyền người quản lý cho tài khoản người dùng mới.
//+ Quản lý trạng thái người dùng(Khóa/Mở khóa tài khoản): Khóa tài khoản người dùng hoặc người quản lý vi phạm quy định, mở khóa tài khoản nếu cần.
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

    //Đăng nhập hệ thống với quyền Admin.
    public void menu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("1. Quản lý người dùng");
            System.out.println("2. Thống kê, báo cáo doanh thu");
            System.out.println("3. Đăng xuất");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 3 vui lòng nhập lại: ", 1,3);

            switch (choice) {
                case 1:
                    //Quản lý người dùng
                    UserManagementMenu();
                    break;
                case 2:
                    //Thống kê, báo cáo doanh thu
                    Statisticreport();
                    break;
                case 3:
                    //Đăng xuất
                    mainMenu.mainmenu(AccountService.user);
                    return;
            }
        }
    }

    //Quản lý người dùng
    private void UserManagementMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ NGƯỜI DÙNG ------------------");
            System.out.println("1. Xem danh sách người dùng");
            System.out.println("2. Tạo account quản lý");
            System.out.println("3. Khóa tài khoản");
            System.out.println("4. Mở khóa tài khoản");
            System.out.println("5. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 5 vui lòng nhập lại: ", 1,5);

            switch (choice) {
                case 1:
                    //Xem danh sách người dùng

                    break;
                case 2:
                    //Tạo account quản lý

                    break;
                case 3:
                    //Quản lý trạng thái người dùng

                    break;
                case 4:
                    //Thoát

                    mainMenu.mainmenu(AccountService.user);
                    return;
            }
        }
    }

    //Thống kê và báo cáo
    private void Statisticreport() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ THỐNG KÊ VA BÁO CÁO ------------------");
            System.out.println("1. Thống kê số lượng sách mượn ");
            System.out.println("2. Thống kê số lượng sách trả");
            System.out.println("3. Báo cáo người dùng");
            System.out.println("4. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 4, vui lòng nhập lại: ", 1,4);
            switch (choice) {
                case 1:
                    //Thống kê số lượng sách mượn

                    break;
                case 2:
                    //Thống kê số lượng sách trả

                    break;
                case 3:
                    //Báo cáo người dùng

                    break;
                case 4:
                    //Thoát

                    return;
            }
        }
    }

    //Xem danh sách người dùng
    private void userManagementMenu() {
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ XEM DANH SÁCH NGƯỜI DÙNG ------------------");
            System.out.println("1. Tìm kiếm người dùng theo email");
            System.out.println("2. Tìm kiếm người dùng theo tên");
            System.out.println("3. Xem danh sách tất cả người dùng trong hệ thống");
            System.out.println("4. Xem danh sách tất cả người quản lý trong hệ thống");
            System.out.println("5. Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương từ 1 tới 5, vui lòng nhập lại: ", 1,5);
            switch (choice) {
                case 1:
                    //Tìm kiếm người dùng theo email
                    System.out.println("Mời bạn nhập Email của User muốn tìm: ");
                    String email = new Scanner(System.in).nextLine();
                    adminService.findUserByEmail(email);
                    break;
                case 2:
                    //Tìm kiếm người dùng theo tên
                    System.out.println("Mời bạn nhập tên của User muốn tìm:");
                    int name = new Scanner(System.in).nextInt();
                    adminService.createUserCommonInfo();
                    break;
                case 3:
                    //Xem danh sách tất cả người dùng trong hệ thống

                    break;
                case 4:
                    //Xem danh sách tất cả người quản lý trong hệ thống
                    statusUserManagementMenu();

                    break;
                case 5:
                    //Thoát
                    adminService.transactionHistory();
                    return;

            }
        }
    }
    //Tạo account quản lý
    private void manageraccount(){
        while (true){
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("1.Cấp quyền người quản lý cho tài khoản người dùng mới.");
            System.out.println("2.Thoát");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    "Chức năng là số dương tu 1 đến 2, vui lòng nhập lại: ",1,2);
            switch (choice){
                case 1:
                    System.out.println("Mời bạn nhập thông tin tài khoản quản lý mới.");
                    System.out.print("Email: ");
                    String newAdminEmail = new Scanner(System.in).nextLine();
                    System.out.print("Tên: ");
                    String newAdminName = new Scanner(System.in).nextLine();
                    adminService.createAdmin(newAdminEmail, newAdminName);
                    System.out.println("Tài khoản quản lý đã được tạo.");
                    break;

            }
        }


    }
    //Quản lý trạng thái người dùng
    private void statusUserManagementMenu() {
        User user;
        int idUserLock;
        while (true) {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN --------");
            System.out.println("------------------ QUẢN LÝ TRẠNG THÁI NGƯỜI DÙNG ------------------");
            System.out.println("1. Khóa trạng thái hoạt động của người dùng");
            System.out.println("2. Mở khóa trạng thái hoạt động của người dùng");
            System.out.println("Xem danh sách người dùng: Xem danh sách tất cả người dùng và người quản lý trong hệ thống.");
            System.out.println("3. Thoát");
            int functionChoice = InputUtil.chooseOption("Xin mời chọn chức năng",
                    " Chức năng là số dương từ 1 tới 3, vui lòng nhập lai: ",
                    1,3);
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
}



