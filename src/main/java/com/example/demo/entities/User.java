package com.example.demo.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;
    @Column(length = 45)
    private String firstName;
    @Column(length = 45)
    private String lastName;
    @Column(length = 128,nullable = false, unique = true)
    private String email;
    @Column(length = 500, nullable = false)
    private String password;
    @Column(length = 45, nullable = false)
    private String city;
    @Column(length = 45, nullable = false)
    private String country;
    @Column(length = 45, nullable = false)
    private String gender;
    @Column(nullable = false)
    private int age;
    @Column(length = 45)
    private String likes;
    @Column(length = 500, nullable = false)
    private String bio;
    @Column(length = 45)
    private String photo;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_account",
            joinColumns = @JoinColumn(name = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "idcccountType")
    )
    private Set<AccountType> accounttype = new HashSet<>();

    public User() {
    }


    public User( String firstName, String lastName, String email, String password, String city, String country, String gender, int age, String likes, String bio, String photo) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.age = age;
        this.likes = likes;
        this.bio = bio;
        this.photo = photo;

    }

    public User(String firstName, String lastName, String email, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;

    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<AccountType> getAccountType() {
        return accounttype;
    }

    public String getAccountTypeName() {

        return this.getAccountTypeName();
    }

    public void setAccountType(Set<AccountType> accounttype) {
        this.accounttype = accounttype;
    }

    public void ajouter(AccountType accounttype0){
        this.accounttype.add(accounttype0);
    }

    public String getFullName() {
        return firstName+" "+lastName;
    }

    public boolean hasAccountType(String roleName) {
        Iterator<AccountType> iterator = this.accounttype.iterator();
        while (iterator.hasNext()) {
            AccountType aaccountType = iterator.next();
            if (aaccountType.getAccountTypeName().equals(roleName)) {
                return true;
            }
        }

        return false;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<AccountType> roles = User.getAccountType();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (AccountType role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getAccountTypeName()));
//        }
//
//        return authorities;
//    }


    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + age + '\'' +
                ", likes='" + likes + '\'' +
                ", bio='" + bio + '\'' +
                ", photo='" + photo + '\'' +
                ", accounttype=" + accounttype +
                '}';
    }
}
