// 2.Librarian (Người quản lý thư viện):
//- Đăng ký tài khoản/Đăng nhập: Người quản lý mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
//- Quản lý sách: Thêm mới, chỉnh sửa hoặc xóa sách.
//- Quản lý mượn/trả sách: Xem danh sách mượn/trả sách, cập nhật trạng thái các giao dịch mượn/trả sách.
//- Quản lý người dùng: Xem thông tin người dùng và lịch sử mượn/trả sách, cập nhật thông tin người dùng và lịch sử mượn/trả sách.
//- Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.

package view;
import entities.Book;
import service.AccountService;
import service.LibrarianService;
import util.InputUtil;
import java.util.List;
public class LibrarianMenu
{
    private final LibrarianService librarianService;
    private final MainMenu mainMenu;

    public LibrarianMenu(LibrarianService librarianService, MainMenu mainMenu) {
        this.librarianService = librarianService;
        this.mainMenu = mainMenu;
    }
    public void menu(){
        while (true)
        {
            System.out.println("------- PHẦN MỀM QUẢN LÝ THƯ VIỆN--------");
            System.out.println("------------------ QUẢN LÝ PHÒNG THƯ VIỆN------------------");
            System.out.println("1. Quản lý sách");
            System.out.println("2. Quản lý mượn sách");
            System.out.println("3. Quản lý trả sách");
            System.out.println("4. Quản lý người dùng: ");
            System.out.println("5. Thoát ");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 5, vui lòng nhập lại: ", 1, 4);
            switch (choice) {
                case 1:

                    //Quản lý sách
                    managerbook();
                    break;
                case 2:
                    //Quản lý mượn sách
                    managerborrowbook();
                    break;
                case 3:
                    //Quản lý trả sách
                    managerreturnbook();
                case 4:
                    //Quản lý người dùng
                    manageruser();
                    break;
                case 5:
                    //Thoát
                    mainMenu.mainmenu(AccountService.user);
                    return;
            }
        }
    }
    //Quản lý sách

    public void managerbook(){
        while (true)
        {
            System.out.println("------- Quản lý sách--------");
            System.out.println("1. Thông tin sách");
            System.out.println("2. Thêm sách");
            System.out.println("3. Sửa thông tin sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Thoát ");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 5, vui lòng nhập lại: ", 1, 5);
            switch (choice) {
                case 1:
                    //Thông tin sách
                    librarianService.getBooks();
                    break;
                case 2:

                    //Thêm sách
                    librarianService.addBook();
                    break;
                case 3:
                    //Sửa thông tin sách
                    librarianService.updateBook();
                    break;
                case 4:
                    //Xóa sách
                    librarianService.deleteBook();
                    break;
                case 5:

                    //Thoát
                    menu();
                    return;
            }
        }
    }
    // Quản lý mượn sách
    public void managerborrowbook()
    {
        while (true)
        {

            System.out.println("------- Quản lý mượn sách--------");
            System.out.println("1. Xem danh sách mượn sách");
            System.out.println("2. Cập nhật trạng thái các giao dịch mượn sách");
            System.out.println("3. Thoát ");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 3, vui lòng nhập lại: ", 1, 3);
            switch (choice) {
                case 1:
                    //Xem danh sách mượn sách
                    break;
                case 2:
                    //Cập nhật trạng thái các giao dịch mượn sách
                    break;
                case 3:
                    //Thoát
                    menu();
                    return;
            }
        }
    }

    //Quản lý trả sách
    private void managerreturnbook() {
        while (true) {

            System.out.println("------- Quản lý trả sách--------");
            System.out.println("1. Xem danh sách trả sách ");
            System.out.println("2. Cập nhật trạng thái các giao dịch trả sách");
            System.out.println("3. Thoát ");
            int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                    "Chức năng là số dương từ 1 tới 3, vui lòng nhập lại: ", 1, 3);
            switch (choice) {
                case 1:
                    //Xem danh sách trả sách
                    break;
                case 2:

                    //Cập nhật trạng thái các giao dịch trả sách
                    break;
                case 3:
                    //Thoát
                    menu();
                    return;
            }
            //Quản lý người dùng
            public void manageruser(){
                while (true) {
                    System.out.println("------- Quản lý người dùng--------");
                    System.out.println("1. Thông tin người dùng");
                    System.out.println("2. Thêm sách");
                    System.out.println("3. Sửa thông tin sách");
                    System.out.println("4. Xóa sách");
                    System.out.println("5. Thoát ");
                    int choice = InputUtil.chooseOption("Xin mời chọn chức năng: ",
                            "Chức năng là số dương từ 1 tới 5, vui lòng nhập lại: ", 1, 5);
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
                            menu();
                            return;
                    }
                }
            }
        }
    }



