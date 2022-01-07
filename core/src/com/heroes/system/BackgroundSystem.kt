package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.heroes.component.BackgroundComponent
import com.heroes.component.SizeComponent
import com.heroes.util.Common

class BackgroundSystem : IteratingSystem(Family.all(BackgroundComponent::class.java, SizeComponent::class.java).get()) {

    override fun update(deltaTime: Float) {
        Common.prepareBatch()
        super.update(deltaTime)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val texture = BackgroundComponent.MAPPER.get(entity).texture
        val size = SizeComponent.MAPPER.get(entity)

        Common.batch.draw(texture, 0F, 0F, size.width, size.height)
    }
}