package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.Users} entity.
 */
public class UsersDTO implements Serializable {
    
    private Long id;

    private String email;

    private String password;

    private String fullName;

    private String imagePath;

    private String phoneNumber;

    private String createdDate;

    private Boolean isActive;

    private Integer deviceType;

    private String deviceToken;

    private String balance;


    private Long basketId;

    private Long countriesId;

    private Long userTypeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public Long getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(Long countriesId) {
        this.countriesId = countriesId;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsersDTO)) {
            return false;
        }

        return id != null && id.equals(((UsersDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UsersDTO{" +
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
            ", basketId=" + getBasketId() +
            ", countriesId=" + getCountriesId() +
            ", userTypeId=" + getUserTypeId() +
            "}";
    }
}
