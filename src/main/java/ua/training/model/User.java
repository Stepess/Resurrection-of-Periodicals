package ua.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "{username.required}")
    @Size(min = 5, max = 16, message = "{username.size}")
    private String username;

    @Column
    @NotBlank(message = "{password.required}")
    @Size(min = 5, max = 25, message = "{password.size}")
    private String password;

    @Column(name = "first_name")
    @NotBlank(message = "{firstName.required}")
    @Size(min = 2, max = 30, message = "{firstName.size}")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "{lastName.required}")
    @Size(min = 2, max = 30, message = "{lastName.size}")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.valid}")
    private String email;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User(String username, String password, String email, String firstName, String lastName) {
        this(null, username, password, email, firstName, lastName, LocalDate.now());
    }
}
