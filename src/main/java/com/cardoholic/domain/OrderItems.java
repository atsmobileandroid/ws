package com.cardoholic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A OrderItems.
 */
@Entity
@Table(name = "order_items")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_title")
    private String itemTitle;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_image_path")
    private String itemImagePath;

    @Column(name = "item_price")
    private String itemPrice;

    @Column(name = "item_count")
    private Integer itemCount;

    @Column(name = "item_codes")
    private String itemCodes;

    @Column(name = "to_id")
    private String toId;

    @Column(name = "is_sent_to_id")
    private Boolean isSentToId;

    @ManyToOne
    @JsonIgnoreProperties(value = "orderItems", allowSetters = true)
    private Orders orders;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public OrderItems itemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        return this;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public OrderItems itemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImagePath() {
        return itemImagePath;
    }

    public OrderItems itemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
        return this;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public OrderItems itemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public OrderItems itemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemCodes() {
        return itemCodes;
    }

    public OrderItems itemCodes(String itemCodes) {
        this.itemCodes = itemCodes;
        return this;
    }

    public void setItemCodes(String itemCodes) {
        this.itemCodes = itemCodes;
    }

    public String getToId() {
        return toId;
    }

    public OrderItems toId(String toId) {
        this.toId = toId;
        return this;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Boolean isIsSentToId() {
        return isSentToId;
    }

    public OrderItems isSentToId(Boolean isSentToId) {
        this.isSentToId = isSentToId;
        return this;
    }

    public void setIsSentToId(Boolean isSentToId) {
        this.isSentToId = isSentToId;
    }

    public Orders getOrders() {
        return orders;
    }

    public OrderItems orders(Orders orders) {
        this.orders = orders;
        return this;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItems)) {
            return false;
        }
        return id != null && id.equals(((OrderItems) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderItems{" +
            "id=" + getId() +
            ", itemTitle='" + getItemTitle() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemImagePath='" + getItemImagePath() + "'" +
            ", itemPrice='" + getItemPrice() + "'" +
            ", itemCount=" + getItemCount() +
            ", itemCodes='" + getItemCodes() + "'" +
            ", toId='" + getToId() + "'" +
            ", isSentToId='" + isIsSentToId() + "'" +
            "}";
    }
}
