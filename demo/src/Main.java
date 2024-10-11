//Role: Customer, Librarian, Admin
//Mô tả: Trang web sẽ bao gồm các phần chính sau:
//1.Trang chủ: Hiển thị các sách mới, sách nổi bật và các danh mục sách.
//2.Trang sách: Hiển thị chi tiết sách, bao gồm hình ảnh, mô tả, tác giả, số lượng còn lại và đánh giá từ người dùng.
//3.Giỏ sách: Cho phép người dùng xem và quản lý các sách đã chọn để mượn.
//4.Quản lý tài khoản: Người dùng có thể đăng ký, đăng nhập và quản lý thông tin cá nhân.
//5.Quản lý sách: Người quản lý có thể thêm, chỉnh sửa và xóa sách.
//6.Quản lý mượn/trả sách: Theo dõi và cập nhật trạng thái mượn/trả sách.
//7.Quản lý người dùng: Quản lý thông tin và lịch sử mượn sách của người dùng.
// Role và Chức năng
// 1.Customer (khách hang):
//         - Đăng ký tài khoản/Đăng nhập(Log in): Người dùng mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
// - Xem danh sách sách: Xem, hiển thị các sách có sẵn, tìm kiếm sách theo từ khóa hoặc bộ lọc (tác giả, thể loại,…).
//         - Xem chi tiết sách: Xem thông tin chi tiết của sách: tên sách, tác giả, mã sách, nsx, số lượng sách, giá sách, trạng thái sách: trc va sau khi cho mượn.
//         - Thêm sách vào giỏ sách: Lựa chọn số lượng và thêm sách vào giỏ sách để mượn.
//         - Xem giỏ sách: Xem danh sách các sách đã thêm vào giỏ sách, thay đổi số lượng hoặc xóa sách khỏi giỏ sách.
// - Mượn/trả sách: Xác nhận mượn/trả sách và nhập thông tin giao nhận.
//         - Xem lịch sử mượn/trả sách: Xem danh sách các sách đã mượn/trả, bao gồm thông tin chi tiết về từng sách và trạng thái mượn
// - Cập nhật tài khoản cá nhân: Thay đổi thông tin cá nhân như tên, địa chỉ email, mật khẩu.
// - Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.
// 2.Librarian (Người quản lý thư viện):
//         - Đăng ký tài khoản/Đăng nhập: Người quản lý mới tạo tài khoản bằng cách nhập thông tin như tên, email, mật khẩu sau đó đăng nhập bằng tài khoản đã tạo.
//         - Quản lý sách: Thêm mới, chỉnh sửa hoặc xóa sách.
//         - Quản lý mượn/trả sách: Xem danh sách mượn sách, cập nhật trạng thái các giao dịch mượn/trả sách.
//         - Quản lý người dùng: Xem thông tin người dùng và lịch sử mượn sách.
//         - Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống.
// 3.Admin (Quản trị viên):
//         - Đăng nhập hệ thống với quyền Admin.
// - Quản lý người dùng:
//         + Xem danh sách người dùng: Xem danh sách tất cả người dùng và người quản lý trong hệ thống.
//         + Tạo account quản lý: Cấp quyền người quản lý cho tài khoản người dùng mới.
// + Khóa/Mở khóa tài khoản: Khóa tài khoản người dùng hoặc người quản lý vi phạm quy định, mở khóa tài khoản nếu cần.
//         - Thống kê và báo cáo: Thống kê số lượng sách mượn/trả, báo cáo người dùng.
//         - Đăng xuất: Kết thúc phiên làm việc và đăng xuất khỏi hệ thống