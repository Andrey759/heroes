package com.heroes.component.point

import com.badlogic.ashley.core.ComponentMapper

class CCenterPoint(x: Float, y: Float) : CAbstractPoint(x, y) {
    companion object { val MAPPER = ComponentMapper.getFor(CCenterPoint::class.java) }
}