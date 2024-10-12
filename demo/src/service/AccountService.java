package service;

import entities.Account;
import entities.User;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountService {
    public static User user;
    private List<Account> accounts; // Danh sách tài khoản
    private static final String ACCOUNT_DATA_FILE = "accounts.json"; // Tên file để lưu tài khoản
    private final FileUtil<Account> fileUtil = new FileUtil<>();
// Đối tượng để thao tác với file
    private final Scanner scanner = new Scanner(System.in); // Đối tượng để nhập từ bàn phím

    public AccountService() {
        this.accounts = new ArrayList<>(); // Khởi tạo danh sách tài khoản
        loadAccounts(); // Tải tài khoản từ file khi khởi tạo
    }

    private void loadAccounts() {
        List<Account> loadedAccounts = fileUtil.readDataFromFile(ACCOUNT_DATA_FILE,Account.class);
        if (loadedAccounts != null) {
            accounts = loadedAccounts;
        }
        else {
            accounts = new ArrayList<>(); // Nếu không có tài khoản, khởi tạo danh sách trống
        }
    }

    // Đăng ký tài khoản mới
    public User register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Kiểm tra trùng tên người dùng
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose another.");
                return null;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Tạo tài khoản mới
        Account newAccount = new Account(username, password, 0.0);
        accounts.add(newAccount);
        saveAccountData(); // Lưu danh sách tài khoản
        System.out.println("Account registered successfully: " + newAccount);
        return null;
    }

    // Đăng nhập vào tài khoản
    public Account login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return account; // Trả về tài khoản đã đăng nhập
            }
        }
        System.out.println("Invalid username or password.");
        return null; // Không tìm thấy tài khoản
    }

    // Kiểm tra số dư tài khoản
    public void checkBalance(Account account) {
        System.out.printf("Your current balance is: %.2f%n", account.getBalance());
    }

    // Cập nhật số dư tài khoản
    public void updateBalance(Account account) {
        System.out.print("Enter new balance: ");
        double newBalance;
        while (true) {
            try {
                newBalance = Double.parseDouble(scanner.nextLine());
                account.setBalance(newBalance);
                saveAccountData(); // Lưu dữ liệu sau khi cập nhật
                System.out.println("Balance updated successfully.");
                return;
            } catch (NumberFormatException e) {
                System.out.println("Invalid balance. Please enter a valid number.");
            }
        }
    }

    // Lưu danh sách tài khoản vào file
    private void saveAccountData() {
        fileUtil.writeDataToFile(accounts, ACCOUNT_DATA_FILE); // Lưu toàn bộ danh sách tài khoản
    }

    public void setUsers() {
    }

    public void createDefaultAdminUser() {
    }

    public void findCurrentAutoId() {
    }
}
