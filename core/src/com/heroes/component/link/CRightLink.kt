package com.heroes.component.link

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity

class CRightLink(entity: Entity) : CAbstractLink(entity) {
    companion object { val MAPPER = ComponentMapper.getFor(CRightLink::class.java) }
}