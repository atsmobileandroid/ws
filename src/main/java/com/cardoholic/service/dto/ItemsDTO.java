package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.Items} entity.
 */
public class ItemsDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;

    private String imagePath;

    private Boolean isLeaf;


    private Long itemPricesId;

    private Long basketId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getItemPricesId() {
        return itemPricesId;
    }

    public void setItemPricesId(Long itemPricesId) {
        this.itemPricesId = itemPricesId;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemsDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemsDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", isLeaf='" + isIsLeaf() + "'" +
            ", itemPricesId=" + getItemPricesId() +
            ", basketId=" + getBasketId() +
            "}";
    }
}
