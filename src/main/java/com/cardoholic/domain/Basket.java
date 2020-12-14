package com.cardoholic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Basket.
 */
@Entity
@Table(name = "basket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private Integer count;

    @Column(name = "to_id")
    private String toId;

    @Column(name = "added_date")
    private String addedDate;

    @OneToMany(mappedBy = "basket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Items> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public Basket count(Integer count) {
        this.count = count;
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getToId() {
        return toId;
    }

    public Basket toId(String toId) {
        this.toId = toId;
        return this;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public Basket addedDate(String addedDate) {
        this.addedDate = addedDate;
        return this;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public Set<Items> getItems() {
        return items;
    }

    public Basket items(Set<Items> items) {
        this.items = items;
        return this;
    }

    public Basket addItems(Items items) {
        this.items.add(items);
        items.setBasket(this);
        return this;
    }

    public Basket removeItems(Items items) {
        this.items.remove(items);
        items.setBasket(null);
        return this;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Basket)) {
            return false;
        }
        return id != null && id.equals(((Basket) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Basket{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", toId='" + getToId() + "'" +
            ", addedDate='" + getAddedDate() + "'" +
            "}";
    }
}
