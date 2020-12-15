package com.cardoholic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Users.
 */
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "device_type")
    private Integer deviceType;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "balance")
    private String balance;

    @OneToOne
    @JoinColumn(unique = true)
    private Basket basket;

    @OneToMany(mappedBy = "users")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Orders> orders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Countries countries;

    @ManyToOne
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private UserType userType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Users email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Users password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public Users fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Users imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Users phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Users createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Users isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public Users deviceType(Integer deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public Users deviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getBalance() {
        return balance;
    }

    public Users balance(String balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Basket getBasket() {
        return basket;
    }

    public Users basket(Basket basket) {
        this.basket = basket;
        return this;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public Users orders(Set<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public Users addOrders(Orders orders) {
        this.orders.add(orders);
        orders.setUsers(this);
        return this;
    }

    public Users removeOrders(Orders orders) {
        this.orders.remove(orders);
        orders.setUsers(null);
        return this;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Countries getCountries() {
        return countries;
    }

    public Users countries(Countries countries) {
        this.countries = countries;
        return this;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public UserType getUserType() {
        return userType;
    }

    public Users userType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Users)) {
            return false;
        }
        return id != null && id.equals(((Users) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Users{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", deviceType=" + getDeviceType() +
            ", deviceToken='" + getDeviceToken() + "'" +
            ", balance='" + getBalance() + "'" +
            "}";
    }
}
