package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.graphics.Texture

class BackgroundComponent(val texture: Texture) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(BackgroundComponent::class.java) }
}