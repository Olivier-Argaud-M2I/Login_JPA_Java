package fr.m2i.login.models;

import fr.m2i.login.utils.Encrypt;

import javax.persistence.*;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name="selectAllUser", query="SELECT user FROM User user"),
        @NamedQuery(name="findUserById", query="SELECT user FROM User user WHERE id = :id"),
        @NamedQuery(name="findUserByUsername", query="SELECT user FROM User user WHERE username = :username"),
        @NamedQuery(name="deleteUserById",query ="DELETE FROM User WHERE id = :id"),
        @NamedQuery(name="updateUser",query="UPDATE User SET username = :username,password = :password")
})
@NamedNativeQueries({
        @NamedNativeQuery(name="addUser", query="INSERT INTO User(username,password) VALUES(:username,:password)",resultClass = User.class )

})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name="username")
    private String username;

    @Basic
    @Column(name="password")
    private String password;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Encrypt.encryptPassword(password);
    }


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        setPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
