package com.cardoholic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ItemPrices.
 */
@Entity
@Table(name = "item_prices")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ItemPrices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private String price;

    @OneToOne
    @JoinColumn(unique = true)
    private Levels levels;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public ItemPrices price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Levels getLevels() {
        return levels;
    }

    public ItemPrices levels(Levels levels) {
        this.levels = levels;
        return this;
    }

    public void setLevels(Levels levels) {
        this.levels = levels;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemPrices)) {
            return false;
        }
        return id != null && id.equals(((ItemPrices) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemPrices{" +
            "id=" + getId() +
            ", price='" + getPrice() + "'" +
            "}";
    }
}
