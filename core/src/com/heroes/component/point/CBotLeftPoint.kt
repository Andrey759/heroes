package com.heroes.component.point

import com.badlogic.ashley.core.ComponentMapper

class CBotLeftPoint(x: Float, y: Float) : CAbstractPoint(x, y) {
    companion object { val MAPPER = ComponentMapper.getFor(CBotLeftPoint::class.java) }
}