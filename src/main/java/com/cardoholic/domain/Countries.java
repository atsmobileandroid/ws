package com.cardoholic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Countries.
 */
@Entity
@Table(name = "countries")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name_en")
    private String countryNameEn;

    @Column(name = "country_name_ar")
    private String countryNameAr;

    @Column(name = "flag_image_path")
    private String flagImagePath;

    @Column(name = "currency_name_en")
    private String currencyNameEn;

    @Column(name = "currency_name_ar")
    private String currencyNameAr;

    @Column(name = "exchange_rate")
    private String exchangeRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public Countries countryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
        return this;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public Countries countryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
        return this;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getFlagImagePath() {
        return flagImagePath;
    }

    public Countries flagImagePath(String flagImagePath) {
        this.flagImagePath = flagImagePath;
        return this;
    }

    public void setFlagImagePath(String flagImagePath) {
        this.flagImagePath = flagImagePath;
    }

    public String getCurrencyNameEn() {
        return currencyNameEn;
    }

    public Countries currencyNameEn(String currencyNameEn) {
        this.currencyNameEn = currencyNameEn;
        return this;
    }

    public void setCurrencyNameEn(String currencyNameEn) {
        this.currencyNameEn = currencyNameEn;
    }

    public String getCurrencyNameAr() {
        return currencyNameAr;
    }

    public Countries currencyNameAr(String currencyNameAr) {
        this.currencyNameAr = currencyNameAr;
        return this;
    }

    public void setCurrencyNameAr(String currencyNameAr) {
        this.currencyNameAr = currencyNameAr;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public Countries exchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Countries)) {
            return false;
        }
        return id != null && id.equals(((Countries) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Countries{" +
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
