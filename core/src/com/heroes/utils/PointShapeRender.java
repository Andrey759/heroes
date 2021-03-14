package com.heroes.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.heroes.dto.PointFloat;

public class PointShapeRender extends ShapeRenderer {

    public void line(PointFloat point1, PointFloat point2, Color color) {
        super.line(point1.x, point1.y, point2.x, point2.y, color, color);
    }
}
