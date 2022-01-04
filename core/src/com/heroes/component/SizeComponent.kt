package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.graphics.Texture

class SizeComponent(var width: Float, var height: Float) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(SizeComponent::class.java) }
}