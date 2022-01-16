package com.heroes.component.point

import com.badlogic.ashley.core.ComponentMapper

class CBotRightPoint(x: Float, y: Float) : CAbstractPoint(x, y) {
    companion object { val MAPPER = ComponentMapper.getFor(CBotRightPoint::class.java) }
}