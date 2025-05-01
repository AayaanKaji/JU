package Database;

public class User {
    private String loginName, password, email, firstName, lastName, phone;

    public User(String loginName, String password, String email,
            String firstName, String lastName, String phone) {
        this.loginName = loginName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
