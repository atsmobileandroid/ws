package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.ItemCodes} entity.
 */
public class ItemCodesDTO implements Serializable {
    
    private Long id;

    private String code;


    private Long itemsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemCodesDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemCodesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemCodesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", itemsId=" + getItemsId() +
            "}";
    }
}
