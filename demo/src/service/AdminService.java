// 3.Admin (Quản trị viên):
//- Đăng nhập hệ thống với quyền Admin.
//- Quản lý người dùng:
//+ Xem danh sách người dùng:Tìm kiếm người dùng theo ten(email), Xem danh sách tất cả người dùng và người quản lý trong hệ thống.
//+ Tạo account quản lý: Cấp quyền người quản lý cho tài khoản người dùng mới.
//+ Quản lý trạng thái người dùng(Khóa/Mở khóa tài khoản): Khóa tài khoản người dùng hoặc người quản lý vi phạm quy định, mở khóa tài khoản nếu cần.

//- Thống kê và báo cáo: Thống kê số lượng sách mượn/trả, báo cáo người dùng.
//- Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống
package service;

import constant.Regex;
import constant.Status;
import entities.*;
import enums.Role;
import main.Main;

import util.FileUtil;
import util.InputUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
public class AdminService {
    private List<User> users;
    private List<String> lockedCustomer = new ArrayList<>();

    private static final HashSet<String> lockUserByEmails = new HashSet<>();
    private static final String USER_DATA_FILE = "users.json";
    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";
    private final FileUtil<User> fileUtil = new FileUtil<>();
    private static int AUTO_ID;
    private static final int MAX_LOGIN_TIMES = 5;
    //tim kiem nguoi dung với email
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail() == email) {
                return user;
            }
        }
        return null;
    }
    //tim kiem nguoi dung voi id
    public User findUserById(int idUser) {
        for (User user : users) {
            if (user.getId() == idUser) {
                return user;
            }
        }
        return null;
    }

    //tim kiem nguoi dung với tên
    public User findUserByName(int idUser) {
        for (User user : users) {
            if (user.getId() == idUser) {
                return user;
            }
        }
        return null;
    }

    public User createUserCommonInfo() {
        // TODO - nhập các thông tin cần tạo cho 1 user
        //  (cần chọn quyền của user vì đây là admin tạo user nên admin hoan toàn có thể chọn user họ tạo là một admin hay là 1 user bình thường)
        String email;
        String password;
        String phone;
        String name;
        String address;
        while (true) {
            System.out.println("Nhập email (nhập 'exit' để thoát): ");
            email = new Scanner(System.in).nextLine();
            if (InputUtil.exitInput(email)) {
                return null;
            }
            if (!email.matches(Regex.EMAIL_REGEX)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại đúng định dạng mail: ");
                continue;
            }
            User existedUser = findUserByEmail(email);
            if (existedUser != null) {
                System.out.println("Email đã tồn tại trong hệ thống, vui lòng nhập lại: ");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Nhập mật khẩu (nhập 'exit' để thoát): ");
            password = new Scanner(System.in).nextLine();
            if (InputUtil.exitInput(password)) {
                return null;
            }
            if (!password.matches(Regex.PASSWORD_REGEX)) {
                System.out.println("Password không đúng định dạng vui lòng nhập lại ");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Mời bạn nhập SĐT (đầu 0 và có 9 so tiep theo): ");
            phone = new Scanner(System.in).nextLine();
            if (!phone.matches(Regex.VN_PHONE_REGEX)) {
                System.out.println("Số điện thoại không đúng định dạng , vui lòng nhập lại ");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Mời bạn nhập tên: ");
            name = new Scanner(System.in).nextLine();
            if (!name.matches(".*\\d.*") && !name.isEmpty()) {
                break;
            } else {
                System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
            }
        }
        Role role = null;
        System.out.println("Mời bạn lựa chọn chức năng của người dùng: ");
        System.out.println("1. Admin");
        System.out.println("2. Khách hàng");
        int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                "Chức năng là số dương từ 1 tới 2, vui lòng nhập lại: ", 1,2);
        role = switch (choice) {
            case 1 -> Role.ADMIN;
            case 2 -> Role.CUSTOMER;
            case 3 -> Role.LIBRARIAN;
            default -> role;
        };
        System.out.println("Mời bạn nhập địa chỉ : ");
        address = new Scanner(System.in).nextLine();
        double balance = 0;
        User user = new User(AUTO_ID++, email, password, phone, role, address, balance, name, Status.ACTIVE);
        users.add(user);
        saveUserData();

        return user;
    }

    public void showUsers(List<User> users1) {
        printHeader();
        for (User user : users1) {
            showUserDetail(user);
        }
    }

    public void printHeader() {
        System.out.printf("%-5s%-30s%-30s%-20s%-20s%-10s%-10s%-10s%n", "Id", "Name", "Email", "Phone", "Address", "Role", "Balance", "Status");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    //xem danh sach nguoi dung
    public void showUserDetail(User user) {
        System.out.printf("%-5s%-30s%-30s%-20s%-20s%-10s%-10s%-10s%n", user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getRole(), user.getBalance(), user.getStatus());
    }

    public void updateUserInformation(int idUserUpdate) {
        User user = findUserById(idUserUpdate);
        System.out.println("Mời bạn chọn phần thông tin muốn chỉnh sửa: ");
        System.out.println("1. Email");
        System.out.println("2. Password");
        System.out.println("3. Tên");
        System.out.println("4. Số điện thoại");
        System.out.println("5. Địa chỉ");
        System.out.println("6. Thoát");
        int featureChoice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                "Chức năng là số dương từ 1 tới 6, vui lòng nhập lại: ", 1,6);
        switch (featureChoice) {
            case 1:
                String newEmail;
                while (true) {
                    System.out.println("Mời bạn nhập email mới: ");
                    newEmail = new Scanner(System.in).nextLine();
                    if (!newEmail.matches(Regex.EMAIL_REGEX)) {
                        System.out.println("Email không đúng định dạng vui lòng nhập lại");
                        continue;
                    }
                    boolean coTrungEmailKhong = false;
                    for (User user1 : users) {
                        if (newEmail.equalsIgnoreCase(user1.getEmail()) && user1.getId() != user.getId()) {
                            System.out.println("Email đã tồn tại vui lòng nhập lại");
                            coTrungEmailKhong = true;
                            break;
                        }
                    }
                    if (coTrungEmailKhong == false) {
                        break;
                    }
                }
                user.setEmail(newEmail);
                break;
            case 2:
                String newPassword;
                while (true) {
                    System.out.println("Mới bạn nhập password (8 -> 16 ký tự cả chữ thường, chữ hoa và cả số)");
                    newPassword = new Scanner(System.in).nextLine();
                    if (!newPassword.matches(Regex.PASSWORD_REGEX)) {
                        System.out.println("Password không đúng định dạng vui lòng nhập lại ");
                        continue;
                    }
                    break;
                }
                user.setPassword(newPassword);
                break;
            case 3:
                System.out.println("Mời bạn nhập tên mới: ");
                String newName = new Scanner(System.in).nextLine();
                user.setName(newName);
                break;
            case 4:
                String newPhone;
                while (true) {
                    System.out.println("Mời bạn nhập SĐT (đầu 0 và có 9 so tiep theo): ");
                    newPhone = new Scanner(System.in).nextLine();
                    if (!newPhone.matches(Regex.VN_PHONE_REGEX)) {
                        System.out.println("Số điện thoại không đúng định dạng , vui lòng nhập lại ");
                        continue;
                    }
                    break;
                }
                user.setPhone(newPhone);
                break;
            case 5:
                System.out.println("Mời bạn nhập địa chỉ mới : ");
                String newAddress = new Scanner(System.in).nextLine();
                user.setAddress(newAddress);
                break;
            case 6:
                return;
        }
        showUser(user);
        saveUserData();// FILE - khi có thay đổi về list user, can luu vao file
    }

    public void showUser(User user) {
        printHeader();
        showUserDetail(user);
    }

    public void saveUserData() {
        fileUtil.writeDataToFile(users, USER_DATA_FILE);
    }



    public void createAdmin(String newAdminEmail, String newAdminName) {
        User user = new User(ADMIN_EMAIL, ADMIN_PASSWORD, Role.ADMIN);
        user.setId(0);
        users.add(user);
        saveUserData();
    }

    public void findCurrentAutoId() {
        int maxId = -1;
        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        AUTO_ID = maxId + 1;
    }

    //dnhap nguoi dung
    public User getLoggedInUser() {
        for (User userTemp : users) {
            if (userTemp.getId() == Main.LOGGED_IN_CUSTOMER.getId()) {
                return userTemp;
            }
        }
        return null;
    }

    public void updateUserInformationByAdmin() {
        System.out.println("Mời bạn nhập email tài khoản cần chỉnh sửa thông tin: ");
        String email = new Scanner(System.in).nextLine();
        User user = findUserByEmail(email);
        System.out.println("Mời bạn chọn phần thông tin muốn chỉnh sửa: ");
        System.out.println("1. Email");
        System.out.println("2. Password");
        System.out.println("3. Tên");
        System.out.println("4. Số điện thoại");
        System.out.println("5. Địa chỉ");
        System.out.println("6. Thoát");
        int featureChoice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                "Chức năng là số dương từ 1 tới 6, vui lòng nhập lại: ", 1,6);
        switch (featureChoice) {
            case 1:
                String newEmail;
                while (true) {
                    System.out.println("Mời bạn nhập email mới: ");
                    newEmail = new Scanner(System.in).nextLine();
                    if (!newEmail.matches(Regex.EMAIL_REGEX)) {
                        System.out.println("Email không đúng định dạng vui lòng nhập lại");
                        continue;
                    }
                    boolean coTrungEmailKhong = false;
                    for (User user1 : users) {
                        if (newEmail.equalsIgnoreCase(user1.getEmail()) && user1.getId() != user.getId()) {
                            System.out.println("Email đã tồn tại vui lòng nhập lại");
                            coTrungEmailKhong = true;
                            break;
                        }
                    }
                    if (coTrungEmailKhong == false) {
                        break;
                    }
                }
                user.setEmail(newEmail);
                break;
            case 2:
                String newPassword;
                while (true) {
                    System.out.println("Mới bạn nhập password (8 -> 16 ký tự cả chữ thường, chữ hoa và cả số)");
                    newPassword = new Scanner(System.in).nextLine();
                    if (!newPassword.matches(Regex.PASSWORD_REGEX)) {
                        System.out.println("Password không đúng định dạng vui lòng nhập lại ");
                        continue;
                    }
                    break;
                }
                user.setPassword(newPassword);
                break;
            case 3:
                System.out.println("Mời bạn nhập tên mới: ");
                String newName = new Scanner(System.in).nextLine();
                user.setName(newName);
                break;
            case 4:
                String newPhone;
                while (true) {
                    System.out.println("Mời bạn nhập SĐT (đầu 0 và có 9 so tiep theo): ");
                    newPhone = new Scanner(System.in).nextLine();
                    if (!newPhone.matches(Regex.VN_PHONE_REGEX)) {
                        System.out.println("Số điện thoại không đúng định dạng , vui lòng nhập lại ");
                        continue;
                    }
                    break;
                }
                user.setPhone(newPhone);
                break;
            case 5:
                System.out.println("Mời bạn nhập địa chỉ mới : ");
                String newAddress = new Scanner(System.in).nextLine();
                user.setAddress(newAddress);
                break;
            case 6:
                return;
        }
        showUser(user);
        saveUserData();

    }

    public void showBalance() {
        User user = getLoggedInUser();
        System.out.println("Số dư tài khoản của khách hàng là " + user.getBalance());
    }

    public void transactionHistory() {
    }

    public void lockedUserById(int idUserLock) {

        for (User user : users) {
            if (user.getId() == idUserLock) {
                user.setStatus(Status.INACTIVE);
                System.out.println("User có ID trên đã được khóa");
                printHeader();
                showUserDetail(user);
                saveUserData();
                break;
            }
        }
    }

    public void unlockedUserById(int idUserLock) {
        for (User user : users) {
            if (user.getId() == idUserLock) {
                user.setStatus(Status.ACTIVE);
                System.out.println("User có ID trên đã được mở khóa");
                saveUserData();
                printHeader();
                showUserDetail(user);
                break;
            }
        }
    }

    public void updateUserBalance(int idUser, double amount) {
        for (User user : users) {
            if (user.getId() == idUser) {
                user.setBalance(user.getBalance() + amount);
                System.out.println("Số dư tài khoản của quý khách là " + user.getBalance());
                saveUserData();// FILE - khi có thay đổi về list user, can luu vao file
                return;
            }
        }
    }

}


