package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper

class RowColComponent(val row: Int, val col: Int) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(RowColComponent::class.java) }
}