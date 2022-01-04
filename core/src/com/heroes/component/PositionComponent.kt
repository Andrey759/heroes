package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper

class PositionComponent(var x: Float, var y: Float) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(PositionComponent::class.java) }
}