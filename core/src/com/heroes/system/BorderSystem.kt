package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.heroes.component.link.*
import com.heroes.component.point.*
import com.heroes.util.ColorType
import com.heroes.util.Common

class BorderSystem : IteratingSystem(Family.all(
    CBotLeftPoint::class.java,
    CBotRightPoint::class.java,
    CTopLeftPoint::class.java,
    CTopRightPoint::class.java
).get()) {

    override fun update(deltaTime: Float) {
        Common.prepareLineRender()
        Common.blendOn()
        super.update(deltaTime)
        Common.blendOff()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val botLeft = CBotLeftPoint.MAPPER.get(entity)
        val bot = CBotPoint.MAPPER.get(entity)
        val botRight = CBotRightPoint.MAPPER.get(entity)
        val topLeft = CTopLeftPoint.MAPPER.get(entity)
        val top = CTopPoint.MAPPER.get(entity)
        val topRight = CTopRightPoint.MAPPER.get(entity)

        rectLine(topLeft, botLeft)
        rectLine(topLeft, top)
        rectLine(top, topRight)
        if (!CBotLeftLink.MAPPER.has(entity)) {
            rectLine(botLeft, bot)
        }
        if (!CBotRightLink.MAPPER.has(entity)) {
            rectLine(bot, botRight)
        }
        if (!CRightLink.MAPPER.has(entity)) {
            rectLine(topRight, botRight)
        }
    }

    private fun rectLine(from: CAbstractPoint, to: CAbstractPoint) {
        Common.lineRender.rectLine(
            from.x,
            from.y,
            to.x,
            to.y,
            0F,
            ColorType.CELL_WHITE.color,
            ColorType.CELL_WHITE.color
        )
    }
}