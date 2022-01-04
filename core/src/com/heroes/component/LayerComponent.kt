package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper

class LayerComponent(val layerType: LayerType) : Component {
    companion object { val MAPPER = ComponentMapper.getFor(LayerComponent::class.java) }

    enum class LayerType(val value: Int) {
        BACKGROUND_COLOR(0),
        BACKGROUND_IMAGE(1),
        GRID(2),
        BACKGROUND_TEXTURE(3),
        ENTITY(4),
    }
}