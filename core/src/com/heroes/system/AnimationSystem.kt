package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.heroes.component.AnimationComponent
import com.heroes.util.Common

class AnimationSystem : IteratingSystem(Family.all(AnimationComponent::class.java).get()) {

    override fun update(deltaTime: Float) {
        Common.prepareAnimationBatch()
        super.update(deltaTime)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animationComponent = AnimationComponent.MAPPER.get(entity)
        val skeleton = animationComponent.skeleton
        val state = animationComponent.state
        state.update(deltaTime)
        state.apply(skeleton)
        skeleton.updateWorldTransform()

        Common.skeletonRender.draw(Common.animationBatch, skeleton)
    }
}