package com.heroes.entity;

import com.heroes.component.CellTypeComponent;
import com.heroes.dto.PointFloat;
import com.heroes.enums.CellType;

public class CellEntity {
    public final PointFloat leftBottom, rightBottom, leftTop, rightTop;
    public CellTypeComponent cellTypeComponent;

    public CellEntity(float x1Bottom, float x2Bottom, float x1Top, float x2Top, float yBottom, float yTop) {
        this.leftBottom = PointFloat.get(x1Bottom, yBottom);
        this.rightBottom = PointFloat.get(x2Bottom, yBottom);
        this.leftTop = PointFloat.get(x1Top, yTop);
        this.rightTop = PointFloat.get(x2Top, yTop);
        this.cellTypeComponent = new CellTypeComponent(CellType.NORMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellEntity that = (CellEntity) o;

        if (leftBottom != null ? !leftBottom.equals(that.leftBottom) : that.leftBottom != null) return false;
        if (rightBottom != null ? !rightBottom.equals(that.rightBottom) : that.rightBottom != null) return false;
        if (leftTop != null ? !leftTop.equals(that.leftTop) : that.leftTop != null) return false;
        if (rightTop != null ? !rightTop.equals(that.rightTop) : that.rightTop != null) return false;
        return cellTypeComponent != null ? cellTypeComponent.equals(that.cellTypeComponent) : that.cellTypeComponent == null;
    }

    @Override
    public int hashCode() {
        int result = leftBottom != null ? leftBottom.hashCode() : 0;
        result = 31 * result + (rightBottom != null ? rightBottom.hashCode() : 0);
        result = 31 * result + (leftTop != null ? leftTop.hashCode() : 0);
        result = 31 * result + (rightTop != null ? rightTop.hashCode() : 0);
        result = 31 * result + (cellTypeComponent != null ? cellTypeComponent.hashCode() : 0);
        return result;
    }
}
