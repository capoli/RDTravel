package com.realdolmen.rdtravel.domain;

import com.realdolmen.rdtravel.util.PasswordUtil;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Basic(optional = false)
    private String name;

    /**
     * ALWAYS use the setter to alter the password.
     */
    @Basic(optional = false)
    private String password;

    public User() {
    }

    public User(String name, String password) {
        setName(name);
        setHashedPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * ALWAYS use the setter.
     * Otherwise the user password will be saved unhashed
     *
     * @param password the password that will be saved as a hash
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String password) {
        if (password != null) {
            this.password = PasswordUtil.getPasswordHash(password);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
