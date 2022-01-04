package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.esotericsoftware.spine.AnimationState
import com.esotericsoftware.spine.Skeleton
import com.heroes.util.AnimationType

class AnimationComponent(var skeleton: Skeleton, var state: AnimationState): Component {
    companion object { val MAPPER = ComponentMapper.getFor(AnimationComponent::class.java) }

    fun play(animationType: AnimationType) {
        state.addAnimation(0, animationType.formal, false, 0F)
        state.addAnimation(0, AnimationType.INACTION_1.formal, true, 0F)
    }

    fun playAndStop(animationType: AnimationType) {
        state.addAnimation(0, animationType.formal, false, 0F)
    }
}