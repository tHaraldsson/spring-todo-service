package models;


import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private Long id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;


    public CustomUser() {}

    public CustomUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
