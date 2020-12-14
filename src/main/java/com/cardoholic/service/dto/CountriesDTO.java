package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.Countries} entity.
 */
public class CountriesDTO implements Serializable {
    
    private Long id;

    private String countryNameEn;

    private String countryNameAr;

    private String flagImagePath;

    private String currencyNameEn;

    private String currencyNameAr;

    private String exchangeRate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getFlagImagePath() {
        return flagImagePath;
    }

    public void setFlagImagePath(String flagImagePath) {
        this.flagImagePath = flagImagePath;
    }

    public String getCurrencyNameEn() {
        return currencyNameEn;
    }

    public void setCurrencyNameEn(String currencyNameEn) {
        this.currencyNameEn = currencyNameEn;
    }

    public String getCurrencyNameAr() {
        return currencyNameAr;
    }

    public void setCurrencyNameAr(String currencyNameAr) {
        this.currencyNameAr = currencyNameAr;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountriesDTO)) {
            return false;
        }

        return id != null && id.equals(((CountriesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountriesDTO{" +
            "id=" + getId() +
            ", countryNameEn='" + getCountryNameEn() + "'" +
            ", countryNameAr='" + getCountryNameAr() + "'" +
            ", flagImagePath='" + getFlagImagePath() + "'" +
            ", currencyNameEn='" + getCurrencyNameEn() + "'" +
            ", currencyNameAr='" + getCurrencyNameAr() + "'" +
            ", exchangeRate='" + getExchangeRate() + "'" +
            "}";
    }
}
