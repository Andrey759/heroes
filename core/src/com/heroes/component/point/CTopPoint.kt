package com.heroes.component.point

import com.badlogic.ashley.core.ComponentMapper

class CTopPoint(x: Float, y: Float) : CAbstractPoint(x, y) {
    companion object { val MAPPER = ComponentMapper.getFor(CTopPoint::class.java) }
}