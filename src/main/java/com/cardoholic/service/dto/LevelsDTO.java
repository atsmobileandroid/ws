package com.cardoholic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cardoholic.domain.Levels} entity.
 */
public class LevelsDTO implements Serializable {
    
    private Long id;

    private String title;

    private String color;

    
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LevelsDTO)) {
            return false;
        }

        return id != null && id.equals(((LevelsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LevelsDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", color='" + getColor() + "'" +
            "}";
    }
}
