package ua.training.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    private Long id;

    @NotNull
    @Size(min=5, max=16, message="{username.size}")
    private String username;
    @NotNull
    @Size(min=5, max=25, message="{password.size}")
    private String password;
    @NotNull
    @Size(min=2, max=30, message="{firstName.size}")
    private String firstName;
    @NotNull
    @Size(min=2, max=30, message="{lastName.size}")
    private String lastName;
    @NotNull
    @Email(message="{email.valid}")
    private String email;
    public User() {
    }

    public User(Long id, String username, String password, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username, String password, String email, String firstName, String lastName) {
        this(null, username, password, email, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
