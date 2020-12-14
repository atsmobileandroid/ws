package com.cardoholic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ItemCodes.
 */
@Entity
@Table(name = "item_codes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ItemCodes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JsonIgnoreProperties(value = "itemCodes", allowSetters = true)
    private Items items;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ItemCodes code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Items getItems() {
        return items;
    }

    public ItemCodes items(Items items) {
        this.items = items;
        return this;
    }

    public void setItems(Items items) {
        this.items = items;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemCodes)) {
            return false;
        }
        return id != null && id.equals(((ItemCodes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemCodes{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            "}";
    }
}