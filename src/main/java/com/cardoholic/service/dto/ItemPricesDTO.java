package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.ItemPrices} entity.
 */
public class ItemPricesDTO implements Serializable {
    
    private Long id;

    private String price;


    private Long levelsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getLevelsId() {
        return levelsId;
    }

    public void setLevelsId(Long levelsId) {
        this.levelsId = levelsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemPricesDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemPricesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemPricesDTO{" +
            "id=" + getId() +
            ", price='" + getPrice() + "'" +
            ", levelsId=" + getLevelsId() +
            "}";
    }
}
