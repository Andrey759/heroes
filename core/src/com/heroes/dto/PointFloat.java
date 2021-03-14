package com.heroes.dto;

import java.util.HashMap;
import java.util.Map;

public class PointFloat {
    private static final Map<Float, Map<Float, PointFloat>> BUFFER = new HashMap<>();

    public final float x, y;

    private PointFloat(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static PointFloat get(float x, float y) {
        if (!BUFFER.containsKey(x)) {
            BUFFER.put(x, new HashMap<Float, PointFloat>());
        }
        if (!BUFFER.get(x).containsKey(y)) {
            BUFFER.get(x).put(y, new PointFloat(x, y));
        }
        return BUFFER.get(x).get(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointFloat that = (PointFloat) o;

        if (Float.compare(that.x, x) != 0) return false;
        return Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }
}
