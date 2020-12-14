package com.cardoholic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Items.
 */
@Entity
@Table(name = "items")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @OneToOne
    @JoinColumn(unique = true)
    private ItemPrices itemPrices;

    @OneToMany(mappedBy = "items")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ItemCodes> itemCodes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private Basket basket;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Items title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Items description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Items imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public Items isLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public ItemPrices getItemPrices() {
        return itemPrices;
    }

    public Items itemPrices(ItemPrices itemPrices) {
        this.itemPrices = itemPrices;
        return this;
    }

    public void setItemPrices(ItemPrices itemPrices) {
        this.itemPrices = itemPrices;
    }

    public Set<ItemCodes> getItemCodes() {
        return itemCodes;
    }

    public Items itemCodes(Set<ItemCodes> itemCodes) {
        this.itemCodes = itemCodes;
        return this;
    }

    public Items addItemCodes(ItemCodes itemCodes) {
        this.itemCodes.add(itemCodes);
        itemCodes.setItems(this);
        return this;
    }

    public Items removeItemCodes(ItemCodes itemCodes) {
        this.itemCodes.remove(itemCodes);
        itemCodes.setItems(null);
        return this;
    }

    public void setItemCodes(Set<ItemCodes> itemCodes) {
        this.itemCodes = itemCodes;
    }

    public Basket getBasket() {
        return basket;
    }

    public Items basket(Basket basket) {
        this.basket = basket;
        return this;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Items)) {
            return false;
        }
        return id != null && id.equals(((Items) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Items{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", isLeaf='" + isIsLeaf() + "'" +
            "}";
    }
}
