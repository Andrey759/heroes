package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.math.Vector2
import com.heroes.dto.UnitDto

class CellComponent(
    val x: Int,
    val y: Int,
    val center: Vector2,
    val left: LineComponent,
    val top: LineComponent
) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(CellComponent::class.java) }

    lateinit var right: LineComponent
    lateinit var bottom: LineComponent
    var unit: UnitDto? = null
}