package com.heroes.dto;

import java.util.*;

public class PointInt {
    private static final Map<Integer, Map<Integer, PointInt>> BUFFER = new HashMap<>();

    public final int x, y;

    private PointInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static PointInt get(int x, int y) {
        if (!BUFFER.containsKey(x)) {
            BUFFER.put(x, new HashMap<Integer, PointInt>());
        }
        if (!BUFFER.get(x).containsKey(y)) {
            BUFFER.get(x).put(y, new PointInt(x, y));
        }
        return BUFFER.get(x).get(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointInt pointInt = (PointInt) o;

        if (x != pointInt.x) return false;
        return y == pointInt.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
