package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.OrderItems} entity.
 */
public class OrderItemsDTO implements Serializable {
    
    private Long id;

    private String itemTitle;

    private String itemDescription;

    private String itemImagePath;

    private String itemPrice;

    private Integer itemCount;

    private String itemCodes;

    private String toId;

    private Boolean isSentToId;


    private Long ordersId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(String itemCodes) {
        this.itemCodes = itemCodes;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Boolean isIsSentToId() {
        return isSentToId;
    }

    public void setIsSentToId(Boolean isSentToId) {
        this.isSentToId = isSentToId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItemsDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderItemsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderItemsDTO{" +
            "id=" + getId() +
            ", itemTitle='" + getItemTitle() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemImagePath='" + getItemImagePath() + "'" +
            ", itemPrice='" + getItemPrice() + "'" +
            ", itemCount=" + getItemCount() +
            ", itemCodes='" + getItemCodes() + "'" +
            ", toId='" + getToId() + "'" +
            ", isSentToId='" + isIsSentToId() + "'" +
            ", ordersId=" + getOrdersId() +
            "}";
    }
}
