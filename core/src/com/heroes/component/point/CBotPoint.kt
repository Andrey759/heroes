package com.heroes.component.point

import com.badlogic.ashley.core.ComponentMapper

class CBotPoint(x: Float, y: Float) : CAbstractPoint(x, y) {
    companion object { val MAPPER = ComponentMapper.getFor(CBotPoint::class.java) }
}