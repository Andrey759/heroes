package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

class LineComponent(val x: Int, val y: Int, val side: String, val from: Vector2, val to: Vector2, var color: Color) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(LineComponent::class.java) }
}