package com.umd.hrm.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="user", schema = "public")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="user_email", nullable=false, unique=true)
    private String email;
    @Column(name="user_address")
    private String address;
    private String department;

    @Column(name="user_name", nullable=false, unique=true)
    private String userName;

    @Column(name="user_password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_mobile", unique=true)
    private Long mobileNo;

    Boolean locked;

    Boolean enabled;


    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "failed_attempt")
    private Long failedAttempt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountRole accountRole;



    public Account(String email,
                   String address,
                   String department,
                   String userName,
                   String password,
                   String firstName,
                   String lastName,
                   Long mobileNo,
                   Boolean locked,
                   Boolean enabled) {
        this.email = email;
        this.address = address;
        this.department = department;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.locked = locked;
        this.enabled = enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("");
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {return userName;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return locked;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return enabled;}

    public String getEmail() {return email;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}
}
