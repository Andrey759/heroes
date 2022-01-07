package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.heroes.component.TextSupplierComponent2
import com.heroes.util.Common

class Text2System : IteratingSystem(Family.all(TextSupplierComponent2::class.java).get()) {

    override fun update(deltaTime: Float) {
        Common.prepareBatch()
        super.update(deltaTime)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val component = TextSupplierComponent2.MAPPER.get(entity)
        val font = component.font
        val text = component.getText()
        val x = component.x
        val y = component.y

        font.draw(Common.batch, text, x, y)
    }
}