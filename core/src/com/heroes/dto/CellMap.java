package com.heroes.dto;

import com.heroes.entity.CellEntity;

import java.util.HashMap;

public class CellMap extends HashMap<PointInt, CellEntity> {

    public CellEntity get(int row, int col) {
        return super.get(PointInt.get(row, col));
    }

    public CellEntity put(int row, int col, CellEntity value) {
        return super.put(PointInt.get(row, col), value);
    }
}
