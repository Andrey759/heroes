package com.heroes.component;

import com.heroes.enums.CellType;

public class CellTypeComponent {
    public CellType cellType;

    public CellTypeComponent(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellTypeComponent that = (CellTypeComponent) o;

        return cellType == that.cellType;
    }

    @Override
    public int hashCode() {
        return cellType != null ? cellType.hashCode() : 0;
    }
}
