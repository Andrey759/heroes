package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.heroes.component.LineComponent
import com.heroes.util.ColorType
import com.heroes.util.Common

class LineSystem : IteratingSystem(Family.all(LineComponent::class.java).get()) {

    override fun update(deltaTime: Float) {
        Common.prepareLineRender()
        Common.blendOn()
        super.update(deltaTime)
        Common.blendOff()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val line = LineComponent.MAPPER.get(entity)

        Common.lineRender.rectLine(
            Math.round(line.from.x).toFloat(),
            Math.round(line.from.y).toFloat(),
            Math.round(line.to.x).toFloat(),
            Math.round(line.to.y).toFloat(),
            0F,
            ColorType.CELL_WHITE.color,
            ColorType.CELL_WHITE.color
        )
    }
}