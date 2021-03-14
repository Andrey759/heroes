package com.heroes.utils;

import com.badlogic.gdx.graphics.Color;
import com.heroes.entity.CellEntity;
import com.heroes.enums.CellType;

public class BattleUtils {

    public static Color selectColorForCurr(CellEntity curr) {
        return selectColorByType(curr.cellTypeComponent.cellType);
    }

    public static Color selectColorForCurrAndOther(CellEntity curr, CellEntity other) {
        CellType type = selectTypeForCurrAndOther(curr, other);
        return selectColorByType(type);
    }

    private static Color selectColorByType(CellType type) {
        return type == CellType.MOUSEOVER ? Color.RED : type == CellType.HIGHLIGHTED ? Color.DARK_GRAY : Color.GRAY;
    }

    public static CellType selectTypeForCurrAndOther(CellEntity curr, CellEntity other) {
        if (other == null) {
            return curr.cellTypeComponent.cellType;
        }
        CellType currType = curr.cellTypeComponent.cellType;
        CellType otherType = other.cellTypeComponent.cellType;
        if (currType == CellType.MOUSEOVER || otherType == CellType.MOUSEOVER) {
            return CellType.MOUSEOVER;
        }
        if (currType == CellType.HIGHLIGHTED || otherType == CellType.HIGHLIGHTED) {
            return CellType.HIGHLIGHTED;
        }
        return CellType.NORMAL;
    }

}
