package enums;

public enum Role {
    ADMIN("Admin"),
    LIBRARIAN("Librarian"),
    CUSTOMER("Customer");

    private final String roleName;

    public String getRoleName() {
        return roleName;
    }

    Role(String roleName) {
        this.roleName = roleName;
    }


}

